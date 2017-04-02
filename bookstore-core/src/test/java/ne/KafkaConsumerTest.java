package ne;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class KafkaConsumerTest {

	private void startConsumer(String zks, String topic) {
		Properties props = new Properties();
		props.put("zookeeper.connect", zks);
		props.put("group.id", "gjs_nxm");
		props.put("zookeeper.session.timeout.ms", "30000");
		props.put("rebalance.backoff.ms", "30000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "smallest");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		ConsumerConfig config = new ConsumerConfig(props);
		ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, 1);
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		KafkaStream<String, String> stream = consumerMap.get(topic).get(0);
		ConsumerIterator<String, String> it = stream.iterator();
		while (it.hasNext()){
			System.out.println(it.next().message());
		}
	}
	
	@Test
	public void consumeQZ(){
		String TOPIC = "gjs_q";
		String  SERVER = "223.252.197.55:2181,223.252.197.56:2181,223.252.197.57:2181";
		startConsumer(SERVER, TOPIC);
	}
	@Test
	public void consumeZZ(){
		String TOPIC = "gjs_trade";
		String SERVER = "223.252.197.84:2182";
		startConsumer(SERVER, TOPIC);
	}
	@Test
	public void consumeNXM(){
		String TOPIC = "nxm";
		String SERVER = "127.0.0.1:2181";
		startConsumer(SERVER, TOPIC);
	}

}
