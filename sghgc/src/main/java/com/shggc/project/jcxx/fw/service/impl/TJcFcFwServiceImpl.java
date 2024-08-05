package com.shggc.project.jcxx.fw.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shggc.project.jcxx.fw.mapper.TJcFcFwMapper;
import com.shggc.project.jcxx.fw.domain.TJcFcFw;
import com.shggc.project.jcxx.fw.service.ITJcFcFwService;
import com.shggc.common.utils.text.Convert;

/**
 * 房产房屋信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TJcFcFwServiceImpl implements ITJcFcFwService
{
    @Autowired
    private TJcFcFwMapper tJcFcFwMapper;

    /**
     * 查询房产房屋信息
     *
     * @param id 房产房屋信息主键
     * @return 房产房屋信息
     */
    @Override
    public TJcFcFw selectTJcFcFwById(String id)
    {
        return tJcFcFwMapper.selectTJcFcFwById(id);
    }

    /**
     * 查询房产房屋信息列表
     *
     * @param tJcFcFw 房产房屋信息
     * @return 房产房屋信息
     */
    @Override
    public List<TJcFcFw> selectTJcFcFwList(TJcFcFw tJcFcFw)
    {
        return tJcFcFwMapper.selectTJcFcFwList(tJcFcFw);
    }

    /**
     * 新增房产房屋信息
     *
     * @param tJcFcFw 房产房屋信息
     * @return 结果
     */
    @Override
    public int insertTJcFcFw(TJcFcFw tJcFcFw)
    {
        return tJcFcFwMapper.insertTJcFcFw(tJcFcFw);
    }

    /**
     * 修改房产房屋信息
     *
     * @param tJcFcFw 房产房屋信息
     * @return 结果
     */
    @Override
    public int updateTJcFcFw(TJcFcFw tJcFcFw)
    {
        return tJcFcFwMapper.updateTJcFcFw(tJcFcFw);
    }

    /**
	 * 批量删除房产房屋信息
     *
	 * @param ids 需要删除的房产房屋信息主键
     * @return 结果
     */
    @Override
    public int deleteTJcFcFwByIds(String ids)
    {
        return tJcFcFwMapper.deleteTJcFcFwByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除房产房屋信息信息
     *
     * @param id 房产房屋信息ID
     * @return 结果
     */
    @Override
    public int deleteTJcFcFwById(String id)
    {
        return tJcFcFwMapper.deleteTJcFcFwById(id);
    }
}
