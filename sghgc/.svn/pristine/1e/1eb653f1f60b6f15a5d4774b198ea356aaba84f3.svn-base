package com.shggc.project.fwjk.dtxx.service;

import java.util.List;

import com.shggc.project.fwjk.dtxx.domain.TJcFcxx;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxxJsonList;

public interface TJcFcxxService {
	/**
	 * 
	 * @param tJcFcxx
	 * @return 返回房产信息，以及与房产信息关联的房屋信息集合
	 */
	 public List<TJcFcxx> selectTJcFcxxList(TJcFcxx tJcFcxx);

	 /**
	  * 
	  * @param tJcFcxx
	  * 更新房产的kz1字段，也就是地理信息。更新与置空地理信息都使用该方法。
	  * @return
	  */
	 public int changeGeo(TJcFcxx tJcFcxx);
	 /**
	  * 通过房产编号查询房产信息
	  * @param bh  房产编号
	  * @return 房产信息
	  */
	 public TJcFcxx selectTJcFcxxByBh(String bh);
}
