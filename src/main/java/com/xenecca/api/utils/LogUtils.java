package com.xenecca.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

	private static final Logger logger = LogManager.getLogger(LogUtils.class);

	public static void log(String message) {
		logger.info(message);
	}

}
