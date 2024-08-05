package com.xyt.project.youbeisoft.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyt.project.youbeisoft.base.mapper.ISqlMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//2022-10-17yanp增加简化方法工具类
@Service
public class SqlUtil
{
	@Autowired
	ISqlMapper sqlMapper;
	private static SqlUtil sqlUtil;

	@PostConstruct
	public void init(){
		sqlUtil = this;
		sqlUtil.sqlMapper = this.sqlMapper;
	}

	//查询单条记录的单个字段
	public String selectOne(String sql)
	{
		return sqlMapper.selectOne(sql);
	}

	//预编译查询单条记录的单个字段
	public String preparedSelectOne(Map<String, Object> map)
	{
		return sqlMapper.preparedSelectOne(map);
	}

	//执行insert语句
	public int insert(String sql)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.insert(sql);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
		}
		return li_return;
	}
	//执行delete语句
	public int delete(String sql)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.delete(sql);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
		}
		return li_return;
	}
	//执行update语句
	public int update(String sql)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.update(sql);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
		}
		return li_return;
	}
	//执行查询返回list
	public List<Map<String, Object>> selectList(String sql)
	{
		return transformUpperCase(sqlMapper.selectList(sql));
	}
	//预编译执行查询
	public List<Map<String, Object>> preparedSelectMap(Map<String, Object> map)
	{
		return transformUpperCase(sqlMapper.preparedSelectMap(map));
	}

	//预编译执行insert
	public int preparedInsert(Map<String, Object> map)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.preparedInsert(map);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
		}
		return li_return;
	}

	//预编译执行update
	public int preparedUpdate(Map<String, Object> map)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.preparedUpdate(map);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return li_return;
	}

	//预编译执行delete
	public int preparedDelete(Map<String, Object> map)
	{
		int li_return = 1;
		try
		{
			li_return = sqlMapper.preparedDelete(map);
		} catch (Exception e) {
			li_return = -1;
			System.out.println(e.getMessage());
		}
		return li_return;
	}

	//1.简单的执行存储过程(字符串输入,字符串输出)
	public String getSerialCodeBySiteCode(Map<String, Object> map){
		sqlMapper.getSerialCodeBySiteCode(map);
		return map.get("tv").toString();
	}
	//2.简单的执行存储过程返回游标,返回值需要用实体接收
	public <CourierInfo> List<CourierInfo> listCourierInfoList(Map<String, Object> map) {
		sqlMapper.listCourierInfoList(map);
		return (List) map.get("page_emps");
	}
	//3.执行存储过程返回list结果集
	public List<Map<String, Object>> testcommon_cursor(Map<String, Object> map)
	{
		sqlMapper.testcommon_cursor(map);
		return (List) map.get("page_emps");
	}
	//4.执行存储过程返回map对象
	public Map<String, Object> testcommon_cursor1(Map<String, Object> map)
	{
		sqlMapper.testcommon_cursor1(map);
		return map;
	}
	//5.执行存储过程主分页程序prc_querysplit_parent返回map对象
	public Map<String, Object> prc_querysplit_parent(Map<String, Object> map)
	{
		sqlMapper.prc_querysplit_parent(map);
		return map;
	}
	//6.执行存储过程子分页程序prc_querysplit_child返回map对象
	public Map<String, Object> prc_querysplit_child(Map<String, Object> map)
	{
		sqlMapper.prc_querysplit_child(map);
		return map;
	}
	//7.执行存储过程分页主入口
	public Map<String, Object> prc_querysplit(Map<String, Object> map)
	{
		String pageIndex = map.get("pageIndex").toString();
		if (pageIndex == null){
			pageIndex = "1";
			map.put("pageIndex",pageIndex);
		}
		/*
		if (pageIndex.equals("1"))
		{
			return prc_querysplit_parent(map);
		}
		else
		{
			return prc_querysplit_child(map);
		}
		*/
		return prc_querysplit_parent(map);
	}

	//测试执行存储过程
    public Map<String, Object> PRC_YANPTEST(Map<String, Object> map)
    {
        sqlMapper.PRC_YANPTEST(map);
        return map;
    }

	//通用执行存储过程
	public Map<String, Object> executeProc(Map<String, Object> map)
	{
		sqlMapper.executeProc(map);
		return map;
	}

	//map中的key转小写
	public static Map<String, Object> transformUpperCase(Map<String, Object> orgMap)
	{
		Map<String, Object> resultMap = new HashMap<>();
		if (orgMap == null || orgMap.isEmpty()) {
			return resultMap;
		}
		Set<String> keySet = orgMap.keySet();
		for (String key : keySet)
		{
			String newKey = key.toLowerCase();

			//=========================================处理null值问题
			String ls_data = "";
			Object obj_data = orgMap.get(key);

			if (obj_data == null){
				ls_data = "";
			}
			else{
				ls_data = orgMap.get(key).toString();
			}
			//=========================================

			resultMap.put(newKey, ls_data);
		}
		return resultMap;
	}

	//list中的key转小写
	public static List<Map<String, Object>> transformUpperCase(List<Map<String, Object>> list)
	{
		for (int i = 0;i < list.size(); i++){

			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> map = list.get(i);

			if (map == null || map.isEmpty()) {
				return list;
			}

			Set<String> keySet = map.keySet();

			for (String key : keySet) {
				String newKey = key.toLowerCase();

				//=========================================处理null值问题
				String ls_data = "";
				Object obj_data = map.get(key);

				if (obj_data == null){
					ls_data = "";
				}
				else{
					ls_data = map.get(key).toString();
				}
				//=========================================

				resultMap.put(newKey, ls_data);
			}
			list.set(i,resultMap);
		}
		return list;
	}
}
