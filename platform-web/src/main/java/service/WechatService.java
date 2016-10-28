package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import constants.WechatConstants;
import constants.enums.WxMsgType;
import model.response.WxResponseBaseMsg;
import model.response.WxTextMessage;
import model.wechat.MenuItem;
import util.DateUtils;
import util.HttpClientUtils;
import util.LogUtils;
import util.MessageUtil;
import util.RegexUtils;

@Service
public class WechatService {
	/**
	 * 刷新access_token
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public String accessToken(String appId, String appSecret) {
		String accessToken = null;

		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.accessTokenUrl).append("?grant_type=client_credential");
		builder.append("&appid=").append(appId);
		builder.append("&secret=").append(appSecret);

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
	 * 获取微信服务器列表
	 * 
	 * @param accessToken
	 * @return
	 */
	public List<String> getCallbackIp(String accessToken) {
		List<String> ipList = null;
		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.callbackIpUrl);
		builder.append("?access_token=").append(accessToken);

		try {
			String response = HttpClientUtils.doGet(builder.toString());
			JSONObject json = JSON.parseObject(response);

			if (json.get("ip_list") != null) { // 获取token成功
				ipList = JSONArray.parseArray(json.get("ip_list").toString(), String.class);
			} else { // 获取失败，记录error日志
				LogUtils.logError("wechat getCallbackIp fail url:{},response:{}", builder.toString(), response);
			}
		} catch (Exception e) {
			LogUtils.logError(e, "wechat getCallbackIp url:{},error:{}", builder.toString());
			return null;
		}

		return ipList;
	}

	/**
	 * 创建菜单
	 * 
	 * @param accessToken
	 * @param menuInfo
	 * @return
	 */
	public boolean menuCreate(String accessToken, List<MenuItem> menuList) {
		boolean result = false;
		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.menuCreateUrl);
		builder.append("?access_token=").append(accessToken);

		JSONObject button = new JSONObject();
		button.put("button", menuList);

		try {
			String response = HttpClientUtils.doPost(builder.toString(), button.toJSONString());
			JSONObject json = JSON.parseObject(response);

			if ("0".equals(json.get("errcode"))) { // 获取token成功
				result = true;
			} else { // 获取失败，记录error日志
				LogUtils.logError("wechat menuCreate fail url : {},data : {},response : {}", builder.toString(),
						button.toJSONString(), response);
			}
		} catch (Exception e) {
			LogUtils.logError(e, "wechat menuCreate url : {},data : {},error : {}", builder.toString(),
					button.toJSONString());
			return false;
		}
		return result;
	}

	public String processWechatMsg(Map<String, String> wechatData) {
		String respMsg = null;
		// 消息类型
		String msgType = wechatData.get("MsgType");
		LogUtils.logInfo("wchat send a msg, msg : {}", wechatData);
		
		WxMsgType type = WxMsgType.valueOf(msgType.toUpperCase());
		switch (type) {
		case TEXT:
			WxTextMessage textMessage = processTextMsg(wechatData);
			respMsg = MessageUtil.textMessageToXml(textMessage);
			break;
		case IMAGE:
			break;
		case VOICE:
			break;
		case VIDEO:
			break;
		case SHORTVIDEO:
			break;
		case LOCATION:
			break;
		case LINK:
			break;
		default:
			break;
		}
		
		LogUtils.logInfo("process wechat msg done. response : {}", respMsg);
		return respMsg;
	}
	
	private WxTextMessage processTextMsg(Map<String, String> wechatData){
		String msg = wechatData.get("Content");
		String respContent = "Welcome!";
		// 对消息格式校验
		if(RegexUtils.checkDate(msg)){
			String[] date = msg.split("-");
			Map<String,List> dataMap = buildData();
			
			List<String> detail = dataMap.get(date[0]);
			if(detail != null){
				if(date[1].compareTo(detail.get(2)) > 0){
					respContent = detail.get(1);
				}else{
					respContent = detail.get(0);
				}
			}
		}
		
		WxTextMessage textMessage = new WxTextMessage();
        textMessage.setToUserName(wechatData.get("FromUserName"));
        textMessage.setFromUserName(wechatData.get("ToUserName"));
        textMessage.setCreateTime(DateUtils.getCurrentTimestamp());
        textMessage.setMsgType(WxMsgType.TEXT.getType());
        textMessage.setContent(respContent);
        return textMessage;
	}
	
	private Map<String,List> buildData(){
		Map<String,List> dataMap = new HashMap<String,List>();
		// 一月份
		List<String> dataList = new ArrayList<String>();
		dataList.add("摩羯座");
		dataList.add("水瓶座");
		dataList.add("19");
		dataMap.put("01", dataList);
		// 二月份
		dataList = new ArrayList<String>();
		dataList.add("水瓶座");
		dataList.add("双鱼座");
		dataList.add("18");
		dataMap.put("02", dataList);
		// 三月份
		dataList = new ArrayList<String>();
		dataList.add("双鱼座");
		dataList.add("白羊座");
		dataList.add("20");
		dataMap.put("03", dataList);
		// 四月份
		dataList = new ArrayList<String>();
		dataList.add("白羊座");
		dataList.add("金牛座");
		dataList.add("20");
		dataMap.put("04", dataList);
		// 五月份
		dataList = new ArrayList<String>();
		dataList.add("金牛座");
		dataList.add("双子座");
		dataList.add("20");
		dataMap.put("05", dataList);
		// 六月份
		dataList = new ArrayList<String>();
		dataList.add("双子座");
		dataList.add("巨蟹座");
		dataList.add("21");
		dataMap.put("06", dataList);
		// 七月份
		dataList = new ArrayList<String>();
		dataList.add("巨蟹座");
		dataList.add("狮子座");
		dataList.add("22");
		dataMap.put("07", dataList);
		// 八月份
		dataList = new ArrayList<String>();
		dataList.add("狮子座");
		dataList.add("处女座");
		dataList.add("22");
		dataMap.put("08", dataList);
		// 九月份
		dataList = new ArrayList<String>();
		dataList.add("处女座");
		dataList.add("天秤座");
		dataList.add("22");
		dataMap.put("09", dataList);
		// 10月份
		dataList = new ArrayList<String>();
		dataList.add("天秤座");
		dataList.add("天蝎座");
		dataList.add("22");
		dataMap.put("10", dataList);
		// 11月份
		dataList = new ArrayList<String>();
		dataList.add("天蝎座");
		dataList.add("射手座");
		dataList.add("21");
		dataMap.put("11", dataList);
		// 12月份
		dataList = new ArrayList<String>();
		dataList.add("射手座");
		dataList.add("摩羯座");
		dataList.add("21");
		dataMap.put("12", dataList);
		
		return dataMap;
	}
}
