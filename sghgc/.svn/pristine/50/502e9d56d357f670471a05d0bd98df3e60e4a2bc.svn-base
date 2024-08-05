package com.shggc.project.system.datasql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 参数配置 数据层
 * 
 * @author 
 */
public interface SysDataSqlMapper
{
    /**
     * 根据sql查询返回结果集
     * 
     * @param sql
     * @return 参数配置信息
     */
    public List<Map<String,Object>> dynamicsSelect(@Param("sql") String sql);
    public void dynamicsInsert(@Param("sql") String sql);
    public void dynamicsUpdate(@Param("sql") String sql);
    public void dynamicsDelete(@Param("sql") String sql);
}