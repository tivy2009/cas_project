package com.huawei.pcloud.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.huawei.pcloud.service.IActivemqService;

@Service
public class ActivemqService implements IActivemqService{

	protected final Logger logger = LogManager.getLogger();
	
	//@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
	
	//@Resource(name = "demoQueueDestination")
	private Destination demoQueueDestination;
	
	@Override
	public void sendMessage(String message) {
		logger.info("Send {} to Destination {}",message,demoQueueDestination);
		MessageCreator messageCreator = new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
				}
			};
			jmsQueueTemplate.send(demoQueueDestination, messageCreator);
	}

	@Override
	public TextMessage receive() throws JMSException {
		TextMessage tm = (TextMessage) jmsQueueTemplate.receive(demoQueueDestination);
		if (tm != null) {
			logger.info("receive one message:" + tm.getText() + " from Destination " + demoQueueDestination.toString());
		}
		return tm;
	}
}
