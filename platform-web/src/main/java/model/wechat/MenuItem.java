package model.wechat;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuItem {
	private String type; // 菜单类型（）
	private String name; // 菜单名称
	private String key; //
	private String url; // type=view类型的属性
	@JSONField(name="sub_button")
	private List<MenuItem> childMenu; // 子菜单

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuItem> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<MenuItem> childMenu) {
		this.childMenu = childMenu;
	}

	@Override
	public String toString() {
		return "MenuItem [type=" + type + ", name=" + name + ", key=" + key + ", url=" + url + ", childMenu="
				+ childMenu + "]";
	}
}
