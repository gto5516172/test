package com.bety.web.model;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="import_excel")
public class ImportExcel extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buyNo;
	private String orderNo;
	private String shopName;
	
	public String getShopName() {
		return getString("shopName");
	}
	public void setShopName(String shopName) {
		this.set("shopName",shopName);
	}
	public String getBuyNo() {
		return getString("buyNo");
	}
	public void setBuyNo(String buyNo) {
		this.set("buyNo",buyNo);
	}
	public String getOrderNo() {
		return getString("orderNo");
	}
	public void setOrderNo(String orderNo) {
		this.set("orderNo",orderNo);
	}
	
}
