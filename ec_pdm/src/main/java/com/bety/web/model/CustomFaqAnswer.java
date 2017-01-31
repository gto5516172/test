package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_faq_custom_answer")
public class CustomFaqAnswer extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descr;
    private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private Long faqId;//faqId
	private Long userId;//关联的用户ID
	
	public Long getUserId() {
		return getLong("userId");
	}
	public void setUserId(Long userId) {
		this.set("userId",userId);
	}
	@Transient
	private String timeDistance;
	@Transient
	private String name;//用户名称
	@Transient
	private String headPath;//用户头像
	
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getHeadPath() {
		return getString("headPath");
	}
	public void setHeadPath(String headPath) {
		this.set("headPath",headPath);
	}
	public String getTimeDistance() {
		return getString("timeDistance");
	}
	public void setTimeDistance(String timeDistance) {
		this.set("timeDistance",timeDistance);
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
	public Long getFaqId() {
		return getLong("faqId");
	}
	public void setFaqId(Long faqId) {
		this.set("faqId",faqId);
	}

	
}
