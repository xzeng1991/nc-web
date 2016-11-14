package constants.enums;
/**
 * 微信JS接口列表
 * @author zengxing
 *
 */
public enum WxJsInterfaceType {
	// 分享类
	ONMENUSHARETIMELINE("onMenuShareTimeline"), // 分享朋友圈
	ONMENUSHAREAPPMESSAGE("onMenuShareAppMessage"), // 分享给朋友
	ONMENUSHAREQQ("onMenuShareQQ"), // 分享到QQ
	ONMENUSHAREWEIBO("onMenuShareWeibo"), // 分享到腾讯微博
	ONMENUSHAREQZONE("onMenuShareQZone"), // 分享到QQ空间
	// 音频类
	STARTRECORD("startRecord"), // 开始录音
	STOPRECORD("stopRecord"), // 停止录音
	ONVOICERECORDEND("onVoiceRecordEnd"), // 录音自动停止
	PLAYVOICE("playVoice"), // 播放语音
	PAUSEVOICE("pauseVoice"), // 暂停播放
	STOPVOICE("stopVoice"), // 停止播放
	ONVOICEPLAYEND("onVoicePlayEnd"), // 语音播放完毕
	UPLOADVOICE("uploadVoice"), // 上传语音
	DOWNLOADVOICE("downloadVoice"), // 下载语音
	// 图像类
	CHOOSEIMAGE("chooseImage"), // 选择图片
	PREVIEWIMAGE("previewImage"), // 预览图片
	UPLOADIMAGE("uploadImage"), // 上传图片
	DOWNLOADIMAGE("downloadImage"), // 下载图片
	// 智能类
	TRANSLATEVOICE("translateVoice"), // 智能语音识别
	// 设备信息类
	GETNETWORKTYPE("getNetworkType"), // 获取网络状态
	// 地理位置
	OPENLOCATION("openLocation"), // 查看位置
	GETLOCATION("getLocation"), // 获取地理位置
	// 界面操作
	HIDEOPTIONMENU("hideOptionMenu"), // 
	SHOWOPTIONMENU("showOptionMenu"), // 
	HIDEMENUITEMS("hideMenuItems"), // 批量隐藏功能按钮
	SHOWMENUITEMS("showMenuItems"), // 批量显示功能按钮
	HIDEALLNONBASEMENUITEM("hideAllNonBaseMenuItem"), // 隐藏非基础按钮
	SHOWALLNONBASEMENUITEM("showAllNonBaseMenuItem"), // 显示非基础按钮
	CLOSEWINDOW("closeWindow"), // 关闭当前窗口
	// 扫一扫类
	SCANQRCODE("scanQRCode"), // 微信扫一扫
	CHOOSEWXPAY("chooseWXPay"), // 
	OPENPRODUCTSPECIFICVIEW("openProductSpecificView"), // 
	ADDCARD("addCard"), // 
	CHOOSECARD("chooseCard"), // 
	OPENCARD("openCard"); // 
	
	String type;

	WxJsInterfaceType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
