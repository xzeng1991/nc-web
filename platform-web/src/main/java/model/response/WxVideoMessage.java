package model.response;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;

@XmlRootElement(name = "xml")
public class WxVideoMessage extends WxResponseBaseMsg {
	private MediaInfo Video;	// 回复视频信息

	public WxVideoMessage() {
	}

	public WxVideoMessage(String toUserName, String fromUserName, String mediaId, String title, String description) {
		super(toUserName, fromUserName, WxMsgType.VIDEO.getType());
		this.Video = new MediaInfo(mediaId, title, description);
	}

	public MediaInfo getVideo() {
		return Video;
	}

	public void setVideo(MediaInfo video) {
		Video = video;
	}

}
