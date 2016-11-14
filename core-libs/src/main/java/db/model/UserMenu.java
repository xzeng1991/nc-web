package db.model;

import java.util.List;
/**
 * 菜单实体
 * @author zengxing
 *
 */
public class UserMenu extends BaseDomain {
	private static final long serialVersionUID = 8049572748477788977L;
	
	private int menuId; // ID
	private int parentId; // 父菜单ID
	private String name; // 菜单名称
	private String url;
	private int number; // 菜单序号
	private List<UserMenu> subMenu;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrder() {
		return number;
	}

	public void setOrder(int number) {
		this.number = number;
	}

	public List<UserMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<UserMenu> subMenu) {
		this.subMenu = subMenu;
	}

}
