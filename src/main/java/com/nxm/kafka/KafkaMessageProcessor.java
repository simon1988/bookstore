package com.nxm.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProcessor {
	
	private static Logger logger = LoggerFactory.getLogger("kafka");
	
	@KafkaMessageProcess(KafkaMessageType.IN_MONEY)
	public void notifyQuanziInMoney(String msg){
		logger.debug("[notifyQuanziInMoney] deal with kafka msg:"+msg);
	}
	
	public static void main(String args[]){
		String msg = "{\"bankCardNo\":\"666614709632580745\",\"createTime\":1440487606000,\"currencyType\":\"00\",\"firmId\":\"16380494\",\"localOrderId\":\"150817001016\",\"notifyStatus\":0,\"orderId\":\"2015081716JY06306434\",\"orderMoney\":\"632.00\",\"orderMoneyCent\":63200,\"orderType\":\"deposit\",\"orderTypeDisplay\":\"1\",\"partnerId\":\"njs\",\"realTradeMoney\":\"632.00\",\"realTradeMoneyCent\":63200,\"realTradeTime\":1439799998000,\"reconStatus\":0,\"status\":2,\"updateTime\":1439823600000,\"userName\":\"sophiatest5@126.com\"}";
		new KafkaMessageProcessor().notifyQuanziInMoney(msg);
	}
}
