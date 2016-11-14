package constants.enums;

public enum WxMenuType {
	CLICK("click"), 	// 点击推事件
	VIEW("view"), 		// 跳转URL
	SCANCODE_PUSH("scancode_push"),		// 扫码推事件 
	SCANCODE_WAITMSG("scancode_waitmsg"), 	// 扫码推事件并弹出对话框
	PIC_SYSPHOTO("pic_sysphoto"), 	// 弹出系统拍照发图
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album"), 	// 弹出拍照或者相册
	PIC_WEIXIN("pic_weixin"), 	// 弹出微信相册
	LOCATION_SELECT("location_select"), 	// 弹出地理位置选择
	MEDIA_ID("media_id"), 	// 下发消息
	VIEW_LIMITED("view_limited");	// 跳转图文消息URL

	String type;

	WxMenuType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
