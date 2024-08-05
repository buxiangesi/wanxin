package com.shggc.project.jcxx.fc.service;

import java.sql.SQLException;
import java.util.List;

import com.shggc.framework.web.domain.Ztree;
import com.shggc.project.jcxx.fc.domain.TJcFc;
import com.shggc.project.jcxx.pq.domain.TJcPq;

/**
 * 房产信息Service接口
 *
 * @author ruoyi
 * @date 2022-07-27
 */
public interface ITJcFcService
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
     * 批量删除房产信息
     *
	 * @param ids 需要删除的房产信息主键集合
     * @return 结果
     */
    public int deleteTJcFcByIds(String ids);

    /**
     * 删除房产信息信息
     *
     * @param id 房产信息主键
     * @return 结果
     */
    public int deleteTJcFcById(String id);
    
    /**
     * 查询片区街道树结构
     *
     * @return 片区集合
     */
    public List<Ztree>selectPqJdTree();
}
