package com.shggc.project.jcxx.pqjd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.shggc.framework.aspectj.lang.annotation.Excel;
import com.shggc.framework.web.domain.BaseEntity;

/**
 * 片区街道对象 t_jc_pq_jd
 *
 * @author ruoyi
 * @date 2022-07-25
 */
public class TJcPqJd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 片区id */
    @Excel(name = "片区id")
    private String pqid;
    
    /** 片区名称 */
    @Excel(name = "片区名称")
    private String pqmc;

    /** 街道编号 */
    @Excel(name = "街道编号")
    private String jdbh;

    /** 街道名称 */
    @Excel(name = "街道名称")
    private String jdmc;

    /** 注销标志 */
    @Excel(name = "注销标志")
    private String zxbz;

    /** 备注 */
    @Excel(name = "备注")
    private String bz;

    /** 扩展1 */
    private String kz1;

    /** 扩展2 */
    private String kz2;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
   
    public String getPqid() {
		return pqid;
	}

	public void setPqid(String pqid) {
		this.pqid = pqid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setJdbh(String jdbh)
    {
        this.jdbh = jdbh;
    }

    public String getJdbh()
    {
        return jdbh;
    }
    public void setJdmc(String jdmc)
    {
        this.jdmc = jdmc;
    }

    public String getJdmc()
    {
        return jdmc;
    }
    public void setZxbz(String zxbz)
    {
        this.zxbz = zxbz;
    }

    public String getZxbz()
    {
        return zxbz;
    }
    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public String getBz()
    {
        return bz;
    }
    public void setKz1(String kz1)
    {
        this.kz1 = kz1;
    }

    public String getKz1()
    {
        return kz1;
    }
    public void setKz2(String kz2)
    {
        this.kz2 = kz2;
    }

    public String getKz2()
    {
        return kz2;
    }
    
    

    public String getPqmc() {
		return pqmc;
	}

	public void setPqmc(String pqmc) {
		this.pqmc = pqmc;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pqbh", getPqid())
            .append("jdbh", getJdbh())
            .append("jdmc", getJdmc())
            .append("zxbz", getZxbz())
            .append("bz", getBz())
            .append("kz1", getKz1())
            .append("kz2", getKz2())
            .toString();
    }
}
