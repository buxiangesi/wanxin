package com.shggc.project.fwjk.dtxx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TJcFcGeoJson {
	private String type;
	//为了同时满足json数据格式以及JAVA命名规范
	@JsonProperty("GUID")
	private String guid;
	@JsonProperty("OldGUID")
	private String oldGuid;
	
	private FcGeometry geometry;
	

	public FcGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(FcGeometry geometry) {
		this.geometry = geometry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getOldGuid() {
		return oldGuid;
	}

	public void setOldGuid(String oldGuid) {
		this.oldGuid = oldGuid;
	}

	
}
