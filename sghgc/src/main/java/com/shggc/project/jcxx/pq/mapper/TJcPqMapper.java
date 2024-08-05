package com.shggc.project.jcxx.pq.mapper;

import java.util.List;
import com.shggc.project.jcxx.pq.domain.TJcPq;

/**
 * 片区Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-22
 */
public interface TJcPqMapper
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
     * 查询片区街道树结构
     *
     * @return 片区集合
     */
    public List<TJcPq> selectPqJdTree();

    /**
     * 查询全部片区
     *
     * @param tJcPq 片区
     * @return 片区集合
     */
    public List<TJcPq> selectTJcPqAll();
    
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
     * 删除片区
     *
     * @param id 片区ID
     * @return 结果
     */
    public int deleteTJcPqById(String id);

    /**
     * 批量删除片区
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJcPqByIds(String[] ids);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param Map id：片区编号  pqbh:片区名称
     * @return 结果
     */
    public int checkPqbhUnique(java.util.Map Map);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param Map id：片区编号  pqmc:片区名称
     * @return 结果
     */
    public int checkPqmcUnique(java.util.Map Map);
}
