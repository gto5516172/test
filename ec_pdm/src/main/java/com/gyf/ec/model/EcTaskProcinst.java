package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONArray;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_task_procinst")
public class EcTaskProcinst extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long taskId;
	
	private String procInstId;
	
	private Date createDate;
	
	private Long createUser;
	
	private Long responsibilityUser;
	
	private Integer status;
	
	public Long getCreateUser() {
		return getLong("createUser");
	}

	public void setCreateUser(Long createUser) {
		this.set("createUser", createUser);
	}

	public Long getResponsibilityUser() {
		return getLong("responsibilityUser");
	}

	public void setResponsibilityUser(Long responsibilityUser) {
		this.set("responsibilityUser", responsibilityUser);
	}

	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		this.set("status", status);
	}

	public Long getTaskId() {
		return getLong("taskId");
	}

	public void setTaskId(Long taskId) {
		this.set("taskId", taskId);
	}

	public String getProcInstId() {
		return getString("procInstId");
	}

	public void setProcInstId(String procInstId) {
		this.set("procInstId", procInstId);
	}

	public Date getCreateDate() {
		return getDate("createDate");
	}

	public void setCreateDate(Date createDate) {
		this.set("createDate", createDate);
	}
	
}
