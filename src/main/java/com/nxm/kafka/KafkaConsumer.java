package com.nxm.kafka;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	@Value("#{kafkaProps['kafka.zookeeper.connect']}")
	private String KAFKA_ZOOKEEPER_SERVERS;
	@Value("#{kafkaProps['kafka.topic']}")
	private String KAFKA_TOPIC;
	@Value("#{kafkaProps['kafka.group.id']}")
	private String KAFKA_GROUP_ID;
	
	@Autowired
	KafkaMessageProcessor kafkaMessageProcessor;
	
	private static Logger logger = LoggerFactory.getLogger("kafka");
	
	private static final ExecutorService consumerMainThreadPool = Executors.newSingleThreadExecutor();
	private static final ExecutorService consumerWorkerThreadPool = Executors.newFixedThreadPool(10);
	
	@PostConstruct
	private void init(){
		consumerMainThreadPool.execute(new Runnable(){
			@Override
			public void run() {
				
				Properties props = new Properties();
				props.put("zookeeper.connect", KAFKA_ZOOKEEPER_SERVERS);
				props.put("group.id", KAFKA_GROUP_ID);
				props.put("zookeeper.session.timeout.ms", "4000");
				props.put("zookeeper.sync.time.ms", "200");
				props.put("auto.commit.interval.ms", "1000");
				props.put("auto.offset.reset", "smallest");
				props.put("serializer.class", "kafka.serializer.StringEncoder");
				
				ConsumerConfig config = new ConsumerConfig(props);
				ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
				
				Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
				topicCountMap.put(KAFKA_TOPIC, 1);

				StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
				StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

				Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
				KafkaStream<String, String> stream = consumerMap.get(KAFKA_TOPIC).get(0);
				ConsumerIterator<String, String> it = stream.iterator();
				
				Map<KafkaMessageType, List<Method>> methodMap = initMethodMap();
				
				logger.info("[KafkaConsumer] init complete methods:{}", methodMap);
				
				while (it.hasNext()){
					String message = it.next().message();
					logger.info("[KafkaConsumer] recieve raw message----->{}<-----", message);
					
					if(StringUtils.isBlank(message)){
						continue;
					}
					
					KafkaMessage kafkaMessage = KafkaUtils.jsonToObject(message, KafkaMessage.class);
					if(kafkaMessage==null){
						logger.info("[KafkaConsumer] not a kafka format message:{}, ignored.", message);
						continue;
					}
					if(StringUtils.isBlank(kafkaMessage.getMessage())){
						logger.info("[KafkaConsumer] inner message for kafka message {} is blank, discarded.", kafkaMessage.getMessageId());
						continue;
					}
					
					for(Method method : methodMap.get(kafkaMessage.getMessageType())){
						consumerWorkerThreadPool.submit(new WorkerThread(method, kafkaMessage));
					}
					
				}
			}
			
			private Map<KafkaMessageType, List<Method>> initMethodMap(){
				Map<KafkaMessageType, List<Method>> methodMap = new HashMap<>();
				for(KafkaMessageType messageType : KafkaMessageType.values()){
					methodMap.put(messageType, new ArrayList<Method>());
				}
				try{
					Method[] methods = KafkaMessageProcessor.class.getDeclaredMethods();
					for(Method method : methods){
						KafkaMessageProcess message = method.getAnnotation(KafkaMessageProcess.class);
						if(message!=null && message.value().length>0){
							Class<?>[] ptypes = method.getParameterTypes();
							if(ptypes==null||ptypes.length!=1||ptypes[0]!=String.class){
								logger.warn("[KafkaConsumer] method {} ignored while init method map!", method.getName());
							}
							for(KafkaMessageType messageType : message.value()){
								methodMap.get(messageType).add(method);
							}
						}
					}
				}catch(Exception e){
					logger.error("[KafkaConsumer] failed to init method map!", e);
				}
				return methodMap;
			}
		});
		consumerMainThreadPool.shutdown();
	}
	private class WorkerThread implements Runnable{
		private Method method;
		private KafkaMessage kafkaMessage;
		public WorkerThread(Method method, KafkaMessage kafkaMessage){
			this.method = method;
			this.kafkaMessage = kafkaMessage;
		}
		@Override
		public void run() {
			try{
				method.invoke(kafkaMessageProcessor, kafkaMessage.getMessage());
			}catch(Exception e){
				logger.error("[KafkaConsumer] failed to invoke method {} for kafka message {}", method.getName(), kafkaMessage.getMessageId(), e);
			}
		}
		
	}
}
