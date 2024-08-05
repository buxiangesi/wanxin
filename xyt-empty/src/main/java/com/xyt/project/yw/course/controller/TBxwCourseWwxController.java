package com.xyt.project.yw.course.controller;

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
import com.xyt.framework.aspectj.lang.annotation.Log;
import com.xyt.framework.aspectj.lang.enums.BusinessType;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;
import com.xyt.project.yw.course.service.ITBxwCourseWwxService;
import com.xyt.framework.web.controller.BaseController;
import com.xyt.framework.web.domain.AjaxResult;
import com.xyt.common.utils.poi.ExcelUtil;
import com.xyt.framework.web.page.TableDataInfo;

/**
 * 课程Controller
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Controller
@RequestMapping("/yw/course")
public class TBxwCourseWwxController extends BaseController
{
    private String prefix = "yw/course";

    @Autowired
    private ITBxwCourseWwxService tBxwCourseWwxService;

    @RequiresPermissions("yw:course:view")
    @GetMapping()
    public String course()
    {
        return "yw/course/course";
    }

    /**
     * 查询课程列表
     */
    @RequiresPermissions("yw:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBxwCourseWwx tBxwCourseWwx)
    {
        startPage();
        List<TBxwCourseWwx> list = tBxwCourseWwxService.selectTBxwCourseWwxList(tBxwCourseWwx);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @RequiresPermissions("yw:course:export")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBxwCourseWwx tBxwCourseWwx)
    {
        List<TBxwCourseWwx> list = tBxwCourseWwxService.selectTBxwCourseWwxList(tBxwCourseWwx);
        ExcelUtil<TBxwCourseWwx> util = new ExcelUtil<TBxwCourseWwx>(TBxwCourseWwx.class);
        return util.exportExcel(list, "course数据");
    }

    /**
     * 新增课程
     */
    @GetMapping("/add")
    public String add()
    {
        return "yw/course/add";
    }

    /**
     * 新增保存课程
     */
    @RequiresPermissions("yw:course:add")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBxwCourseWwx tBxwCourseWwx)
    {
        return toAjax(tBxwCourseWwxService.insertTBxwCourseWwx(tBxwCourseWwx));
    }

    /**
     * 修改课程
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("yw:course:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TBxwCourseWwx tBxwCourseWwx = tBxwCourseWwxService.selectTBxwCourseWwxById(id);
        mmap.put("tBxwCourseWwx", tBxwCourseWwx);
        return "yw/course/edit";
    }

    /**
     * 修改保存课程
     */
    @RequiresPermissions("yw:course:edit")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBxwCourseWwx tBxwCourseWwx)
    {
        return toAjax(tBxwCourseWwxService.updateTBxwCourseWwx(tBxwCourseWwx));
    }

    /**
     * 删除课程
     */
    @RequiresPermissions("yw:course:remove")
    @Log(title = "课程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBxwCourseWwxService.deleteTBxwCourseWwxByIds(ids));
    }
}
