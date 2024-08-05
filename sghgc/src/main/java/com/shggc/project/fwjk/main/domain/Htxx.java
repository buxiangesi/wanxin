package com.shggc.project.fwjk.main.domain;

import com.shggc.framework.web.domain.BaseEntity;
/**
 * 胡同信息
 * */
public class Htxx extends BaseEntity {
	//胡同名称
	private String htmc;
	
	//产权数
	private int cqs;
	
	//房屋面积
	private double fwmj;
	
	//土地面积
	private double tdmj;

	public String getHtmc() {
		return htmc;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public int getCqs() {
		return cqs;
	}

	public void setCqs(int cqs) {
		this.cqs = cqs;
	}

	public double getFwmj() {
		return fwmj;
	}

	public void setFwmj(double fcmj) {
		this.fwmj = fcmj;
	}

	public double getTdmj() {
		return tdmj;
	}

	public void setTdmj(double tdmj) {
		this.tdmj = tdmj;
	}
	
	
}
