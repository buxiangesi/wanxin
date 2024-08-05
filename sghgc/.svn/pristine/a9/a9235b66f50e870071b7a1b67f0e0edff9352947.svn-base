package com.shggc.project.fwjk.wxzf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.project.fwjk.wxzf.service.*;
import com.shggc.project.youbeisoft.base.SqlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fwjk")
@Api(tags = "服务接口")
//开启事务
@Transactional
public class WxController extends BaseController {

    @Autowired
    SfjnYbdyhService sfjnYbdyhService;      //按appid查询已绑定用户
    @Autowired
    SfjnYhjfxxService sfjnYhjfxxService;    //根據客戶ID查詢欠費信息
    @Autowired
    YhxxService yhxxService;                //按khid查询用户信息
    @Autowired
    YsfxNyyslService ysfxNyyslService;      //用水分析
    @Autowired
    SfjnYsxqlService sfjnYsxqlService;      //用水詳情
    @Autowired
    WdzdJfjlService wdzdJfjlService;        //缴费记录
    @Autowired
    WdzdJfmxService wdzdJfmxService;        //缴费明细
    @Autowired
    SfjnWxzfyddService sfjnWxzfyddService;  //微信预缴费
    @Autowired
    SqlUtil sqlUtil;
    //微信小程序主入口
    //访问地址http://127.0.0.1:8080/fwjk/wxzf

        @ApiOperation("微信支付")
        @PostMapping(path = "/wxzf")
        public ResponseEntity<Object> InterFace(@RequestHeader(value = "openid",defaultValue = "",required = false) String openid, @RequestBody Map<String, String> parameters) {

        JSONObject jsonReturn= new JSONObject();//接口返回值

        //=================================================判断openid在数据库中是否存在(yanp2022-03-18增加)
        String ls_code = "0";
        String ls_message = "";
        String ls_sql="select * from T_WX_GZHYH where openid=#{openid}";
        Map<String, Object> params = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        try{
            params.put("sql", ls_sql);               //将sql语句设置到map
            params.put("openid", openid);            //将openid设置到map
            List<Map<String, Object>> list_info = sqlUtil.preparedSelectMap(params);//执行查询
            String jsonStr = JSON.toJSONString(list_info);        //map转json字符串
            jsonArray = JSONArray.parseArray( jsonStr );          //json字符串转json数组
            if (jsonArray.size() == 0){
                ls_code = "-1";
                ls_message = "请您先关注泰盛水务微信公众号！";
            }
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
        }

        if (ls_code.equals("-1")){
            jsonReturn.put("code", ls_code);                              //成功返回0,失败返回-1
            jsonReturn.put("message", ls_message);                        //成功message为空,失败message为捕获异常的信息
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn); //返回请求
        }

        //将openid注入到json传参中,供所有接口访问使用
        parameters.put("openid",openid);
        logger.info("openid:"+openid);
        //=================================================判断openid在数据库中是否存在完毕
        String ls_ywlx=parameters.get("ywlx");   //业务类型
        //业务类型判断
        //03 按小区、栋号、单元号、房号查询居民用户信息
        //04 按编号查询商业企业用户信息
        //05 更新居民用户的用户名称和联系电话
        //06 绑定用户
        //07 解除绑定用户
        //if("01,02,03,04,05,06,07".indexOf(ls_ywlx) < 0) {
        //	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("业务类型不正确，请检查。");
        //}
        //=======================================按appid查询已绑定用户
        
        /*
          协议代码：A0101001
          功能名称：查询已绑定用户
          业务说明：进入到水费缴纳页面，首先查询到当前关注的微信号所绑定的所有用户信息。
          {
            "openid": "1",
            "ywlx": "A0101001"
        }
        */
        if (ls_ywlx.equals("A0101001"))
        {
            jsonReturn=sfjnYbdyhService.GetYbdyhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================按khid查询用户信息
        /*
         协议代码：A0101002
         功能名称：用户待缴费信息
         业务说明：在水费缴纳主页面，点击一个欠费用户，传入客户ID，查询该用户的所有欠费信息。
        {
	        "khid": "10000000011194",
	        "ywlx": "A0101002"
        }
        */
        else if (ls_ywlx.equals("A0101002")){
            jsonReturn=sfjnYhjfxxService.GetYhjfxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
      //=======================================按fyid查询用户用水详细信息
        /*
         协议代码：A0101003
         功能名称：费用详情
         业务说明：点击某个欠费信息，传入费用ID，查询用水详情。
        {
	        "fyid": "10000000011859",
	        "ywlx": "A0101003"
        }
        */
        else if (ls_ywlx.equals("A0101003")){
            jsonReturn=sfjnYsxqlService.GetYsxq(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        
        //=======================================按小区、栋号、单元号、房号查询居民用户信息
        /*
        {
	        "ywlx": "A0102003",
	        "xqid": "10000000011177",
	        "dh": "01",
	        "dyh": "1",
	        "fh": "0101"
        }
        */
        else if (ls_ywlx.equals("A0102003")){
            jsonReturn=yhxxService.GetJMYhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================按编号查询商业企业用户信息
        /*
        {
	        "ywlx": "A0102004",
	        "yhbh": "500000",
	        "khlx": "0",
	        "sjhm": "4565"  //手机后四位
        }
        */
        else if (ls_ywlx.equals("A0102004")){
            jsonReturn=yhxxService.GetSYqyyhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================更新居民用户的用户名称和联系电话
        /*
        {
	        "ywlx": "A0102005",
	        "yhmc": "测试用户",
	        "lxdh": "13888888888",
	        "khid": "10000000011277"
        }
        */
        else if (ls_ywlx.equals("A0102005")){
        	jsonReturn =yhxxService.UpdateJMyhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================绑定用户
        /*
        {
	        "ywlx": "A0102001",
	        "openid": "2",
	        "khid": "10000000011395",
	        "nickname": "测试",
	        "sex": "1",
	        "language": "",
	        "city": "",
	        "province": "",
	        "country": "",
	        "headimgurl": "",
	        "identyid": "",
	        "name": "测试用户",
	        "password": "2342",
	        "phone": "13888888888",
	        "radom": "35234",
	        "radomdate":"2022-01-01 14:22:34",
	        "zcsj": "2022-01-01 14:02:34"
        }
        */
        else if (ls_ywlx.equals("A0102001")){
        	jsonReturn =yhxxService.WxgzhYhbd(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================解除用户绑定
        /*
        {
	        "ywlx": "A0102002",
	        "openid": "2",
	        "khid": "10000000011395"
        }
        */
        else if (ls_ywlx.equals("A0102002")){
        	jsonReturn =yhxxService.WxgzhJcYhbd(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================查询所有辖区信息
        /*
        {
	        "ywlx": "A0102006"
        }
        */
        else if (ls_ywlx.equals("A0102006")){
            jsonReturn=yhxxService.GetXqxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================查询辖区的所有栋号
        /*
        {
	        "ywlx": "A0102007",
	        "xqid": "10000000000216"
        }
        */
        else if (ls_ywlx.equals("A0102007")){
            jsonReturn=yhxxService.GetXqdhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================查询辖区栋号的所有单元号
        /*
        {
	        "ywlx": "A0102008",
	        "xqid": "10000000000216",
	        "dh": "01"
        }
        */
        else if (ls_ywlx.equals("A0102008")){
            jsonReturn=yhxxService.GetXqdhdyhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================查询辖区栋号单元号的所有房号
        /*
        {
	        "ywlx": "A0102009",
	        "xqid": "10000000000216",
	        "dh": "01",
	        "dyh": "1"
        }
        */
        else if (ls_ywlx.equals("A0102009")){
            jsonReturn=yhxxService.GetXqdhdyhfhxx(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        //=======================================按照客户ID和年度查询月份用水量
        /*
        {
	        "ywlx": "A0104001",
	        "khid": "10000000000216",
	        "nd": "01"
        }
        */
        else if (ls_ywlx.equals("A0104001")){
            jsonReturn=ysfxNyyslService.GetKhysl(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
      //=======================================按openid和年度查询用户繳費信息
        /*
         协议代码：A0103001
         功能名称：查询缴费记录
         业务说明：返回結果交易ID、客户ID、用户编号、用户名称、用户地址、缴费日期、缴费金额、缴费方式。
        {
	        "openid": "1",
	        "nd":"2022",
	        "ywlx": "A0103001"
        }
        */
        else if (ls_ywlx.equals("A0103001")){
            jsonReturn=wdzdJfjlService.GetJfjl(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }
        /*
        协议代码：A0103002
        功能名称：查询缴费明細
        业务说明：返回結果费用ID、业务年月、用水数量、应缴金额、实缴金额
        {
	        "jyid": "10000000011918",
	        "ywlx": "A0103002"
        }
       */
       else if (ls_ywlx.equals("A0103002")){
           jsonReturn=wdzdJfmxService.GetJfmx(parameters);
           return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
       }

        /*
        协议代码：A0101004
        功能名称：生成预缴费订单
        {
            "ywlx": "A0101004",
            "fyid": "001,002,003"
            "fyje": "5,10,20"
        }
        http://127.0.0.1:8083/fwjk/wxzf
       */

        else if (ls_ywlx.equals("A0101004")){
            jsonReturn=sfjnWxzfyddService.Wxzfydd(parameters);
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
        }

        return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);//返回请求
    }

}

