package com.shggc.project.jcxx.pq.controller;

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
import com.shggc.framework.web.controller.BaseController;
import com.shggc.framework.web.domain.AjaxResult;
import com.shggc.common.utils.poi.ExcelUtil;
import com.shggc.framework.web.page.TableDataInfo;

/**
 * 片区Controller
 *
 * @author ruoyi
 * @date 2022-07-22
 */
@Controller
@RequestMapping("/jcxx/pq")
public class TJcPqController extends BaseController
{
    private String prefix = "jcxx/pq";

    @Autowired
    private ITJcPqService tJcPqService;

    @RequiresPermissions("jcxx:pq:view")
    @GetMapping()
    public String pq()
    {
        return "jcxx/pq/pq";
    }

    /**
     * 查询片区列表
     */
    @RequiresPermissions("jcxx:pq:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TJcPq tJcPq)
    {
        startPage();
        List<TJcPq> list = tJcPqService.selectTJcPqList(tJcPq);
        return getDataTable(list);
    }

    /**
     * 导出片区列表
     */
    @RequiresPermissions("jcxx:pq:export")
    @Log(title = "片区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TJcPq tJcPq)
    {
        List<TJcPq> list = tJcPqService.selectTJcPqList(tJcPq);
        ExcelUtil<TJcPq> util = new ExcelUtil<TJcPq>(TJcPq.class);
        return util.exportExcel(list, "pq数据");
    }

    /**
     * 新增片区
     */
    @GetMapping("/add")
    public String add()
    {
        return "jcxx/pq/add";
    }

    /**
     * 新增保存片区
     */
    @RequiresPermissions("jcxx:pq:add")
    @Log(title = "片区", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TJcPq tJcPq)
    {
        return toAjax(tJcPqService.insertTJcPq(tJcPq));
    }

    /**
     * 修改片区
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("jcxx:pq:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TJcPq tJcPq = tJcPqService.selectTJcPqById(id);
        mmap.put("tJcPq", tJcPq);
        return "jcxx/pq/edit";
    }

    /**
     * 修改保存片区
     */
    @RequiresPermissions("jcxx:pq:edit")
    @Log(title = "片区", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TJcPq tJcPq)
    {
        return toAjax(tJcPqService.updateTJcPq(tJcPq));
    }

    /**
     * 删除片区
     */
    @RequiresPermissions("jcxx:pq:remove")
    @Log(title = "片区", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tJcPqService.deleteTJcPqByIds(ids));
    }
    
    /**
     * 校验片区编号
     */
    @PostMapping("/checkPqbhUnique")
    @ResponseBody
    public String checkXqbhUnique(TJcPq tJcPq){
    	String back = tJcPqService.checkPqbhUnique(tJcPq.getId(),tJcPq.getPqbh());
        return back;
    }
    
    /**
     * 校验片区编号
     */
    @PostMapping("/checkPqmcUnique")
    @ResponseBody
    public String checkXqmcUnique(TJcPq tJcPq){
    	String back = tJcPqService.checkPqmcUnique(tJcPq.getId(),tJcPq.getPqmc());
        return back;
    }
}
