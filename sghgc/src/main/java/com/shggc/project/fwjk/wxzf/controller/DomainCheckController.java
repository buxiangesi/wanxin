package com.shggc.project.fwjk.wxzf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DomainCheckController {

    @Value("${wx.txt}")
    private String txt;
    //校验微信域名添加(yanp 2022-03-25增加)
    @RequestMapping("/MP_verify*")
    public String wxPrivateKey(){
        //直接返回你下载的授权文件里的内容就好
        return txt;
    }

}
