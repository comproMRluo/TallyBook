package com.tallybook.bean;

import java.io.Serializable;
import java.util.List;

/**
 *       业务bean   分页技术实现的核心
 * @author Luo
 *
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNO;										//当前页码:用户选择
	private int totalPageNo;								//总得页数:计算得出-总条数/每页显示的个数 注:可能无法整除,故需修改返回页数的算法
	private int totalRecord;								//总得记录条数:dao,sql: select count(*) from goods
	public static final int PAGE_SIZE = 10;					//每页的记录条数:静态常量
	private List<T> list;									//每页的记录信息的集合:dao,sql:select * from records LIMIT 开始位置(含0),查询个数
	public int getPageNO() {
		if(pageNO<=1||getTotalPageNo()==0)
			return 1;
		if(pageNO>getTotalPageNo())
			return getTotalPageNo();
		return pageNO;
	}
	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}
	/**
	 * 考虑了多出几条记录无法被整除的情况
	 * 
	 * @return
	 */
	public int getTotalPageNo() {
		return totalRecord%PAGE_SIZE==0?totalRecord/PAGE_SIZE:totalRecord/PAGE_SIZE+1;
	}
	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page(int pageNO, int totalPageNo, int totalRecord, List<T> list) {
		this.pageNO = pageNO;
		this.totalPageNo = totalPageNo;
		this.totalRecord = totalRecord;
		this.list = list;
	}
	public Page() {
	}
	@Override
	public String toString() {
		return "Page [pageNO=" + pageNO + ", totalPageNo=" + totalPageNo + ", totalRecord=" + totalRecord + ", list="
				+ list + "]";
	}
	
	
}
