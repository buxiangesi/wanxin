package com.shggc.project.jcxx.pqjd.controller;

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
import com.shggc.project.jcxx.pq.domain.TJcPq;
import com.shggc.project.jcxx.pq.service.ITJcPqService;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;
import com.shggc.project.jcxx.pqjd.service.ITJcPqJdService;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.framework.web.domain.AjaxResult;
import com.shggc.common.exception.BusinessException;
import com.shggc.common.utils.poi.ExcelUtil;
import com.shggc.framework.web.page.TableDataInfo;

/**
 * 片区街道Controller
 *
 * @author ruoyi
 * @date 2022-07-25
 */
@Controller
@RequestMapping("/jcxx/pqjd")
public class TJcPqJdController extends BaseController
{
    private String prefix = "jcxx/pqjd";

    @Autowired
    private ITJcPqJdService tJcPqJdService;
    
    @Autowired
    private ITJcPqService tJcPqService;

    @RequiresPermissions("jcxx:pqjd:view")
    @GetMapping()
    public String pqjd()
    {
        return "jcxx/pqjd/pqjd";
    }

    /**
     * 查询片区街道列表
     */
    @RequiresPermissions("jcxx:pqjd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TJcPqJd tJcPqJd)
    {
        startPage();
        List<TJcPqJd> list = tJcPqJdService.selectTJcPqJdList(tJcPqJd);
        return getDataTable(list);
    }

    /**
     * 导出片区街道列表
     */
    @RequiresPermissions("jcxx:pqjd:export")
    @Log(title = "片区街道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TJcPqJd tJcPqJd)
    {
        List<TJcPqJd> list = tJcPqJdService.selectTJcPqJdList(tJcPqJd);
        ExcelUtil<TJcPqJd> util = new ExcelUtil<TJcPqJd>(TJcPqJd.class);
        return util.exportExcel(list, "pqjd数据");
    }

    /**
     * 新增片区街道
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
    	modelMap.put("pqs", tJcPqService.selectTJcPqAll());
        return "jcxx/pqjd/add";
    }

    /**
     * 新增保存片区街道
     */
    @RequiresPermissions("jcxx:pqjd:add")
    @Log(title = "片区街道", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TJcPqJd tJcPqJd)
    {
        return toAjax(tJcPqJdService.insertTJcPqJd(tJcPqJd));
    }

    /**
     * 修改片区街道
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("jcxx:pqjd:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TJcPqJd tJcPqJd = tJcPqJdService.selectTJcPqJdById(id);
        mmap.put("tJcPqJd", tJcPqJd);
        return "jcxx/pqjd/edit";
    }

    /**
     * 修改保存片区街道
     */
    @RequiresPermissions("jcxx:pqjd:edit")
    @Log(title = "片区街道", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TJcPqJd tJcPqJd)
    {
        return toAjax(tJcPqJdService.updateTJcPqJd(tJcPqJd));
    }

    /**
     * 删除片区街道
     */
    @RequiresPermissions("jcxx:pqjd:remove")
    @Log(title = "片区街道", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tJcPqJdService.deleteTJcPqJdByIds(ids));
    }
    
    /**
     * 校验片区编号
     */
    @PostMapping("/checkJdbhUnique")
    @ResponseBody
    public String checkJdbhUnique(TJcPqJd tJcPqJd){
    	String back = tJcPqJdService.checkJdbhUnique(tJcPqJd.getId(),tJcPqJd.getJdbh());
        return back;
    }
    
    /**
     * 校验片区编号
     */
    @PostMapping("/checkJdmcUnique")
    @ResponseBody
    public String checkJdmcUnique(TJcPqJd tJcPqJd){
    	String back = tJcPqJdService.checkJdmcUnique(tJcPqJd.getId(),tJcPqJd.getJdmc());
        return back;
    }
}
