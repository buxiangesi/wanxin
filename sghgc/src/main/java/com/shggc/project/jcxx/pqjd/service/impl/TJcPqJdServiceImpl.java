package com.shggc.project.jcxx.pqjd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shggc.project.jcxx.pqjd.mapper.TJcPqJdMapper;
import com.shggc.project.jcxx.fc.mapper.TJcFcMapper;
import com.shggc.project.jcxx.pq.domain.TJcPq;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;
import com.shggc.project.jcxx.pqjd.service.ITJcPqJdService;
import com.shggc.common.exception.BusinessException;
import com.shggc.common.utils.text.Convert;

/**
 * 片区街道Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TJcPqJdServiceImpl implements ITJcPqJdService
{
    @Autowired
    private TJcPqJdMapper tJcPqJdMapper;

    @Autowired
    private TJcFcMapper tJcFcMapper;
    /**
     * 查询片区街道
     *
     * @param id 片区街道主键
     * @return 片区街道
     */
    @Override
    public TJcPqJd selectTJcPqJdById(String id)
    {
        return tJcPqJdMapper.selectTJcPqJdById(id);
    }

    /**
     * 查询片区街道列表
     *
     * @param tJcPqJd 片区街道
     * @return 片区街道
     */
    @Override
    public List<TJcPqJd> selectTJcPqJdList(TJcPqJd tJcPqJd)
    {
        return tJcPqJdMapper.selectTJcPqJdList(tJcPqJd);
    }

    /**
     * 查询全部街道
     *
     * @return 街道集合
     */
    @Override
    public List<TJcPqJd> selectTJcPqJdAll(){
    	return tJcPqJdMapper.selectTJcPqJdAll();
    }
    
    /**
     * 新增片区街道
     *
     * @param tJcPqJd 片区街道
     * @return 结果
     */
    @Override
    public int insertTJcPqJd(TJcPqJd tJcPqJd)
    {
        return tJcPqJdMapper.insertTJcPqJd(tJcPqJd);
    }

    /**
     * 修改片区街道
     *
     * @param tJcPqJd 片区街道
     * @return 结果
     */
    @Override
    public int updateTJcPqJd(TJcPqJd tJcPqJd)
    {
        return tJcPqJdMapper.updateTJcPqJd(tJcPqJd);
    }

    /**
	 * 批量删除片区街道
     *
	 * @param ids 需要删除的片区街道主键
     * @return 结果
     */
    @Override
    public int deleteTJcPqJdByIds(String ids)
    {
    	String[] iids = Convert.toStrArray(ids);
        for (String id : iids)
        {
        	TJcPqJd tjcPqJd = selectTJcPqJdById(id);
            int pqjdNum = tJcFcMapper.selectTJcFcByJdId(id).size();
            if (pqjdNum > 0)
            {
                throw new BusinessException(String.format("%1$s下已经有房产信息,不能删除", tjcPqJd.getJdmc()));
            }
        }
        return tJcPqJdMapper.deleteTJcPqJdByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除片区街道信息
     *
     * @param id 片区街道ID
     * @return 结果
     */
    @Override
    public int deleteTJcPqJdById(String id)
    {
        return tJcPqJdMapper.deleteTJcPqJdById(id);
    }
    
    @Override
    public String checkJdbhUnique(String id, String jdbh) {
    	// TODO Auto-generated method stub
    	if (id != null && id.length() != 0) {
    	}else {
    		id = "1";
    	}
    	Map <String , String> map = new HashMap <String , String> ();
        map.put("id", id);
        map.put("jdbh", jdbh);
    	
        int count = tJcPqJdMapper.checkJdbhUnique(map);
        if (count > 0)
        {
           return "1";
        }
        return "0";
    }
    
    @Override
    public String checkJdmcUnique(String id, String jdmc) {
    	// TODO Auto-generated method stub
    	if (id != null && id.length() != 0) {
    	}else {
    		id = "1";
    	}
    	Map <String , String> map = new HashMap <String , String> ();
        map.put("id", id);
        map.put("jdmc", jdmc);
    	
        int count = tJcPqJdMapper.checkJdmcUnique(map);
        if (count > 0)
        {
           return "1";
        }
        return "0";
    }
}
