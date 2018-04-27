package com.huawei.pcloud.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueueMessageListener implements MessageListener {

	private static final Logger logger = LogManager.getLogger();
	
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
		try {
			logger.info("receive one message:" + tm.getText() + " from Destination.");
		} catch (JMSException e) {
			throw new RuntimeException();
		}
    }
}