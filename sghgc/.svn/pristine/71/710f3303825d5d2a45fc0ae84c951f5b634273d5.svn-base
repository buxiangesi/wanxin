package com.shggc.project.jcxx.pqjd.service;

import java.util.List;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;

/**
 * 片区街道Service接口
 *
 * @author ruoyi
 * @date 2022-07-25
 */
public interface ITJcPqJdService
{
    /**
     * 查询片区街道
     *
     * @param id 片区街道主键
     * @return 片区街道
     */
    public TJcPqJd selectTJcPqJdById(String id);

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
     * 批量删除片区街道
     *
	 * @param ids 需要删除的片区街道主键集合
     * @return 结果
     */
    public int deleteTJcPqJdByIds(String ids);

    /**
     * 删除片区街道信息
     *
     * @param id 片区街道主键
     * @return 结果
     */
    public int deleteTJcPqJdById(String id);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 街道id
     * @param jdbh 街道编号
     * @return 结果
     */
    public String checkJdbhUnique(String id,String jdbh);
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 街道id
     * @param jdmc 街道名称
     * @return 结果
     */
    public String checkJdmcUnique(String id,String jdmc);
}
