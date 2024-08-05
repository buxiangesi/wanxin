package com.xyt.project.yw.stucour.mapper;

import java.util.List;

import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.stucour.domain.TBxwStucourWwx;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;

/**
 * 选课Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public interface TBxwStucourWwxMapper
{
    /**
     * 查询选课
     *
     * @param id 选课主键
     * @return 选课
     */
    public TBxwStucourWwx selectTBxwStucourWwxById(String id);

    /**
     * 查询选课列表
     *
     * @param tBxwStucourWwx 选课
     * @return 选课集合
     */
    public List<TBxwStucourWwx> selectTBxwStucourWwxList(TBxwStucourWwx tBxwStucourWwx);

    /**
     * 新增选课
     *
     * @param tBxwStucourWwx 选课
     * @return 结果
     */
    public int insertTBxwStucourWwx(TBxwStucourWwx tBxwStucourWwx);

    /**
     * 修改选课
     *
     * @param tBxwStucourWwx 选课
     * @return 结果
     */
    public int updateTBxwStucourWwx(TBxwStucourWwx tBxwStucourWwx);

    /**
     * 删除选课
     *
     * @param id 选课ID
     * @return 结果
     */
    public int deleteTBxwStucourWwxById(String id);

    /**
     * 批量删除选课
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBxwStucourWwxByIds(String[] ids);
    
    public List  <TBxwCourseWwx> selectTBxwCourseByStudent(String id);
}
