package constants.enums;

/**
 * 用户权限枚举
 * 
 * @author zengxing
 *
 */
public enum UserType {
	ROOT(0, "超级管理员"), // root 权限
	ADMIN(1, "管理员"), // 管理员权限
	NORMAL(2, "普通用户"); // 普通权限

	int type;
	String desc;

	UserType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static UserType match(Integer type) {
		if (type == null) {
			return null;
		}
		for (UserType item : UserType.values()) {
			if (item.type == type) {
				return item;
			}
		}
		return null;
	}
}
