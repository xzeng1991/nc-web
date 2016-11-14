package constants.enums;
/**
 * 事件类型枚举
 * @author zengxing
 *
 */
public enum EventType {
	SUBSCRIBE("subscribe"), // 关注事件
	UNSUBSCRIBE("unsubscribe"), // 取消关注事件
	SCAN("SCAN"), // 扫码事件
	LOCATION("LOCATION"), // 上报地理位置事件
	CLICK("CLICK"), // 点击菜单拉取消息时的事件推送
	VIEW("VIEW"); // 点击菜单跳转链接时的事件推送

	String type;

	EventType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
