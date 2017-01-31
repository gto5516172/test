package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONArray;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_sku_cost_price_log")
public class EcSkuCostPriceLog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private Long skuId; //sku_id <skuID>
	
	private String oldCostPrice; //old_cost_price <旧成本价>
	
	private Date oldCostPriceTime; //old_cost_price_time <旧成本价创建时间>
	
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

	public Long getSkuId() {
		return getLong("skuId");
	}

	public void setSkuId(Long skuId) {
		this.set("skuId", skuId);
	}

	public String getOldCostPrice() {
		return getString("oldCostPrice");
	}

	public void setOldCostPrice(String oldCostPrice) {
		this.set("oldCostPrice", oldCostPrice);
	}

	public Date getOldCostPriceTime() {
		return getDate("oldCostPriceTime");
	}

	public void setOldCostPriceTime(Date oldCostPriceTime) {
		this.set("oldCostPriceTime", oldCostPriceTime);
	}
	
}
