package com.shggc.project.fwjk.dtxx.domain;

import java.awt.List;
/**
 * 
 * @author 
 * 为符合json数据格式创建的类
 *
 */
public class TJcFcxxJson {
	private String type;
	private FcProperties properties;
	private FcGeometry geometry;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public FcProperties getProperties() {
		return properties;
	}
	public void setProperties(FcProperties properties) {
		this.properties = properties;
	}
	public FcGeometry getGeometry() {
		return geometry;
	}
	public void setGeometry(FcGeometry geometry) {
		this.geometry = geometry;
	}
	
}
