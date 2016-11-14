package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import constants.WechatConstants;
import constants.enums.EventType;
import constants.enums.WxMsgType;
import db.manager.XingzuoManager;
import db.model.XingzuoInfo;
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
	@Autowired
	private XingzuoManager xzManager;

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

	/**
	 * 查询菜单列表
	 * 
	 * @param accessToken
	 * @return
	 */
	public List<MenuItem> menuList(String accessToken) {
		List<MenuItem> menus = null;
		
		StringBuilder builder = new StringBuilder();
		builder.append(WechatConstants.menuGetUrl);
		builder.append("?access_token=").append(accessToken);

		try {
			String response = HttpClientUtils.doGet(builder.toString());
			JSONObject json = JSON.parseObject(response);
			
			JSONObject menu = json.getJSONObject("menu");
			if (menu != null) { // 获取token成功
				JSONArray array = menu.getJSONArray("button");
				menus = JSONArray.parseArray(array.toJSONString(), MenuItem.class);
			} else { // 获取失败，记录error日志
				LogUtils.logError("wechat menuGet fail url : {},response : {}", builder.toString(), response);
			}
		} catch (Exception e) {
			LogUtils.logError(e, "wechat menuCreate url : {},error : {}", builder.toString());
		}
		
		return menus;
	}

	public String processWechatMsg(Map<String, String> wechatData) {
		String respMsg = null;
		// 消息类型
		String msgType = wechatData.get("MsgType");
		LogUtils.logInfo("wchat send a msg, msg : {}", wechatData);

		WxMsgType type = WxMsgType.valueOf(msgType.toUpperCase());
		WxResponseBaseMsg respMessge = null;
		switch (type) {
		case TEXT:
			respMessge = processTextMsg(wechatData);
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
		case EVENT:
			processEventMsg(wechatData);
			break;
		default:
			break;
		}

		respMsg = MessageUtil.textMessageToXml(respMessge);
		LogUtils.logInfo("process wechat msg done. response : {}", respMsg);
		return respMsg;
	}

	// 文本消息处理
	private WxTextMessage processTextMsg(Map<String, String> wechatData) {
		String msg = wechatData.get("Content");
		String respContent = "Welcome! Please input your birthday like : 03-19, to find out your Constellation (^_^)";
		// 对消息格式校验
		if (RegexUtils.checkDate(msg)) {
			String[] date = msg.split("-");
			if (date[0].length() == 1) {
				date[0] = "0" + date[0];
			}
			if (date[1].length() == 1) {
				date[1] = "0" + date[1];
			}
			// 查询星座信息
			XingzuoInfo xzInfo = xzManager.findByBirthday(date[0] + "-" + date[1]);
			if (xzInfo != null) {
				respContent = xzInfo.getXzName();
			}
		}

		// 组装微信响应数据
		WxTextMessage textMessage = new WxTextMessage();
		textMessage.setToUserName(wechatData.get("FromUserName"));
		textMessage.setFromUserName(wechatData.get("ToUserName"));
		textMessage.setCreateTime(DateUtils.getCurrentTimestamp());
		textMessage.setMsgType(WxMsgType.TEXT.getType());
		textMessage.setContent(respContent);
		return textMessage;
	}

	// 事件消息处理
	private void processEventMsg(Map<String, String> wechatData) {
		String eventType = wechatData.get("Event");
		EventType type = EventType.valueOf(eventType.toUpperCase());

		switch (type) {
		case SUBSCRIBE:
			processSubscribeEvent(wechatData);
			break;
		case UNSUBSCRIBE:
			processUnsubscribeEvent(wechatData);
			break;
		case SCAN:
			processScanEvent(wechatData);
			break;
		case LOCATION:
			processLocationEvent(wechatData);
			break;
		case CLICK:
			processClickMenuEvent(wechatData);
			break;
		case VIEW:
			processClickUrlEvent(wechatData);
			break;
		default:
			break;
		}
	}

	// 处理关注事件
	private void processSubscribeEvent(Map<String, String> eventData) {
		String eventKey = eventData.get("EventKey");
		String ticket = eventData.get("Ticket");

		LogUtils.logInfo("user : '{}' subscribe wechat : '{}' at '{}',eventKey='{}',ticket='{}'",
				eventData.get("FromUserName"), eventData.get("ToUserName"), eventData.get("CreateTime"), eventKey,
				ticket);
	}

	// 处理取消关注事件
	private void processUnsubscribeEvent(Map<String, String> eventData) {
		LogUtils.logInfo("user : '{}' unsubscribe wechat : '{}' at '{}'", eventData.get("FromUserName"),
				eventData.get("ToUserName"), eventData.get("CreateTime"));
	}

	// 处理扫码事件
	private void processScanEvent(Map<String, String> eventData) {
		String eventKey = eventData.get("EventKey");
		String ticket = eventData.get("Ticket");

		LogUtils.logInfo("user : '{}' scan wechat : '{}' at '{}',eventKey='{}',ticket='{}'",
				eventData.get("FromUserName"), eventData.get("ToUserName"), eventData.get("CreateTime"), eventKey,
				ticket);
	}

	// 处理上报地理位置事件
	private void processLocationEvent(Map<String, String> eventData) {
		String latitude = eventData.get("Latitude");
		String longitude = eventData.get("Longitude");
		String precision = eventData.get("Precision");

		LogUtils.logInfo("user : '{}' location is [{},{},{}] at '{}'", eventData.get("FromUserName"), latitude,
				longitude, precision, eventData.get("CreateTime"));
	}

	// 处理点击菜单事件
	private void processClickMenuEvent(Map<String, String> eventData) {
		String eventKey = eventData.get("EventKey");

		LogUtils.logInfo("user : '{}' click menu key '{}' at '{}'", eventData.get("FromUserName"), eventKey,
				eventData.get("CreateTime"));
	}

	// 处理点击URL事件
	private void processClickUrlEvent(Map<String, String> eventData) {
		String eventKey = eventData.get("EventKey");

		LogUtils.logInfo("user : '{}' click url key '{}' at '{}'", eventData.get("FromUserName"), eventKey,
				eventData.get("CreateTime"));
	}
}
