package com.xyt.project.yw.course.service;

import java.util.List;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;

/**
 * 课程Service接口
 *
 * @author ruoyi
 * @date 2024-07-17
 */
public interface ITBxwCourseWwxService
{
    /**
     * 查询课程
     *
     * @param id 课程主键
     * @return 课程
     */
    public TBxwCourseWwx selectTBxwCourseWwxById(String id);

    /**
     * 查询课程列表
     *
     * @param tBxwCourseWwx 课程
     * @return 课程集合
     */
    public List<TBxwCourseWwx> selectTBxwCourseWwxList(TBxwCourseWwx tBxwCourseWwx);

    /**
     * 新增课程
     *
     * @param tBxwCourseWwx 课程
     * @return 结果
     */
    public int insertTBxwCourseWwx(TBxwCourseWwx tBxwCourseWwx);

    /**
     * 修改课程
     *
     * @param tBxwCourseWwx 课程
     * @return 结果
     */
    public int updateTBxwCourseWwx(TBxwCourseWwx tBxwCourseWwx);

    /**
     * 批量删除课程
     *
	 * @param ids 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteTBxwCourseWwxByIds(String ids);

    /**
     * 删除课程信息
     *
     * @param id 课程主键
     * @return 结果
     */
    public int deleteTBxwCourseWwxById(String id);
}
