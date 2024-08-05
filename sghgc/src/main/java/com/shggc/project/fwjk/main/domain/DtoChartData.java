package com.shggc.project.fwjk.main.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shggc.framework.web.domain.BaseEntity;

public class DtoChartData extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**房产分布统计*/
	private List <Map<String, String>> fcfbtj;

	/**胡同排行名称*/
	private List <String> htphmc;
	
	/**胡同排行数据*/
	private List <Integer> htphsj;
	
	/**房产类别名称*/
	private List <String> fclbmc;
	
	/**房产类别私产数据*/
	private List <Integer> fclbscsj;
	
	/**房产类别公产数据*/
	private List <Integer> fclbgcsj;
	
	/**房产类别路产数据*/
	private List <Integer> fclblcsj;
	
	/**房产类别教产数据*/
	private List <Integer> fclbjcsj;
	
	/**房产类别军产数据*/
	private List <Integer> fclbjuncsj;
	
	/**权力类型*/
	private List <Map<String, String>> qllx;
	
	/**权力性质名称*/
	private List <String> qlxzmc;
	
	/**权力性质*/
	private List <Map<String, String>> qlxz;
	
	public List<Map<String, String>> getFcfbtj() {
		return fcfbtj;
	}

	public void setFcfbtj(List<Map<String, String>> fcfbtj) {
		this.fcfbtj = fcfbtj;
	}

	public List<String> getHtphmc() {
		return htphmc;
	}

	public void setHtphmc(List<String> htphmc) {
		this.htphmc = htphmc;
	}

	public List<Integer> getHtphsj() {
		return htphsj;
	}

	public void setHtphsj(List<Integer> htphsj) {
		this.htphsj = htphsj;
	}

	public List<String> getFclbmc() {
		return fclbmc;
	}

	public void setFclbmc(List<String> fclbmc) {
		this.fclbmc = fclbmc;
	}

	public List<Integer> getFclbscsj() {
		return fclbscsj;
	}

	public void setFclbscsj(List<Integer> fclbscsj) {
		this.fclbscsj = fclbscsj;
	}

	public List<Integer> getFclbgcsj() {
		return fclbgcsj;
	}

	public void setFclbgcsj(List<Integer> fclbgcsj) {
		this.fclbgcsj = fclbgcsj;
	}

	public List<Integer> getFclblcsj() {
		return fclblcsj;
	}

	public void setFclblcsj(List<Integer> fclblcsj) {
		this.fclblcsj = fclblcsj;
	}

	public List<Map<String, String>> getQllx() {
		return qllx;
	}

	public void setQllx(List<Map<String, String>> qllx) {
		this.qllx = qllx;
	}

	
	public List<String> getQlxzmc() {
		return qlxzmc;
	}

	public void setQlxzmc(List<String> qlxzmc) {
		this.qlxzmc = qlxzmc;
	}

	public List<Map<String, String>> getQlxz() {
		return qlxz;
	}

	public void setQlxz(List<Map<String, String>> qlxz) {
		this.qlxz = qlxz;
	}

	public List<Integer> getFclbjcsj() {
		return fclbjcsj;
	}

	public void setFclbjcsj(List<Integer> fclbjcsj) {
		this.fclbjcsj = fclbjcsj;
	}

	public List<Integer> getFclbjuncsj() {
		return fclbjuncsj;
	}

	public void setFclbjuncsj(List<Integer> fclbjuncsj) {
		this.fclbjuncsj = fclbjuncsj;
	}
		
}
