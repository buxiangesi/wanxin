package com.xyt.project.yw.major.controller;

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
import com.xyt.framework.web.controller.BaseController;
import com.xyt.framework.web.domain.AjaxResult;
import com.xyt.common.utils.poi.ExcelUtil;
import com.xyt.framework.web.page.TableDataInfo;

/**
 * 专业Controller
 *
 * @author ruoyi
 * @date 2024-07-16
 */
@Controller
@RequestMapping("/yw/major")
public class TBxwMajorWwxController extends BaseController
{
    private String prefix = "yw/major";

    @Autowired
    private ITBxwMajorWwxService tBxwMajorWwxService;

    @RequiresPermissions("yw:major:view")
    @GetMapping()
    public String major()
    {
        return "yw/major/major";
    }

    /**
     * 查询专业列表
     */
    @RequiresPermissions("yw:major:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBxwMajorWwx tBxwMajorWwx)
    {
        startPage();
        List<TBxwMajorWwx> list = tBxwMajorWwxService.selectTBxwMajorWwxList(tBxwMajorWwx);
        return getDataTable(list);
    }

    /**
     * 导出专业列表
     */
    @RequiresPermissions("yw:major:export")
    @Log(title = "专业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBxwMajorWwx tBxwMajorWwx)
    {
        List<TBxwMajorWwx> list = tBxwMajorWwxService.selectTBxwMajorWwxList(tBxwMajorWwx);
        ExcelUtil<TBxwMajorWwx> util = new ExcelUtil<TBxwMajorWwx>(TBxwMajorWwx.class);
        return util.exportExcel(list, "major数据");
    }

    /**
     * 新增专业
     */
    @GetMapping("/add")
    public String add()
    {
        return "yw/major/add";
    }

    /**
     * 新增保存专业
     */
    @RequiresPermissions("yw:major:add")
    @Log(title = "专业", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBxwMajorWwx tBxwMajorWwx)
    {
        return toAjax(tBxwMajorWwxService.insertTBxwMajorWwx(tBxwMajorWwx));
    }

    /**
     * 修改专业
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("yw:major:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TBxwMajorWwx tBxwMajorWwx = tBxwMajorWwxService.selectTBxwMajorWwxById(id);
        mmap.put("tBxwMajorWwx", tBxwMajorWwx);
        return "yw/major/edit";
    }

    /**
     * 修改保存专业
     */
    @RequiresPermissions("yw:major:edit")
    @Log(title = "专业", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBxwMajorWwx tBxwMajorWwx)
    {
        return toAjax(tBxwMajorWwxService.updateTBxwMajorWwx(tBxwMajorWwx));
    }

    /**
     * 删除专业
     */
    @RequiresPermissions("yw:major:remove")
    @Log(title = "专业", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBxwMajorWwxService.deleteTBxwMajorWwxByIds(ids));
    }
}
