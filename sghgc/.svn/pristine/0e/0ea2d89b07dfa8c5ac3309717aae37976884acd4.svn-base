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

//交易id
@Service
public class WdzdJfmxService {

    @Autowired
    SqlUtil sqlUtil;

    public JSONObject GetJfmx(Map<String, String> parameters) {

    	String ls_jyid = parameters.get("jyid");
    	String ls_code="0";
        String ls_message = "";
        //1、查询缴费明细：费用ID、业务年月、用水数量、应缴金额、实缴金额
        String ls_sql_jfmx = "select d.yhbh,d.hzxm,a.id as fyid,a.jfywn || a.jfywy as ywny, a.yssl, a.yjje, b.jfje as sjje"
        		+ " from t_yw_fyxx a, t_yw_fyxx_jfmx b, t_yw_fyxx_jfmx_jyjl c, "
        		+ " (select id,yhbh,hzxm from t_jc_khxx_jm union select id,yhbh,dwmc hzxm from t_jc_khxx_sy ) d "
        		+ " where a.id = b.fyid"
        		+ " and a.khid = d.id"
        		+ " and b.jyid = c.id"
        		+ " and c.id = #{jyid}"
        		+ " order by a.jfywn || a.jfywy desc";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        JSONArray jsonArray_jfmx = new JSONArray();
        try{
	        //======================================================================查询第一个sql
	        params.put("sql", ls_sql_jfmx);                                          //将sql语句设置到map
	        params.put("jyid", ls_jyid);                                             //将openid设置到map
	        List<Map<String, Object>> list_jfmx = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_jfmx = JSON.toJSONString(list_jfmx);                      //map转json字符串
	        jsonArray_jfmx = JSONArray.parseArray(jsonStr_jfmx);           //json字符串转json数组
	        jsonReturn.put("jfmx", jsonArray_jfmx);                                  //用户信息放到jfmx标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
         return jsonReturn;
    }
}
