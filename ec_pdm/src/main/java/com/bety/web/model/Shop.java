package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_shop")
public class Shop extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String sellerAccount;
	private String sellerShop;
	private String payPal;
	private String timeTask;
	private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private String status;//状态
	private Long platformId;
	private Long siteId;
	private String akId;
	private String secrectKey;
	private String sellerId;
	private String shopId;
	private String accountType;
	private String account;
	private String accessOrder;
	private String note;
	private String verifyStatus;
	private Date authorizationTime;
	
	public Date getAuthorizationTime() {
		return this.getDate("authorizationTime");
	}
	public void setAuthorizationTime(Date authorizationTime) {
		this.set("authorizationTime",authorizationTime);
	}
	public String getVerifyStatus() {
		return this.getString("verifyStatus");
	}
	public void setVerifyStatus(String verifyStatus) {
		this.set("verifyStatus", verifyStatus);
	}
	public Long getSiteId() {
		return getLong("siteId");
	}
	public void setSiteId(Long siteId) {
		this.set("siteId",siteId);
	}
	public String getAkId() {
		return getString("akId");
	}
	public void setAkId(String akId) {
		this.set("akId", akId);
	}
	public String getSecrectKey() {
		return this.getString("secrectKey");
	}
	public void setSecrectKey(String secrectKey) {
		this.set("secrectKey", secrectKey);
	}
	public String getSellerId() {
		return this.getString("sellerId");
	}
	public void setSellerId(String sellerId) {
		this.set("sellerId", sellerId);
	}
	public String getShopId() {
		return this.getString("sellerId");
	}
	public void setShopId(String shopId) {
		this.set("shopId", shopId);
	}
	public String getAccountType() {
		return this.getString("accountType");
	}
	public void setAccountType(String accountType) {
		this.set("accountType",accountType);
	}
	public String getAccount() {
		return this.getString("account");
	}
	public void setAccount(String account) {
		this.set("account", account);
	}
	public String getAccessOrder() {
		return this.getString("accessOrder");
	}
	public void setAccessOrder(String order) {
		this.set("accessOrder", order);
	}
	public String getNote() {
		return this.getString("note");
	}
	public void setNote(String note) {
		this.set("note", note);
	}
	public Long getPlatformId() {
		return getLong("platformId");
	}
	public void setPlatformId(Long platformId) {
		this.set("platformId",platformId);
	}
	public String getTimeTask() {
		return this.getString("timeTask");
	}
	public void setTimeTask(String timeTask) {
		this.set("timeTask",timeTask);
	}
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name", name);
	}
	public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		this.set("status", status);
	}
	public String getSellerAccount() {
		return this.getString("sellerAccount");
	}
	public void setSellerAccount(String sellerAccount) {
		this.set("sellerAccount", sellerAccount);
	}
	public String getSellerShop() {
		return this.getString("sellerShop");
	}
	public void setSellerShop(String sellerShop) {
		this.set("sellerShop", sellerShop);
	}
	public String getPayPal() {
		return this.getString("payPal");
	}
	public void setPayPal(String payPal) {
		this.set("payPal",payPal);
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
