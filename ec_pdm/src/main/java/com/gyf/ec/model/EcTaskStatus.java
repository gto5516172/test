package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@Table(name="ec_task_status")
public class EcTaskStatus extends BaseEntity{
	
	private String doStatus;//状态
	
	private Long taskUser;//执行任务的人
	
	private Long taskId;//对应ec_task 的Id
	private String updateBy; //update_by <更新>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	public Long getTaskId() {
		return getLong("taskId");
	}

	public void setTaskId(Long taskId) {
		this.set("taskId", taskId);
	}

	



	
	public String getDoStatus() {
		return getString("doStatus");
	}

	public void setDoStatus(String doStatus) {
		this.set("doStatus",doStatus);
	}

	

	public Long getTaskUser() {
		return getLong("taskUser");
	}

	public void setTaskUser(Long taskUser) {
		this.set("taskUser",taskUser);
	}
	public String getUpdateBy() {
		return getString("updateBy");
	}

	public void setUpdateBy(String updateBy) {
		this.set("updateBy", updateBy);
	}

	public Date getUpdateDate() {
		return getDate("updateDate");
	}

	public void setUpdateDate(Date updateDate) {
		this.set("updateDate", updateDate);
	}

	public String getCreateBy() {
		return getString("createBy");
	}

	public void setCreateBy(String createBy) {
		this.set("createBy", createBy);
	}

	public Date getCreateDate() {
		return getDate("createDate");
	}

	public void setCreateDate(Date createDate) {
		this.set("createDate", createDate);
	}

	public String getDelFlag() {
		return getString("delFlag");
	}

	public void setDelFlag(String delFlag) {
		this.set("delFlag", delFlag);
	}
	
}
