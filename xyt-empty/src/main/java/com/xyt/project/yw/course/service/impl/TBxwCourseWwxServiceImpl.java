package com.xyt.project.yw.course.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xyt.project.yw.course.mapper.TBxwCourseWwxMapper;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.course.service.ITBxwCourseWwxService;
import com.xyt.common.utils.text.Convert;

/**
 * 课程Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBxwCourseWwxServiceImpl implements ITBxwCourseWwxService
{
    @Autowired
    private TBxwCourseWwxMapper tBxwCourseWwxMapper;

    /**
     * 查询课程
     *
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public TBxwCourseWwx selectTBxwCourseWwxById(String id)
    {
        return tBxwCourseWwxMapper.selectTBxwCourseWwxById(id);
    }

    /**
     * 查询课程列表
     *
     * @param tBxwCourseWwx 课程
     * @return 课程
     */
    @Override
    public List<TBxwCourseWwx> selectTBxwCourseWwxList(TBxwCourseWwx tBxwCourseWwx)
    {
        return tBxwCourseWwxMapper.selectTBxwCourseWwxList(tBxwCourseWwx);
    }

    /**
     * 新增课程
     *
     * @param tBxwCourseWwx 课程
     * @return 结果
     */
    @Override
    public int insertTBxwCourseWwx(TBxwCourseWwx tBxwCourseWwx)
    {
        return tBxwCourseWwxMapper.insertTBxwCourseWwx(tBxwCourseWwx);
    }

    /**
     * 修改课程
     *
     * @param tBxwCourseWwx 课程
     * @return 结果
     */
    @Override
    public int updateTBxwCourseWwx(TBxwCourseWwx tBxwCourseWwx)
    {
        return tBxwCourseWwxMapper.updateTBxwCourseWwx(tBxwCourseWwx);
    }

    /**
	 * 批量删除课程
     *
	 * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteTBxwCourseWwxByIds(String ids)
    {
        return tBxwCourseWwxMapper.deleteTBxwCourseWwxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程信息
     *
     * @param id 课程ID
     * @return 结果
     */
    @Override
    public int deleteTBxwCourseWwxById(String id)
    {
        return tBxwCourseWwxMapper.deleteTBxwCourseWwxById(id);
    }
}
