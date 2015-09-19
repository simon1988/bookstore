package com.nxm.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	@Value("#{kafkaProps['kafka.metadata.broker.list']}")
	private String KAFKA_BROKERS;
	@Value("#{kafkaProps['kafka.topic']}")
	private String KAFKA_TOPIC;
	
	private static Logger logger = LoggerFactory.getLogger("kafka");
	
	private Producer<String, String> producer = null;
	
	@PostConstruct
	private void init(){
		Properties props = new Properties();
		props.put("metadata.broker.list", KAFKA_BROKERS);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("producer.type", "async");
		props.put("request.required.acks", "0");
		props.put("queue.enqueue.timeout.ms", "0");
		
		producer = new Producer<String, String>(new ProducerConfig(props));
	}
	public boolean sendMessage(KafkaMessageType messageType, Object obj){
		if(obj==null){
			return false;
		}
		if(obj instanceof String){
			return sendMessage(messageType, obj.toString());
		}
		return sendMessage(messageType, KafkaUtils.objectToJson(obj));
	}
	public boolean sendMessage(KafkaMessageType messageType, String message){
		if(messageType==null || StringUtils.isBlank(message)){
			return false;
		}
		KafkaMessage kafkaMessage = new KafkaMessage();
		kafkaMessage.setMessageId(KafkaUtils.getUUID());
		kafkaMessage.setMessageTime(KafkaUtils.getCurrentTime());
		kafkaMessage.setMessageType(messageType);
		kafkaMessage.setMessage(message);
		
		String kafkaMessageJson = KafkaUtils.objectToJson(kafkaMessage);
		if(StringUtils.isNotBlank(kafkaMessageJson)){
			logger.info("[KafkaProducer] sending msg: ----->{}<-----", kafkaMessageJson);
			producer.send(new KeyedMessage<String, String>(KAFKA_TOPIC, kafkaMessageJson));
			return true;
		}else{
			logger.warn("[KafkaProducer] message {} converted to blank json!", message);
			return false;
		}
	}
}
