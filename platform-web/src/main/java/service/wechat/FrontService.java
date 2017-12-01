package service.wechat;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import constants.WechatConstants;
import util.HttpClientUtils;
import util.LogUtils;

//@Service
public class FrontService {
	// 网页授权的URL
	public String generateCodeUrl(String appId, String redirectUrl, String scope, String state) throws Exception{
		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.authorizeUrl);
		builder.append("?appid=").append(appId);
		builder.append("&redirect_uri=").append(URLEncoder.encode(redirectUrl,"utf-8"));
		builder.append("&response_type=code");
		builder.append("&scope=").append(scope);
		builder.append("&state=").append(state);
		builder.append("#wechat_redirect");

		return builder.toString();
	}
	
	// 通过code 获取token
	public String getTokenByCode(String code){
		return accessToken(WechatConstants.appId, WechatConstants.appSecret, code);
	}

	/**
	 * 获取网页授权的token
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public String accessToken(String appId, String appSecret, String code) {
		String accessToken = null;

		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.authorizeTokenUrl).append("?grant_type=authorization_code");
		builder.append("&appid=").append(appId);
		builder.append("&secret=").append(appSecret);
		builder.append("&code=").append(code);

		try {
			String response = HttpClientUtils.doGet(builder.toString());
			JSONObject json = JSON.parseObject(response);

			if (json.get("access_token") != null) { // 获取token成功
				accessToken = json.get("access_token").toString();
			} else { // 获取失败，记录error日志
				LogUtils.logError("wechat accessToken fail url:{},response:{}", builder.toString(), response);
			}
		} catch (Exception e) {
			LogUtils.logError(e, "wechat accessToken url:{},error:{}", builder.toString());
			return null;
		}
		return accessToken;
	}

	/**
	 * 刷新授权token
	 * 
	 * @param appId
	 * @param refreshToken
	 * @return
	 */
	public String refreshToken(String appId, String refreshToken) {
		String accessToken = null;

		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.freshTokenUrl).append("?grant_type=refresh_token");
		builder.append("&appid=").append(appId);
		builder.append("&refresh_token=").append(refreshToken);

		try {
			String response = HttpClientUtils.doGet(builder.toString());
			JSONObject json = JSON.parseObject(response);

			if (json.get("access_token") != null) { // 获取token成功
				accessToken = json.get("access_token").toString();
			} else { // 获取失败，记录error日志
				LogUtils.logError("wechat accessToken fail url:{},response:{}", builder.toString(), response);
			}
		} catch (Exception e) {
			LogUtils.logError(e, "wechat accessToken url:{},error:{}", builder.toString());
			return null;
		}
		return accessToken;
	}

	public void getUserInfo(String token, String openId, String lang) {
		// TODO 使用前面查到的token & openId查询用户信息
	}

	public void checkUserToken(String token, String openId) {
		// TODO 校验用户的token是否还有效
	}

	/**
	 * JS_SDK适用权限验证
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> sign(String jsapiTicket, String url) throws Exception {
		Map<String, String> ret = new HashMap<String, String>();
		String nonceStr = createNonceStr(); // 随机字符串
		String timestamp = createTimestamp(); // 时间戳
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		LogUtils.logInfo(string1);

		// 加密
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(string1.getBytes("UTF-8"));
		signature = byteToHex(crypt.digest());

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapiTicket);
		ret.put("nonceStr", nonceStr);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	// 创建uuid -->noncestr随机字符串
	private String createNonceStr() {
		return UUID.randomUUID().toString();
	}

	// 获取事件戳
	private String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
