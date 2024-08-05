package com.xyt.project.yw.stucour.service;

import java.util.List;

import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.stucour.domain.TBxwStucourWwx;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;

/**
 * 选课Service接口
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public interface ITBxwStucourWwxService
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
     * 批量删除选课
     *
	 * @param ids 需要删除的选课主键集合
     * @return 结果
     */
    public int deleteTBxwStucourWwxByIds(String ids);

    /**
     * 删除选课信息
     *
     * @param id 选课主键
     * @return 结果
     */
    public int deleteTBxwStucourWwxById(String id);
    
    public List  <TBxwCourseWwx> selectTBxwCourseByStudent(String id);
}
