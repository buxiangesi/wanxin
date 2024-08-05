package com.shggc.project.fwjk.dtxx.domain;
/**
 * 
 * @author 
 * 综合房产信息和房屋信息类
 *
 */
public class TJcFcxx {
	//	//房产编号,对应t_jc_fc中fcbh，为符合json数据格式，命名为guid
	private String guid;
	
	private String fwzl;
	
	private String cqr;
	
	private String fwcqz;
	
	private String fwcb;
	
	private Double fwjsh;
	
	private Double jzmjh;
	
	private String tdsyzh;
	
	private Double tdsymj;
	
	private String qllx;
	
	private String qlxz;
	
	private String bz;
	
	private String kz1;
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getFwzl() {
		return fwzl;
	}

	public void setFwzl(String fwzl) {
		this.fwzl = fwzl;
	}

	public String getCqr() {
		return cqr;
	}

	public void setCqr(String cqr) {
		this.cqr = cqr;
	}

	public String getFwcqz() {
		return fwcqz;
	}

	public void setFwcqz(String fwcqz) {
		this.fwcqz = fwcqz;
	}

	public String getFwcb() {
		return fwcb;
	}

	public void setFwcb(String fwcb) {
		this.fwcb = fwcb;
	}

	public Double getFwjsh() {
		return fwjsh;
	}

	public void setFwjsh(Double fwjsh) {
		this.fwjsh = fwjsh;
	}

	public Double getJzmjh() {
		return jzmjh;
	}

	public void setJzmjh(Double jzmjh) {
		this.jzmjh = jzmjh;
	}

	public String getTdsyzh() {
		return tdsyzh;
	}

	public void setTdsyzh(String tdsyzh) {
		this.tdsyzh = tdsyzh;
	}

	public Double getTdsymj() {
		return tdsymj;
	}

	public void setTdsymj(Double tdsymj) {
		this.tdsymj = tdsymj;
	}

	public String getQllx() {
		return qllx;
	}

	public void setQllx(String qllx) {
		this.qllx = qllx;
	}

	public String getQlxz() {
		return qlxz;
	}

	public void setQlxz(String qlxz) {
		this.qlxz = qlxz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getKz1() {
		return kz1;
	}

	public void setKz1(String kz1) {
		this.kz1 = kz1;
	}

	@Override
	public String toString() {
		return "TJcFcxx [guid=" + guid + ", fwzl=" + fwzl + ", cqr=" + cqr + ", fwcqz=" + fwcqz + ", fwcb=" + fwcb
				+ ", fwjsh=" + fwjsh + ", jzmjh=" + jzmjh + ", tdsyzh=" + tdsyzh + ", tdsymj=" + tdsymj + ", qllx="
				+ qllx + ", qlxz=" + qlxz + ", bz=" + bz + ", kz1=" + kz1 + "]";
	}
	
	
}
