package com.shggc.project.jcxx.pq.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.shggc.framework.aspectj.lang.annotation.Excel;
import com.shggc.framework.web.domain.BaseEntity;
import com.shggc.project.jcxx.pqjd.domain.*;

/**
 * 片区对象 t_jc_pq
 *
 * @author ruoyi
 * @date 2022-07-22
 */
public class TJcPq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 片区编号 */
    @Excel(name = "片区编号")
    private String pqbh;

    /** 片区名称 */
    @Excel(name = "片区名称")
    private String pqmc;

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
    
    private List <TJcPqJd> tJcPqJds;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setPqbh(String pqbh)
    {
        this.pqbh = pqbh;
    }

    public String getPqbh()
    {
        return pqbh;
    }
    public void setPqmc(String pqmc)
    {
        this.pqmc = pqmc;
    }

    public String getPqmc()
    {
        return pqmc;
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

    
    
    public List<TJcPqJd> gettJcPqJds() {
		return tJcPqJds;
	}

	public void settJcPqJds(List<TJcPqJd> tJcPqJds) {
		this.tJcPqJds = tJcPqJds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TJcPq [id=" + id + ", pqbh=" + pqbh + ", pqmc=" + pqmc + ", zxbz=" + zxbz + ", bz=" + bz + ", kz1="
				+ kz1 + ", kz2=" + kz2 + ", tJcPqJds=" + tJcPqJds + "]";
	}
}
