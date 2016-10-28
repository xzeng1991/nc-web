package util;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	private final static Logger log = LoggerFactory.getLogger(LogUtils.class);
	
	public static void logInfo(String msg, Object... args){
		log.info(msg,args);
	}
	
	public static void logError(String msg,Object... args){
		log.error(msg,args);
	}
	
	public static void logError(Throwable t,String msg,Object... args){
		Object[] logArgs = addThrowableToMessageParameters(args, t);
		log.error(msg,logArgs);
	}
	
	private static Object[] addThrowableToMessageParameters(Object[] args,
			Throwable t) {
		if (ArrayUtils.isEmpty(args)) {
			return new Object[] { t };
		} else {
			Object[] argsCopy = Arrays.copyOf(args, args.length + 1);
			argsCopy[args.length] = t;
			return argsCopy;
		}
	}
}
