package model.response;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;

@XmlRootElement(name = "xml")
public class WxTextMessage extends WxResponseBaseMsg {
	// 回复的消息内容
	private String Content;

	public WxTextMessage() {

	}

	public WxTextMessage(String toUserName, String fromUserName, String content) {
		super(toUserName, fromUserName, WxMsgType.TEXT.getType());
		this.Content = content;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
