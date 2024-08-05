package com.xyt.project.yw.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xyt.project.yw.student.mapper.TBxwStudentWwxMapper;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;
import com.xyt.project.yw.student.service.ITBxwStudentWwxService;
import com.xyt.common.utils.text.Convert;

/**
 * 学生Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBxwStudentWwxServiceImpl implements ITBxwStudentWwxService
{
    @Autowired
    private TBxwStudentWwxMapper tBxwStudentWwxMapper;

    /**
     * 查询学生
     *
     * @param id 学生主键
     * @return 学生
     */
    @Override
    public TBxwStudentWwx selectTBxwStudentWwxById(String id)
    {
        return tBxwStudentWwxMapper.selectTBxwStudentWwxById(id);
    }

    /**
     * 查询学生列表
     *
     * @param tBxwStudentWwx 学生
     * @return 学生
     */
    @Override
    public List<TBxwStudentWwx> selectTBxwStudentWwxList(TBxwStudentWwx tBxwStudentWwx)
    {
        return tBxwStudentWwxMapper.selectTBxwStudentWwxList(tBxwStudentWwx);
    }

    /**
     * 新增学生
     *
     * @param tBxwStudentWwx 学生
     * @return 结果
     */
    @Override
    public int insertTBxwStudentWwx(TBxwStudentWwx tBxwStudentWwx)
    {
        return tBxwStudentWwxMapper.insertTBxwStudentWwx(tBxwStudentWwx);
    }

    /**
     * 修改学生
     *
     * @param tBxwStudentWwx 学生
     * @return 结果
     */
    @Override
    public int updateTBxwStudentWwx(TBxwStudentWwx tBxwStudentWwx)
    {
        return tBxwStudentWwxMapper.updateTBxwStudentWwx(tBxwStudentWwx);
    }

    /**
	 * 批量删除学生
     *
	 * @param ids 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteTBxwStudentWwxByIds(String ids)
    {
        return tBxwStudentWwxMapper.deleteTBxwStudentWwxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生信息
     *
     * @param id 学生ID
     * @return 结果
     */
    @Override
    public int deleteTBxwStudentWwxById(String id)
    {
        return tBxwStudentWwxMapper.deleteTBxwStudentWwxById(id);
    }
}
