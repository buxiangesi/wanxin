package com.shggc.project.fwjk.wxzf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shggc.common.exception.BusinessException;
import com.shggc.project.youbeisoft.base.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//按khid查询用户信息
@Service
public class YhxxService {

    @Autowired
    SqlUtil sqlUtil;

    public JSONObject GetYhxx(Map<String, String> parameters) {

        String ls_khid = parameters.get("khid");
        //主表信息sql
        String ls_sql_mian = "select khid, yhbh, yhmc, lxdh, yhdz, qfzje, bz\n" +
                "\n" +
                "  from (select a.id as khid,\n" +
                "               a.yhbh as yhbh,\n" +
                "               a.hzxm as yhmc,\n" +
                "               c.xqmc || b.dh || '栋' || b.dyh || '单元' || b.fh || '号' as yhdz,\n" +
                "               a.lxdh_sj as lxdh,\n" +
                "               '' bz,\n" +
                "               (select nvl(sum(e.qjzje), 0)\n" +
                "                  from t_yw_fyxx e\n" +
                "                 where e.khid = a.id\n" +
                "                   and e.fylx < '2'\n" +
                "                   and e.qjzje > 0) as qfzje\n" +
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
                "               '' bz,\n" +
                "               (select nvl(sum(e.qjzje), 0)\n" +
                "                  from t_yw_fyxx e\n" +
                "                 where e.khid = a.id\n" +
                "                   and e.fylx < '2'\n" +
                "                   and e.qjzje > 0) as qfzje\n" +
                "          from t_jc_khxx_sy a, t_jc_xq c, t_jc_ysxz d\n" +
                "         where a.ssxqid = c.id\n" +
                "           and a.ysxzid = d.id)\n" +
                " where khid = #{khid}";

        //欠费明细表sql
        String ls_sql_child = "select a.id as fyid,\n" +
                "       a.jfywn || a.jfywy as ywny,\n" +
                "       a.yssl,\n" +
                "       a.qjzje as qfje\n" +
                "  from t_yw_fyxx a, t_jc_ysxz d\n" +
                " where a.ysxzid = d.id\n" +
                "   and a.fylx < '2'\n" +
                "   and a.khid = #{khid}\n" +
                "   and nvl(a.qjzje,0) > 0\n" +
                " order by ywny";

        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值

        //======================================================================查询第一个sql
        params.put("sql", ls_sql_mian);                                          //将sql语句设置到map
        params.put("khid", ls_khid);                                             //将openid设置到map
        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
        jsonReturn.put("mian", jsonArray_main);                                  //用户信息放到mian标签

        //======================================================================查询第二个sql
        params.put("sql", ls_sql_child);                                         //将sql语句设置到map
        params.put("khid", ls_khid);                                             //将openid设置到map
        List<Map<String, Object>> list_child = sqlUtil.preparedSelectMap(params);//查询用户信息
        String jsonStr_child = JSON.toJSONString(list_child);                    //map转json字符串
        JSONArray jsonArray_child = JSONArray.parseArray(jsonStr_child);         //json字符串转json数组
        jsonReturn.put("child", jsonArray_child);                                //用户信息放到data标签

        return jsonReturn;
    }
    
    //按小区、栋号、单元号、房号查询居民用户信息
    public JSONObject GetJMYhxx(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        String xqid = parameters.get("xqid");
        String dh = parameters.get("dh");
        String dyh = parameters.get("dyh");
        String fh = parameters.get("fh");
        //主表信息sql
        String ls_sql = 
        		"select a.id as khid," +
				       "a.yhbh as yhbh," +
				       "a.hzxm as yhmc," +
				       "e.xqmc || f.dh || '栋' || f.dyh || '单元' || f.fh || '号' as yhdz," +
				       "a.lxdh_sj as lxdh " +
				  "from t_jc_khxx_jm a, t_jc_xq e, t_jc_xq_fwxx f " +
				 "where a.fwid = f.id " +
				   "and f.xqid = e.id " +
				   "and f.xqid = #{xqid} " +
				   "and f.dh = #{dh} " +
				   "and f.dyh = #{dyh} " +
				   "and f.fh = #{fh}";
        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值

        try{
	        params.put("sql", ls_sql);                                          //将sql语句设置到map
	        params.put("xqid", xqid);                                         //将sql语句设置到map
	        params.put("dh", dh);                                         //将sql语句设置到map
	        params.put("dyh", dyh);                                         //将sql语句设置到map
	        params.put("fh", fh);                                             //将openid设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }

    //按编号查询居民和商业企业用户信息
    public JSONObject GetSYqyyhxx(Map<String, String> parameters) {

        /*"ywlx": "A0102004",
        "yhbh": "500000",
        "khlx": "0",
        "sjhm": "4565"  //手机后四位
        */
    	String yhbh = parameters.get("yhbh");
        String khlx = parameters.get("khlx");
        String sjhm = parameters.get("sjhm");
        String ls_code="0";
        String ls_message = "";
        
        //主表信息sql
        String ls_sql = 
        		"select a.id as khid,a.yhbh as yhbh,a.dwmc as yhmc,a.dwdz as yhdz,decode(nvl(a.lxdh_sj,''),'','','*******'||substr(a.lxdh_sj,length(a.lxdh_sj) - 3)) as lxdh " +
				  "from t_jc_khxx_sy a where a.zxbz = '0' and a.yhbh = #{yhbh} and '1' = #{khlx}" +
				  "union all " +
				  "select a.id as khid,a.yhbh as yhbh,decode(nvl(a.hzxm,''),'','',substr('********',1,length(a.hzxm) - 1)||substr(a.hzxm,length(a.hzxm))) as yhmc, " +
				  		"e.xqmc || f.dh || '栋' || f.dyh || '单元' || f.fh || '号' as yhdz,decode(nvl(a.lxdh_sj,''),'','','*******'||substr(a.lxdh_sj,length(a.lxdh_sj) - 3)) as lxdh " +
				  "from t_jc_khxx_jm a, t_jc_xq e, t_jc_xq_fwxx f " +
				  "where a.fwid = f.id and f.xqid = e.id and a.zxbz = '0' and a.yhbh = #{yhbh} and '0' = #{khlx}";
        
        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值
        try{
	        params.put("sql", ls_sql);                                          //将sql语句设置到map
	        params.put("yhbh", yhbh);                                         //将sql语句设置到map
	        params.put("khlx", khlx);                                         //将sql语句设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
        	//带有参数手机后四位，进行校验手机号码是否一致
	        String lxdh_sj = "";
	        if (list_main.size() > 0)
	        {
	        	lxdh_sj = list_main.get(0).get("lxdh").toString();
	        }
	        if(lxdh_sj == null || lxdh_sj.isEmpty())
	        //数据库中返回的用户的联系电话lxdh_sj为空，如果客户输入了手机号后四位，则提示不需要输入手机号后四位
	        {
	        	if(sjhm != null && sjhm.length() != 0) {
	                jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
	                jsonReturn.put("message","此用户在系统中没有设置手机号，不需要输入手机号后四位进行查询。");                 //成功message为空,失败message为捕获异常的信息
	                return jsonReturn;
	        	}
	        }else 
	        //数据库中返回的用户的联系电话lxdh_sj不为空，先判断用户是否输入手机号，再判断手机号后四位是否匹配	
	        {
	        	if(sjhm == null || sjhm.length() == 0) {
	                jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
	                jsonReturn.put("message","未查询到此用户，请核对用户编号和手机号后四位输入是否准确。");                 //成功message为空,失败message为捕获异常的信息
	                return jsonReturn;
	        	}
	        	if(!lxdh_sj.endsWith(sjhm)) {
	                jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
	                jsonReturn.put("message","未查询到此用户，请核对用户编号和手机号后四位输入是否准确。");                 //成功message为空,失败message为捕获异常的信息
	                return jsonReturn;
	        	}
	        }
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }

    //更新居民用户的用户名称和联系电话
    public JSONObject UpdateJMyhxx(Map<String, String> parameters) {
        JSONObject jsonReturn = new JSONObject();//json返回值
        String yhmc = parameters.get("yhmc");    //户主姓名
        String lxdh = parameters.get("lxdh"); //联系电话
        String khid = parameters.get("khid");      //用户id
        String openid = parameters.get("openid");      //用户openid
        //主表信息sql(只有在hzxm为空的情况下才更新，不为空的情况下不更新）
        String ls_sql = 
        		"update t_jc_khxx_jm a "
        		+ "set a.hzxm = decode(a.hzxm,null, #{yhmc},'',#{yhmc},a.hzxm),"+
                       "a.lxdh_sj = decode(a.lxdh_sj,null,#{lxdh},'',#{lxdh},a.lxdh_sj) "+
        		" where a.id = #{khid} ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sql", ls_sql);                                          //将sql语句设置到map
        params.put("yhmc", yhmc);                                         //将sql语句设置到map
        params.put("lxdh", lxdh);                                         //将sql语句设置到map
        params.put("khid", khid);                                         //将sql语句设置到map
        int li_ret=sqlUtil.preparedUpdate(params);
        if(li_ret <= 0) {
            jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
            jsonReturn.put("message","更新失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
            return jsonReturn;
        }else {
        	Map<String, Object> params_selectone = new HashMap<String, Object>();
            ls_sql = "select count(*) from t_wx_gzhyh_bd where openid = '"+openid+"' and khid = '"+khid+"'";
            params_selectone.put("sql",ls_sql);
            String count= sqlUtil.preparedSelectOne(params_selectone);
            if(count.equals("0")) {
            	Map<String, Object> params_insert = new HashMap<String, Object>();
                ls_sql = "insert into t_wx_gzhyh_bd (id, openid, khid, bdbz, bdrq)values (seq_id.nextval, #{openid}, #{khid}, '1', sysdate)";
                params_insert.put("openid",openid);
                params_insert.put("khid",khid);
                params_insert.put("sql",ls_sql);
                li_ret=sqlUtil.preparedInsert(params_insert);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","更新成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }else {
            	ls_sql = 
                		"update t_wx_gzhyh_bd set bdbz = '1', bdrq = sysdate where openid = '"+openid+"' and khid = '"+khid+"'";
                params = new HashMap<String, Object>();
                params.put("sql", ls_sql);                                          //将sql语句设置到map
                li_ret=sqlUtil.preparedUpdate(params);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","更新成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }
        }
    }
    //绑定用户
    public JSONObject WxgzhYhbd(Map<String, String> parameters) {
        JSONObject jsonReturn = new JSONObject();//json返回值
        String openid = parameters.get("openid");
        String khid  = parameters.get("khid");
        String nickname = parameters.get("nickname");
        String sex = parameters.get("sex");
        String language = parameters.get("language");
        String city = parameters.get("city");
        String province = parameters.get("province");
        String country = parameters.get("country");
        String headimgurl = parameters.get("headimgurl");
        String identyid = parameters.get("identyid");
        String name = parameters.get("name");
        String password = parameters.get("password");
        String phone = parameters.get("phone");
        String radom = parameters.get("radom");
        String radomdate = parameters.get("radomdate");
        String zcsj = parameters.get("zcsj");
        //查询用户是否存在
        Map<String,Object> params_selectone = new HashMap<String, Object>();
        String ls_sql = "select count(*) count from t_wx_gzhyh t where t.openid = '"+openid+"'";
        params_selectone.put("sql",ls_sql);
        String count= sqlUtil.preparedSelectOne(params_selectone);
        if(count.equals("0")) {
            Map<String,Object> params_insert = new HashMap<String, Object>();
            ls_sql = "insert into t_wx_gzhyh(openid,nickname,sex,language,city,province,country,headimgurl,identyid,name,password,phone,radom,radomdate,zcsj) "
            		+ "values (#{openid},#{nickname},#{sex},#{language},#{city},#{province},#{country},#{headimgurl},"
            		+ "#{identyid},#{name},#{password},#{phone},#{radom},to_date(#{radomdate},'yyyy-mm-dd hh24:mi:ss'),to_date(#{zcsj},'yyyy-mm-dd hh24:mi:ss'))";
            params_insert.put("openid",openid);
            params_insert.put("nickname",nickname);
            params_insert.put("sex",sex);
            params_insert.put("language",language);
            params_insert.put("city",city);
            params_insert.put("province",province);
            params_insert.put("country",country);
            params_insert.put("headimgurl",headimgurl);
            params_insert.put("identyid",identyid);
            params_insert.put("name",name);
            params_insert.put("password",password);
            params_insert.put("phone",phone);
            params_insert.put("radom",radom);
            params_insert.put("radomdate",radomdate);
            params_insert.put("zcsj",zcsj);
            params_insert.put("sql",ls_sql);
            int li_ret=sqlUtil.preparedInsert(params_insert);
            if(li_ret == 0) {
                jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                return jsonReturn;
            }
        	params_selectone = new HashMap<String, Object>();
            ls_sql = "select count(*) from t_wx_gzhyh_bd where openid = '"+openid+"' and khid = '"+khid+"'";
            params_selectone.put("sql",ls_sql);
            count= sqlUtil.preparedSelectOne(params_selectone);
            if(count.equals("0")) {
            	params_insert = new HashMap<String, Object>();
                ls_sql = "insert into t_wx_gzhyh_bd (id, openid, khid, bdbz, bdrq)values (seq_id.nextval, #{openid}, #{khid}, '1', sysdate)";
                params_insert.put("openid",openid);
                params_insert.put("khid",khid);
                params_insert.put("sql",ls_sql);
                li_ret=sqlUtil.preparedInsert(params_insert);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","绑定成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }else {
            	ls_sql = 
                		"update t_wx_gzhyh_bd set bdbz = '1', bdrq = sysdate where openid = '"+openid+"' and khid = '"+khid+"'";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("sql", ls_sql);                                          //将sql语句设置到map
                li_ret=sqlUtil.preparedUpdate(params);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","绑定成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }
        }else {
        	params_selectone = new HashMap<String, Object>();
            ls_sql = "select count(*) from t_wx_gzhyh_bd where openid = '"+openid+"' and khid = '"+khid+"'";
            params_selectone.put("sql",ls_sql);
            count= sqlUtil.preparedSelectOne(params_selectone);
            if(count.equals("0")) {
            	Map<String,Object> params_insert = new HashMap<String, Object>();
                ls_sql = "insert into t_wx_gzhyh_bd (id, openid, khid, bdbz, bdrq)values (seq_id.nextval, #{openid}, #{khid}, '1', sysdate)";
                params_insert.put("openid",openid);
                params_insert.put("khid",khid);
                params_insert.put("sql",ls_sql);
                int li_ret=sqlUtil.preparedInsert(params_insert);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","绑定成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }else {
            	ls_sql = 
                		"update t_wx_gzhyh_bd set bdbz = '1', bdrq = sysdate where openid = '"+openid+"' and khid = '"+khid+"'";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("sql", ls_sql);                                          //将sql语句设置到map
                int li_ret=sqlUtil.preparedUpdate(params);
                if(li_ret <= 0) {
                    jsonReturn.put("code","-1");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","用户绑定失败，请检查。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }else {
                    jsonReturn.put("code","0");                       //成功返回0,失败返回-1
                    jsonReturn.put("message","绑定成功。");                 //成功message为空,失败message为捕获异常的信息
                    return jsonReturn;
                }
            }
        }
    }

    //解除用户绑定
    public JSONObject WxgzhJcYhbd(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//json返回值
        String openid = parameters.get("openid");
        String khid = parameters.get("khid");
        //主表信息sql
        String ls_sql = 
        		"update t_wx_gzhyh_bd set bdbz = '0',jcbdrq = sysdate where openid = #{openid}  and khid = #{khid}";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sql", ls_sql);                                          //将sql语句设置到map
        params.put("openid", openid);                                         //将sql语句设置到map
        params.put("khid", khid);                                     //将sql语句设置到map
        try{
        	int li_ret=sqlUtil.preparedUpdate(params);
            if(li_ret <= 0) {
                ls_code = "-1";
                ls_message = "解除用户绑定失败，请检查。";
            }else {
                ls_code = "0";
                ls_message = "解除绑定成功";
            }
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }

    //查询所有辖区信息
    public JSONObject GetXqxx(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        String ls_sql = 
        		"select t.id as xqid, t.xqbh, t.xqmc" +
                		"  from t_jc_xq t" +
                		" where t.xqlx = '0'" +
                		"   and t.zxbz = '0'" +
                		" order by t.xqmc";
        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonReturn = new JSONObject();//json返回值

        try{
	        params.put("sql", ls_sql);                                        //将sql语句设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
    //查询辖区的所有栋号
    public JSONObject GetXqdhxx(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//json返回值
        try{
	        String xqid = parameters.get("xqid");
	        String ls_sql = 
	        		"select disTINCT T.DH " +
	                		" from t_jc_xq_fwxx t" +
	                		" where t.xqid = #{xqid}" +
	                		" ORDER by t.dh";
	        Map<String, Object> params = new HashMap<String, Object>();
	
	        params.put("sql", ls_sql);                                        //将sql语句设置到map
	        params.put("xqid", xqid);                                         //将sql语句设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
    //查询辖区栋号的所有单元号
    public JSONObject GetXqdhdyhxx(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//json返回值
        try{
	        String xqid = parameters.get("xqid");
	        String dh = parameters.get("dh");
	        String ls_sql = 
	        		"select disTINCT T.dyh " +
	                		" from t_jc_xq_fwxx t " +
	                		" where t.xqid = #{xqid} " +
	                		" and t.dh = #{dh} " +
	                		" ORDER by t.dyh";
	        Map<String, Object> params = new HashMap<String, Object>();
	
	        params.put("sql", ls_sql);                                        //将sql语句设置到map
	        params.put("xqid", xqid);                                         //将sql语句设置到map
	        params.put("dh", dh);                                         //将sql语句设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
    //查询辖区栋号单元号的所有房号
    public JSONObject GetXqdhdyhfhxx(Map<String, String> parameters) {
        String ls_code="0";
        String ls_message = "";
        JSONObject jsonReturn = new JSONObject();//json返回值
        try{
	        String xqid = parameters.get("xqid");
	        String dh = parameters.get("dh");
	        String dyh = parameters.get("dyh");
	        String ls_sql = 
	        		"select disTINCT T.fh " +
	                		" from t_jc_xq_fwxx t " +
	                		" where t.xqid = #{xqid} " +
	                		" and t.dh = #{dh} " +
	                		" and t.dyh = #{dyh} " +
	                		" ORDER by t.fh";
	        Map<String, Object> params = new HashMap<String, Object>();
	
	        params.put("sql", ls_sql);                                        //将sql语句设置到map
	        params.put("xqid", xqid);                                         //将sql语句设置到map
	        params.put("dh", dh);                                         //将sql语句设置到map
	        params.put("dyh", dyh);                                         //将sql语句设置到map
	        List<Map<String, Object>> list_main = sqlUtil.preparedSelectMap(params); //查询用户信息
	        String jsonStr_mian = JSON.toJSONString(list_main);                      //map转json字符串
	        JSONArray jsonArray_main = JSONArray.parseArray(jsonStr_mian);           //json字符串转json数组
	        jsonReturn.put("data", jsonArray_main);                                  //用户信息放到mian标签
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }
        jsonReturn.put("code",ls_code);                       //成功返回0,失败返回-1
        jsonReturn.put("message",ls_message);                 //成功message为空,失败message为捕获异常的信息
        return jsonReturn;
    }
}
