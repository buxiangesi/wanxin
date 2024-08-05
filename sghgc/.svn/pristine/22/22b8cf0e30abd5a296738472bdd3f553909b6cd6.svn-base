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
public class SfjnYhjfxxService {

    @Autowired
    SqlUtil sqlUtil;

    public JSONObject GetYhjfxx(Map<String, String> parameters) {

        String ls_khid = parameters.get("khid");
        String ls_code="0";
        String ls_message = "";
        //主表信息sql
        String ls_sql_main = "select khid, yhbh, yhmc, yhdz, lxdh, qfje\n" +
                "\n" +
                "  from (select a.id as khid,\n" +
                "               a.yhbh as yhbh,\n" +
                "               a.hzxm as yhmc,\n" +
                "               c.xqmc || b.dh || '栋' || b.dyh || '单元' || b.fh || '号' as yhdz,\n" +
                "               a.lxdh_sj as lxdh,\n" +
                "               (select nvl(sum(e.qjzje), 0)\n" +
                "                  from t_yw_fyxx e\n" +
                "                 where e.khid = a.id\n" +
                "                   and e.fylx < '2'\n" +
                "                   and e.qjzje > 0) as qfje\n" +
                "          from t_jc_khxx_jm a, t_jc_xq_fwxx b, t_jc_xq c, t_jc_ysxz d\n" +
                "         where a.fwid = b.id\n" +
                "           and b.xqid = c.id\n" +
                "           and a.ysxzid = d.id\n" +
                "        union all\n" +
                "        select a.id as khid,\n" +
                "               a.yhbh as yhbh,\n" +
                "               a.dwmc as yhmc,\n" +
                "               a.dwdz as yhdz,\n" +
                "               a.lxdh_sj as lxdh,\n" +
                "               (select nvl(sum(e.qjzje), 0)\n" +
                "                  from t_yw_fyxx e\n" +
                "                 where e.khid = a.id\n" +
                "                   and e.fylx < '2'\n" +
                "                   and e.qjzje > 0) as qfje\n" +
                "          from t_jc_khxx_sy a, t_jc_xq c, t_jc_ysxz d\n" +
                "         where a.ssxqid = c.id\n" +
                "           and a.ysxzid = d.id)\n" +
                " where khid = #{khid}";

        //欠费明细表sql
        String ls_sql_child = "select a.id as fyid,\n" +
                "       a.jfywn || a.jfywy as ywny,\n" +
                "       a.yssl,\n" +
                "       a.yjje as ysje,\n" +
                "       a.qjzje as qfje\n" +
                "  from t_yw_fyxx a, t_jc_ysxz d\n" +
                " where a.ysxzid = d.id\n" +
                "    and a.fylx < '2'\n" +
                "   and a.khid = #{khid}\n" +
                "   and nvl(a.qjzje,0) > 0\n" +
                " order by ywny";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        JSONArray jsonArray_main = new JSONArray();
        JSONArray jsonArray_child = new JSONArray();
        try{
	        //======================================================================查询第一个sql
	        params.put("sql", ls_sql_main);                                          //将sql语句设置到map
	        params.put("khid", ls_khid);                                             //将openid设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("yhxx", jsonArray_main);                                  //用户信息放到mian标签
	
	        //======================================================================查询第二个sql
	        params.put("sql", ls_sql_child);                                         //将sql语句设置到map
	        params.put("khid", ls_khid);                                             //将openid设置到map
	        List<Map<String, Object>> list_child = sqlUtil.preparedSelectMap(params);//查询用户信息
	        String jsonStr_child = JSON.toJSONString(list_child);                    //map转json字符串
	        jsonArray_child = JSONArray.parseArray(jsonStr_child);         //json字符串转json数组
	        jsonReturn.put("qfjl", jsonArray_child);                                //用户信息放到data标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
}
