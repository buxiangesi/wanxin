package com.shggc.project.fwjk.dtxx.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.project.fwjk.dtxx.domain.FcGeometry;
import com.shggc.project.fwjk.dtxx.domain.FcProperties;
import com.shggc.project.fwjk.dtxx.domain.TJcFcGeoJson;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxx;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxxJson;
import com.shggc.project.fwjk.dtxx.domain.TJcFcxxJsonList;
import com.shggc.project.fwjk.dtxx.service.TJcFcxxService;

@Controller
@RequestMapping("/dtxx")
/**
 * 
 * @author 提供获取房产点位信息 提供修改房产点位信息
 *
 */
public class DtController extends BaseController {

	@Autowired
	private TJcFcxxService tJcFcxxService;

	@GetMapping(value = "/getMapJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	/**
	 * 
	 * @param tJcFcxx 房产房屋信息类
	 * @return 房产点位信息列表
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public TJcFcxxJsonList fcxxList(TJcFcxx tJcFcxx) throws JsonMappingException, JsonProcessingException {
		//最终返回的房产信息以及房产点位信息集合
		TJcFcxxJsonList tJcFcxxJsonList = new TJcFcxxJsonList();
		tJcFcxxJsonList.setType("FeatureCollection");
		List<TJcFcxxJson> list = new ArrayList<TJcFcxxJson>();
		// 取到房产信息，将房产信息按照JSON格式进行组织。
		List<TJcFcxx> tJcFcxxList = tJcFcxxService.selectTJcFcxxList(tJcFcxx);
		Iterator<TJcFcxx> iterator = tJcFcxxList.iterator();
		//遍历数据库返回的房产房屋信息
		while (iterator.hasNext()) {
			TJcFcxx next = iterator.next();
			//单个房产信息的json格式，包括FcProperties和FcGeometry
			TJcFcxxJson tJcFcxxJson = new TJcFcxxJson();
			tJcFcxxJson.setType("Feature");
			FcProperties fcProperties = new FcProperties(next.getGuid(), next.getFwzl(), next.getCqr(), next.getFwcqz(),
					next.getFwcb(), next.getFwjsh(), next.getJzmjh(), next.getTdsyzh(), next.getTdsymj(),
					next.getQllx(), next.getQlxz(), next.getBz());
			tJcFcxxJson.setProperties(fcProperties);
			//将json字符串，转换为三维数组格式
			ObjectMapper om=new ObjectMapper();
			FcGeometry fcGeometry = new FcGeometry("Polygon", om.readValue(next.getKz1(), double[][][].class));
			tJcFcxxJson.setGeometry(fcGeometry);
			list.add(tJcFcxxJson);
		}
		tJcFcxxJsonList.setFeatures(list);
		return tJcFcxxJsonList;
	}

	@PostMapping(value = "/postMapJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	/**
	 * 
	 * @param tJcFcGeoJson 接收的房产点位信息
	 * @return 操作成功返回ok,否则返回err
	 */
	public String postMapJson(@RequestBody TJcFcGeoJson tJcFcGeoJson) {
		//接受地理系统发送的信息，分为删除地理信息del,更改地理信息changeGeo，添加地理信息add
		String type = tJcFcGeoJson.getType();
		TJcFcxx tJcFcxx = new TJcFcxx();
		switch (type) {
		//删除地理信息，将相应房产编号的kz1字段清空
		case "del": {
			tJcFcxx.setGuid(tJcFcGeoJson.getGuid());
			tJcFcxx.setKz1("");
			if(tJcFcxxService.changeGeo(tJcFcxx)==1)
				return "ok";
			else 
				return "err";
		}
		//新增地理信息，如果此Guid对应的kz1有信息，返回错误。
		//否则将房产编号为guid的kz1设置为地理信息
		case "add": {
			String kz1=tJcFcxxService.selectTJcFcxxByBh(tJcFcGeoJson.getGuid()).getKz1();
			if(kz1!=null&&!kz1.equals("")){
				return "err";
			}
			tJcFcxx.setGuid(tJcFcGeoJson.getGuid());
			try {
				ObjectMapper om = new ObjectMapper();
				//将三维数组信息转换为字符串
				String str = om.writeValueAsString(tJcFcGeoJson.getGeometry().getCoordinates());
				tJcFcxx.setKz1(str);
				
			}
			catch(JsonProcessingException e) {
				e.printStackTrace();
				return "err";
			}
			if(tJcFcxxService.changeGeo(tJcFcxx)==1)
				return "ok";
			else 
				return "err";
		}
		//如果有OldGuid，需要将OldGuid对应的kz1先查找出来，将其设置为新的guid对应的房产信息的kz1.
		//modify本质上是对guid的modify.
		case "modify": {
			String oldGuid = tJcFcGeoJson.getOldGuid();
			String kz1 = "";
			if (oldGuid != null && !oldGuid.equals("")) {
				
				kz1 = tJcFcxxService.selectTJcFcxxByBh(oldGuid).getKz1();
				tJcFcxx.setGuid(oldGuid);
				tJcFcxx.setKz1("");
				tJcFcxxService.changeGeo(tJcFcxx);
			}
			tJcFcxx.setGuid(tJcFcGeoJson.getGuid());
			tJcFcxx.setKz1(kz1);
			if(tJcFcxxService.changeGeo(tJcFcxx)==1)
				return "ok";
			else 
				return "err";
		}
		//修改地理信息
		case "changeGeo": {
			tJcFcxx.setGuid(tJcFcGeoJson.getGuid());
			try {
				ObjectMapper om = new ObjectMapper();
				String str = om.writeValueAsString(tJcFcGeoJson.getGeometry().getCoordinates());
				tJcFcxx.setKz1(str);
			}
			catch(JsonProcessingException e) {
				e.printStackTrace();
				return "err";
			}
			if(tJcFcxxService.changeGeo(tJcFcxx)==1)
				return "ok";
			else 
				return "err";
		}
		default:
			return "err";
		}
	}
}
