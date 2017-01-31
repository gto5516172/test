package com.gyf.ec.model;

import java.util.Date;
import javax.persistence.Table;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_suppliers_com_price_log")
public class EcSuppliersComPriceLog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private Long suppcomId; //suppcom_id <供应商报价表ec_suppliers_commodity 的ID>
	
	private String oldPurchasePrice; //old_purchase_price <上次报价>
	
	private Date oldPurchasePriceTime; //old_purchase_price_time <上次报价时间>
	
	private Long skuId; // sku_id <skuID>
	
	public Long getSkuId() {
		return getLong("skuId");
	}

	public void setSkuId(Long skuId) {
		this.set("skuId", skuId);
	}

	public Date getOldPurchasePriceTime() {
		return getDate("oldPurchasePriceTime");
	}

	public void setOldPurchasePriceTime(Date oldPurchasePriceTime) {
		this.set("oldPurchasePriceTime", oldPurchasePriceTime);
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

	public Long getSuppcomId() {
		return getLong("suppcomId");
	}

	public void setSuppcomId(Long suppcomId) {
		this.set("suppcomId", suppcomId);
	}

	public String getOldPurchasePrice() {
		return getString("oldPurchasePrice");
	}

	public void setOldPurchasePrice(String oldPurchasePrice) {
		this.set("oldPurchasePrice", oldPurchasePrice);
	}
	
}
