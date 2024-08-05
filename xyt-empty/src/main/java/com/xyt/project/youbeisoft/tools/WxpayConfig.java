package com.xyt.project.youbeisoft.tools;

import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Data
//@Component
public class WxpayConfig {

    public static String app_id = "wx9a8f7b02a06c2e79"; // 公众账号ID
    public static String mch_id = "1619863275"; // 商户号
    public static String mchSerialNo = "5844C337FC6B0B6A1C20F244CB22C098911143A7"; //微信商家api序列号
    public static String v3Key = "UptFU7LuJ0QQbeayx9eszKNB4UfR6EIZ"; // 回调报文解密V3密钥key
    public static String KeyPath = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDxQRqSRm+13W04p82WJoHSDv1ci1YEBKeegL5dO0kNfIS5Ha3fRKT8riL59AEt6lA3Oe6brt2WQfubnAFy13xR0JbtwcvxTSZbZF343AdRZufizepDFpasdCVybnTAi8UuGpWiZ52SFU14fBFTmXMzs/zv6GvrFBjV45EdzIIbacEqbHRw6Tqxfn6TwIQLcgbA8JxT7E5woTfkMUvtFS42wtlR062bCElyLPNh5gqyrk1bB01PG5EH1Xlv/NxrewG7P0Hc2golCfxi4oi6+JPT4+PY9hqOhKX0R7PIOKibblKpuzZJN86e1nnOvsWSmvcxNCjCWsFNqQnYfTjrvYtTAgMBAAECggEAGbljZ+1yw+WUW9IorbX1abyHb3K7/Tmx1cjMg4xqwbFhRYvQmlnrbbXzrMtDxO2TGBBpTNhccEUg2vrLfL9VVjPzo+5iHJypVl1hV8O+VAOw/hIOxJCICGNqCsWUVWOR5tSW1/ZMauMym4I/JrbZ/c1XTw1tVGeULlpRgufnh5yOIs7qknqg0KFNTdLtG/TrAB/gE88Ej8DkBtnqVEJ8iih3AxfUTvfTNjK25d5bGfNqg1Kbwf4ZtVoecC2NJDcc0NhuqzC33BWUyH8lnqucmIQIVxBMQ3R2wYsp+H2R1epMsqAhXHivCD/yP/Cj/ICsCSlZUG2VsIp4+jvWSNkC4QKBgQD/+s9t2oOEoqBWx+rl/P4Q3jDuqSQxcOgDBsTTSZ2rqAhVbN5qhXd6u3UVayd6DYrqwgDyBoWP/1++6NBX5nbn8itOugnKfc6Q6CXSbWWhQqGnHKkvmqKbTipNZWPtV9BDmjUkle4vltJHyzWrI+oSeDDVIY9q/6LrktQ+YBF1jQKBgQDxRf63HQVQ1Adoc3bY29LO3mTRbwccT63uY9+bJD4vTQsRUZWYaaxGzMZVhEDNAc3KZGCCrhFmKeBqScZ6yI9raHbhRbYzKxADXLMcFGEm4F2buX4tyFW5illZ8W2hfhSoc256KLw7NJndUdOxeFabYxd1RDkpiHx25qyfhWWcXwKBgG9GTy944t0geMTgqPsLTEJ25ByLSGF5rFHMenlCshixtdW0usbhd/vRS2dhKksDYhUE+rY7IYpkP/HUcOUVGTFg83SIqMLuh4I0eGSOC7TbKzxi2CUKeo86nmcAoTzGhJEuktVxm5XyYTy2WIEp3pHiz4+GxDqGIAN502u+BTjtAoGBAJAVDayKfx8qz5W3xlRwye5R7wEG/ccRajv/lowOU7hpmN9pyh0vlfrd1N9C0HPczOk9kkjX7HoHLgYrMoUmekdZJg0XVVwvLivj0u5E2VOfDyes2UY76b2Yeg11WNPk+C80Z9g5tVbh7bS2ZeYIiCfZQCv+Zuh/olxbGuFUhz6fAoGBAPNMbhb8TLC4VU+H/9BXPgSLqsytLqV9z4PepGBdtBwq7gxikWOyA3jFfeau2EsZ8nN2gZGiKxVJWrK7uVi/24+bZrKtec1I99A07FnMylFODMJp6EG2v3DPiSgvSElVH3EZKl+phSil3xPueT3v9WEPHvO4IHhVPjQYoldvuc1i"; // 商户的key【API密匙】存放路径
    public static String notify_order_url = "https://wx.qhdtssw.cn/wxAuth/pay/callback"; // 服务器异步通知页面路径--下单
    public static String notify_refound_url = "*************************************";   // 服务器异步通知页面路径-退款
    public static String return_url = "*************************************";           // 服务器同步通知页面路径
    public static Map<String, X509Certificate> certificateMap = new ConcurrentHashMap<>(); // 定义全局容器 保存微信平台证书公钥

}
