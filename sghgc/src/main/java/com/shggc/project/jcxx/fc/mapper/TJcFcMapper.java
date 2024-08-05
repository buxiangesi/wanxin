package com.shggc.project.jcxx.fc.mapper;

import java.util.List;
import com.shggc.project.jcxx.fc.domain.TJcFc;

/**
 * 房产信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-27
 */
public interface TJcFcMapper
{
    /**
     * 查询房产信息
     *
     * @param id 房产信息主键
     * @return 房产信息
     */
    public TJcFc selectTJcFcById(String id);

    /**
     * 查询房产信息列表
     *
     * @param tJcFc 房产信息
     * @return 房产信息集合
     */
    public List<TJcFc> selectTJcFcList(TJcFc tJcFc);

    /**
     * 查询房产信息
     *
     * @param jdid 街道id
     * @return 房产信息
     */
    public List<TJcFc> selectTJcFcByJdId(String jdid);
    
    /**
     * 新增房产信息
     *
     * @param tJcFc 房产信息
     * @return 结果
     */
    public int insertTJcFc(TJcFc tJcFc);

    /**
     * 修改房产信息
     *
     * @param tJcFc 房产信息
     * @return 结果
     */
    public int updateTJcFc(TJcFc tJcFc);

    /**
     * 删除房产信息
     *
     * @param id 房产信息ID
     * @return 结果
     */
    public int deleteTJcFcById(String id);

    /**
     * 批量删除房产信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJcFcByIds(String[] ids);
    
    /**
     * 生产房产编号
     *
     * @return 房产编号
     */
    public int generateFcbh();
}
