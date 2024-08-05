package com.xyt.project.yw.student.controller;

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
import com.xyt.project.yw.major.domain.TBxwMajorWwx;
import com.xyt.project.yw.major.service.ITBxwMajorWwxService;
import com.xyt.project.yw.student.domain.TBxwStudentWwx;
import com.xyt.project.yw.student.service.ITBxwStudentWwxService;
import com.xyt.framework.web.controller.BaseController;
import com.xyt.framework.web.domain.AjaxResult;
import com.xyt.common.utils.poi.ExcelUtil;
import com.xyt.framework.web.page.TableDataInfo;

/**
 * 学生Controller
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Controller
@RequestMapping("/yw/student")
public class TBxwStudentWwxController extends BaseController
{
    private String prefix = "yw/student";

    @Autowired
    private ITBxwStudentWwxService tBxwStudentWwxService;
    @Autowired
    private ITBxwMajorWwxService tBxwMajorWwxService;
    @RequiresPermissions("yw:student:view")
    @GetMapping()
    public String student(ModelMap mmap)
    {
    	List <TBxwMajorWwx> majors=tBxwMajorWwxService.selectTBxwMajorWwxList(new TBxwMajorWwx());
    	mmap.put("majors", majors);
        return "yw/student/student";
    }

    /**
     * 查询学生列表
     */
    @RequiresPermissions("yw:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBxwStudentWwx tBxwStudentWwx)
    {
        startPage();
        List<TBxwStudentWwx> list = tBxwStudentWwxService.selectTBxwStudentWwxList(tBxwStudentWwx);
       
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @RequiresPermissions("yw:student:export")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBxwStudentWwx tBxwStudentWwx)
    {
        List<TBxwStudentWwx> list = tBxwStudentWwxService.selectTBxwStudentWwxList(tBxwStudentWwx);
        ExcelUtil<TBxwStudentWwx> util = new ExcelUtil<TBxwStudentWwx>(TBxwStudentWwx.class);
        return util.exportExcel(list, "student数据");
    }

    /**
     * 新增学生
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	List <TBxwMajorWwx> majors=tBxwMajorWwxService.selectTBxwMajorWwxList(new TBxwMajorWwx());
    	mmap.put("majors", majors);
        return "yw/student/add";
    }

    /**
     * 新增保存学生
     */
    @RequiresPermissions("yw:student:add")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBxwStudentWwx tBxwStudentWwx)
    {
        return toAjax(tBxwStudentWwxService.insertTBxwStudentWwx(tBxwStudentWwx));
    }

    /**
     * 修改学生
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("yw:student:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TBxwStudentWwx tBxwStudentWwx = tBxwStudentWwxService.selectTBxwStudentWwxById(id);
        List <TBxwMajorWwx> majors=tBxwMajorWwxService.selectTBxwMajorWwxList(new TBxwMajorWwx());
    	mmap.put("majors", majors);
        mmap.put("tBxwStudentWwx", tBxwStudentWwx);
        return "yw/student/edit";
    }

    /**
     * 修改保存学生
     */
    @RequiresPermissions("yw:student:edit")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBxwStudentWwx tBxwStudentWwx)
    {
        return toAjax(tBxwStudentWwxService.updateTBxwStudentWwx(tBxwStudentWwx));
    }

    /**
     * 删除学生
     */
    @RequiresPermissions("yw:student:remove")
    @Log(title = "学生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBxwStudentWwxService.deleteTBxwStudentWwxByIds(ids));
    }
}
