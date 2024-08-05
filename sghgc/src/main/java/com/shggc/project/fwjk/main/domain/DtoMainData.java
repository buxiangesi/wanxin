package com.shggc.project.fwjk.main.domain;

import java.util.List;

import com.shggc.framework.web.domain.BaseEntity;

public class DtoMainData extends BaseEntity{
	
	 private static final long serialVersionUID = 1L;
	 
	/** 街道总数 */
	private int jdzs;
	
	/** 户数 */
	private int hs;
	
	/** 房间数量 */
	private double fjsl;
	
	/** 建筑面积 */
	private double jzmj;
	
	/** 居主人总数 */
	private int jzrzs;
	
	/** 土地面积 */
	private double tdmj;

	/**房产信息点*/
	private List <Fcxxd> fcxxds;
	
	/**胡同信息*/
	private List <Htxx> htxxs;
	
	public int getJdzs() {
		return jdzs;
	}

	public void setJdzs(int jdzs) {
		this.jdzs = jdzs;
	}

	public int getHs() {
		return hs;
	}

	public void setHs(int hs) {
		this.hs = hs;
	}

	

	public double getFjsl() {
		return fjsl;
	}

	public void setFjsl(double fjsl) {
		this.fjsl = fjsl;
	}

	public double getJzmj() {
		return jzmj;
	}

	public void setJzmj(double jzmj) {
		this.jzmj = jzmj;
	}

	public int getJzrzs() {
		return jzrzs;
	}

	public void setJzrzs(int jzrzs) {
		this.jzrzs = jzrzs;
	}

	public double getTdmj() {
		return tdmj;
	}

	public void setTdmj(double tdmj) {
		this.tdmj = tdmj;
	}

	public List<Fcxxd> getFcxxds() {
		return fcxxds;
	}

	public void setFcxxds(List<Fcxxd> fcxxds) {
		this.fcxxds = fcxxds;
	}

	public List<Htxx> getHtxxs() {
		return htxxs;
	}

	public void setHtxxs(List<Htxx> htxxs) {
		this.htxxs = htxxs;
	}
	
	
	
}
