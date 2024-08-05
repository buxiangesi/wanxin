package com.shggc.project.jcxx.fw.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.shggc.framework.aspectj.lang.annotation.Excel;
import com.shggc.framework.web.domain.BaseEntity;

/**
 * 房产房屋信息对象 t_jc_fc_fw
 *
 * @author ruoyi
 * @date 2022-07-28
 */
public class TJcFcFw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 房产ID */
    @Excel(name = "房产ID")
    private String fcid;

    /** 房屋间数 */
    @Excel(name = "房屋间数")
    private double fwjs;

    /** 建筑面积（平方米） */
    @Excel(name = "建筑面积", readConverterExp = "平=方米")
    private double jzmj;

    /** 注销标志0正常1注销 */
    @Excel(name = "注销标志0正常1注销")
    private String zxbz;

    /** 备注 */
    @Excel(name = "备注")
    private String bz = "";

    /** 扩展1 */
    private String kz1 = "";

    /** 扩展2 */
    private String kz2 = "";

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setFcid(String fcid)
    {
        this.fcid = fcid;
    }

    public String getFcid()
    {
        return fcid;
    }
    
    public void setFwjs(double fwjs)
    {
        this.fwjs = fwjs;
    }

    public double getFwjs()
    {
        return fwjs;
    }
    public void setJzmj(Double jzmj)
    {
        this.jzmj = jzmj;
    }

    public double getJzmj()
    {
        return jzmj;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fcid", getFcid())      
            .append("fwjs", getFwjs())
            .append("jzmj", getJzmj())
            .append("zxbz", getZxbz())
            .append("bz", getBz())
            .append("kz1", getKz1())
            .append("kz2", getKz2())
            .toString();
    }
}
