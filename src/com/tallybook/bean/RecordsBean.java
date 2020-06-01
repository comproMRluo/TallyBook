package com.tallybook.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * 	消费一览页面数据的封装对象
 * @author Luo
 *
 */
public class RecordsBean {
	private double totalin;
	private double totalout;
	private double accountRest;
	private double ioratio;
	public RecordsBean() {
	}
	public RecordsBean(double totalin, double totalout, double accountRest, double ioratio) {
		this.totalin = totalin;
		this.totalout = totalout;
		this.accountRest = accountRest;
		this.ioratio = ioratio;
	}
	public double getTotalin() {
		return totalin;
	}
	public void setTotalin(double totalin) {
		this.totalin = totalin;
	}
	public double getTotalout() {
		return totalout;
	}
	public void setTotalout(double totalout) {
		this.totalout = totalout;
	}
	public double getAccountRest() {
		return accountRest;
	}
	public void setAccountRest(double accountRest) {
		this.accountRest = accountRest;
	}
	public double getIoratio() {
		return ioratio;
	}
	public void setIoratio(double ioratio) {
		this.ioratio = ioratio;
	}
	@Override
	public String toString() {
		return "RecordsBean [totalin=" + totalin + ", totalout=" + totalout + ", accountRest=" + accountRest
				+ ", ioratio=" + ioratio + "]";
	}
	
	

}
