package com.xyt.project.yw.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xyt.framework.aspectj.lang.annotation.Excel;
import com.xyt.framework.web.domain.BaseEntity;
import com.xyt.project.yw.major.domain.TBxwMajorWwx;

/**
 * 学生对象 t_bxw_student_wwx
 *
 * @author ruoyi
 * @date 2024-07-17
 */
public class TBxwStudentWwx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 学号 */
    @Excel(name = "学号")
    private String xh;

    /** 姓名 */
    @Excel(name = "姓名")
    private String xm;

    /** 性别 */
    @Excel(name = "性别")
    private String xb;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date csrq;

    /** 专业 */
    @Excel(name = "专业")
    private String tBxwMajorWwxId;

    //学生专业类
    
    private  TBxwMajorWwx tBxwMajorWwx;
    
    public TBxwMajorWwx gettBxwMajorWwx() {
		return tBxwMajorWwx;
	}

	public void settBxwMajorWwx(TBxwMajorWwx tBxwMajorWwx) {
		this.tBxwMajorWwx = tBxwMajorWwx;
	}

	public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setXh(String xh)
    {
        this.xh = xh;
    }

    public String getXh()
    {
        return xh;
    }
    public void setXm(String xm)
    {
        this.xm = xm;
    }

    public String getXm()
    {
        return xm;
    }
    public void setXb(String xb)
    {
        this.xb = xb;
    }

    public String getXb()
    {
        return xb;
    }
    public void setCsrq(Date csrq)
    {
        this.csrq = csrq;
    }

    public Date getCsrq()
    {
        return csrq;
    }
    public void settBxwMajorWwxId(String tBxwMajorWwxId)
    {
        this.tBxwMajorWwxId = tBxwMajorWwxId;
    }

    public String gettBxwMajorWwxId()
    {
        return tBxwMajorWwxId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("xh", getXh())
            .append("xm", getXm())
            .append("xb", getXb())
            .append("csrq", getCsrq())
            .append("tBxwMajorWwxId", gettBxwMajorWwxId())
            .toString();
    }
}
