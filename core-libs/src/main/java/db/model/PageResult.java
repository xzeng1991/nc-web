package db.model;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = 6598118304654850124L;
	private final static int DEFAULT_PAGE_SIZE = 10;	// 默认每页记录数
	private int currentPage = 1; 	// 当前页
	private int pageSize = DEFAULT_PAGE_SIZE;		// 每页记录数
	private int totalCount;			// 总记录数
	private List<T> items;			// 记录
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}
