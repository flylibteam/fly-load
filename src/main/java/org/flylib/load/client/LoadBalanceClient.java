package org.flylib.load.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadBalanceClient {
	private static final Logger logger = LoggerFactory.getLogger(LoadBalanceClient.class);
	
	private static String nodeList = LoadBalanceConfig.getNodeList();
	private static String[] nodeArray = nodeList.split(",");
	private static Random random = new Random();

	public static String getUrl() {
		int nodeSize = nodeArray.length;
		int nodeIndex = random.nextInt(nodeSize);
		String node = nodeArray[nodeIndex];
		
		String checkUrl = "http://" + node + LoadBalanceConfig.getCheckUrl();
		boolean alive = checkAlive(checkUrl);
		int newIndex = 0;
		
		if (alive) {
			String sendUrl = "http://" + node + LoadBalanceConfig.getSendUrl();
			return sendUrl;
		} else {
			newIndex = (nodeIndex + 1) % nodeSize;
			node = nodeArray[newIndex];
			checkUrl = "http://" + node + LoadBalanceConfig.getCheckUrl();
			alive = checkAlive(checkUrl);
			
			if (alive) {
				return "http://" + node + LoadBalanceConfig.getSendUrl();
			} else {
				newIndex = (newIndex + 1) % nodeSize;
				node = nodeArray[newIndex];
				checkUrl = "http://" + node + LoadBalanceConfig.getCheckUrl();
				alive = checkAlive(checkUrl);
				if (alive) {
					return "http://" + node + LoadBalanceConfig.getSendUrl();
				} else {
					return "";
				}
			}
		}
	}
	
	private static boolean checkAlive(String checkUrl) {
//		String responseStr = "";
//		boolean result = false;
//		try {
//			responseStr = HttpClientUtil.doGet(checkUrl, null, "UTF-8");
//			if (StringUtils.isEmpty(responseStr)) {
//				result = false;
//			} else {
//				JSONObject json = JSON.parseObject(responseStr);
//				Integer alive = json.getInteger("alive");
//				if (alive != null && alive == 1) {
//					result = true;
//				} else {
//					result = false;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result = false;
//		}
//		logger.info("checkUrl={},alive={}", new Object[] { checkUrl, result });
//		return result;
		
		return false;
	}
	
	public static void main(String[] args) {
		for (int i = 0 ; i < 100; i++) {
			String url = LoadBalanceClient.getUrl();
			logger.info("---->available_sendpath_url={}", url);
		}
	}
}
