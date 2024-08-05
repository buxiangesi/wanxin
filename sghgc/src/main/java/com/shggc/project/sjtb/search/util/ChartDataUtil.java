package com.shggc.project.sjtb.search.util;

import java.util.ArrayList;
import java.util.List;

import com.shggc.project.sjtb.search.domain.DtoFwtjlb;

public class ChartDataUtil {
	public List <String> getFwtjlbmcs(List <DtoFwtjlb> fwtjlbs){
		List <String> mcs = new ArrayList<String>();
		for(DtoFwtjlb lb: fwtjlbs) {
			mcs.add( lb.getMc() );
		}
		return mcs;
	}
}
