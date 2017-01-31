package com.gyf.ec.model;

import java.util.Date;
import javax.persistence.Table;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_task_type")
public class EcTaskType extends BaseEntity {

	private String name; //name <任务类型名称> 
	
	private Integer status; //status <状态>
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		this.set("name", name);
	}

	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		this.set("status", status);
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

	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
	}
	
}
