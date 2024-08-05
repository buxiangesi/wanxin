package com.shggc.project.fwjk.wxzf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shggc.common.exception.BusinessException;
import com.shggc.project.youbeisoft.base.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//按fyid查询信息
@Service
public class SfjnYsxqlService {

    @Autowired
    SqlUtil sqlUtil;

    public JSONObject GetYsxq(Map<String, String> parameters) {

    	String ls_fyid = parameters.get("fyid");
    	String ls_code="0";
        String ls_message = "";
        //1、用户信息（单记录）：用户编号、用户名称、用户地址、用水性质
        String ls_sql_yhxx = "select m.khid, m.yhbh, m.yhmc, m.yhdz, k.ysxzmc as ysxz"+
                             " from (select a.id as khid,"+
			                              " a.yhbh as yhbh,"+
			                              " a.hzxm as yhmc,"+
			                              " c.xqmc || b.dh || '栋' || b.dyh || '单元' || b.fh || '号' as yhdz,"+
			                              " a.lxdh_sj as lxdh,"+
			                              " (select nvl(sum(e.qjzje), 0)"+
			                                  " from t_yw_fyxx e"+
			                                  " where e.khid = a.id"+
			                                     " and e.fylx < '2'" +
			                                     " and e.qjzje > 0) as qfzje"+
			                        " from t_jc_khxx_jm a, t_jc_xq_fwxx b, t_jc_xq c, t_jc_ysxz d"+
			                        " where a.fwid = b.id"+
			                           " and b.xqid = c.id"+
			                           " and a.ysxzid = d.id"+
			                  " union all"+
		                      " select a.id as khid,"+
		                             " a.yhbh as yhbh,"+
		                             " a.dwmc as yhmc,"+
		                             " a.dwdz as yhdz,"+
		                             " a.lxdh_sj as lxdh,"+
		                             " (select nvl(sum(e.qjzje), 0)"+
		                                " from t_yw_fyxx e"+
		                                " where e.khid = a.id"+
		                                "   and e.fylx < '2'" +
		                                "   and e.qjzje > 0) as qfzje"+
		                          " from t_jc_khxx_sy a, t_jc_xq c, t_jc_ysxz d"+
		                         " where a.ssxqid = c.id"+
		                            " and a.ysxzid = d.id) m,"+
		                     " t_yw_fyxx n,"+
		                     " t_jc_ysxz k"+
		               " where n.khid = m.khid"+
		                 " and k.id = n.ysxzid"+
		                 " and n.id = #{fyid}";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        JSONArray jsonArray_yhxx = new JSONArray();
        JSONArray jsonArray_yslfy = new JSONArray();
        JSONArray jsonArray_ysqk = new JSONArray();
        JSONArray jsonArray_hzysqk = new JSONArray();
        try {
	        //======================================================================查询第一个sql
	        params.put("sql", ls_sql_yhxx);                                          //将sql语句设置到map
	        params.put("fyid", ls_fyid);                                             //将openid设置到map
	        List<Map<String, Object>> list_yhxx = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_yhxx = JSON.toJSONString(list_yhxx);                      //map转json字符串
	        jsonArray_yhxx = JSONArray.parseArray(jsonStr_yhxx);           //json字符串转json数组
	        jsonReturn.put("yhxx", jsonArray_yhxx);                                  //用户信息放到yhxx标签
	        
	        //2、总用水量和总费用（单记录）：业务年月、用水量、用水金额、欠费金额
	        String ls_sql_yslfy = "select a.id as fyid,"
				        		+ " a.jfywn || a.jfywy as ywny,"
				        		+ " a.yssl,"
				        		+ " a.Yjje as ysje,"
				        		+ " a.qjzje as qfje"
				        		+ " from t_yw_fyxx a"
				        		+ " where a.id = #{fyid}";
	        //======================================================================查询第二个sql
	        params.put("sql", ls_sql_yslfy);                                         //将sql语句设置到map
	        params.put("fyid", ls_fyid);                                             //将openid设置到map
	        List<Map<String, Object>> list_yslfy = sqlUtil.preparedSelectMap(params);//查询用户信息
	        String jsonStr_yslfy = JSON.toJSONString(list_yslfy);                    //map转json字符串
	        jsonArray_yslfy = JSONArray.parseArray(jsonStr_yslfy);         //json字符串转json数组
	        jsonReturn.put("yslfy", jsonArray_yslfy);                                //用户信息放到yslfy标签
	        
	        //3、水表用水情况（多记录）：水表编号、期初读数、期末读数、用水数量、本次抄表日期、上期抄表日期、用水说明
	        String ls_sql_ysqk = "select c.sbbh,"
	        		+ "       b.syds as qcds,"
	        		+ "       b.byds as qmds,"
	        		+ "       b.yssl,"
	        		+ "       b.cbrq as bqcbrq,"
	        		+ "       b.sqcbrq,"
	        		+ "       decode(b.sfkndys,'1','跨年度用水，归属' || b.sqywn || '年' || to_char(b.sqywnyssl) || '吨，' || '归属' ||"
	        		+ "              b.dQYWN || '年' || to_char(b.dqywnyssl) || '吨',"
	        		+ "              '') yssm"
	        		+ "  from t_yw_fyxx a, t_yw_ysxx_fick b, T_JC_KHXX_JM_SY_SBXX c"
	        		+ " where a.id = b.fyid"
	        		+ "   and b.sbid = c.id"
	        		+ "   and b.fyid = #{fyid}"
	        		+ " order by c.sbbh";
	        //======================================================================查询第二个sql
	        params.put("sql", ls_sql_ysqk);                                         //将sql语句设置到map
	        params.put("fyid", ls_fyid);                                             //将openid设置到map
	        List<Map<String, Object>> list_ysqk = sqlUtil.preparedSelectMap(params);//查询用户信息
	        String jsonStr_ysqk = JSON.toJSONString(list_ysqk);                    //map转json字符串
	        jsonArray_ysqk = JSONArray.parseArray(jsonStr_ysqk);         //json字符串转json数组
	        jsonReturn.put("ysqk", jsonArray_ysqk);                                //用户信息放到ysqk标签
	        //4、按户汇总用水情况（多记录）：用水年度、阶梯类型、用水数量、用水单价、用水金额、备注
	        String ls_sql_hzysqk = "select b.YSYWN as ysnd,"
	        		+ " decode(b.SFJTSJ,'1','第' || to_char(b.jtz) || '阶梯','0','非阶梯水价') as jtlx,"
	        		+ " b.yssl,"
	        		+ " b.zdj as ysdj,"
	        		+ " b.zdjje as ysje,"
	        		+ " nvl(b.bz, '') || decode(b.sfjtsj,'1',',家庭户人数' || to_char(b.jtrksl) || '第' || to_char(b.jtz) ||'阶梯（' ||decode(b.jtz,3,to_char(b.jt_qssl) || '以上',"
	        		+ " to_char(b.jt_qssl) || '-' || to_char(b.jt_jzsl)) || ')','0','') as bz "
	        		+ " from t_yw_fyxx a, t_yw_fyxx_ysmx b"
	        		+ " where a.id = b.fyid"
	        		+ " and b.fyid = #{fyid}"
	        		+ " order by b.YSYWN, b.jtz";
	        //======================================================================查询第二个sql
	        params.put("sql", ls_sql_hzysqk);                                         //将sql语句设置到map
	        params.put("fyid", ls_fyid);                                             //将openid设置到map
	        List<Map<String, Object>> list_hzysqk = sqlUtil.preparedSelectMap(params);//查询用户信息
	        String jsonStr_hzysqk = JSON.toJSONString(list_hzysqk);                    //map转json字符串
	        jsonArray_hzysqk = JSONArray.parseArray(jsonStr_hzysqk);         //json字符串转json数组
	        jsonReturn.put("hzysqk", jsonArray_hzysqk);                                //用户信息放到hzysqk标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
}
