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

//openid、年度（yyyy）
@Service
public class WdzdJfjlService {

    @Autowired
    SqlUtil sqlUtil;

    public JSONObject GetJfjl(Map<String, String> parameters) {

    	String ls_openid = parameters.get("openid");
    	String ls_nd = parameters.get("nd");
    	String ls_code="0";
        String ls_message = "";
        //1、查询缴费记录：交易ID、客户ID、用户编号、用户名称、用户地址、缴费日期、缴费金额、缴费方式
        String ls_sql_jfjl = "select distinct d.id as jyid,"
        		+ "    a.khid,"
        		+ "    a.yhbh,"
        		+ "    a.yhmc,"
        		+ "    a.yhdz,"
        		+ "    d.jyq as jfrq,"
        		+ "    d.jyje as jfje,"
        		+ "    decode(d.jffs,'0','现金','1','POS','2','微信','3','支付宝','4','对公网银','5','入户','其他') AS JFFS"
        		+ " from (select a.id as khid,"
        		+ "              a.yhbh as yhbh,"
        		+ "              a.hzxm as yhmc,"
        		+ "              e.xqmc || f.dh || '栋' || f.dyh || '单元' || f.fh || '号' as yhdz"
        		+ "         from t_jc_khxx_jm a, t_jc_xq e, t_jc_xq_fwxx f"
        		+ "         where e.id = f.xqid"
        		+ "         and f.id = a.fwid"
        		+ "      union all"
        		+ "        select a.id, a.yhbh as yhbh, a.dwmc as yhmc, a.DWDZ as yhdz"
        		+ "        from t_jc_khxx_sy a) a,"
        		+ "    t_yw_fyxx b,"
        		+ "    t_yw_fyxx_jfmx c,"
        		+ "    t_yw_fyxx_jfmx_jyjl d"
        		+ " where a.khid = b.khid"
        		+ "   and b.id = c.fyid"
        		+ "   and c.jyid = d.id"
        		+ "   and to_char(d.jyq, 'yyyy') = #{nd}"
        		+ "   and exists (select 1"
        		+ "      from t_wx_gzhyh_bd m"
        		+ "      where m.khid = a.khid"
        		+ "        and m.bdbz = '1'"
        		+ "        and m.openid = #{openid})"
        		+ " order by d.jyq desc";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        JSONArray jsonArray_jfjl = new JSONArray();
        try{
	        //======================================================================查询第一个sql
	        params.put("sql", ls_sql_jfjl);                                          //将sql语句设置到map
	        params.put("openid", ls_openid);                                             //将openid设置到map
	        params.put("nd", ls_nd);
	        List<Map<String, Object>> list_jfjl = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_jfjl = JSON.toJSONString(list_jfjl);                      //map转json字符串
	        jsonArray_jfjl = JSONArray.parseArray(jsonStr_jfjl);           //json字符串转json数组
	        jsonReturn.put("jfjl", jsonArray_jfjl);                                  //用户信息放到jfjl标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
         return jsonReturn;
    }
}
