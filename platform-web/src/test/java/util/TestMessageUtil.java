package util;

import org.junit.Test;

import constants.enums.WxMsgType;
import model.response.WxTextMessage;

public class TestMessageUtil {
	@Test
	public void testTextMessageToXml() {
		WxTextMessage message = new WxTextMessage();
		message.setToUserName("FromUserName");
		message.setFromUserName("ToUserName");
		message.setCreateTime(DateUtils.getCurrentTimestamp());
		message.setMsgType(WxMsgType.TEXT.getType());
		message.setContent("test message");

		String str = MessageUtil.textMessageToXml(message);
		LogUtils.logInfo(str);
	}
}
