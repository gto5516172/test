package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_commodity_spu_desc")
public class CommoditySpuDesc extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String descr;//描述
    private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private Long spuId;
	private String image;//快照
	
	public String getImage() {
		return this.getString("image");
	}
	public void setImage(String image) {
		this.set("image",image);
	}
	public Long getSpuId() {
		return getLong("spuId");
	}
	public void setSpuId(Long spuId) {
		this.set("spuId", spuId);
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
