package com.shggc.project.jcxx.fw.mapper;

import java.util.List;
import com.shggc.project.jcxx.fw.domain.TJcFcFw;

/**
 * 房产房屋信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-28
 */
public interface TJcFcFwMapper
{
    /**
     * 查询房产房屋信息
     *
     * @param id 房产房屋信息主键
     * @return 房产房屋信息
     */
    public TJcFcFw selectTJcFcFwById(String id);

    /**
     * 查询房产房屋信息列表
     *
     * @param tJcFcFw 房产房屋信息
     * @return 房产房屋信息集合
     */
    public List<TJcFcFw> selectTJcFcFwList(TJcFcFw tJcFcFw);

    /**
     * 新增房产房屋信息
     *
     * @param tJcFcFw 房产房屋信息
     * @return 结果
     */
    public int insertTJcFcFw(TJcFcFw tJcFcFw);

    /**
     * 修改房产房屋信息
     *
     * @param tJcFcFw 房产房屋信息
     * @return 结果
     */
    public int updateTJcFcFw(TJcFcFw tJcFcFw);

    /**
     * 删除房产房屋信息
     *
     * @param id 房产房屋信息ID
     * @return 结果
     */
    public int deleteTJcFcFwById(String id);

    /**
     * 删除房产房屋信息
     *
     * @param fcid 房产ID
     * @return 结果
     */
    public int deleteTJcFcFwByFcId(String fcid);
    
    /**
     * 批量删除房产房屋信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJcFcFwByIds(String[] ids);
    
    
}
