package com.xyt.project.yw.stucour.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xyt.framework.aspectj.lang.annotation.Excel;
import com.xyt.framework.web.domain.BaseEntity;
import com.xyt.project.yw.course.domain.TBxwCourseWwx;

/**
 * 选课对象 t_bxw_stucour_wwx
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public class TBxwStucourWwx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 学生ID 参考学生表主键 */
    @Excel(name = "学生ID 参考学生表主键")
    private String tBxwStudentWwxId;

    /** 课程ID 参考课程表主键 */
    @Excel(name = "课程ID 参考课程表主键")
    private String tBxwCourseWwxId;
    
    public TBxwStucourWwx getStu() {
		return stu;
	}

	public void setStu(TBxwStucourWwx stu) {
		this.stu = stu;
	}

	public List<TBxwCourseWwx> getCours() {
		return cours;
	}

	public void setCours(List<TBxwCourseWwx> cours) {
		this.cours = cours;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private TBxwStucourWwx stu;
    
    private List <TBxwCourseWwx> cours;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void settBxwStudentWwxId(String tBxwStudentWwxId)
    {
        this.tBxwStudentWwxId = tBxwStudentWwxId;
    }

    public String gettBxwStudentWwxId()
    {
        return tBxwStudentWwxId;
    }
    public void settBxwCourseWwxId(String tBxwCourseWwxId)
    {
        this.tBxwCourseWwxId = tBxwCourseWwxId;
    }

    public String gettBxwCourseWwxId()
    {
        return tBxwCourseWwxId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tBxwStudentWwxId", gettBxwStudentWwxId())
            .append("tBxwCourseWwxId", gettBxwCourseWwxId())
            .toString();
    }
}
