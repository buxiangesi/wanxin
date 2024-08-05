package com.shggc.project.fwjk.main.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shggc.project.system.datasql.mapper.SysDataSqlMapper;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.project.fwjk.main.domain.DtoChartData;
import com.shggc.project.fwjk.main.domain.DtoMainData;
import com.shggc.project.fwjk.main.domain.Fcxxd;
import com.shggc.project.fwjk.main.domain.Htxx;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
	
	  @Autowired
	  private SysDataSqlMapper dataSqlMapper;
	  //List<Map<String,Object>> mydata = dataSqlMapper.dynamicsSelect(sql);
	@GetMapping(value = "/getMainData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DtoMainData getMainData() {
		DtoMainData dmd = new DtoMainData();
		String sql1 = "select count(*) as jdzs from t_jc_pq_jd";
		List<Map<String,Object>> mydata1 = dataSqlMapper.dynamicsSelect(sql1);
		dmd.setJdzs( ((BigDecimal)mydata1.get(0).get("JDZS")).intValue() );
		
		String sql2 = "select count(*) as hs from t_jc_fc";
		List<Map<String,Object>> mydata2 = dataSqlMapper.dynamicsSelect(sql2);
		dmd.setHs( ((BigDecimal)mydata2.get(0).get("HS")).intValue() );
		
		String sql3 = "select sum(fwjs) as fjsl from T_JC_FC_FW";
		List<Map<String,Object>> mydata3 = dataSqlMapper.dynamicsSelect(sql3);
		dmd.setFjsl( ((BigDecimal)mydata3.get(0).get("FJSL")).doubleValue() );
		
		String sql4 = "select sum(jzmj) as jzmj from t_jc_fc_fw";
		List<Map<String,Object>> mydata4 = dataSqlMapper.dynamicsSelect(sql4);
		dmd.setJzmj( ((BigDecimal)mydata4.get(0).get("JZMJ")).doubleValue() );
		
		String sql5 = "select distinct count(jzr) as jzrzs from t_jc_fc";
		List<Map<String,Object>> mydata5 = dataSqlMapper.dynamicsSelect(sql5);
		dmd.setJzrzs( ((BigDecimal)mydata5.get(0).get("JZRZS")).intValue() );
		
		String sql6 = "select sum(tdsymj) as tdmj from t_jc_fc";
		List<Map<String,Object>> mydata6 = dataSqlMapper.dynamicsSelect(sql6);
		dmd.setTdmj( ((BigDecimal)mydata6.get(0).get("TDMJ")).doubleValue() );
		
		/**查询房产信息点*/
		String sql7 = "select jdmc,cqr,jzr,fwcqz from t_jc_pq_jd jd, t_jc_fc fc where jd.id = fc.jdid and rownum < 11";
		List<Map<String,Object>> mydata7 = dataSqlMapper.dynamicsSelect(sql7);
		List <Fcxxd> fcxxds = new ArrayList<Fcxxd>();
		for(Map<String,Object> m : mydata7) {
			Fcxxd fcxxd = new Fcxxd();
			fcxxd.setSzjd((String)m.get("JDMC"));
			fcxxd.setCqr((String)m.get("CQR"));
			fcxxd.setJzr((String)m.get("JZR"));
			fcxxd.setFwcqz((String)m.get("FWCQZ"));
			fcxxds.add(fcxxd);
		}
		dmd.setFcxxds(fcxxds);
		
		/**查询胡同名称，房产数量和土地面积*/
		String sql8 = "select x.htmc as htmc , cqs , fwmj ,tdmj "
				+ "from (select jdmc as htmc , count(fwcqz) as cqs,sum(tdsymj) as tdmj "
				+ "from (select * from t_jc_pq_jd jd, t_jc_fc fc where jd.id=fc.jdid) jdfc "
				+ "group by jdmc) x, "
				+ "(select jdmc as htmc, sum(jzmj) as fwmj "
				+ "from (select * from t_jc_pq_jd jd, t_jc_fc fc, t_jc_fc_fw fw where jd.id=fc.jdid and fc.id=fw.fcid) jdfcfw "
				+ "group by jdmc) y "
				+ "where x.htmc=y.htmc";
		List<Map<String,Object>> mydata8 = dataSqlMapper.dynamicsSelect(sql8);
		List <Htxx> htxxs = new ArrayList<Htxx>();
		for(Map<String,Object> m : mydata8) {
			Htxx htxx = new Htxx();
			BigDecimal cqs = (BigDecimal)m.get("CQS");
			BigDecimal fwmj = (BigDecimal)m.get("FWMJ");
			BigDecimal tdmj = (BigDecimal)m.get("TDMJ");
			htxx.setHtmc((String)m.get("HTMC"));
			if(null != cqs)htxx.setCqs( cqs.intValue() );
			if(null != fwmj)htxx.setFwmj(fwmj.doubleValue());
			if(null != tdmj)htxx.setTdmj(tdmj.doubleValue());
			htxxs.add(htxx);
		}
		dmd.setHtxxs(htxxs);
		return dmd;
	}
	
	@GetMapping(value = "/getChartData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DtoChartData getCharData() {
		DtoChartData dcd = new DtoChartData();
		
		/**房产分布统计*/
		String fcfbSql ="select pqmc as PQMC,count(fcid) as fcsl from "
				+ "(select pq.pqmc as pqmc,fc.id as fcid from t_jc_pq pq, t_jc_pq_jd jd, t_jc_fc fc "
				+ "where pq.id=jd.pqid and jd.id=fc.jdid) group by pqmc";
		List<Map<String,Object>> fcfbData = dataSqlMapper.dynamicsSelect(fcfbSql);
		List<Map<String,String>> fcfbtj = new ArrayList<Map<String,String>>();
		for( Map<String,Object> m : fcfbData) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("value", ((BigDecimal)m.get("FCSL")).intValue()+" ");
			item.put("name", (String)m.get("PQMC"));
			fcfbtj.add(item);
		}
		dcd.setFcfbtj(fcfbtj);
		
		/**胡同排行前十*/
		String htphSql ="select * from"
		+ "	(select jd.jdmc as jdmc, count(fc.id) as jdsl from t_jc_pq_jd jd, t_jc_fc fc "
		+ " where jd.id=fc.jdid"
		+ "	group by jd.jdmc "
		+ "order by jdsl desc) "
		+ "where rownum < 11";
		List<Map<String,Object>> htphData = dataSqlMapper.dynamicsSelect(htphSql);
		List <String> htphmc = new ArrayList<String>();
		List <Integer> htphsj = new ArrayList<Integer>();
		for( Map<String,Object> m : htphData) {
			htphmc.add((String)m.get("JDMC"));
			htphsj.add(((BigDecimal)m.get("JDSL")).intValue());
		}
		dcd.setHtphmc(htphmc);
		dcd.setHtphsj(htphsj);
		
		/**房产类别统计*/
		List<Map<String,Object>> fclbData = dataSqlMapper.dynamicsSelect( fclbtjSql(0) );
		List <String> fclbmc = new ArrayList<String>();
		List <Integer> fclbscsj = new ArrayList<Integer>();
		List <Integer> fclbgcsj = new ArrayList<Integer>();
		List <Integer> fclblcsj = new ArrayList<Integer>();
		List <Integer> fclbjcsj = new ArrayList<Integer>();
		List <Integer> fclbjuncsj = new ArrayList<Integer>();
		
		for( Map<String,Object> m : fclbData) {
			fclbmc.add((String)m.get("PQMC"));
			fclbscsj.add(((BigDecimal)m.get("FCSL")).intValue());
		}
		
		fclbData = dataSqlMapper.dynamicsSelect( fclbtjSql(1) );
		for( Map<String,Object> m : fclbData) {
			fclblcsj.add(((BigDecimal)m.get("FCSL")).intValue());
		}
		
		fclbData = dataSqlMapper.dynamicsSelect( fclbtjSql(2) );
		for( Map<String,Object> m : fclbData) {
			fclbgcsj.add(((BigDecimal)m.get("FCSL")).intValue());
		}
		
		fclbData = dataSqlMapper.dynamicsSelect( fclbtjSql(3) );
		for( Map<String,Object> m : fclbData) {
			fclbjcsj.add(((BigDecimal)m.get("FCSL")).intValue());
		}
		
		fclbData = dataSqlMapper.dynamicsSelect( fclbtjSql(4) );
		for( Map<String,Object> m : fclbData) {
			fclbjuncsj.add(((BigDecimal)m.get("FCSL")).intValue());
		}
		dcd.setFclbmc(fclbmc);
		dcd.setFclbscsj(fclbscsj);
		dcd.setFclbgcsj(fclbgcsj);
		dcd.setFclblcsj(fclblcsj);
		dcd.setFclbjcsj(fclbjcsj);
		dcd.setFclbjuncsj(fclbjuncsj);
		
		/**权力类型*/
		String qllxSql = "select decode(qllx, '0', '划拨','1','出让') as qllx, count(id) as fcsl from t_jc_fc group by qllx";
		List<Map<String,Object>> qllxData = dataSqlMapper.dynamicsSelect(qllxSql);
		List<Map<String,String>> qllx = new ArrayList<Map<String,String>>();
		for( Map<String,Object> m : qllxData) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("value", ((BigDecimal)m.get("FCSL")).intValue()+" ");
			item.put("name", (String)m.get("QLLX"));
			qllx.add(item);
		}
		dcd.setQllx(qllx);
		
		/**权力性质*/
		String qlxzSql = "select decode(qlxz, '0', '国有','1','集体') as qlxz, count(id) as fcsl from t_jc_fc group by qlxz";
		List<Map<String,Object>> qlxzData = dataSqlMapper.dynamicsSelect(qlxzSql);
		List <String> qlxzmc = new ArrayList<String>();
		List<Map<String,String>> qlxz = new ArrayList<Map<String,String>>();
		for( Map<String,Object> m : qlxzData) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("value", ((BigDecimal)m.get("FCSL")).intValue()+" ");
			item.put("name", (String)m.get("QLXZ"));
			qlxzmc.add((String)m.get("QLXZ"));
			qlxz.add(item);
		}
		dcd.setQlxzmc(qlxzmc);
		dcd.setQlxz(qlxz);
		return dcd;
	}
	
	private String  fclbtjSql(int lb) {
		return "select pqmc, count(fcid) as fcsl from "
				+ "(select x.pqmc as pqmc, y.fcid as fcid from "
				+ "(select distinct pqmc from t_jc_pq) x "
				+ "left join "
				+ "(select pq.pqmc as pqmc,fc.id as fcid from t_jc_pq pq, t_jc_pq_jd jd, t_jc_fc fc "
				+ "where pq.id=jd.pqid and jd.id=fc.jdid and fc.fwcb="+ lb + ") y "
				+ "on x.pqmc = y.pqmc "
				+ ") group by pqmc "
				+ "order by pqmc";
	}
}
