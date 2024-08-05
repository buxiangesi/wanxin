package com.shggc.project.fwjk.wxzf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shggc.project.fwjk.wxzf.service.SfjnWxzfyddService;
import com.shggc.project.youbeisoft.base.SqlUtil;
import com.shggc.project.youbeisoft.tools.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wxAuth")
//开启事务
@Transactional
public class WxLoginController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.callBack}")
    private String callBack;

    @Value("${wx.sendRedirect}")
    private String sendRedirect;

    @Value("${wx.token}")
    private String token;

    @Value("${wx.xcxAppId}")
    private String xcxAppId;

    @Value("${wx.xcxAppSecret}")
    private String xcxAppSecret;

    @Autowired
    SfjnWxzfyddService sfjnWxzfyddService;  //微信缴费到账

    @Autowired
    SqlUtil sqlUtil;

    //微信小程序通过code获取openid(yanp 2022-03-06增加)
    //http://127.0.0.1:8083/wxAuth/getOpenId
    //https://wx.qhdtssw.cn/wxAuth/getOpenId/{code}
    @RequestMapping("/getOpenId/{code}")
    public ResponseEntity<Object> getOpenId(@PathVariable String code){

        System.out.println("微信小程序通过code:"+code+"获取openid");
        String ls_code = "0";
        String ls_message = "";
        JSONObject jsonReturn= new JSONObject();   //接口返回值

        try {
            // 配置请求参数
            Map<String, String> param = new HashMap<>();
            param.put("appid", xcxAppId);
            param.put("secret", xcxAppSecret);
            param.put("js_code", code);
            param.put("grant_type", "authorization_code");

            // 发送请求
            String wxResult = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", param);
            JSONObject json = JSONObject.parseObject(wxResult);

            System.out.println("微信小程序通过code:"+code+"获取openid请求返回:"+json.toJSONString());

            if (wxResult.indexOf("errcode") >= 0){
                jsonReturn.put("code", "-1");
                jsonReturn.put("message", json.getString("errcode")+":"+json.getString("errmsg"));
                jsonReturn.put("session_key", "");
                jsonReturn.put("openid", "");
                return ResponseEntity.status(HttpStatus.OK).body(jsonReturn); //返回请求
            }else {
                String openid = json.get("openid").toString();
                String session_key = json.get("session_key").toString();
                jsonReturn.put("session_key", session_key);
                jsonReturn.put("openid", openid);
                System.out.println("微信小程序通过code获取openid返回:"+openid);
            }
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
            System.out.println("捕获错误："+ls_message);
        }

        if (ls_code.equals("-1")){
            jsonReturn.put("code", ls_code);                              //成功返回0,失败返回-1
            jsonReturn.put("message", ls_message);                        //成功message为空,失败message为捕获异常的信息
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn); //返回请求
        }

        jsonReturn.put("code", ls_code);                                 //成功返回0,失败返回-1
        jsonReturn.put("message", ls_message);
        return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);    //返回请求
    }

    //自动注册用户信息(yanp 2022-03-05增加)
    //https://wx.qhdtssw.cn/wxAuth/AutoRegUser
    @PostMapping(path = "/AutoRegUser")
    public ResponseEntity<Object> AutoRegUser(@RequestBody Map<String, String> parameters) {

        System.out.println("自动注册小程序用户信息:"+JSON.toJSONString(parameters));
        //=================================================判断openid在数据库中是否存在(yanp2022-03-18增加)
        String ls_code = "0";
        String ls_message = "";

        String ls_nickname=parameters.get("nickname");
        String ls_sex=parameters.get("sex");
        String ls_language=parameters.get("language");
        String ls_city=parameters.get("city");
        String ls_province=parameters.get("province");
        String ls_country=parameters.get("country");
        String ls_headimgurl=parameters.get("headimgurl");
        String ls_session_key=parameters.get("session_key");

        String ls_openid = parameters.get("openid");//先获取openid
        String ls_sql="select openid from T_WX_GZHYH where openid = #{openid}";

        JSONObject jsonReturn= new JSONObject();   //接口返回值
        jsonReturn.put("code", "0");               //成功返回0,失败返回-1
        jsonReturn.put("message", "");

        Map<String, Object> params = new HashMap<String, Object>();

        try{
            params.put("sql", ls_sql);             //将sql语句设置到map
            params.put("openid", ls_openid);       //将openid设置到map
            List<Map<String, Object>> list_info = sqlUtil.preparedSelectMap(params);//执行查询

            JSONArray jsonArray = new JSONArray();
            String jsonStr = JSON.toJSONString(list_info);        //map转json字符串
            jsonArray = JSONArray.parseArray( jsonStr );          //json字符串转json数组

            if (jsonArray.size() == 0){
                //如果没查询到用户注册信息则进行数据的插入操作
                ls_sql = "insert into T_WX_GZHYH(openid, nickname, sex, language, city, province, country, headimgurl, session_key，zcsj)" +
                         " values (#{openid}, #{nickname}, #{sex}, #{language}, #{city}, #{province}, #{country}, #{headimgurl}, #{session_key}, sysdate)";

                params.put("sql", ls_sql);                       //将sql语句设置到map
                params.put("openid", ls_openid);
                params.put("nickname",ls_nickname);
                params.put("sex",ls_sex);
                params.put("language",ls_language);
                params.put("city",ls_city);
                params.put("province",ls_province);
                params.put("country",ls_country);
                params.put("headimgurl",ls_headimgurl);
                params.put("session_key",ls_session_key);

                int li_ret=sqlUtil.preparedInsert(params);

                if (li_ret == -1){
                    ls_code = "-1";
                    ls_message = "注册用户信息失败！";
                }
            }
        }catch (Exception e){
            ls_code = "-1";
            ls_message = e.getMessage();
            System.out.println("错误："+ls_message);
        }

        if (ls_code.equals("-1")){
            jsonReturn.put("code", ls_code);                              //成功返回0,失败返回-1
            jsonReturn.put("message", ls_message);                        //成功message为空,失败message为捕获异常的信息
            return ResponseEntity.status(HttpStatus.OK).body(jsonReturn); //返回请求
        }

        System.out.println("自动注册小程序用户信息成功:"+JSON.toJSONString(parameters));

        jsonReturn.put("code", ls_code);                                 //成功返回0,失败返回-1
        jsonReturn.put("message", ls_message);
        return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);    //返回请求
    }

    //测试微信预支付订单(yanp2022-04-20增加)
    //http://127.0.0.1:8083/wxAuth/test
    @RequestMapping("/test")
    public ResponseEntity<Object> test() throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        JSONObject jsonReturn= new JSONObject();   //接口返回值
        jsonReturn.put("code", "0");               //成功返回0,失败返回-1
        jsonReturn.put("message", "");

        Map<String, Object> map = new HashMap();
        // 支付的产品（小程序或者公众号，主要需要和微信支付绑定哦）
        map.put("appid", WxpayConfig.app_id);
        // 支付的商户号
        map.put("mchid", WxpayConfig.mch_id);

        //临时写死配置

        String ls_out_trade_no=WeixinchatPayUtils.getNonceStr();

        map.put("description","水费");//订单名称
        map.put("out_trade_no",ls_out_trade_no);//订单号(唯一)
        map.put("notify_url", WxpayConfig.notify_order_url);

        Map<String, Object> amount = new HashMap();
        //订单金额 单位分
        amount.put("total", Integer.parseInt("20") * 100);
        amount.put("currency", "CNY");
        map.put("amount", amount);
        // 设置小程序所需的opendi
        Map<String, Object> payermap = new HashMap();
        payermap.put("openid", "omwHV4sj1Fj8PvehfEXA5ipn2pVg");
        map.put("payer", payermap);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(map);

        Map<String, Object> stringObjectMap = null;
        HashMap<String, Object> dataMap = null;

        try {
                stringObjectMap = HttpUtils.doPostWexin(WechatUrlConfig.JSAPIURL, body);//获取预支付订单id
                dataMap = WeixinchatPayUtils.getTokenJSAPI(WxpayConfig.app_id, String.valueOf(stringObjectMap.get("prepay_id")));
            }
            catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(dataMap);//返回请求
    }

    //测试微信到账接口(yanp 2022-04-20增加)
    //http://127.0.0.1:8083/wxAuth/testWxdz
    //{"out_trade_no":out_trade_no,
    // "flag":"1"
    // }
    @PostMapping(path = "/testWxdz")
    public ResponseEntity<Object> testWxdz(@RequestBody Map<String, String> parameters) {

        //params.put("out_trade_no",out_trade_no);//订单号
        //params.put("flag","1");                 //到账标识（1成功,2失败）
        sfjnWxzfyddService.Wxzf(parameters);      //到账关键函数

        JSONObject jsonReturn= new JSONObject();  //接口返回值
        jsonReturn.put("code", "0");              //成功返回0,失败返回-1
        jsonReturn.put("message", "");

        return ResponseEntity.status(HttpStatus.OK).body(jsonReturn);    //返回请求
    }

    /*
    测试解密(下面参数是微信支付回调返回信息)
    {
    "id": "93b3ae1f-256e-53fc-ba9b-d4dfa6873907",
    "create_time": "2022-04-24T20:30:26+08:00",
    "resource_type": "encrypt-resource",
    "event_type": "TRANSACTION.SUCCESS",
    "summary": "支付成功",
    "resource": {
        "original_type": "transaction",
        "algorithm": "AEAD_AES_256_GCM",
        "ciphertext": "AsAege0c6dOE94z756ts94aXdIeNHslNMVZ+u4noyfSU8VrNIDdbHzL1vqB9m6QiGEhTiRelV0qWQyzFEH+QX0RRaWPlCid33zxNIH9wgdKqTOJDQ0EIv5NCeXjVj1fJx5QurgQJqIoHh6Zb7Cf8D9yQVcm3PyAzWwLojevLX6UylCTGznlJ7NoctXtAjSM3Jo3aksximzCVzQ0Zt3HpAvHyOcYMf+H0Jn3vQqFh9v1v2tQRmxVOce3EeWCLdlfWb5gWc25XcglgFSqy1f3at81OOpWH8xwkvqYvjElWtto3TeabGgMa6lHBNuLO6+ztjcXQW3hwc6o0od7MoRuqFRn2hDTBOchX/EAQfFHEK7wXVCvSvLSjEnKihFVOX0jfKKXP/bG4/lmXTp7btyX5Vyc/KSTMQh3vf+ynpnchoczLgvy9HtPEY8yP0KSMe1M41LwzSeiuZshD6lRpLdQxV/Fg6ViEubrK0kY+hcacsMPKerNu0Q0tLWcChH4swCBsgDrC8pRS445QTDH+/tIQSfoZJzKb2iV/jfGujpgzjnfI8jw+9K7EKd1l2AaL",
        "associated_data": "transaction",
        "nonce": "wlJxQMUdE2TR"
        }
    }
    下面接口传resource即可
    http://127.0.0.1:8083/wxAuth/testJiemi
    {
        "original_type": "transaction",
        "algorithm": "AEAD_AES_256_GCM",
        "ciphertext": "AsAege0c6dOE94z756ts94aXdIeNHslNMVZ+u4noyfSU8VrNIDdbHzL1vqB9m6QiGEhTiRelV0qWQyzFEH+QX0RRaWPlCid33zxNIH9wgdKqTOJDQ0EIv5NCeXjVj1fJx5QurgQJqIoHh6Zb7Cf8D9yQVcm3PyAzWwLojevLX6UylCTGznlJ7NoctXtAjSM3Jo3aksximzCVzQ0Zt3HpAvHyOcYMf+H0Jn3vQqFh9v1v2tQRmxVOce3EeWCLdlfWb5gWc25XcglgFSqy1f3at81OOpWH8xwkvqYvjElWtto3TeabGgMa6lHBNuLO6+ztjcXQW3hwc6o0od7MoRuqFRn2hDTBOchX/EAQfFHEK7wXVCvSvLSjEnKihFVOX0jfKKXP/bG4/lmXTp7btyX5Vyc/KSTMQh3vf+ynpnchoczLgvy9HtPEY8yP0KSMe1M41LwzSeiuZshD6lRpLdQxV/Fg6ViEubrK0kY+hcacsMPKerNu0Q0tLWcChH4swCBsgDrC8pRS445QTDH+/tIQSfoZJzKb2iV/jfGujpgzjnfI8jw+9K7EKd1l2AaL",
        "associated_data": "transaction",
        "nonce": "wlJxQMUdE2TR"
        }
    */
    @PostMapping(path = "/testJiemi")
    public Map testJiemi(@RequestBody Map<String, String> parameters) {
        System.out.println("1----------->微信支付回调开始");
        Map<String, Object> result = new HashMap();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //log.info("签名验证成功");
            Map<String, String> resource = parameters;
            // 5：回调报文解密
            System.out.println("5----------->回调报文解密");
            AesUtil aesUtil = new AesUtil(WxpayConfig.v3Key.getBytes());
            //解密后json字符串
            String decryptToString = aesUtil.decryptToString(
                    resource.get("associated_data").getBytes(),
                    resource.get("nonce").getBytes(),
                    resource.get("ciphertext"));

            System.out.println("回调报文解密:"+decryptToString);
            // log.info("2------------->decryptToString====>{}", decryptToString);

            //6：获取微信支付返回的信息
            System.out.println("6----------->获取微信支付返回的信息");
            Map<String, Object> jsonData = objectMapper.readValue(decryptToString, Map.class);
            System.out.println("获取微信支付返回的信息:"+JSON.toJSONString(jsonData));
            //7:支付状态的判断 如果是success就代表支付成功
            System.out.println("7----------->支付状态的判断,如果是success就代表支付成功");

            if ("SUCCESS".equals(jsonData.get("trade_state"))) {
                // 8：获取支付的交易单号，流水号，和附属参数
                String out_trade_no = jsonData.get("out_trade_no").toString();
                String transaction_id = jsonData.get("transaction_id").toString();
                String attach = jsonData.get("attach").toString();

                System.out.println("8----------->获取支付的交易单号，流水号，和附属参数");

                System.out.println("支付的交易单号out_trade_no:"+out_trade_no);
                System.out.println("流水号transaction_id:"+transaction_id);
                System.out.println("附属参数attach:"+attach);

                //TODO 根据订单号查询支付状态，如果未支付，更新支付状态 为已支付
                //   log.info("3----------->微信支付成功,支付流水号是：{},附属参数是：{}", out_trade_no, attach);
                //   log.info("4----------->微信支付成功,支付流水号是：{}", transaction_id);
                // 转换附属参数
                HashMap<String, Object> map = JsonUtil.string2Obj(attach, HashMap.class);

                // 9：保存用户支付信息
                //=================================================================================
                System.out.println("9----------->设置到账信息");
                Map<String, String> params = new HashMap<String, String>();;
                params.put("out_trade_no",out_trade_no);//订单号
                params.put("flag","1");                 //到账标识（1成功,2失败）
                sfjnWxzfyddService.Wxzf(params);        //到账关键函数
                //=================================================================================

                result.put("code", "SUCCESS");
                result.put("message", "成功");
            }else{
                result.put("code", "fail");
                result.put("message", "失败");
            }

        } catch (Exception e) {
            result.put("code", "fail");
            result.put("message", "系统错误");
            e.printStackTrace();
        }
        return result;
    }

    //http://127.0.0.1:8083/wxAuth/pay/callback
    //https://sw.home365v.cn/wxAuth/pay/callback
    //微信支付回调接口(支付成功后微信会根据支付设置自动回调该函数,如果支付成功在此设置到账标志)
    //(yanp 2022-04-19增加)
    @PostMapping("pay/callback")
    public Map orderPayCallback(@RequestBody Map body, HttpServletRequest request) {
        System.out.println("1----------->微信支付回调开始");
        Map<String, Object> result = new HashMap();
        //1：获取微信支付回调的获取签名信息
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            // 2: 开始解析报文体
//            System.out.println("2----------->开始解析报文体");
//            String data = objectMapper.writeValueAsString(body);
//            String message = timestamp + "\n" + nonce + "\n" + data + "\n";
//            System.out.println("开始解析报文体:"+message);
//            //3：获取应答签名
//            System.out.println("3----------->获取应答签名");
//            String sign = request.getHeader("Wechatpay-Signature");
//            System.out.println("获取应答签名:"+sign);
//            //4：获取平台对应的证书
//            System.out.println("4----------->获取平台对应的证书");
//            String serialNo = request.getHeader("Wechatpay-Serial");
//            if (!WxpayConfig.certificateMap.containsKey(serialNo)) {
//                WxpayConfig.certificateMap = WechatPayUtils.refreshCertificate();
//            }
//            X509Certificate x509Certificate = WxpayConfig.certificateMap.get(serialNo);
//            if (!WechatPayUtils.verify(x509Certificate, message.getBytes(), sign)) {
//                throw new IllegalArgumentException("微信支付签名验证失败:" + message);
//            }
            //log.info("签名验证成功");
            Map<String, String> resource = (Map) body.get("resource");
            // 5：回调报文解密
            System.out.println("5----------->回调报文解密");
            AesUtil aesUtil = new AesUtil(WxpayConfig.v3Key.getBytes());
            //解密后json字符串
            String decryptToString = aesUtil.decryptToString(
                    resource.get("associated_data").getBytes(),
                    resource.get("nonce").getBytes(),
                    resource.get("ciphertext"));

            System.out.println("回调报文解密:"+decryptToString);
            // log.info("2------------->decryptToString====>{}", decryptToString);

            //6：获取微信支付返回的信息
            System.out.println("6----------->获取微信支付返回的信息");
            Map<String, Object> jsonData = objectMapper.readValue(decryptToString, Map.class);
            System.out.println("获取微信支付返回的信息:"+JSON.toJSONString(jsonData));
            //7:支付状态的判断 如果是success就代表支付成功
            System.out.println("7----------->支付状态的判断,如果是success就代表支付成功");

            if ("SUCCESS".equals(jsonData.get("trade_state"))) {
                // 8：获取支付的交易单号，流水号，和附属参数
                String out_trade_no = jsonData.get("out_trade_no").toString();
                String transaction_id = jsonData.get("transaction_id").toString();
                String attach = jsonData.get("attach").toString();

                System.out.println("8----------->获取支付的交易单号，流水号，和附属参数");

                System.out.println("支付的交易单号out_trade_no:"+out_trade_no);
                System.out.println("流水号transaction_id:"+transaction_id);
                System.out.println("附属参数attach:"+attach);

                //TODO 根据订单号查询支付状态，如果未支付，更新支付状态 为已支付
                //   log.info("3----------->微信支付成功,支付流水号是：{},附属参数是：{}", out_trade_no, attach);
                //   log.info("4----------->微信支付成功,支付流水号是：{}", transaction_id);
                // 转换附属参数
                HashMap<String, Object> map = JsonUtil.string2Obj(attach, HashMap.class);

                // 9：保存用户支付信息
                //=================================================================================
                System.out.println("9----------->设置到账信息");
                Map<String, String> params = new HashMap<String, String>();;
                params.put("out_trade_no",out_trade_no);//订单号
                params.put("flag","1");                 //到账标识（1成功,2失败）
                sfjnWxzfyddService.Wxzf(params);        //到账关键函数
                //=================================================================================

                result.put("code", "SUCCESS");
                result.put("message", "成功");
            }else{
                result.put("code", "fail");
                result.put("message", "失败");
            }

        } catch (Exception e) {
            result.put("code", "fail");
            result.put("message", "系统错误");
            e.printStackTrace();
        }
        return result;
    }

    //微信公众号验证token
    @RequestMapping("/checkToken")
    public String checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //https://sw.home365v.cn/wxAuth/checkToken
        //http://127.0.0.1/wxAuth/checkToken
        /**
         * 微信消息接收和token验证
         */
        System.out.println("----------------验证微信服务号信息开始----------");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("----验证服务号结束.........");
            return echostr;
        }else{
            return null;
        }
    }

    //微信公众号菜单登录
    //https://sw.home365v.cn/wxAuth/login/m01
    @RequestMapping("/login/{menuid}")
    public void wxLogin(HttpServletResponse response,@PathVariable String menuid) throws IOException {
        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        //String callBack = "https://sw.home365v.cn/wxAuth/callBack";

        //请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(callBack+"/"+menuid) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //微信公众号回调方法
    @RequestMapping("/callBack/{menuid}")
    public void wxCallBack(HttpServletRequest request, HttpServletResponse response,@PathVariable String menuid) throws IOException {

        System.out.println("回调菜单id:" + menuid);
        String ls_sql_select="select * from T_WX_MENU where menuid=#{menuid}";
        Map<String, Object> params_select = new HashMap<String, Object>();
        JSONArray jsonArray_select = new JSONArray();

        params_select.put("sql", ls_sql_select);                                //将sql语句设置到map
        params_select.put("menuid", menuid);                                    //将openid设置到map
        List<Map<String, Object>> list_info_select = sqlUtil.preparedSelectMap(params_select);//执行查询

        if (list_info_select.size() > 0){
            sendRedirect = (String) list_info_select.get(0).get("sendredirect");
        }

        String code = request.getParameter("code");

        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        String result = HttpClientUtil.doGet(url);

        System.out.println("请求获取access_token:" + result);
        //返回结果的json对象
        JSONObject resultObject = (JSONObject) JSONObject.parse(result);

        //请求获取userInfo
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + resultObject.getString("access_token") +
                "&openid=" + resultObject.getString("openid") +
                "&lang=zh_CN";

        String resultInfo = HttpClientUtil.doGet(infoUrl);

        //此时已获取到userInfo，再根据业务进行处理
        System.out.println("请求获取userInfo:" + resultInfo);

        JSONObject jsonParam = JSONObject.parseObject(resultInfo);
        String openid = jsonParam.getString("openid").trim();
        String nickname = jsonParam.getString("nickname").trim();
        String sex = jsonParam.getString("sex").trim();
        String language = jsonParam.getString("language").trim();
        String city = jsonParam.getString("city").trim();
        String province = jsonParam.getString("province").trim();
        String country = jsonParam.getString("country").trim();
        String headimgurl = jsonParam.getString("headimgurl").trim();

        String ls_sql="select * from T_WX_GZHYH where openid=#{openid}";
        Map<String, Object> params = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        try{
            params.put("sql", ls_sql);                            //将sql语句设置到map
            params.put("openid", openid);                         //将openid设置到map
            List<Map<String, Object>> list_info = sqlUtil.preparedSelectMap(params);//执行查询
            String jsonStr = JSON.toJSONString(list_info);        //map转json字符串
            jsonArray = JSONArray.parseArray( jsonStr );          //json字符串转json数组
            if (jsonArray.size() == 0){
                //数据库中不存在该关注用户,则自动进行注册
                System.out.println("自动进行注册openid为："+openid+"的用户！");
                Map<String, Object> params_insert = new HashMap<String, Object>();
                String ls_insert_sql="insert into T_WX_GZHYH(OPENID,NICKNAME,SEX,LANGUAGE,CITY,PROVINCE,COUNTRY,HEADIMGURL,ZCSJ) values (#{OPENID},#{NICKNAME},#{SEX},#{LANGUAGE},#{CITY},#{PROVINCE},#{COUNTRY},#{HEADIMGURL},sysdate)";
                params_insert.put("sql", ls_insert_sql);
                params_insert.put("OPENID", openid);
                params_insert.put("NICKNAME", nickname);
                params_insert.put("SEX", sex);
                params_insert.put("LANGUAGE", language);
                params_insert.put("CITY", city);
                params_insert.put("PROVINCE", province);
                params_insert.put("COUNTRY", country);
                params_insert.put("HEADIMGURL", headimgurl);
                sqlUtil.preparedInsert(params_insert);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        response.sendRedirect(sendRedirect+"?openid="+openid);//跳转到小程序指定页面
    }

    public static boolean checkSignature(String signature, String timestamp, String nonce,String token) {
        String[] paramArr = new String[] { token, timestamp, nonce };
        Arrays.sort(paramArr);
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }


}
