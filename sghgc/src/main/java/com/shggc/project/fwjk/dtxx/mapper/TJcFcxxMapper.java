package com.shggc.project.fwjk.dtxx.mapper;

import java.util.List;

import com.shggc.project.fwjk.dtxx.domain.TJcFcxx;



public interface TJcFcxxMapper {
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
	 
	 public TJcFcxx selectTJcFcxxByBh(String bh);
}
