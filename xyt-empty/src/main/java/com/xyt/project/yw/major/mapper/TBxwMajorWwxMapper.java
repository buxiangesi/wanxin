package com.xyt.project.yw.major.mapper;

import java.util.List;
import com.xyt.project.yw.major.domain.TBxwMajorWwx;

/**
 * 专业Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-16
 */
public interface TBxwMajorWwxMapper
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
     * 删除专业
     *
     * @param id 专业ID
     * @return 结果
     */
    public int deleteTBxwMajorWwxById(String id);

    /**
     * 批量删除专业
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBxwMajorWwxByIds(String[] ids);
}
