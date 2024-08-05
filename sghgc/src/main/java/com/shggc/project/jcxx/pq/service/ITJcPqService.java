package com.shggc.project.jcxx.pq.service;

import java.util.List;
import com.shggc.project.jcxx.pq.domain.TJcPq;

/**
 * 片区Service接口
 *
 * @author ruoyi
 * @date 2022-07-22
 */
public interface ITJcPqService
{
    /**
     * 查询片区
     *
     * @param id 片区主键
     * @return 片区
     */
    public TJcPq selectTJcPqById(String id);

    /**
     * 查询片区列表
     *
     * @param tJcPq 片区
     * @return 片区集合
     */
    public List<TJcPq> selectTJcPqList(TJcPq tJcPq);
    
    /**
     * 查询全部片区
     *
     * @param tJcPq 片区
     * @return 片区集合
     */
    public List<TJcPq> selectTJcPqAll();
    
    /**
     * 查询片区街道树结构
     *
     * @return 片区集合
     */
    public List<TJcPq> selectPqJdTree();

    /**
     * 新增片区
     *
     * @param tJcPq 片区
     * @return 结果
     */
    public int insertTJcPq(TJcPq tJcPq);

    /**
     * 修改片区
     *
     * @param tJcPq 片区
     * @return 结果
     */
    public int updateTJcPq(TJcPq tJcPq);

    /**
     * 批量删除片区
     *
	 * @param ids 需要删除的片区主键集合
     * @return 结果
     */
    public int deleteTJcPqByIds(String ids);

    /**
     * 删除片区信息
     *
     * @param id 片区主键
     * @return 结果
     */
    public int deleteTJcPqById(String id);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 片区id
     * @param pqbh 片区编号
     * @return 结果
     */
    public String checkPqbhUnique(String id,String pqbh);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 片区id
     * @param pqmc 片区名称
     * @return 结果
     */
    public String checkPqmcUnique(String id,String pqmc);
}
