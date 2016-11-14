package model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;

@XmlRootElement(name = "xml")
public class WxNewsMessage extends WxResponseBaseMsg {
	private List<NewsInfo> Articles;	// 回复图文消息

	public WxNewsMessage() {
	}

	public WxNewsMessage(String toUserName, String fromUserName, List<NewsInfo> articles) {
		super(toUserName, fromUserName, WxMsgType.NEWS.getType());
		this.Articles = articles;
	}

	public List<NewsInfo> getArticles() {
		return Articles;
	}

	public void setArticles(List<NewsInfo> articles) {
		Articles = articles;
	}

}
