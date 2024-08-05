package com.shggc.project.jcxx.pq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shggc.project.jcxx.pq.mapper.TJcPqMapper;
import com.shggc.project.jcxx.pq.domain.TJcPq;
import com.shggc.project.jcxx.pq.service.ITJcPqService;
import com.shggc.project.jcxx.pqjd.mapper.TJcPqJdMapper;
import com.shggc.common.exception.BusinessException;
import com.shggc.common.utils.text.Convert;

/**
 * 片区Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TJcPqServiceImpl implements ITJcPqService
{
    @Autowired
    private TJcPqMapper tJcPqMapper;
    
    @Autowired
    private TJcPqJdMapper tJcPqJdMapper;

    /**
     * 查询片区
     *
     * @param id 片区主键
     * @return 片区
     */
    @Override
    public TJcPq selectTJcPqById(String id)
    {
        return tJcPqMapper.selectTJcPqById(id);
    }

    /**
     * 查询片区列表
     *
     * @param tJcPq 片区
     * @return 片区
     */
    @Override
    public List<TJcPq> selectTJcPqList(TJcPq tJcPq)
    {
        return tJcPqMapper.selectTJcPqList(tJcPq);
    }

    /**
     * 查询全部片区
     *
     * @param tJcPq 片区
     * @return 片区集合
     */
    @Override
    public List<TJcPq> selectTJcPqAll(){
    	return tJcPqMapper.selectTJcPqAll();
    }
    
    /**
     * 查询片区街道树结构
     *
     * @return 片区集合
     */
    public List<TJcPq> selectPqJdTree(){
    	return tJcPqMapper.selectPqJdTree();
    }
    
    /**
     * 新增片区
     *
     * @param tJcPq 片区
     * @return 结果
     */
    @Override
    public int insertTJcPq(TJcPq tJcPq)
    {
        return tJcPqMapper.insertTJcPq(tJcPq);
    }

    /**
     * 修改片区
     *
     * @param tJcPq 片区
     * @return 结果
     */
    @Override
    public int updateTJcPq(TJcPq tJcPq)
    {
        return tJcPqMapper.updateTJcPq(tJcPq);
    }

    /**
	 * 批量删除片区
     *
	 * @param ids 需要删除的片区主键
     * @return 结果
     */
    @Override
    public int deleteTJcPqByIds(String ids)
    {
    	String[] iids = Convert.toStrArray(ids);
        for (String id : iids)
        {
        	TJcPq tjcPq = selectTJcPqById(id);
            int pqjdNum = tJcPqJdMapper.selectTJcPqJdByPqId(tjcPq.getId()).size();
            if (pqjdNum > 0)
            {
                throw new BusinessException(String.format("%1$s下已经有街道信息,不能删除", tjcPq.getPqmc()));
            }
        }
        return tJcPqMapper.deleteTJcPqByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除片区信息
     *
     * @param id 片区ID
     * @return 结果
     */
    @Override
    public int deleteTJcPqById(String id)
    {
    	TJcPq tjcPq = selectTJcPqById(id);
        int pqjdNum = tJcPqJdMapper.selectTJcPqJdByPqId(tjcPq.getId()).size();
        if (pqjdNum > 0)
        {
            throw new BusinessException(String.format("%1$s下已经有房屋信息,不能删除", tjcPq.getPqmc()));
        }
        return tJcPqMapper.deleteTJcPqById(id);
    }
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 片区id
     * @param pqbh 片区编号
     * @return 0 唯一，1 不唯一
     */
    @Override
    public String checkPqbhUnique(String id, String pqbh) {
    	// TODO Auto-generated method stub
    	if (id != null && id.length() != 0) {
    	}else {
    		id = "1";
    	}
    	Map <String , String> map = new HashMap <String , String> ();
        map.put("id", id);
        map.put("pqbh", pqbh);
    	
        int count = tJcPqMapper.checkPqbhUnique(map);
        if (count > 0)
        {
           return "1";
        }
        return "0";
    }    
    
    /**
     * 校验片区编号是否唯一
     * 
     * @param id 片区id
     * @param pqbh 片区名称
     * @return 0 唯一，1 不唯一
     */
    @Override
    public String checkPqmcUnique(String id, String pqmc) {
    	// TODO Auto-generated method stub
    	if (id != null && id.length() != 0) {
    	}else {
    		id = "1";
    	}
    	Map <String , String> map = new HashMap <String , String> ();
        map.put("id", id);
        map.put("pqmc", pqmc);
    	
        int count = tJcPqMapper.checkPqmcUnique(map);
        if (count > 0)
        {
           return "1";
        }
        return "0";
    }    
}
