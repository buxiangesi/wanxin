package com.xyt.project.yw.course.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xyt.framework.aspectj.lang.annotation.Excel;
import com.xyt.framework.web.domain.BaseEntity;

/**
 * 课程对象 t_bxw_course_wwx
 *
 * @author ruoyi
 * @date 2024-07-17
 */
public class TBxwCourseWwx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String kcbh;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String kcmc;

    /** 主讲教师 */
    @Excel(name = "主讲教师")
    private String zjjs;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setKcbh(String kcbh)
    {
        this.kcbh = kcbh;
    }

    public String getKcbh()
    {
        return kcbh;
    }
    public void setKcmc(String kcmc)
    {
        this.kcmc = kcmc;
    }

    public String getKcmc()
    {
        return kcmc;
    }
    public void setZjjs(String zjjs)
    {
        this.zjjs = zjjs;
    }

    public String getZjjs()
    {
        return zjjs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("kcbh", getKcbh())
            .append("kcmc", getKcmc())
            .append("zjjs", getZjjs())
            .toString();
    }
}
