package constants.enums;

public enum UserStatus {
	UNLOCKED(0, "正常"), // 非锁定状态
	LOCKED(1, "锁定"); // 锁定状态

	int status;
	String desc; // 描述

	UserStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	public static UserStatus match(Integer status) {
		if (status == null) {
			return null;
		}
		for (UserStatus item : UserStatus.values()) {
			if (item.status == status) {
				return item;
			}
		}
		return null;
	}
}
