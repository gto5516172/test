package com.gyf.ec.model;

import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_task")
public class EcTask extends BaseEntity {
	/**
	 * 
	 */
	private String title; //title <标题>
	
	private Long typeId; //typeId <任务类型ID>
	
	private Integer emergencyDegree; //emergencyDegree <紧急程度>
	
	private Long createUser; //createUser <发布者>
	
	private Long responsibilityUser; //responsibilityUser <执行者>
	
	private String finishTime; //finishTime <完成时间>
	
	private String daysLate; //daysLate <延迟时间>
	
	private Integer status; //status <任务状态>
	
	private String processInstanceId; //processInstanceId <流程实例ID>
	
	private Integer model; //model <流程模板>
	
	//added by BetyChen
	private String descr;//任务描述
	private Long personInCharge;//负责人
	private String startTime;//开始时间
	private String endTime;//结束时间
	private Integer categoryType;//详细分类
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private String note;//备注
	@Transient
	private String[] product1;
	@Transient
	private String[] product2;
	@Transient
	private String[] product3;
	@Transient
	private String[] shopIds;
	@Transient
	private String[] notes1;
	@Transient
	private String[] suppliers;
	

	@Transient
	private String[] notes2;
	@Transient
	private String[] notes3;
	@Transient
	private String productDescr;
	@Transient
	private Long doId;
	@Transient
	private String doStatus;
	
	public String getDoStatus() {
		return getString("doStatus");
	}

	public void setDoStatus(String doStatus) {
		this.set("doStatus",doStatus);
	}

	public Long getDoId() {
		return getLong("doId");
	}

	public void setDoId(Long doId) {
		this.set("doId",doId);
	}

	public String[] getProduct1() {
		return product1;
	}

	public void setProduct1(String[] product1) {
		this.product1 = product1;
	}

	public String[] getProduct2() {
		return product2;
	}

	public void setProduct2(String[] product2) {
		this.product2 = product2;
	}

	public String[] getProduct3() {
		return product3;
	}

	public void setProduct3(String[] product3) {
		this.product3 = product3;
	}

	
	public String[] getShopIds() {
		return shopIds;
	}

	public void setShopIds(String[] shopIds) {
		this.shopIds = shopIds;
	}



	public String[] getNotes3() {
		return notes3;
	}

	public void setNotes3(String[] notes3) {
		this.notes3 = notes3;
	}

	public String getProductDescr() {
		return getString("productDescr");
	}

	public void setProductDescr(String productDescr) {
		this.set("productDescr",productDescr);
	}

	public String[] getNotes1() {
		return notes1;
	}

	public void setNotes1(String[] notes1) {
		this.notes1 = notes1;
	}

	public String getDescr() {
		return getString("descr");
	}

	public void setDescr(String descr) {
		this.set("descr",descr);
	}

	public Long getPersonInCharge() {
		return getLong("personInCharge");
	}

	public void setPersonInCharge(Long personInCharge) {
		this.set("personInCharge",personInCharge) ;
	}

	public String getStartTime() {
		return getString("startTime");
	}

	public void setStartTime(String startTime) {
		this.set("startTime",startTime);
	}

	public String getEndTime() {
		return getString("endTime");
	}

	public void setEndTime(String endTime) {
		this.set("endTime", endTime);
	}

	public Integer getCategoryType() {
		return getInteger("categoryType");
	}

	public void setCategoryType(Integer categoryType) {
		this.set("categoryType", categoryType);
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
	
	public String[] getNotes2() {
		return notes2;
	}

	public void setNotes2(String[] notes2) {
		this.notes2 = notes2;
	}

	public String[] getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(String[] suppliers) {
		this.suppliers = suppliers;
	}
//	--------------------add end----------
	
	
	
	
	


	@Transient
	private String taskId; //taskId <任务ID>
	
	@Transient
	private String taskName; //taskName <任务名称>
	
	private String userIds; //userIds <选择人员> 
	
	@Transient
	private String userName;
	
	public String getUserName() {
		return getString("userName");
	}

	public void setUserName(String userName) {
		this.set("userName", userName);
	}

	public String getUserIds() {
		return getString("userIds");
	}

	public void setUserIds(String userIds) {
		this.set("userIds", userIds);
	}

	public String getTaskId() {
		return getString("taskId");
	}

	public void setTaskId(String taskId) {
		this.set("taskId", taskId);
	}

	public String getTaskName() {
		return getString("taskName");
	}

	public void setTaskName(String taskName) {
		this.set("taskName", taskName);
	}

	public String getTitle() {
		return getString("title");
	}

	public void setTitle(String title) {
		this.set("title", title);
	}

	public Long getTypeId() {
		return getLong("typeId");
	}

	public void setTypeId(Long typeId) {
		this.set("typeId", typeId);
	}

	public Integer getEmergencyDegree() {
		return getInteger("emergencyDegree");
	}

	public void setEmergencyDegree(Integer emergencyDegree) {
		this.set("emergencyDegree", emergencyDegree);
	}

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

	public String getCreateTime() {
		return getString("createTime");
	}

	public void setCreateTime(String createTime) {
		this.set("createTime", createTime);
	}

	public String getFinishTime() {
		return getString("finishTime");
	}

	public void setFinishTime(String finishTime) {
		this.set("finishTime", finishTime);
	}

	public String getDaysLate() {
		return getString("daysLate");
	}

	public void setDaysLate(String daysLate) {
		this.set("daysLate", daysLate);
	}

	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		this.set("status", status);
	}

	public String getProcessInstanceId() {
		return getString("processInstanceId");
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.set("processInstanceId", processInstanceId);
	}

	public Integer getModel() {
		return getInteger("model");
	}

	public void setModel(Integer model) {
		this.set("model", model);
	}

	public String getNote() {
		return getString("note");
	}

	public void setNote(String note) {
		this.set("note",note);
	}
	
}
