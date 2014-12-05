package bbq.db.project.dao.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageInfo implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPageNo;
		result = prime * result + pageSize;
		result = prime * result + (totalCount == null ? 0 : totalCount);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageInfo other = (PageInfo) obj;
		if (currentPageNo != other.currentPageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if ((totalCount==null?0:totalCount.intValue()) != (other.totalCount==null?0:other.totalCount.intValue()))
			return false;
		return true;
	}

	public static final String CURRENT_PAGE_NAME = "_pn"; 
	public static final int FIRST_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE;

	private int currentPageNo; 

	private Integer totalCount = null; 

	public PageInfo() {
		this(FIRST_PAGE_NO, DEFAULT_PAGE_SIZE);
	}

	public PageInfo(int start, int pageSize) {
		this.currentPageNo = start;
		this.pageSize = pageSize;
	}

	public String makePage(String url, int CASE) {
		StringBuffer str = new StringBuffer("");
		int tostart = currentPageNo - CASE;
		int toend = currentPageNo + CASE;
		if (currentPageNo - CASE < 1) {
			toend = (currentPageNo + CASE) - (currentPageNo - CASE - 1);
		}
		if (currentPageNo + CASE > getTotalPage()) {
			tostart = (currentPageNo - CASE)
					- (currentPageNo + CASE - getTotalPage());
		}
		if (toend > getTotalPage())
			toend = getTotalPage();
		if (tostart < 1)
			tostart = 1;

		for (int i = 1; i <= getTotalPage(); i++) {
			if (i >= tostart && i <= toend) {
				if (i != currentPageNo) {
					str.append("<a href=\"");
					str.append(url);
					str.append(i);
					str.append("\">");
					str.append(i);
					str.append("</a>");
					str.append("&nbsp;&nbsp;");
				} else {
					str.append(i);
					str.append("&nbsp;&nbsp;");
				}
			}
		}
		return str.toString();
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public int getTotalPage() {
		return pageSize > 0 ? (totalCount + pageSize - 1) / pageSize : 0;
	}

	public int getPageSize() {
		return pageSize;
	}

	public boolean isFirstPage() {
		return this.getCurrentPageNo() == FIRST_PAGE_NO ? true : false;
	}

	public boolean hasNextPage() {
		return this.getCurrentPageNo() < getTotalPage() - 1;
	}

	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	public boolean isLastPage() {
		return this.getCurrentPageNo() == getTotalPage() ? true : false;
	}

	public int getCurrentPageStart() {
		return getPageSize() * (getCurrentPageNo() - 1);
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNO) {
		this.currentPageNo = currentPageNO;
	}
}