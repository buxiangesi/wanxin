package com.shggc.project.fwjk.dtxx.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shggc.project.fwjk.dtxx.domain.TJcFcxx;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxxJson;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxxJsonList;
import com.shggc.project.fwjk.dtxx.mapper.TJcFcxxMapper;
import com.shggc.project.fwjk.dtxx.service.TJcFcxxService;
@Service
@Transactional(rollbackFor = Exception.class)
public class TJcFcxxServiceImpl implements TJcFcxxService {
	@Autowired
	private TJcFcxxMapper tJcFcxxMapper;
	@Override
	public List<TJcFcxx> selectTJcFcxxList(TJcFcxx tJcFcxx){
		
		return tJcFcxxMapper.selectTJcFcxxList(tJcFcxx);
	}
	
	@Override
	public int changeGeo(TJcFcxx tJcFcxx) {
		System.out.println("------------------------------------------");
		int x = tJcFcxxMapper.changeGeo(tJcFcxx);
		System.out.println("******************************************");
		return x;
	}
	
	@Override
	public TJcFcxx selectTJcFcxxByBh(String bh) {
		return tJcFcxxMapper.selectTJcFcxxByBh(bh);
	}
}
