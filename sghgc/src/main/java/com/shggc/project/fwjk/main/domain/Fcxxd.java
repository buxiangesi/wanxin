package com.shggc.project.fwjk.main.domain;

import com.shggc.framework.web.domain.BaseEntity;

/**
 * 房产信息点
 * */
public class Fcxxd extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**所在街道*/
	private String szjd;
	
	/**产权人*/
	private String cqr;
	
	/**居主人*/
	private String jzr;
	
	/**房屋产权证*/
	private String fwcqz;

	public String getSzjd() {
		return szjd;
	}

	public void setSzjd(String szjd) {
		this.szjd = szjd;
	}

	public String getCqr() {
		return cqr;
	}

	public void setCqr(String cqr) {
		this.cqr = cqr;
	}

	public String getJzr() {
		return jzr;
	}

	public void setJzr(String jzr) {
		this.jzr = jzr;
	}

	public String getFwcqz() {
		return fwcqz;
	}

	public void setFwcqz(String fwcqz) {
		this.fwcqz = fwcqz;
	}
}
