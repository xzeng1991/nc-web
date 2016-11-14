package constants.enums;

public enum WxMsgType {
	TEXT("text"),		// 文本消息
	IMAGE("image"),		// 图片消息
	VOICE("voice"),		// 语音消息
	VIDEO("video"),		// 视频消息
	SHORTVIDEO("shortvideo"),	// 小视频消息
	LOCATION("location"),		// 地理位置消息
	LINK("link"),		// 链接消息
	EVENT("event"),		// 事件推送
	MUSIC("music"),		// 音乐消息
	NEWS("news");		// 图文消息
	
	String type;

	WxMsgType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
