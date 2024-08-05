package com.shggc.project.jcxx.fw.controller;

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
import com.shggc.project.jcxx.fw.domain.TJcFcFw;
import com.shggc.project.jcxx.fw.service.ITJcFcFwService;
import com.shggc.framework.web.controller.BaseController;
import com.shggc.framework.web.domain.AjaxResult;
import com.shggc.common.utils.poi.ExcelUtil;
import com.shggc.framework.web.page.TableDataInfo;

/**
 * 房产房屋信息Controller
 *
 * @author ruoyi
 * @date 2022-07-28
 */
@Controller
@RequestMapping("/jcxx/fw")
public class TJcFcFwController extends BaseController
{
    private String prefix = "jcxx/fw";

    @Autowired
    private ITJcFcFwService tJcFcFwService;

    @RequiresPermissions("jcxx:fw:view")
    @GetMapping()
    public String fw()
    {
        return "jcxx/fw/fw";
    }

    /**
     * 查询房产房屋信息列表
     */
    @RequiresPermissions("jcxx:fw:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TJcFcFw tJcFcFw)
    {
        startPage();
        List<TJcFcFw> list = tJcFcFwService.selectTJcFcFwList(tJcFcFw);
        return getDataTable(list);
    }

    /**
     * 导出房产房屋信息列表
     */
    @RequiresPermissions("jcxx:fw:export")
    @Log(title = "房产房屋信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TJcFcFw tJcFcFw)
    {
        List<TJcFcFw> list = tJcFcFwService.selectTJcFcFwList(tJcFcFw);
        ExcelUtil<TJcFcFw> util = new ExcelUtil<TJcFcFw>(TJcFcFw.class);
        return util.exportExcel(list, "fw数据");
    }

    /**
     * 新增房产房屋信息
     */
    @GetMapping("/add")
    public String add()
    {
        return "jcxx/fw/add";
    }

    /**
     * 新增保存房产房屋信息
     */
    @RequiresPermissions("jcxx:fw:add")
    @Log(title = "房产房屋信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TJcFcFw tJcFcFw)
    {
        return toAjax(tJcFcFwService.insertTJcFcFw(tJcFcFw));
    }

    /**
     * 修改房产房屋信息
     */
    @GetMapping("/edit/{id}")
	@RequiresPermissions("jcxx:fw:edit")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TJcFcFw tJcFcFw = tJcFcFwService.selectTJcFcFwById(id);
        mmap.put("tJcFcFw", tJcFcFw);
        return "jcxx/fw/edit";
    }

    /**
     * 修改保存房产房屋信息
     */
    @RequiresPermissions("jcxx:fw:edit")
    @Log(title = "房产房屋信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TJcFcFw tJcFcFw)
    {
        return toAjax(tJcFcFwService.updateTJcFcFw(tJcFcFw));
    }

    /**
     * 删除房产房屋信息
     */
    @RequiresPermissions("jcxx:fw:remove")
    @Log(title = "房产房屋信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tJcFcFwService.deleteTJcFcFwByIds(ids));
    }
}
