package com.xyt.project.yw.major.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xyt.project.yw.major.mapper.TBxwMajorWwxMapper;
import com.xyt.project.yw.major.domain.TBxwMajorWwx;
import com.xyt.project.yw.major.service.ITBxwMajorWwxService;
import com.xyt.common.utils.text.Convert;

/**
 * 专业Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBxwMajorWwxServiceImpl implements ITBxwMajorWwxService
{
    @Autowired
    private TBxwMajorWwxMapper tBxwMajorWwxMapper;

    /**
     * 查询专业
     *
     * @param id 专业主键
     * @return 专业
     */
    @Override
    public TBxwMajorWwx selectTBxwMajorWwxById(String id)
    {
        return tBxwMajorWwxMapper.selectTBxwMajorWwxById(id);
    }

    /**
     * 查询专业列表
     *
     * @param tBxwMajorWwx 专业
     * @return 专业
     */
    @Override
    public List<TBxwMajorWwx> selectTBxwMajorWwxList(TBxwMajorWwx tBxwMajorWwx)
    {
        return tBxwMajorWwxMapper.selectTBxwMajorWwxList(tBxwMajorWwx);
    }

    /**
     * 新增专业
     *
     * @param tBxwMajorWwx 专业
     * @return 结果
     */
    @Override
    public int insertTBxwMajorWwx(TBxwMajorWwx tBxwMajorWwx)
    {
        return tBxwMajorWwxMapper.insertTBxwMajorWwx(tBxwMajorWwx);
    }

    /**
     * 修改专业
     *
     * @param tBxwMajorWwx 专业
     * @return 结果
     */
    @Override
    public int updateTBxwMajorWwx(TBxwMajorWwx tBxwMajorWwx)
    {
        return tBxwMajorWwxMapper.updateTBxwMajorWwx(tBxwMajorWwx);
    }

    /**
	 * 批量删除专业
     *
	 * @param ids 需要删除的专业主键
     * @return 结果
     */
    @Override
    public int deleteTBxwMajorWwxByIds(String ids)
    {
        return tBxwMajorWwxMapper.deleteTBxwMajorWwxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专业信息
     *
     * @param id 专业ID
     * @return 结果
     */
    @Override
    public int deleteTBxwMajorWwxById(String id)
    {
        return tBxwMajorWwxMapper.deleteTBxwMajorWwxById(id);
    }
}
