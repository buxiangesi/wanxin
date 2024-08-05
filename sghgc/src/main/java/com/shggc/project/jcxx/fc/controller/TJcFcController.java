package com.shggc.project.jcxx.fc.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shggc.framework.aspectj.lang.annotation.Log;
import com.shggc.framework.aspectj.lang.enums.BusinessType;
import com.shggc.project.jcxx.fc.domain.TJcFc;
import com.shggc.project.jcxx.fc.service.ITJcFcService;
import com.shggc.project.jcxx.fw.domain.TJcFcFw;
import com.shggc.project.jcxx.pqjd.service.ITJcPqJdService;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.framework.web.domain.AjaxResult;
import com.shggc.framework.web.domain.Ztree;
import com.shggc.common.utils.poi.ExcelUtil;
import com.shggc.framework.web.page.TableDataInfo;

/**
 * 房产信息Controller
 *
 * @author ruoyi
 * @date 2022-07-27
 */
@Controller
@RequestMapping("/jcxx/fc")
public class TJcFcController extends BaseController
{
    private String prefix = "jcxx/fc";

    @Autowired
    private ITJcFcService tJcFcService;
    
    @Autowired
    private ITJcPqJdService tJcPqJdService;

    @RequiresPermissions("jcxx:fc:view")
    @GetMapping()
    public String fc()
    {
        return "jcxx/fc/fc";
    }

    /**
     * 查询房产信息列表
     */
    @RequiresPermissions("jcxx:fc:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TJcFc tJcFc)
    {
        startPage();
        List<TJcFc> fcs = tJcFcService.selectTJcFcList(tJcFc);
        for(TJcFc fc : fcs) {
			List<TJcFcFw> fws = fc.gettJcFcFws();
        	fc.setFwtms( fws.size() );   
        	double jzmj = 0.0;
        	double fwjs = 0.0;
        	for(TJcFcFw fw : fws) {
        		jzmj = jzmj + fw.getJzmj();
        		fwjs = fwjs + fw.getFwjs();
        	}
        	fc.setJzmj(jzmj);
        	fc.setFwjs(fwjs);
        }
        return getDataTable(fcs);
    }

    /**
     * 导出房产信息列表
     */
    @RequiresPermissions("jcxx:fc:export")
    @Log(title = "房产信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TJcFc tJcFc)
    {
        List<TJcFc> fcs = tJcFcService.selectTJcFcList(tJcFc);
        for(TJcFc fc : fcs) {
			List<TJcFcFw> fws = fc.gettJcFcFws();
			fc.setJdmc(fc.gettJcPqJd().getJdmc());
			fc.setPqmc(fc.gettJcPqJd().getPqmc());
        	fc.setFwtms( fws.size() );   
        	double jzmj = 0.0;
        	double fwjs = 0.0;
        	for(TJcFcFw fw : fws) {
        		jzmj = jzmj + fw.getJzmj();
        		fwjs = fwjs + fw.getFwjs();
        	}
        	fc.setJzmj(jzmj);
        	fc.setFwjs(fwjs);
        }
        ExcelUtil<TJcFc> util = new ExcelUtil<TJcFc>(TJcFc.class);
        return util.exportExcel(fcs, "fc数据");
    }

    /**
     * 新增房产信息
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
    	modelMap.put("jds", tJcPqJdService.selectTJcPqJdAll());
        return "jcxx/fc/add";
    }

    /**
     * 新增保存房产信息
     */
    @RequiresPermissions("jcxx:fc:add")
    @Log(title = "房产信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TJcFc tJcFc)
    {
  
        return toAjax(tJcFcService.insertTJcFc(tJcFc));
    }

    /**
     * 修改房产信息
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("jcxx:fc:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TJcFc tJcFc = tJcFcService.selectTJcFcById(id);
        mmap.put("tJcFc", tJcFc);
        return "jcxx/fc/edit";
    }

    /**
     * 修改保存房产信息
     */
    @RequiresPermissions("jcxx:fc:edit")
    @Log(title = "房产信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TJcFc tJcFc)
    {
        return toAjax(tJcFcService.updateTJcFc(tJcFc));
    }

    /**
     * 删除房产信息
     */
    @RequiresPermissions("jcxx:fc:remove")
    @Log(title = "房产信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tJcFcService.deleteTJcFcByIds(ids));
    }
    
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = tJcFcService.selectPqJdTree();
        return ztrees;
    }
}
