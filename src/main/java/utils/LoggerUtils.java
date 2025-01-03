package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
	private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);
	
	public static void logInfo(String info) {
		logger.info(info);
	}
	
	public static void logDebug(String details) {
		logger.debug(details);
	}
	
	public static void logError(String error) {
		logger.error(error);
	}
}