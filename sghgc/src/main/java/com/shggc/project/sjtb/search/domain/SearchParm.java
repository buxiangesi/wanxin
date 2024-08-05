package com.shggc.project.sjtb.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shggc.framework.web.domain.BaseEntity;

public class SearchParm extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private List<DtoFormWz> formWz;
	private String formXz;
	private String formLx;
	private String formCb;
	



	public List<DtoFormWz> getFormWz() {
		return formWz;
	}



	public void setFormWz(List<DtoFormWz> formWz) {
		this.formWz = formWz;
	}



	public String getFormXz() {
		return formXz;
	}



	public void setFormXz(String formXz) {
		this.formXz = formXz;
	}



	public String getFormLx() {
		return formLx;
	}



	public void setFormLx(String formLx) {
		this.formLx = formLx;
	}



	public String getFormCb() {
		return formCb;
	}



	public void setFormCb(String formCb) {
		this.formCb = formCb;
	}



	@Override
	public String toString() {
		return "SearchParm [formWz=" + formWz + ", formXz=" + formXz + ", formLx=" + formLx + ", formCb=" + formCb
				+ "]";
	}
	
}
