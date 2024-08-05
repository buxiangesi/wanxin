package com.shggc.project.fwjk.dtxx.domain;

import java.util.List;
/**
 * 
 * @author 
 * 为符合json格式创建的类
 *
 */

public class TJcFcxxJsonList {
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<TJcFcxxJson> getFeatures() {
		return features;
	}
	public void setFeatures(List<TJcFcxxJson> features) {
		this.features = features;
	}
	private List<TJcFcxxJson> features;
	

}
