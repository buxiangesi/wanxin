package com.shggc.project.jcxx.fc.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shggc.project.jcxx.fc.mapper.TJcFcMapper;
import com.shggc.project.jcxx.fc.domain.TJcFc;
import com.shggc.project.jcxx.fc.service.ITJcFcService;
import com.shggc.project.jcxx.fw.domain.TJcFcFw;
import com.shggc.project.jcxx.fw.mapper.TJcFcFwMapper;
import com.shggc.project.jcxx.pq.domain.TJcPq;
import com.shggc.project.jcxx.pq.mapper.TJcPqMapper;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;
import com.shggc.common.exception.BusinessException;
import com.shggc.common.utils.text.Convert;
import com.shggc.framework.web.domain.Ztree;

/**
 * 房产信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TJcFcServiceImpl implements ITJcFcService
{
    @Autowired
    private TJcFcMapper tJcFcMapper;
    
    @Autowired
    private TJcFcFwMapper tJcFcFwMapper;
    
    @Autowired
    private TJcPqMapper tJcPqMapper;

    /**
     * 查询房产信息
     *
     * @param id 房产信息主键
     * @return 房产信息
     */
    @Override
    public TJcFc selectTJcFcById(String id)
    {
        return tJcFcMapper.selectTJcFcById(id);
    }

    /**
     * 查询房产信息列表
     *
     * @param tJcFc 房产信息
     * @return 房产信息
     */
    @Override
    public List<TJcFc> selectTJcFcList(TJcFc tJcFc)
    {
        return tJcFcMapper.selectTJcFcList(tJcFc);
    }

    /**
     * 新增房产信息
     *
     * @param tJcFc 房产信息
     * @return 结果
     */
    @Override
    public int insertTJcFc(TJcFc tJcFc)
    {
    	int fcbh = tJcFcMapper.generateFcbh();
    	tJcFc.setFcbh(fcbh+"");
    	List <TJcFcFw> fws = tJcFc.gettJcFcFws();
    	int fcNum = tJcFcMapper.insertTJcFc(tJcFc);  
    	if(fws == null)return fcNum;
    	for(TJcFcFw fw : fws ) {
    		fw.setFcid(tJcFc.getId());
    		int fwNum =tJcFcFwMapper.insertTJcFcFw(fw);
    		fcNum += fwNum;
    	}
        return fcNum;
    }

    /**
     * 修改房产信息
     *
     * @param tJcFc 房产信息
     * @return 结果
     * @throws SQLException 
     */
    @Override
    public int updateTJcFc(TJcFc tJcFc)
    {
    	List <TJcFcFw> fws = tJcFc.gettJcFcFws();
    	int fcNum  = tJcFcMapper.updateTJcFc(tJcFc);
        tJcFcFwMapper.deleteTJcFcFwByFcId(tJcFc.getId()); 
        if(fws == null)return fcNum;
    	for(TJcFcFw fw : fws ) {
    		fw.setFcid(tJcFc.getId());
    		int fwNum =tJcFcFwMapper.insertTJcFcFw(fw);
    		fcNum += fwNum;
    	}
        return fcNum;
    }

    /**
	 * 批量删除房产信息
     *
	 * @param ids 需要删除的房产信息主键
     * @return 结果
     */
    @Override
    public int deleteTJcFcByIds(String ids)
    {
    	String[] iids = Convert.toStrArray(ids);
        for (String id : iids)
        {
        	TJcFc tjcFc = tJcFcMapper.selectTJcFcById(id);
            int pqjdNum = tjcFc.gettJcFcFws().size();
            if (pqjdNum > 0)
            {
                throw new BusinessException(String.format("该下已经有房屋信息,不能删除"));
            }
        }
        return tJcFcMapper.deleteTJcFcByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除房产信息信息
     *
     * @param id 房产信息ID
     * @return 结果
     */
    @Override
    public int deleteTJcFcById(String id)
    {
        return tJcFcMapper.deleteTJcFcById(id);
    }
    
    /**
     * 查询片区街道树结构
     *
     * @return 片区集合
     */
    @Override
    public List<Ztree> selectPqJdTree(){
    	List <TJcPq> pqs = tJcPqMapper.selectPqJdTree();
    	List <Ztree> tree = new ArrayList <Ztree> ();
    	for(TJcPq pq : pqs) {
    		Ztree zt = new Ztree();
    		zt.setId((long)Integer.parseInt(pq.getId()));
    		zt.setName(pq.getPqmc());
    		zt.setTitle(pq.getPqmc());
    		zt.setpId(0L);
    		tree.add(zt);
    		List <TJcPqJd> jds = pq.gettJcPqJds();
    		if(jds == null)continue;
    		for(TJcPqJd jd : jds) {
    			Ztree ztjd = new Ztree();
    			ztjd.setId((long)Integer.parseInt(jd.getId()));
    			ztjd.setName(jd.getJdmc());
    			ztjd.setTitle(jd.getJdmc());
    			ztjd.setpId((long)Integer.parseInt(pq.getId()));
    			tree.add(ztjd);
    		}
    	}
    	return tree;
    }
}
