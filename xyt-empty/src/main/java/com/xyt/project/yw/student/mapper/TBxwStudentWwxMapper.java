package com.xyt.project.yw.student.mapper;

import java.util.List;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;

/**
 * 学生Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-17
 */
public interface TBxwStudentWwxMapper
{
    /**
     * 查询学生
     *
     * @param id 学生主键
     * @return 学生
     */
    public TBxwStudentWwx selectTBxwStudentWwxById(String id);

    /**
     * 查询学生列表
     *
     * @param tBxwStudentWwx 学生
     * @return 学生集合
     */
    public List<TBxwStudentWwx> selectTBxwStudentWwxList(TBxwStudentWwx tBxwStudentWwx);

    /**
     * 新增学生
     *
     * @param tBxwStudentWwx 学生
     * @return 结果
     */
    public int insertTBxwStudentWwx(TBxwStudentWwx tBxwStudentWwx);

    /**
     * 修改学生
     *
     * @param tBxwStudentWwx 学生
     * @return 结果
     */
    public int updateTBxwStudentWwx(TBxwStudentWwx tBxwStudentWwx);

    /**
     * 删除学生
     *
     * @param id 学生ID
     * @return 结果
     */
    public int deleteTBxwStudentWwxById(String id);

    /**
     * 批量删除学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBxwStudentWwxByIds(String[] ids);
}
