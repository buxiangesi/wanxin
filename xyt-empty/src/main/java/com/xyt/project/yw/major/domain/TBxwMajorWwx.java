package com.xyt.project.yw.major.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xyt.framework.aspectj.lang.annotation.Excel;
import com.xyt.framework.web.domain.BaseEntity;

/**
 * 专业对象 t_bxw_major_wwx
 *
 * @author ruoyi
 * @date 2024-07-16
 */
public class TBxwMajorWwx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 专业编号 */
    @Excel(name = "专业编号")
    private String zybh;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String zymc;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setZybh(String zybh)
    {
        this.zybh = zybh;
    }

    public String getZybh()
    {
        return zybh;
    }
    public void setZymc(String zymc)
    {
        this.zymc = zymc;
    }

    public String getZymc()
    {
        return zymc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("zybh", getZybh())
            .append("zymc", getZymc())
            .toString();
    }
}
