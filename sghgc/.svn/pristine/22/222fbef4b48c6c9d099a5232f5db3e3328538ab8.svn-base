package com.shggc.project.sjtb.search.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.project.sjtb.search.domain.DtoFormWz;
import com.shggc.project.sjtb.search.domain.DtoFormWzChildren;
import com.shggc.project.sjtb.search.domain.DtoSearchChartData;
import com.shggc.project.sjtb.search.domain.SearchParm;
import com.shggc.project.sjtb.search.mapper.SearchMapper;
import com.shggc.project.sjtb.search.util.WzUtil;




/**
 * 房产信息Controller
 *
 * @author ruoyi
 * @date 2022-07-27
 */
@Controller
@RequestMapping("/sjtb/search")
public class SearchController extends BaseController
{
	@Autowired
	SearchMapper searchMapper;
	
    @GetMapping()
    public String search()
    {
        return "sjtb/search/search";
    }
    
    @GetMapping(value = "/getFormWz", produces = "application/json;charset=UTF-8")
	@ResponseBody
    public List <DtoFormWz> getFormWz() {
    	List <DtoFormWzChildren> dfwcs = searchMapper.selectDtoFormWzChildrenList();
    	DtoFormWz dfw = new DtoFormWz();
    	dfw.setTitle("山海关古城");
    	dfw.setId("1");
    	dfw.setChecked(true);
    	dfw.setChildren(dfwcs);
    	List <DtoFormWz> dfws = new ArrayList<DtoFormWz>();
    	dfws.add(dfw);
    	return dfws;
    }
    
    @PostMapping(value = "/getTbData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DtoSearchChartData getTbData(@RequestBody SearchParm sp) {
    	//System.out.println(sp);
    	/*提取参数*/
    	List <DtoFormWz> wzs = sp.getFormWz();
    	List <String> jds = new LinkedList<String>();
    	for(DtoFormWz wz : wzs) {
    		jds.addAll( WzUtil.getAllChildId(wz) );
    	}
    	String xz = sp.getFormXz();
    	String lx = sp.getFormLx();
    	String cb = sp.getFormCb();
    	DtoSearchChartData dscd = new DtoSearchChartData();
    	
    	/*设置房产类别统计数据*/
    	List <String> fwtjlbmc = new ArrayList<String>();
    	List <String> fwtjlbsj = new ArrayList<String>();
    	List<HashMap<String, Object>> fwtjlbs=  searchMapper.selectFwtjlb(jds, xz, lx, cb);
    	for(HashMap<String, Object> fwtjlb : fwtjlbs) {
    		String fclb = (String)fwtjlb.get("FWCB");
    		if(fclb != null) {
    			fwtjlbmc.add(fclb);
    			fwtjlbsj.add(((BigDecimal)fwtjlb.get("FCSL")).intValue()+"");
    		}
    	}
    	dscd.setFwtjlbmc(fwtjlbmc);
    	dscd.setFwtjlbsj(fwtjlbsj);
    	
    	/*设置建筑面积数据*/
    	List <String> jzmj = new ArrayList<String>();
    	Double mj = searchMapper.selectJzmj(jds, xz, lx, cb);
    	if(null == mj)mj=0.0;
    	jzmj.add(mj.toString());
    	dscd.setJzmj(jzmj);
    	
    	/*设置权力类型*/
    	List<HashMap<String, Object>> qllxData = searchMapper.selectQllx(jds, xz, lx, cb);
		List<Map<String,String>> qllx = new ArrayList<Map<String,String>>();
		for( Map<String,Object> m : qllxData) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("value", ((BigDecimal)m.get("FCSL")).intValue()+" ");
			item.put("name", (String)m.get("QLLX"));
			qllx.add(item);
		}
		dscd.setQllx(qllx);
		
		/*设置房产数、产权人数、居住人数、房屋间数*/
		Double fczs = searchMapper.selectFcs(null, "", "", "");
		Double cqrzs = searchMapper.selectCqrs(null, "", "", "");
		Double jzrzs = searchMapper.selectJzrs(null, "", "", "");
		Double fjzs = searchMapper.selectFjs(null, "", "", "");
		Double fcs = searchMapper.selectFcs(jds, xz, lx, cb);
		fcs = (null == fcs) ? 0.0 : fcs;
		Double cqrs = searchMapper.selectCqrs(jds, xz, lx, cb);
		cqrs = (null == cqrs) ? 0.0 : cqrs;
		Double jzrs = searchMapper.selectJzrs(jds, xz, lx, cb);
		jzrs = (null == jzrs) ? 0.0 : jzrs;
		Double fjs = searchMapper.selectFjs(jds, xz, lx, cb);
		fjs= (null == fjs) ? 0.0 : fjs;
		List <Double> visits = new ArrayList<Double>();
		List <Integer> cost = new ArrayList<Integer>();
		visits.add(fczs);
		visits.add(cqrzs);
		visits.add(jzrzs);
		visits.add(fjzs);
		cost.add((int)(fcs*100/fczs));
		cost.add((int)(cqrs*100/cqrzs));
		cost.add((int)(jzrs*100/jzrzs));
		cost.add((int)(fjs*100/fjzs));
		dscd.setVisits(visits);
		dscd.setCost(cost);
		
		/*设置土地使用面积数据*/
		List <String> tdsymj = new ArrayList<String>();
		Double tmj = searchMapper.selectTdsymj(jds, xz, lx, cb);
		tmj = (null == tmj) ? 0.0 : tmj;
		tdsymj.add(tmj.toString());
    	dscd.setTdsymj(tdsymj);
    	
    	/*设置权力性质*/
    	List<HashMap<String, Object>> qlxzData = searchMapper.selectQlxz(jds, xz, lx, cb);
		List<Map<String,String>> qlxz = new ArrayList<Map<String,String>>();
		for( Map<String,Object> m : qlxzData) {
			HashMap<String, String> item = new HashMap<String, String>();
			String title = (String)m.get("QLXZ");
			if(title != null) {
				item.put("value", ((BigDecimal)m.get("FCSL")).intValue()+" ");
				item.put("name", title);
				qlxz.add(item);
			}
			
		}
		dscd.setQlxz(qlxz);
    	
    	return dscd;
    }
    
}
