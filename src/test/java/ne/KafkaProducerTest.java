package ne;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nxm.kafka.KafkaMessage;
import com.nxm.kafka.KafkaMessageType;
import com.nxm.kafka.KafkaUtils;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

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
	
	private String getKafkaMessage(String rawmsg, KafkaMessageType type){
		KafkaMessage kmsg = new KafkaMessage();
		kmsg.setMessage(rawmsg);
		kmsg.setMessageId(KafkaUtils.getUUID());
		kmsg.setMessageTime(KafkaUtils.getCurrentTime());
		kmsg.setMessageType(type);
		String msg = KafkaUtils.objectToJson(kmsg);
		System.out.println(msg);
		return msg;
	}
	
	@Test
	public void sendMessageQZ(){
		String TOPIC = "gjs_q";
		Producer<String, String> producer = getProducer("kafka.dianshang.163.com:9097");
		String msg = "{{'messageId': '222222','messageTime': 1437449018595,'messageIp':'127.0.0.1','messageType': 1,'topic':{'topicId': '390830','userEmail': 'test2@163.com','boardName':'用户反馈','topicUrl': 'http://qz.fa.163.com/comment/postcomment?topicId=390832','content': '泰斯特','userNickName': 'xixi','userHeadUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/200-200_10140_1422425187745_1707.jpg','createTime': '2015-06-23 17:40:52','imageList': [{'thumbnailUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/612-816_10140_1435052451351_5127.jpg?imageView=306y408','thumbnailWidth': '306','thumbnailHeight': '408','originalUrl': '','originalWidth': '612','originalHeight': '816'},{'thumbnailUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/360-640_10140_1435052451843_354.jpg?imageView=180y320','thumbnailWidth': '180','thumbnailHeight': '320','originalUrl': 'http://fa-quanzi-test.nos.netease.com/quanzi/360-640_10140_1435052451843_354.jpg','originalWidth': '360','originalHeight': '640'}]}}}";
		System.out.println(msg);
		producer.send(new KeyedMessage<String, String>(TOPIC, msg));
		producer.close();
	}
	
	@Test
	public void sendMessageZZOpenacc(){
		String TOPIC = "gjs_trade";
		Producer<String, String> producer = getProducer("kafka.dianshang.163.com:9093,kafka.dianshang.163.com:9094,kafka.dianshang.163.com:9095");
		String rawmsg = "{\"id\":7556371,\"userId\":\"7555844\",\"realName\":\"史乐珍\",\"email\":\"testszq80@163.com\",\"phone\":\"18064117989\",\"name\":\"史乐珍\",\"certno\":\"360201199011214902\",\"certType\":\"A\",\"bankid\":\"6\",\"bankName\":\"招商银行\",\"accno\":\"666652132654899652\",\"wxid\":null,\"firmid\":\"1080012822\",\"type\":3,\"createTime\":0,\"sign\":false,\"insertTime\":null,\"updateTime\":null,\"partnerId\":\"sge\",\"username\":\"testszq80@163.com\",\"status\":2,\"source\":\"app_android_tgwwandoujia\",\"password\":\"\",\"passwordFund\":\"fb6952ad2996f9de4d3f6e28a6db0474\",\"vipFlag\":0,\"epayFlag\":1,\"errorCode\":200,\"errorMsg\":\"上金所开户后T+2才能交易，现在转账10月14日20:00以后即可进行交易啦！\",\"bankCityId\":0,\"answer\":0,\"userIp\":\"61.135.255.86\"}";
		producer.send(new KeyedMessage<String, String>(TOPIC, getKafkaMessage(rawmsg, KafkaMessageType.OPEN_ACCOUNT)));
		producer.close();
	}
	
	@Test
	public void sendMessageZZInmoney(){
		String TOPIC = "gjs_trade";
		Producer<String, String> producer = getProducer("kafka.dianshang.163.com:9093,kafka.dianshang.163.com:9094,kafka.dianshang.163.com:9095");
		String rawmsg = "{\"bankCardNo\":\"6228231455139166761\",\"createTime\":1444707755000,\"currencyType\":\"00\",\"firmId\":\"18896002\",\"localOrderId\":\"151013020918\",\"notifyStatus\":0,\"orderId\":\"2015101311JY89674030\",\"orderMoney\":\"2100.00\",\"orderMoneyCent\":210000,\"orderType\":\"deposit\",\"orderTypeDisplay\":\"1\",\"partnerId\":\"njs\",\"realTradeMoney\":\"2100.00\",\"realTradeMoneyCent\":210000,\"realTradeTime\":1444707758000,\"reconStatus\":0,\"status\":2,\"updateTime\":1444707758000,\"userName\":\"m13760979551@163.com\"}";
		producer.send(new KeyedMessage<String, String>(TOPIC, getKafkaMessage(rawmsg, KafkaMessageType.IN_MONEY)));
		producer.close();
	}
	
	@Test
	public void sendMessageNXMtest(){
		String TOPIC = "nxm";
		Producer<String, String> producer = getProducer("127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
		String rawmsg = "NXM is the best forEver!";
		producer.send(new KeyedMessage<String, String>(TOPIC, getKafkaMessage(rawmsg, KafkaMessageType.IN_MONEY)));
		producer.close();
	}
}
