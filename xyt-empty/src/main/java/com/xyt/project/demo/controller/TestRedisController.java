package com.xyt.project.demo.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyt.common.utils.DateUtils;
import com.xyt.common.utils.PageUtils;
import com.xyt.common.utils.StringUtils;
import com.xyt.common.utils.security.ShiroUtils;
import com.xyt.common.utils.sql.SqlUtil;
import com.xyt.framework.web.domain.AjaxResult;
import com.xyt.framework.web.domain.AjaxResult.Type;
import com.xyt.framework.web.page.PageDomain;
import com.xyt.framework.web.page.TableDataInfo;
import com.xyt.framework.web.page.TableSupport;
import com.xyt.project.system.user.domain.User;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/demo")
public class TestRedisController{
	
//	@Autowired
//	RedisTemplate <String , Object> rt;
//	
//	@GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	//@Cacheable("redis")
//	String Hello() {
//		rt.opsForValue().set("eee","sss");
//		return "Hello";
//	}
	
}
