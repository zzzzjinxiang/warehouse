package com.yeqifu.bus.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * dubbo_service
 * @author 
 */
public class DubboServiceDo implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 年龄
     */
    private String stuAge;

    /**
     * 成绩
     */
    private String stuScore;

    private String ecStatus;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuScore() {
        return stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }

    public String getEcStatus() {
        return ecStatus;
    }

    public void setEcStatus(String ecStatus) {
        this.ecStatus = ecStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    private static final long serialVersionUID = 1L;
}