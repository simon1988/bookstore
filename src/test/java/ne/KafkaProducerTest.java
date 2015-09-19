package ne;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nxm.kafka.KafkaMessage;
import com.nxm.kafka.KafkaMessageType;
import com.nxm.kafka.KafkaUtils;

@RunWith(JUnit4.class)
public class KafkaProducerTest {

	private Producer<String, String> getProducer(String brokers) {
		Properties props = new Properties();
		props.put("metadata.broker.list", brokers);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("producer.type", "async");
		props.put("request.required.acks", "0");
		props.put("queue.enqueue.timeout.ms", "0");

		return new Producer<String, String>(new ProducerConfig(props));
	}
	
	private String getKafkaMessage(String rawmsg){
		KafkaMessage kmsg = new KafkaMessage();
		kmsg.setMessage(rawmsg);
		kmsg.setMessageId("testid");
		kmsg.setMessageTime("20150919");
		kmsg.setMessageType(KafkaMessageType.IN_MONEY);
		String msg = KafkaUtils.objectToJson(kmsg);
		System.out.println(msg);
		return msg;
	}
	
	@Test
	public void sendMessageQZ(){
		String TOPIC = "gjs_q";
		Producer<String, String> producer = getProducer("kafka.dianshang.163.com:9097");
		String msg = "{{'messageId': '222222','messageTime': 1437449018595,'messageIp':'127.0.0.1','messageType': 1,'topic':{'topicId': '390830','userEmail': 'test2@163.com','boardName':'鐢ㄦ埛鍙嶉','topicUrl': 'http://qz.fa.163.com/comment/postcomment?topicId=390832','content': '娴嬭瘯','userNickName': 'xixi','userHeadUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/200-200_10140_1422425187745_1707.jpg','createTime': '2015-06-23 17:40:52','imageList': [{'thumbnailUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/612-816_10140_1435052451351_5127.jpg?imageView=306y408','thumbnailWidth': '306','thumbnailHeight': '408','originalUrl': '','originalWidth': '612','originalHeight': '816'},{'thumbnailUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/360-640_10140_1435052451843_354.jpg?imageView=180y320','thumbnailWidth': '180','thumbnailHeight': '320','originalUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/360-640_10140_1435052451843_354.jpg','originalWidth': '360','originalHeight': '640'}]}}}";
		System.out.println(msg);
		producer.send(new KeyedMessage<String, String>(TOPIC, msg));
		producer.close();
	}
	
	@Test
	public void sendMessageZZ(){
		String TOPIC = "gjs_trade";
		Producer<String, String> producer = getProducer("kafka.dianshang.163.com:9093,kafka.dianshang.163.com:9094,kafka.dianshang.163.com:9095");
		String rawmsg = "{\"bankCardNo\":\"666614709632580743\",\"createTime\":1440487606000,\"currencyType\":\"00\",\"firmId\":\"16380494\",\"localOrderId\":\"150817001016\",\"notifyStatus\":0,\"orderId\":\"2015081716JY06306434\",\"orderMoney\":\"632.00\",\"orderMoneyCent\":63200,\"orderType\":\"deposit\",\"orderTypeDisplay\":\"1\",\"partnerId\":\"njs\",\"realTradeMoney\":\"632.00\",\"realTradeMoneyCent\":63200,\"realTradeTime\":1439799998000,\"reconStatus\":0,\"status\":2,\"updateTime\":1439823600000,\"userName\":\"fsx379@163.com\"}";
		producer.send(new KeyedMessage<String, String>(TOPIC, getKafkaMessage(rawmsg)));
		producer.close();
	}
}
