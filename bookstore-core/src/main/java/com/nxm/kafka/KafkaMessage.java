package com.nxm.kafka;

public class KafkaMessage {
	private String messageId;
	private KafkaMessageType messageType;
	private String messageTime;
	private String message;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public KafkaMessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(KafkaMessageType messageType) {
		this.messageType = messageType;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
