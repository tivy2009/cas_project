package com.huawei.pcloud.controller;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huawei.pcloud.service.IActivemqService;

@RestController
@RequestMapping("/api/mq")
public class ActivemqController {
	protected final Logger logger = LogManager.getLogger();

	@Autowired
	private IActivemqService activemqService;
	
    @GetMapping()
    public String Get() {
    	logger.info("send mq message...");
        String message = "test message!";
        
        activemqService.sendMessage(message);
        
        return null;
    }
    
    @GetMapping(value = "/receive")
	public String receive() throws JMSException {
		TextMessage textMessage = activemqService.receive();
		if(textMessage !=null)
		{
			logger.info("textMessage：" + textMessage.getText());
			return textMessage.getText();
		}else{
			logger.info("textMessage is blank;");
			return null;
		}
	}
    
}