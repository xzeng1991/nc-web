package model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WxTextMessage extends WxResponseBaseMsg {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
