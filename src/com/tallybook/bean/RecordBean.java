package com.tallybook.bean;

import java.util.Date;


/**
 * 	取得的一条记录项的封装
 * @author Luo
 *
 */
public class RecordBean {
	private int recordid;
	private Date timing;
	private double amount;
	private String type;
	private int paytype;
	private String remark;
	private String username;
	public RecordBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RecordBean(int recordid, Date timing, double amount, String type, int paytype, String remark,
			String username) {
		this.recordid = recordid;
		this.timing = timing;
		this.amount = amount;
		this.type = type;
		this.paytype = paytype;
		this.remark = remark;
		this.username = username;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getRecordid() {
		return recordid;
	}







	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}







	public int getPaytype() {
		return paytype;
	}



	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Date getTiming() {
		return timing;
	}
	public void setTiming(Date timing) {
		this.timing = timing;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;	}


	@Override
	public String toString() {
		return "RecordBean [recordid=" + recordid + ", timing=" + timing + ", amount=" + amount + ", type=" + type
				+ ", paytype=" + paytype + ", remark=" + remark + ", username=" + username + "]";
	}
	
	
}
