package com.xyt.project.yw.stucour.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xyt.project.yw.stucour.mapper.TBxwStucourWwxMapper;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.stucour.domain.TBxwStucourWwx;
import com.xyt.project.yw.stucour.service.ITBxwStucourWwxService;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;
import com.xyt.common.utils.text.Convert;

/**
 * 选课Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBxwStucourWwxServiceImpl implements ITBxwStucourWwxService
{
    @Autowired
    private TBxwStucourWwxMapper tBxwStucourWwxMapper;

    /**
     * 查询选课
     *
     * @param id 选课主键
     * @return 选课
     */
    @Override
    public TBxwStucourWwx selectTBxwStucourWwxById(String id)
    {
        return tBxwStucourWwxMapper.selectTBxwStucourWwxById(id);
    }

    /**
     * 查询选课列表
     *
     * @param tBxwStucourWwx 选课
     * @return 选课
     */
    @Override
    public List<TBxwStucourWwx> selectTBxwStucourWwxList(TBxwStucourWwx tBxwStucourWwx)
    {
        return tBxwStucourWwxMapper.selectTBxwStucourWwxList(tBxwStucourWwx);
    }

    /**
     * 新增选课
     *
     * @param tBxwStucourWwx 选课
     * @return 结果
     */
    @Override
    public int insertTBxwStucourWwx(TBxwStucourWwx tBxwStucourWwx)
    {
        return tBxwStucourWwxMapper.insertTBxwStucourWwx(tBxwStucourWwx);
    }

    /**
     * 修改选课
     *
     * @param tBxwStucourWwx 选课
     * @return 结果
     */
    @Override
    public int updateTBxwStucourWwx(TBxwStucourWwx tBxwStucourWwx)
    {
        return tBxwStucourWwxMapper.updateTBxwStucourWwx(tBxwStucourWwx);
    }

    /**
	 * 批量删除选课
     *
	 * @param ids 需要删除的选课主键
     * @return 结果
     */
    @Override
    public int deleteTBxwStucourWwxByIds(String ids)
    {
        return tBxwStucourWwxMapper.deleteTBxwStucourWwxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除选课信息
     *
     * @param id 选课ID
     * @return 结果
     */
    @Override
    public int deleteTBxwStucourWwxById(String id)
    {
        return tBxwStucourWwxMapper.deleteTBxwStucourWwxById(id);
    }

	@Override
	public List<TBxwCourseWwx> selectTBxwCourseByStudent(String id) {
		// TODO Auto-generated method stub
		return tBxwStucourWwxMapper.selectTBxwCourseByStudent(id);
	}

	
	
}
