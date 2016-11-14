package model.response;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;

@XmlRootElement(name = "xml")
public class WxMusicMessage extends WxResponseBaseMsg {
	private MediaInfo Music;	// 回复音乐消息

	public WxMusicMessage() {
	}

	public WxMusicMessage(String toUserName, String fromUserName, String title, String description, String musicUrl,
			String hqMusicUrl, String thumbMediaId) {
		super(toUserName, fromUserName, WxMsgType.MUSIC.getType());
		this.Music = new MediaInfo(title, description, musicUrl, hqMusicUrl, thumbMediaId);
	}

	public MediaInfo getMusic() {
		return Music;
	}

	public void setMusic(MediaInfo music) {
		Music = music;
	}

}
