package com.shggc.project.sjtb.search.domain;

import java.util.LinkedList;
import java.util.List;

import com.shggc.framework.web.domain.BaseEntity;

public class DtoFormWz   extends BaseEntity{
	 private static final long serialVersionUID = 1L;
	 
	 private String title;
	 private String id;
	 private boolean checked;
	 private List <DtoFormWzChildren> children;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<DtoFormWzChildren> getChildren() {
		return children;
	}
	public void setChildren(List<DtoFormWzChildren> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "DtoFormWz [title=" + title + ", id=" + id + ", checked=" + checked + ", children=" + children + "]";
	}
	 
//	 public List <String> getAllChildTitle(){
//		 List <String> list = new LinkedList<String>();
//		 for(DtoFormWzChildren c : children) {
//				list.addAll(c.getAllChildTitle());
//			}
//		 return list;
//	 }
	 
}
