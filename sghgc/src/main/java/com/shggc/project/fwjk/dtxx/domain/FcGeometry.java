package com.shggc.project.fwjk.dtxx.domain;
/**
 * 
 * @author 
 * 房产地理信息类，为组织json数据创建
 *
 */
public class FcGeometry {
	/**
	 * type: 当前默认值"Polygon"
	 */
	private String type;
	
	private int level[]= {19};
	
	/**
	 * coordinates：地理信息
	 */
	private double[][][] coordinates;
	
	public FcGeometry(String type, double[][][] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double[][][] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[][][] coordinates) {
		this.coordinates = coordinates;
	}
	public int[] getLevel() {
		return level;
	}
	public void setLevel(int[] level) {
		this.level = level;
	}
}
