package com.shggc.project.jcxx.fc.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.shggc.framework.aspectj.lang.annotation.Excel;
import com.shggc.framework.web.domain.BaseEntity;
import com.shggc.project.jcxx.fw.domain.TJcFcFw;
import com.shggc.project.jcxx.pqjd.domain.TJcPqJd;

/**
 * 房产信息对象 t_jc_fc
 *
 * @author ruoyi
 * @date 2022-07-27
 */
public class TJcFc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;
    
    /***********导出属性*************/
    /** 房产编号 */
    @Excel(name = "房产编号")
    private String fcbh;
    
    @Excel(name = "所属片区")
    private String pqmc; 
    
    @Excel(name = "所属街道")
    private String jdmc;  
    
    /** 房屋产别 */
    @Excel(name = "房屋产别")
    private String fwcb;

    /** 房屋座落 */
    @Excel(name = "房屋座落")
    private String fwzl;

    /** 产权人 */
    @Excel(name = "产权人")
    private String cqr = "";

    /** 产权人身份证号码 */
    @Excel(name = "产权人身份证号码")
    private String cqrsfz = "";

    /** 产权人联系电话 */
    @Excel(name = "产权人联系电话")
    private String cqrlxdh = "";

    /** 居住人 */
    @Excel(name = "居住人")
    private String jzr = "";

    /** 居住人联系电话 */
    @Excel(name = "居住人联系电话")
    private String jzrlxdh = "";

    /** 房屋产权证号 */
    @Excel(name = "房屋产权证号")
    private String fwcqz = "";

    /** 土地使用证号 */
    @Excel(name = "土地使用证号")
    private String tdsyzh = "";

    /** 土地使用面积（平方米） */
    @Excel(name = "土地使用面积", readConverterExp = "平=方米")
    private double tdsymj;

    /** 权利类型 */
    @Excel(name = "权利类型")
    private String qllx;

    /** 权利性质 */
    @Excel(name = "权利性质")
    private String qlxz;

    /** 是否无证房 */
    @Excel(name = "是否无证房")
    private String sfwzf;

    /** 房产出售 */
    @Excel(name = "房产出售")
    private String fccs;

    /** 建筑面积 */
    @Excel(name = "建筑面积")
    private double jzmj;
    
    /** 房屋间数 */
    @Excel(name = "房屋间数 ")
    private double fwjs;
    
    /** 房屋条目数 */
    @Excel(name = "房屋条目数")
    private int fwtms;
    
    /** 注销标志 */
    @Excel(name = "注销标志")
    private String zxbz;

    /** 备注 */
    @Excel(name = "备注")
    private String bz = "";
    
 
    /***********数据库扩展属性*************/
    /** 扩展1 */
    private String kz1 = "";

    /** 扩展2 */
    private String kz2 = "";
    
    
    /***********一对一及一对多属性*************/
    /** 街道信息 */
    private TJcPqJd tJcPqJd;
    
    /** 房屋信息 */
    List <TJcFcFw> tJcFcFws;
    

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
  
    public TJcPqJd gettJcPqJd() {
		return tJcPqJd;
	}

	public void settJcPqJd(TJcPqJd tJcPqJd) {
		this.tJcPqJd = tJcPqJd;
	}

	public void setFcbh(String fcbh)
    {
        this.fcbh = fcbh;
    }

    public String getFcbh()
    {
        return fcbh;
    }
    public void setFwzl(String fwzl)
    {
        this.fwzl = fwzl;
    }

    public String getFwzl()
    {
        return fwzl;
    }
    public void setCqr(String cqr)
    {
        this.cqr = cqr;
    }

    public String getCqr()
    {
        return cqr;
    }
    public void setCqrsfz(String cqrsfz)
    {
        this.cqrsfz = cqrsfz;
    }

    public String getCqrsfz()
    {
        return cqrsfz;
    }
    public void setCqrlxdh(String cqrlxdh)
    {
        this.cqrlxdh = cqrlxdh;
    }

    public String getCqrlxdh()
    {
        return cqrlxdh;
    }
    public void setJzr(String jzr)
    {
        this.jzr = jzr;
    }

    public String getJzr()
    {
        return jzr;
    }
    public void setJzrlxdh(String jzrlxdh)
    {
        this.jzrlxdh = jzrlxdh;
    }

    public String getJzrlxdh()
    {
        return jzrlxdh;
    }
    public void setFwcqz(String fwcqz)
    {
        this.fwcqz = fwcqz;
    }

    public String getFwcqz()
    {
        return fwcqz;
    }
    public void setTdsyzh(String tdsyzh)
    {
        this.tdsyzh = tdsyzh;
    }

    public String getTdsyzh()
    {
        return tdsyzh;
    }
    public void setTdsymj(double tdsymj)
    {
        this.tdsymj = tdsymj;
    }

    public double getTdsymj()
    {
        return tdsymj;
    }
    public void setQllx(String qllx)
    {
        this.qllx = qllx;
    }

    public String getQllx()
    {
        return qllx;
    }
    public void setQlxz(String qlxz)
    {
        this.qlxz = qlxz;
    }

    public String getQlxz()
    {
        return qlxz;
    }
    public void setSfwzf(String sfwzf)
    {
        this.sfwzf = sfwzf;
    }

    public String getSfwzf()
    {
        return sfwzf;
    }
    public void setFccs(String fccs)
    {
        this.fccs = fccs;
    }

    public String getFccs()
    {
        return fccs;
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
    
    public List<TJcFcFw> gettJcFcFws() {
		return tJcFcFws;
	}

	public void settJcFcFws(List<TJcFcFw> tJcFcFws) {
		this.tJcFcFws = tJcFcFws;
	}
	
	public int getFwtms() {
		return fwtms;
	}

	public void setFwtms(int fwtms) {
		this.fwtms = fwtms;
	}
	
	public String getFwcb() {
		return fwcb;
	}

	public void setFwcb(String fwcb) {
		this.fwcb = fwcb;
	}

	
	
	public String getPqmc() {
		return pqmc;
	}

	public void setPqmc(String pqmc) {
		this.pqmc = pqmc;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	public double getJzmj() {
		return jzmj;
	}

	public void setJzmj(double jzmj) {
		this.jzmj = jzmj;
	}

	public double getFwjs() {
		return fwjs;
	}

	public void setFwjs(double fwjs) {
		this.fwjs = fwjs;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jdxx", tJcPqJd.toString())
            .append("fcbh", getFcbh())
            .append("fwzl", getFwzl())
            .append("cqr", getCqr())
            .append("cqrsfz", getCqrsfz())
            .append("cqrlxdh", getCqrlxdh())
            .append("jzr", getJzr())
            .append("jzrlxdh", getJzrlxdh())
            .append("fwcqz", getFwcqz())
            .append("tdsyzh", getTdsyzh())
            .append("tdsymj", getTdsymj())
            .append("qllx", getQllx())
            .append("qlxz", getQlxz())
            .append("sfwzf", getSfwzf())
            .append("fccs", getFccs())
            .append("zxbz", getZxbz())
            .append("bz", getBz())
            .append("kz1", getKz1())
            .append("kz2", getKz2())
            .append("tJcFcFws", gettJcFcFws())
            .toString();
    }
}
