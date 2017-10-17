package org.flylib.load.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class LoadBalanceConfig {
	private static String nodeList;
	private static String defaultNode;
	private static String sendUrl;
	private static String checkUrl;
	
	public static String getSendUrl() {
		return sendUrl;
	}

	public static String getCheckUrl() {
		return checkUrl;
	}

	public static String getNodeList() {
		return nodeList;
	}

	public static String getDefaultNode() {
		return defaultNode;
	}

	static {
		InputStream in = LoadBalanceConfig.class.getClassLoader().getResourceAsStream("property-test/loadBalanced.properties");
		Properties prop = new Properties();
		try {
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			prop.load(reader);
			nodeList = prop.getProperty("nodeList.sendPath");
			defaultNode = prop.getProperty("defaultNode.sendPath");
			sendUrl = prop.getProperty("sendPath.sendUrl");
			checkUrl = prop.getProperty("sendPath.checkUrl");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
