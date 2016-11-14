package model.response;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;

@XmlRootElement(name = "xml")
public class WxVoiceMessage extends WxResponseBaseMsg {
	private MediaInfo Voice; // 回复语音信息

	public WxVoiceMessage() {
	}

	public WxVoiceMessage(String toUserName, String fromUserName, String mediaId) {
		super(toUserName, fromUserName, WxMsgType.VOICE.getType());
		this.Voice = new MediaInfo(mediaId);
	}

	public MediaInfo getVoice() {
		return Voice;
	}

	public void setVoice(MediaInfo voice) {
		Voice = voice;
	}

}
