package model.response;

import javax.xml.bind.annotation.XmlRootElement;

import constants.enums.WxMsgType;
/**
 * 
 * @author zengxing
 *
 */
@XmlRootElement(name = "xml")
public class WxImageMessage extends WxResponseBaseMsg {
	private MediaInfo Image;	// 回复图像信息

	public WxImageMessage() {
	}

	public WxImageMessage(String toUserName, String fromUserName, String mediaId) {
		super(toUserName, fromUserName, WxMsgType.IMAGE.getType());
		this.Image = new MediaInfo(mediaId);
	}

	public MediaInfo getImage() {
		return Image;
	}

	public void setImage(MediaInfo image) {
		Image = image;
	}

}
