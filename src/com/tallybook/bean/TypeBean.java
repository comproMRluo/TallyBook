package com.tallybook.bean;


/**
 * 	管理员查看管理所有类型的数据封装对象
 * @author Luo
 *
 */
public class TypeBean {
	private int typeid;
	private String typename;
	private int paytypeid;
	public TypeBean() {
		// TODO Auto-generated constructor stub
	}


	public TypeBean(int typeid, String typename, int paytypeid) {
		this.typeid = typeid;
		this.typename = typename;
		this.paytypeid = paytypeid;
	}


	public int getTypeid() {
		return typeid;
	}






	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}






	public int getPaytypeid() {
		return paytypeid;
	}



	public void setPaytypeid(int paytypeid) {
		this.paytypeid = paytypeid;
	}



	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}


	@Override
	public String toString() {
		return "TypeBean [typeid=" + typeid + ", typename=" + typename + ", paytypeid=" + paytypeid + "]";
	}



	
}
