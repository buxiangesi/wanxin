package com.shggc.project.youbeisoft;

import com.alibaba.fastjson.JSONObject;
import com.shggc.common.exception.BusinessException;
import com.shggc.project.youbeisoft.base.SqlUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//2022-10-17yanp增加简化方法测试类
//http://127.0.0.1:8083/simple/testsql
@RestController
@RequestMapping("/simple")
@Api(tags = "简化sql测试")
//开启事务
@Transactional
public class YbTestController {

    @Autowired
    SqlUtil sqlUtil;

    @GetMapping(value="/testsql")
    public ResponseEntity<Object> testsql()
    {
        String ls_sql="";
        String ls_id="";
        String ls_xqbh="";
        String ls_xqmc="";
        String ls_xqdz="";
        int li_ret=1;
        //========================================================================1、预编译查询（单条记录、单个字段）
        Map<String,Object> params_selectone = new HashMap<String, Object>();
        ls_sql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
        params_selectone.put("sql",ls_sql);
        String ls_sysdate= sqlUtil.preparedSelectOne(params_selectone);
        System.out.println("===================【查询单条记录单个字段】输出查询结果："+ls_sysdate);
        //========================================================================2、预编译执行查询(多条记录、多个字段、也支持单条记录、单个字段)
        Map<String,Object> params = new HashMap<String, Object>();
        ls_sql = "select id, xqbh, xqmc, xqdz from T_JC_XQ where xqlx = #{xqlx} and zxbz = #{zxbz}";
        params.put("sql",ls_sql);//将sql语句设置到map
        params.put("xqlx","0");  //设置sql查询条件参数
        params.put("zxbz","0");  //设置ql查询条件参数
        //执行查询,将查询结果放到list中
        List<Map<String, Object>> list_t_jc_xq = sqlUtil.preparedSelectMap(params);
        //循环取出查询的list中的值
        for (Map<String, Object> m_list_t_jc_xq : list_t_jc_xq) {
            ls_id = m_list_t_jc_xq.get("id").toString();    //默认查询字段会变小写（兼容若依架构）
            ls_xqbh = m_list_t_jc_xq.get("xqbh").toString();//默认查询字段会变小写（兼容若依架构）
            ls_xqmc = m_list_t_jc_xq.get("xqmc").toString();//默认查询字段会变小写（兼容若依架构）
            ls_xqdz = m_list_t_jc_xq.get("xqdz").toString();//默认查询字段会变小写（兼容若依架构）
            System.out.println("===================【预编译查询多条记录多个字段】输出查询结果：" + ls_id + " " + ls_xqbh + " " + ls_xqmc + " " + ls_xqdz);
        }
        //如果只取第一条记录的各个值,可以这样取
        if (list_t_jc_xq.size() > 0)
        {
            ls_id = list_t_jc_xq.get(0).get("id").toString();
            ls_xqbh = list_t_jc_xq.get(0).get("xqbh").toString();
            ls_xqmc = list_t_jc_xq.get(0).get("xqmc").toString();
            ls_xqdz = list_t_jc_xq.get(0).get("xqdz").toString();
            System.out.println("===================【预编译查询一条记录多个字段】输出查询结果：" + ls_id + " " + ls_xqbh + " " + ls_xqmc + " " + ls_xqdz);
        }
        //==========================================================================3、执行insert语句
        ls_id="111";
        ls_xqbh="222";
        ls_xqmc="333";
        ls_xqdz="444";
        Map<String,Object> params_insert = new HashMap<String, Object>();
        ls_sql = "insert into t_jc_xq(id,xqbh,xqmc,xqdz) values (#{id},#{xqbh},#{xqmc},#{xqdz})";
        params_insert.put("id",ls_id);
        params_insert.put("xqbh",ls_xqbh);
        params_insert.put("xqmc",ls_xqmc);
        params_insert.put("xqdz",ls_xqdz);
        params_insert.put("sql",ls_sql);
        li_ret=sqlUtil.preparedInsert(params_insert);
        if (li_ret == 1){
            System.out.println("===================【执行insert语句】成功！");
        }else{
            System.out.println("===================【执行insert语句】失败！");
            throw new BusinessException("【执行insert语句】失败！");//抛出异常回滚事务
        }
        //==========================================================================4、执行update语句
        ls_xqbh="555";
        ls_xqmc="666";
        ls_xqdz="777";
        Map<String,Object> params_update = new HashMap<String, Object>();
        ls_sql = "update t_jc_xq set xqbh=#{xqbh},xqmc=#{xqmc},xqdz=#{xqdz} where id=#{id}";
        params_update.put("xqbh",ls_xqbh);
        params_update.put("xqmc",ls_xqmc);
        params_update.put("xqdz",ls_xqdz);
        params_update.put("id",ls_id);
        params_update.put("sql",ls_sql);
        li_ret=sqlUtil.preparedUpdate(params_update);
        if (li_ret == 1){
            System.out.println("===================【执行update语句】成功！");
        }else{
            System.out.println("===================【执行update语句】失败！");
            throw new BusinessException("【执行update语句】失败！");//抛出异常回滚事务
        }
        //==========================================================================5、执行delete语句
        Map<String,Object> params_delete = new HashMap<String, Object>();
        ls_sql = "delete from t_jc_xq where id=#{id}";
        params_delete.put("id",ls_id);
        params_delete.put("sql",ls_sql);
        li_ret=sqlUtil.preparedDelete(params_delete);
        if (li_ret == -1){
            System.out.println("===================【执行dellete语句】失败！");
            throw new BusinessException("【执行dellete语句】失败！");//抛出异常回滚事务
        }else{
            System.out.println("===================【执行dellete语句】成功！");
        }
        //=========================================================================6、执行存储过程
        //组装存储过程入参
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("PRM_AAB001","12345678");//入参
        map.put("PRM_COUNT",5);          //入参
        //执行存储过程
        Map<String, Object> map_return=sqlUtil.PRC_YANPTEST(map);
        //获取存储过程返回值
        int li_appcode = Integer.parseInt(map_return.get("PRM_APPCODE").toString());
        String ls_errormsg = map_return.get("PRM_ERRORMSG").toString();
        System.out.println("===================【存储过程返回值：PRM_APPCODE】"+li_appcode);
        System.out.println("===================【存储过程返回值：PRM_ERRORMSG】"+ls_errormsg);

        //=========================================================================7、通用执行存储过程
        //组装执行的存储过程名称、入参、出参结构
        ls_sql="{" +
                    "call PRC_YANPTEST(" +
                          "#{PRM_AAB001,jdbcType=VARCHAR,mode=IN}," +
                          "#{PRM_COUNT,jdbcType=NUMERIC,mode=IN}," +
                          "#{PRM_APPCODE,jdbcType=NUMERIC,mode=OUT}," +
                          "#{PRM_ERRORMSG,jdbcType=VARCHAR,mode=OUT}" +
                           ")" +
                "}";
        //设置存储过程入参
        Map<String,Object> map_new=new HashMap<String,Object>();
        map_new.put("PRM_AAB001","12345678");//存储过程入参
        map_new.put("PRM_COUNT",5);          //存储过程入参
        map_new.put("sql",ls_sql);           //将存储过程结构定义传入
        //执行存储过程
        Map<String, Object> map_return_new=sqlUtil.executeProc(map_new);
        //获取存储过程返回值
        int li_appcode_new = Integer.parseInt(map_return_new.get("PRM_APPCODE").toString());
        String ls_errormsg_new = map_return_new.get("PRM_ERRORMSG").toString();
        System.out.println("===================【通用存储过程返回值：PRM_APPCODE】"+li_appcode_new);
        System.out.println("===================【通用存储过程返回值：PRM_ERRORMSG】"+ls_errormsg_new);
        //=========================================================================

        //=========================================================================执行prc_cbxx_jm_fyxx存储过程
        //组装执行的存储过程名称、入参、出参结构
        ls_sql="{" +
                    "call prc_cbxx_jm_fyxx(" +
                        "#{prm_nian,jdbcType=VARCHAR,mode=IN}," +
                        "#{prm_yue,jdbcType=VARCHAR,mode=IN}," +
                        "#{prm_xqid,jdbcType=VARCHAR,mode=IN}," +
                        "#{prm_errorcode,jdbcType=VARCHAR,mode=OUT}," +
                        "#{prm_errortext,jdbcType=VARCHAR,mode=OUT}" +
                    ")" +
                "}";
        //设置存储过程入参
        Map<String,Object> map_fyxx=new HashMap<String,Object>();
        map_fyxx.put("prm_nian","2022");//存储过程入参
        map_fyxx.put("prm_yue","01");   //存储过程入参
        map_fyxx.put("prm_xqid","01");   //存储过程入参
        map_fyxx.put("sql",ls_sql);     //将存储过程结构定义传入
        //执行存储过程
        Map<String, Object> map_fyxx_return=sqlUtil.executeProc(map_fyxx);
        //获取存储过程返回值
        String ls_errortext ="";
        String ls_errorcode = map_fyxx_return.get("prm_errorcode").toString();

        if (ls_errorcode.equals("-1")){
            ls_errortext = map_fyxx_return.get("prm_errortext").toString();
            throw new BusinessException("【执行过程prc_cbxx_jm_fyxx失败】,失败原因："+ls_errortext+"！");//抛出异常回滚事务
        }

        System.out.println("===================【存储过程prc_cbxx_jm_fyxx执行成功】");
        System.out.println("===================【存储过程prc_cbxx_jm_fyxx返回值】:"+ls_errorcode);
        System.out.println("===================【存储过程prc_cbxx_jm_fyxx返回值】:"+ls_errortext);


        //设置请求返回值
        JSONObject jsonParam_return = new JSONObject();
        jsonParam_return.put("APPCODE","1");
        //返回结果
        return ResponseEntity.status(HttpStatus.OK).body(jsonParam_return);
    }
}
