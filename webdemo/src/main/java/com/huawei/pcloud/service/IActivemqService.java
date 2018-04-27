package com.huawei.pcloud.service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public interface IActivemqService {

	void sendMessage(String message);
	
	TextMessage receive() throws JMSException;
	
}
