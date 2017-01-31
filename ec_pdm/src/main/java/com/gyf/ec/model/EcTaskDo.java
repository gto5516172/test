package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@Table(name="ec_task_do")
public class EcTaskDo extends BaseEntity{
	private String salerPrice;//销售刊登价格
	private String supplyPrice;//供应商报价录入
	private String newPrice;//最新报价
	private String myUrl;//url
	private String status;//状态
	private String productDescr;//第四种模式的描述
	private Long taskUser;//执行任务的人
	private Long pid;//对应ec_task_product的ID
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

	public Long getPid() {
		return getLong("pid");
	}

	public void setPid(Long pid) {
		this.set("pid", pid);
	}

	public String getSalerPrice() {
		return getString("salerPrice");
	}

	public void setSalerPrice(String salerPrice) {
		this.set("salerPrice",salerPrice);
	}

	public String getSupplyPrice() {
		return getString("supplyPrice");
	}

	public void setSupplyPrice(String supplyPrice) {
		this.set("supplyPrice",supplyPrice);
	}

	public String getNewPrice() {
		return getString("newPrice");
	}

	public void setNewPrice(String newPrice) {
		this.set("newPrice",newPrice);
	}

	public String getMyUrl() {
		return getString("myUrl");
	}

	public void setMyUrl(String myUrl) {
		this.set("myUrl",myUrl);
	}

	public String getStatus() {
		return getString("status");
	}

	public void setStatus(String status) {
		this.set("status",status);
	}

	public String getProductDescr() {
		return getString("productDescr");
	}

	public void setProductDescr(String productDescr) {
		this.set("productDescr", productDescr);
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
