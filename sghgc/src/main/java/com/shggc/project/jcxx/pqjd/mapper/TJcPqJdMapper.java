package com.shggc.project.jcxx.pqjd.mapper;

import java.util.List;

import com.shggc.project.jcxx.pq.domain.TJcPq;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;

/**
 * 片区街道Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-25
 */
public interface TJcPqJdMapper
{
    /**
     * 查询片区街道
     *
     * @param id 片区街道主键
     * @return 片区街道
     */
    public TJcPqJd selectTJcPqJdById(String id);
    
    /**
     * 查询片区街道
     *
     * @param pqid 片区主键
     * @return 片区街道
     */
    public List<TJcPqJd> selectTJcPqJdByPqId(String pqid);

    /**
     * 查询片区街道列表
     *
     * @param tJcPqJd 片区街道
     * @return 片区街道集合
     */
    public List<TJcPqJd> selectTJcPqJdList(TJcPqJd tJcPqJd);

    /**
     * 查询全部街道
     *
     * @return 街道集合
     */
    public List<TJcPqJd> selectTJcPqJdAll();
    
    /**
     * 新增片区街道
     *
     * @param tJcPqJd 片区街道
     * @return 结果
     */
    public int insertTJcPqJd(TJcPqJd tJcPqJd);

    /**
     * 修改片区街道
     *
     * @param tJcPqJd 片区街道
     * @return 结果
     */
    public int updateTJcPqJd(TJcPqJd tJcPqJd);

    /**
     * 删除片区街道
     *
     * @param id 片区街道ID
     * @return 结果
     */
    public int deleteTJcPqJdById(String id);

    /**
     * 批量删除片区街道
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJcPqJdByIds(String[] ids);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param Map id：片区编号  jdbh:片区名称
     * @return 结果
     */
    public int checkJdbhUnique(java.util.Map Map);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param Map id：片区编号  jdmc:片区名称
     * @return 结果
     */
    public int checkJdmcUnique(java.util.Map Map);
}
