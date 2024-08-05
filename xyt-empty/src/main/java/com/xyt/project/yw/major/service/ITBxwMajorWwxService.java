package com.xyt.project.yw.major.service;

import java.util.List;
import com.xyt.project.yw.major.domain.TBxwMajorWwx;

/**
 * 专业Service接口
 *
 * @author ruoyi
 * @date 2024-07-16
 */
public interface ITBxwMajorWwxService
{
    /**
     * 查询专业
     *
     * @param id 专业主键
     * @return 专业
     */
    public TBxwMajorWwx selectTBxwMajorWwxById(String id);

    /**
     * 查询专业列表
     *
     * @param tBxwMajorWwx 专业
     * @return 专业集合
     */
    public List<TBxwMajorWwx> selectTBxwMajorWwxList(TBxwMajorWwx tBxwMajorWwx);

    /**
     * 新增专业
     *
     * @param tBxwMajorWwx 专业
     * @return 结果
     */
    public int insertTBxwMajorWwx(TBxwMajorWwx tBxwMajorWwx);

    /**
     * 修改专业
     *
     * @param tBxwMajorWwx 专业
     * @return 结果
     */
    public int updateTBxwMajorWwx(TBxwMajorWwx tBxwMajorWwx);

    /**
     * 批量删除专业
     *
	 * @param ids 需要删除的专业主键集合
     * @return 结果
     */
    public int deleteTBxwMajorWwxByIds(String ids);

    /**
     * 删除专业信息
     *
     * @param id 专业主键
     * @return 结果
     */
    public int deleteTBxwMajorWwxById(String id);
}
