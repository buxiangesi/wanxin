package com.shggc.project.sjtb.search.domain;

import com.shggc.framework.web.domain.BaseEntity;

public class DtoFwtjlb extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/*名称*/
	private String mc;
	
	/*数据*/
	private String sj;

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}
	
	
}
