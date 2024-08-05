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

//按khid查询用户信息
@Service
public class YsfxNyyslService {

    @Autowired
    SqlUtil sqlUtil;
    //按照客户ID和年度查询月份用水量
    public JSONObject GetKhysl(Map<String, String> parameters) {

        String ls_code="0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//json返回值
        try{
	        String ls_khid = parameters.get("khid");
	        String nd = parameters.get("nd");
	        //主表信息sql
	        String ls_sql = 
	        		"select a.yhbh,a.yhmc,to_char(b.cbrq, 'yyyymm') as ny, sum(b.yssl) as yssl " +
	                		"from (select a.id as khid, " +
	                		"a.yhbh as yhbh, " +
	                		"a.hzxm as yhmc, " +
	                		"e.xqmc || f.dh || '栋' || f.dyh || '单元' || f.fh || '号' as yhdz " +
	                		"from t_jc_khxx_jm a, t_jc_xq e, t_jc_xq_fwxx f " +
	                		"where e.id = f.xqid " +
	                		"and f.id = a.fwid " +
	                		"union all " +
	                		"select a.id, a.yhbh as yhbh, a.dwmc as yhmc, a.DWDZ as yhdz " +
	                		"from t_jc_khxx_sy a) a, " +
	                		"t_yw_ysxx_fick b " +
	                		"where a.khid = b.khid " +
	                		"and b.sfycb = '1' " +
	                		"and b.khid = #{khid} " +
	                		"and to_char(b.cbrq, 'yyyy') = #{nd} " +
	                		"group by a.yhbh,a.yhmc,to_char(b.cbrq, 'yyyymm')" +
	                		" order by to_char(b.cbrq, 'yyyymm')";
	        Map<String, Object> params = new HashMap<String, Object>();
	
	        params.put("sql", ls_sql);                                          //将sql语句设置到map
	        params.put("khid", ls_khid);                                             //将openid设置到map
	        params.put("nd", nd);                                             //将openid设置到map
	        List<Map<String, Object>> list = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr = JSON.toJSONString(list);                      //map转json字符串
	        JSONArray jsonArray = JSONArray.parseArray(jsonStr);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
    
}
