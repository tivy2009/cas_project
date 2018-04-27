package com.huawei.pcloud.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ErrorHandler;

public class JmsErrorHandler implements ErrorHandler{

	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void handleError(Throwable e) {
		// TODO Auto-generated method stub
		logger.error("JmsErrorHandler handleError!",e);
	}

}
