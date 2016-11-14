package com.job.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties 文件操作
 * 
 * @author zengxing
 *
 */
public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static final String CONFIG_PROPERTIES = "config.properties";

	/**
	 * 加载资源文件
	 * @param propertyFileName
	 * @return
	 */
	private static Properties loadProperties(String propertyFileName) {
		Properties prop = new Properties();
		InputStreamReader in = null;
		try {
			URL url = null;
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			url = loder.getResource(propertyFileName);
			in = new InputStreamReader(new FileInputStream(url.getPath()), "UTF-8");
			prop.load(in);
		} catch (IOException e) {
			logger.error("load {} error!", propertyFileName);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close {} error!", propertyFileName);
				}
			}
		}
		return prop;
	}
	
	/**
	 * 获取资源文件属性值
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		Properties prop = loadProperties(CONFIG_PROPERTIES);
		if(prop != null){
			return prop.getProperty(key);
		}
		return null;
	}
}
