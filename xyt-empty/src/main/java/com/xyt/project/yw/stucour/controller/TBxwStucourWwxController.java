package com.xyt.project.yw.stucour.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xyt.framework.aspectj.lang.annotation.Log;
import com.xyt.framework.aspectj.lang.enums.BusinessType;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.stucour.domain.TBxwStucourWwx;
import com.xyt.project.yw.stucour.service.ITBxwStucourWwxService;
import com.xyt.framework.web.controller.BaseController;
import com.xyt.framework.web.domain.AjaxResult;
import com.xyt.common.utils.poi.ExcelUtil;
import com.xyt.framework.web.page.TableDataInfo;

/**
 * 选课Controller
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@Controller
@RequestMapping("/yw/stucour")
public class TBxwStucourWwxController extends BaseController
{
    private String prefix = "yw/stucour";

    @Autowired
    private ITBxwStucourWwxService tBxwStucourWwxService;

    @RequiresPermissions("yw:stucour:view")
    @GetMapping("/{id}")
    public String stucour(@PathVariable("id") String id, ModelMap mmap)
    {    
    	mmap.put("stuid", id);
    	
        return "yw/stucour/stucour";
    }

    /**
     * 查询选课列表
     */
    @RequiresPermissions("yw:stucour:list")
    @PostMapping("/list/{stuId}")
    @ResponseBody
    public TableDataInfo list(TBxwStucourWwx tBxwStucourWwx,@PathVariable("stuId") String stuId)
    {
        startPage();
        List<TBxwCourseWwx> list = tBxwStucourWwxService.selectTBxwCourseByStudent(stuId);
        return getDataTable(list);
    }

    /**
     * 导出选课列表
     */
    @RequiresPermissions("yw:stucour:export")
    @Log(title = "选课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBxwStucourWwx tBxwStucourWwx)
    {
        List<TBxwStucourWwx> list = tBxwStucourWwxService.selectTBxwStucourWwxList(tBxwStucourWwx);
        ExcelUtil<TBxwStucourWwx> util = new ExcelUtil<TBxwStucourWwx>(TBxwStucourWwx.class);
        return util.exportExcel(list, "stucour数据");
    }

    /**
     * 新增选课
     */
    @GetMapping("/add")
    public String add()
    {
        return "yw/stucour/add";
    }

    /**
     * 新增保存选课
     */
    @RequiresPermissions("yw:stucour:add")
    @Log(title = "选课", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBxwStucourWwx tBxwStucourWwx)
    {
        return toAjax(tBxwStucourWwxService.insertTBxwStucourWwx(tBxwStucourWwx));
    }

    /**
     * 修改选课
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("yw:stucour:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TBxwStucourWwx tBxwStucourWwx = tBxwStucourWwxService.selectTBxwStucourWwxById(id);
        mmap.put("tBxwStucourWwx", tBxwStucourWwx);
        return "yw/stucour/edit";
    }

    /**
     * 修改保存选课
     */
    @RequiresPermissions("yw:stucour:edit")
    @Log(title = "选课", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBxwStucourWwx tBxwStucourWwx)
    {
        return toAjax(tBxwStucourWwxService.updateTBxwStucourWwx(tBxwStucourWwx));
    }

    /**
     * 删除选课
     */
    @RequiresPermissions("yw:stucour:remove")
    @Log(title = "选课", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBxwStucourWwxService.deleteTBxwStucourWwxByIds(ids));
    }
}
