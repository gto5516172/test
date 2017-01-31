package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_platform")
public class Platform extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String descr;
    private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private String status;//状态
	
	public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		this.set("status", status);
	}
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getDescr() {
		return this.getString("descr");
	}
	public void setDescr(String descr) {
		this.set("descr",descr);
	}
	public String getUpdateBy() {
		return this.getString("updateBy");
	}
	public void setUpdateBy(String updateBy) {
		this.set("updateBy",updateBy);
	}
	public Date getUpdateDate() {
		return this.getDate("updateDate");
	}
	public void setUpdateDate(Date updateDate) {
		this.set("updateDate", updateDate);
	}
	public String getCreateBy() {
		return this.getString("createBy");
	}
	public void setCreateBy(String createBy) {
		this.set("createBy", createBy);
	}
	public Date getCreateDate() {
		return this.getDate("createDate");
	}
	public void setCreateDate(Date createDate) {
		this.set("createDate", createDate);
	}
	public String getDelFlag() {
		return this.getString("delFlag");
	}
	public void setDelFlag(String delFlag) {
		this.set("delFlag", delFlag);
	}

	
}
