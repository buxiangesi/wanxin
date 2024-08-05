package com.shggc.project.sjtb.search.util;

import java.util.LinkedList;
import java.util.List;

import com.shggc.project.sjtb.search.domain.DtoFormWz;
import com.shggc.project.sjtb.search.domain.DtoFormWzChildren;

public class WzUtil {
	public static List <String> getAllChildId(DtoFormWzChildren dfc){
		List <String> list = new LinkedList<String>();
		List <DtoFormWzChildren> children = dfc.getChildren();
		for(DtoFormWzChildren c : children) {
			list.add(c.getId());
		}
		return list;
	}

	public static List <String> getAllChildId(DtoFormWz dfw){
		 List <String> list = new LinkedList<String>();
		 List <DtoFormWzChildren> children = dfw.getChildren();
		 for(DtoFormWzChildren c : children) {
				list.addAll(  getAllChildId(c) );
			}
		 return list;
	}
}
