package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_photos")
public class Photos extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgUrl;
    private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private String imgFrom;//来源
	private Long imgFromId;//来源ID
	private String original;//原图文件名
	
	public String getOriginal() {
		return getString("original");
	}
	public void setOriginal(String original) {
		set("original", original);
	}
	public String getImgUrl() {
		return this.getString("imgUrl");
	}
	public void setImgUrl(String imgUrl) {
		this.set("imgUrl",imgUrl);
	}
	public String getImgFrom() {
		return this.getString("imgFrom");
	}
	public void setImgFrom(String imgFrom) {
		this.set("imgFrom", imgFrom);
	}
	public Long getImgFromId() {
		return getLong("imgFromId");
	}
	public void setImgFromId(Long imgFromId) {
		this.set("imgFromId",imgFromId);
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
