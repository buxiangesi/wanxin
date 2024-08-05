package com.xyt.project.youbeisoft.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xyt.project.youbeisoft.base.CourierInfo;

import java.util.List;
import java.util.Map;

//2022-10-17yanp增加简化方法工具类
@Repository
@Mapper
public interface ISqlMapper 
{
	Integer insert(String statement);
	Integer delete(String statement);
	Integer update(String statement);
	List<Map<String, Object>> selectList(String statement);
	String selectOne(String statement);
	List<Map<String, Object>> preparedSelectMap(Map<String, Object> map);

	Integer preparedInsert(Map<String, Object> map);
	Integer preparedUpdate(Map<String, Object> map);
	Integer preparedDelete(Map<String, Object> map);
	String preparedSelectOne(Map<String, Object> map);

	//1.简单的执行存储过程(字符串输入,字符串输出)
	String getSerialCodeBySiteCode(Map<String, Object> map);
	//2.简单的执行存储过程返回游标,返回值需要用实体接收
	List<CourierInfo> listCourierInfoList(Map<String, Object> map);
	//3.执行存储过程返回list结果集
	List<Map<String, Object>> testcommon_cursor(Map<String, Object> map);
	//4.执行存储过程返回map对象
	Map<String, Object> testcommon_cursor1(Map<String, Object> map);
	//5.执行存储过程主分页程序prc_querysplit_parent返回map对象
	Map<String, Object> prc_querysplit_parent(Map<String, Object> map);
	//6.执行存储过程子分页程序prc_querysplit_child返回map对象
	Map<String, Object> prc_querysplit_child(Map<String, Object> map);
	//测试执行存储过程
	Map<String, Object> PRC_YANPTEST(Map<String, Object> map);
	//通用执行存储过程
	Map<String, Object> executeProc(Map<String, Object> map);
}