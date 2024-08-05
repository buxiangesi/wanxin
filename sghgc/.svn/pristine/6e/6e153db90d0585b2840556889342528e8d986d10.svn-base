package com.shggc.project.fwjk.wxzf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shggc.project.youbeisoft.base.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//查询已绑定用户
@Service
public class SfjnYbdyhService {

    @Autowired
    SqlUtil sqlUtil;
    public JSONObject GetYbdyhxx(Map<String, String> parameters) {
        
        String ls_openid=parameters.get("openid");
        String ls_code="0";
        String ls_message = "";
        String ls_sql="select khid, yhbh, yhmc, lxdh, yhdz, qfje, '' as bz\n" +
                "  from (select b.openid,\n" +
                "               a.id as khid,\n" +
                "               a.yhbh as yhbh,\n" +
                "               a.hzxm as yhmc,\n" +
                "               e.xqmc || f.dh || '栋' || f.dyh || '单元' || f.fh || '号' as yhdz,\n" +
                "               a.lxdh_sj as lxdh,\n" +
                "               (select nvl(sum(d.qjzje), 0)\n" +
                "                  from t_yw_fyxx d\n" +
                "                 where d.khid = a.id\n" +
                "                   and d.fylx < '2'\n" +
                "                   and d.qjzje > 0) as qfje,\n" +
                "               c.bdbz\n" +
                "          from t_jc_khxx_jm  a,\n" +
                "               t_wx_gzhyh    b,\n" +
                "               t_wx_gzhyh_bd c,\n" +
                "               t_jc_xq       e,\n" +
                "               t_jc_xq_fwxx  f\n" +
                "         where a.id = c.khid\n" +
                "           and b.openid = c.openid\n" +
                "           and e.id = f.xqid\n" +
                "           and f.id = a.fwid\n" +
                "        union all\n" +
                "        select b.openid,\n" +
                "               a.id as khid,\n" +
                "               a.yhbh as yhbh,\n" +
                "               a.dwmc as yhmc,\n" +
                "               a.DWDZ as yhdz,\n" +
                "               a.lxdh_sj as lxdh,\n" +
                "               (select nvl(sum(d.qjzje), 0)\n" +
                "                  from t_yw_fyxx d\n" +
                "                 where d.khid = a.id\n" +
                "                   and d.fylx < '2'\n" +
                "                   and d.qjzje > 0) as qfje,\n" +
                "               c.bdbz\n" +
                "          from t_jc_khxx_sy a, t_wx_gzhyh b, t_wx_gzhyh_bd c\n" +
                "         where a.id = c.khid\n" +
                "           and b.openid = c.openid)\n" +
                " where openid = #{openid}" +
                "   and bdbz = '1'\n" +
                " order by yhbh";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        JSONArray jsonArray = new JSONArray();
        try{
	        params.put("sql", ls_sql);               //将sql语句设置到map
	        params.put("openid", ls_openid);         //将openid设置到map
	        List<Map<String, Object>> list_info = sqlUtil.preparedSelectMap(params);//执行查询
	
	        String jsonStr = JSON.toJSONString(list_info);        //map转json字符串
	        jsonArray = JSONArray.parseArray( jsonStr );//json字符串转json数组
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("data",jsonArray);                     //将json数组放到data标签(只有一个标签则用data标签)
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;

    }

}
