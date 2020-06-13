package com.recipe.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatchUtils {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CatchUtils() {}
	
	public CatchUtils(Exception e) {
		for(final String element : ExceptionUtils.getRootCauseStackTrace(e)) {
			logger.error(element);
		}
	}
	
}
