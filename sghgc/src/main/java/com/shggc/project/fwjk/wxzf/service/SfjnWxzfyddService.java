package com.shggc.project.fwjk.wxzf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shggc.project.youbeisoft.base.SqlUtil;
import com.shggc.project.youbeisoft.tools.HttpUtils;
import com.shggc.project.youbeisoft.tools.WechatUrlConfig;
import com.shggc.project.youbeisoft.tools.WeixinchatPayUtils;
import com.shggc.project.youbeisoft.tools.WxpayConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
//生成微信支付预订单（A0101004）

@Service
public class SfjnWxzfyddService {

    @Autowired
    SqlUtil sqlUtil;

    //1.生成微信支付预订单
    public JSONObject Wxzfydd(Map<String, String> parameters) {

        String ls_code = "0";
        String ls_message = "";
        JSONObject jsonReturn= new JSONObject();//接口返回值
        jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
        jsonReturn.put("message", ls_message);

        try {
        	//获取openid
        	Map<String, Object> payermap = new HashMap();
            //payermap.put("openid", "omwHV4sj1Fj8PvehfEXA5ipn2pVg");
            String ls_openid=parameters.get("openid");
            payermap.put("openid", ls_openid);
            
            String ls_fyid = parameters.get("fyid");//费用id
            //String ls_fyje = parameters.get("fyje");//费用金额

            String ls_fyid_new = ls_fyid.replaceAll("\\b", "'");
            String ls_sql="";

            /*
            1、生成微信订单编号
            select seq_id.nextval into :微信订单编号 from dual;
            */
            Map<String, Object> params = new HashMap<String, Object>();
            ls_sql = "select seq_id.nextval from dual";
            params.put("sql", ls_sql);
            String ls_seq_id=sqlUtil.preparedSelectOne(params);
            /*

            2、插入微信订单从表
            insert INTO t_yw_fyxx_jfmx_jyjl_wxdd_cb
                    (ddzbid, fyid, fyje)
            select '微信订单编号', T.ID, t.qjzje
            from t_yw_fyxx t
            where t.id in ('费用ID-1','费用ID-2');
            */

            ls_sql = "insert INTO t_yw_fyxx_jfmx_jyjl_wxdd_cb\n" +
                    "                    (ddzbid, fyid, fyje)\n" +
                    "            select '"+ls_seq_id+"', T.ID, t.qjzje\n" +
                    "            from t_yw_fyxx t\n" +
                    "            where t.id in ("+ls_fyid_new+")";

            int li_ret = sqlUtil.insert(ls_sql);

            if (li_ret == -1){
                ls_code = "-1";
                ls_message="微信订单从表信息插入失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }

            /*
            3、插入微信订单主表
            insert INTO t_yw_fyxx_jfmx_jyjl_wxdd_zb
                    (id, ddje, ddrq, ddzt, bz)
            select '微信订单编号', sum(t.fyje), sysdate, '0', '微信支付水费'
            from t_yw_fyxx_jfmx_jyjl_wxdd_cb t
            where t. ddzbid = '微信订单编号';
            */

            ls_sql = "insert INTO t_yw_fyxx_jfmx_jyjl_wxdd_zb\n" +
                    "                    (id, ddje, ddrq, ddzt, openid, bz)\n" +
                    "            select '"+ls_seq_id+"', sum(t.fyje), sysdate, '0', '"+ ls_openid +"','微信支付水费'\n" +
                    "            from t_yw_fyxx_jfmx_jyjl_wxdd_cb t\n" +
                    "            where t.ddzbid = '"+ls_seq_id+"'";

            li_ret = sqlUtil.insert(ls_sql);

            if (li_ret == -1){
                ls_code = "-1";
                ls_message="微信订单主表信息插入失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }

            /*
            4、获取订单总金额
            select t.fyje
            into :订单金额
            from t_yw_fyxx_jfmx_jyjl_wxdd_zb t
            where t.ddzbid = '微信订单编号';
            */
            //这里金额乘以100精确到分的整数,适应微信支付要求
            ls_sql = "select (t.DDJE * 100)\n" +
                    "            from t_yw_fyxx_jfmx_jyjl_wxdd_zb t\n" +
                    "            where t.id = '"+ls_seq_id+"'";
            params.put("sql", ls_sql);
            String ls_fyje_sum = sqlUtil.preparedSelectOne(params);

            Map<String, Object> map = new HashMap();
            //支付的产品（小程序或者公众号，主要需要和微信支付绑定哦）
            map.put("appid", WxpayConfig.app_id);
            //支付的商户号
            map.put("mchid", WxpayConfig.mch_id);

            //临时写死配置
            //String ls_out_trade_no= WeixinchatPayUtils.getNonceStr();

            map.put("description","水费");          //订单名称
            //map.put("out_trade_no",ls_out_trade_no);//订单号(唯一)
            map.put("out_trade_no",ls_seq_id);//订单号(唯一)
            map.put("notify_url", WxpayConfig.notify_order_url);

            Map<String, Object> amount = new HashMap();
            //订单金额 单位换算成分
            //amount.put("total", Integer.parseInt("20") * 100);
            amount.put("total", Integer.parseInt(ls_fyje_sum));
            amount.put("currency", "CNY");
            map.put("amount", amount);
            //设置小程序所需的openid          
            map.put("payer", payermap);

            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(map);

            Map<String, Object> stringObjectMap = null;
            HashMap<String, Object> dataMap = null;

            stringObjectMap = HttpUtils.doPostWexin(WechatUrlConfig.JSAPIURL, body);//获取预支付订单id
            dataMap = WeixinchatPayUtils.getTokenJSAPI(WxpayConfig.app_id, String.valueOf(stringObjectMap.get("prepay_id")));
            JSONObject json =  new JSONObject(dataMap);

            jsonReturn.put("timeStamp",json.getString("timeStamp"));
            jsonReturn.put("package",json.getString("package"));
            jsonReturn.put("paySign",json.getString("paySign"));
            jsonReturn.put("appId",json.getString("appId"));
            jsonReturn.put("signType",json.getString("signType"));
            jsonReturn.put("nonceStr",json.getString("nonceStr"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ls_code = "-1";
            ls_message = e.getMessage();
        }

        jsonReturn.put("code",ls_code);//成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);
        return jsonReturn;
    }

    //2.支付回调,生成微信支付到账信息(yanp2022-04-20新增)
    public JSONObject Wxzf(Map<String, String> parameters) {

        String ls_code = "0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//接口返回值
        jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
        jsonReturn.put("message", ls_message);
        String ls_out_trade_no=parameters.get("out_trade_no");
        String ls_flag=parameters.get("flag");
        /*
        1、更新微信订单状态
        update t_yw_fyxx_jfmx_jyjl_wxdd_zb
        set ddzt = '1', ddztms = '支付成功'
        where id = '微信订单编号'
        */
        String ls_sql = "";        
        int li_ret = 0;
        Map<String, Object> params = new HashMap<String, Object>();

        if (ls_flag.equals("1")) {

            //由于微信有可能多次进行回调,所以这里先判断一下是否数据库是否已到账,如果已到账就不再进行数据的处理了
            ls_sql = "select count(1) from t_yw_fyxx_jfmx_jyjl_wxdd_zb where ddzt='1' and id = #{out_trade_no}";
            params.put("sql", ls_sql);
            params.put("out_trade_no", ls_out_trade_no);
            int li_count = Integer.parseInt(sqlUtil.preparedSelectOne(params));

            //如果已到账,直接返回
            if (li_count > 0){
                return jsonReturn;
            }

            //=====================================================================================如果到账成功
            ls_sql = "update t_yw_fyxx_jfmx_jyjl_wxdd_zb\n" +
                    "        set ddzt = '1', ddztms = '支付成功'\n" +
                    "        where id = #{out_trade_no}";
            params.put("sql", ls_sql);
            params.put("out_trade_no", ls_out_trade_no);

            li_ret = sqlUtil.preparedUpdate(params);

            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "更新微信订单状态失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
            /*
            2、插入T_YW_FYXX_JFMX_JYJL
            --生成交易id
            select seq_id.nextval into :交易ID from dual;
            --插入交易记录表
            insert into t_yw_fyxx_jfmx_jyjl
                    (id, jylsh, jyq, jffs, jyje,'微信公众号用户支付')
            values
                    ('交易ID', '微信支付交易流水号', sysdate, '3', '交易金额')
             */

            //查询序列
            ls_sql = "select seq_id.nextval from dual";
            params.put("sql", ls_sql);
            String ls_seq_id = sqlUtil.preparedSelectOne(params);

            //查询订单主表金额和openid            
            ls_sql= "select ddje,openid from t_yw_fyxx_jfmx_jyjl_wxdd_zb where id = #{out_trade_no}";
            params.put("sql", ls_sql);
            params.put("out_trade_no", ls_out_trade_no);
            List<Map<String, Object>> list_ddzb_xx =  sqlUtil.preparedSelectMap(params);
            String ls_openid = "";
            String ls_ddje = "";
            //循环取出查询的list中的值
            if (list_ddzb_xx.size() > 0) {
            	ls_ddje = list_ddzb_xx.get(0).get("ddje").toString();    //订单金额
            	ls_openid = list_ddzb_xx.get(0).get("openid").toString();//openid               
            }
            else{
            	ls_code = "-1";
                ls_message = "取订单主表交易金额和交易人失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
            
            //=================================================================
            //这里应该再比对一下微信到账金额与数据库应到账金额(这里先预留出来,后续补充)
            //=================================================================
            ls_sql = "insert into t_yw_fyxx_jfmx_jyjl\n" +
                    "                (id, jylsh, jyq, jffs, jyje,bz,jfczy)\n" +
                    "        values\n" +
                    "                ('" + ls_seq_id + "', '" + ls_out_trade_no + "', sysdate, '2', '"+ls_ddje+
                                      "','微信公众号','" + ls_openid+"')";

            params.put("sql", ls_sql);

            li_ret = sqlUtil.preparedInsert(params);

            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "插入交易记录表失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }

            
            /*
                                    更新 t_yw_fyxx_jfmx_jyjl_wxdd_zb表的jyid
            */
            ls_sql="update t_yw_fyxx_jfmx_jyjl_wxdd_zb set jyid = '" + ls_seq_id + "' where id = '" + ls_out_trade_no + "'";

            params.put("sql", ls_sql);
        
            li_ret = sqlUtil.preparedUpdate(params);
            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "更新微信订单主表的jyid列失败失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
            /*
            3、插入T_YW_FYXX_JFMX表           
            */
            ls_sql = "insert into t_yw_fyxx_jfmx\n" +
                    "                (id, jyid, fyid, jfje, jfrq, jffs, jflx, jfczy, bz)\n" +
                    "        select seq_id.nextval,\n" +
                    "                '" + ls_seq_id + "',\n" +
                    "                b.fyid,\n" +
                    "                B.FYJE,\n" +
                    "                SYSDATE,\n" +
                    "                '2',\n" +
                    "                '0',\n" +
                    "                '"+ls_openid+"',\n" +
                    "                '微信公众号'\n" +
                    "        from t_yw_fyxx_jfmx_jyjl_wxdd_zb a, t_yw_fyxx_jfmx_jyjl_wxdd_cb b\n" +
                    "        where a.id = b.ddzbid\n" +
                    "        and id = '" + ls_out_trade_no + "'";

            params.put("sql", ls_sql);

            li_ret = sqlUtil.preparedInsert(params);

            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "插入T_YW_FYXX_JFMX表失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
            //更新t_yw_fyxx表，sjje和qjje为0
            ls_sql="update t_yw_fyxx t\n" +
                    "            set t.sjje = t.yjje, t.qjzje = 0\n" +
                    "            where exists (select 1\n" +
                    "            from t_yw_fyxx_jfmx_jyjl_wxdd_cb a\n" +
                    "            where a.fyid = t.id\n" +
                    "            and a.ddzbid =  #{out_trade_no})";

            params.put("sql", ls_sql);
            params.put("out_trade_no", ls_out_trade_no);

            li_ret = sqlUtil.preparedUpdate(params);

            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "应缴费用更新失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
            
        }
        else{
            //=====================================================================================如果到账失败,置失败标志
            ls_sql="update t_yw_fyxx_jfmx_jyjl_wxdd_zb\n" +
                    "            set ddzt = '2', ddztms = '支付失败'\n" +
                    "            where id = #{out_trade_no}";
            params.put("sql", ls_sql);
            params.put("out_trade_no", ls_out_trade_no);

            li_ret = sqlUtil.preparedUpdate(params);

            if (li_ret == -1) {
                ls_code = "-1";
                ls_message = "更新微信订单状态失败！";
                jsonReturn.put("code", ls_code);        //成功返回0,失败返回-1
                jsonReturn.put("message", ls_message);
                return jsonReturn;
            }
        }   
        return jsonReturn;
    }
}
