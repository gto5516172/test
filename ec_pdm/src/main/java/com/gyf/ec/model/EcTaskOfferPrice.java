package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;
@Table(name="ec_task_offerprice")
public class EcTaskOfferPrice extends BaseEntity{
	private String offerDate;//报价日期
	private String offerNo;//报价单号
	private String companyName;//公司名称
	private String contactName;//联系人
	private String contactSex;//性别
	private String areaNo;//区号
	private String telephone;//电话
	private String phone;//手机号
	private String faxArea;//传真区号
	private String faxNo;//传真
	private String contactQq;//联系QQ
	private String email;//邮箱
	private String startOffer;//起订量
	private String author;//认证
	private Long skuId;//产品Id
	private String offerCount;//数量
	private String signelPrice;//报价单价
	private String note;//报价备注
	private String validityPeriod;//有效期
	private String deliveryDay;//交货期
	private String descr;//报价说明
	private String preferentialAmount;//优惠金额
	private String offerPrice;//报价金额
	private String updateBy; //update_by <更新者>
	private Long doId;//ec_task_do id
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)
	private Long areaId;//供应商省份城市
	private String businessAddress;//经营地址
	private Integer isCompare;//是否对比过
	@Transient
	private String areaName;//地址名称

	public String getAreaName() {
		return getString("areaName");
	}
	public void setAreaName(String areaName) {
		this.set("areaName",areaName);
	}
	public Integer getIsCompare() {
		return getInteger("isCompare");
	}
	public void setIsCompare(Integer isCompare) {
		this.set("isCompare",isCompare);
	}

	
	public Long getAreaId() {
		return getLong("areaId");
	}
	public void setAreaId(Long areaId) {
		this.set("areaId",areaId);
	}
	public String getBusinessAddress() {
		return getString("businessAddress");
	}
	public void setBusinessAddress(String businessAddress) {
		this.set("businessAddress",businessAddress);
	}
	public Long getDoId() {
		return getLong("doId");
	}
	public void setDoId(Long doId) {
		this.set("doId",doId);
	}
	public String getOfferDate() {
		return getString("offerDate");
	}
	public void setOfferDate(String offerDate) {
		this.set("offerDate",offerDate);
	}
	public String getOfferNo() {
		return getString("offerNo");
	}
	public void setOfferNo(String offerNo) {
		this.set("offerNo",offerNo);
	}
	public String getCompanyName() {
		return getString("companyName");
	}
	public void setCompanyName(String companyName) {
		this.set("companyName", companyName);
	}
	public String getContactName() {
		return getString("contactName");
	}
	public void setContactName(String contactName) {
		this.set("contactName",contactName);
	}
	public String getContactSex() {
		return getString("contactSex");
	}
	public void setContactSex(String contactSex) {
		this.set("contactSex", contactSex);
	}
	public String getAreaNo() {
		return getString("areaNo");
	}
	public void setAreaNo(String areaNo) {
		this.set("areaNo",areaNo);
	}
	public String getTelephone() {
		return getString("telephone");
	}
	public void setTelephone(String telephone) {
		this.set("telephone", telephone);
	}
	public String getPhone() {
		return getString("phone");
	}
	public void setPhone(String phone) {
		this.set("phone", phone);
	}
	public String getFaxArea() {
		return getString("faxArea");
	}
	public void setFaxArea(String faxArea) {
		this.set("faxArea",faxArea);
	}
	public String getFaxNo() {
		return getString("faxNo");
	}
	public void setFaxNo(String faxNo) {
		this.set("faxNo", faxNo);
	}
	public String getContactQq() {
		return getString("contactQq");
	}
	public void setContactQq(String contactQq) {
		this.set("contactQq",contactQq);
	}
	public String getEmail() {
		return getString("email");
	}
	public void setEmail(String email) {
		this.set("email", email);
	}
	public String getStartOffer() {
		return getString("startOffer");
	}
	public void setStartOffer(String startOffer) {
		this.set("startOffer",startOffer);
	}
	public String getAuthor() {
		return getString("author");
	}
	public void setAuthor(String author) {
		this.set("author", author);
	}
	public Long getSkuId() {
		return getLong("skuId");
	}
	public void setSkuId(Long skuId) {
		this.set("skuId", skuId);
	}
	public String getOfferCount() {
		return getString("offerCount");
	}
	public void setOfferCount(String offerCount) {
		this.set("offerCount",offerCount);
	}
	public String getSignelPrice() {
		return getString("signelPrice");
	}
	public void setSignelPrice(String signelPrice) {
		this.set("signelPrice", signelPrice);
	}
	public String getNote() {
		return getString("note");
	}
	public void setNote(String note) {
		this.set("note", note);
	}
	public String getValidityPeriod() {
		return getString("validityPeriod");
	}
	public void setValidityPeriod(String validityPeriod) {
		this.set("validityPeriod",validityPeriod);
	}
	public String getDeliveryDay() {
		return getString("deliveryDay");
	}
	public void setDeliveryDay(String deliveryDay) {
		this.set("deliveryDay",deliveryDay);
	}
	public String getDescr() {
		return getString("descr");
	}
	public void setDescr(String descr) {
		this.set("descr",descr);
	}

	public String getPreferentialAmount() {
		return getString("preferentialAmount");
	}
	public void setPreferentialAmount(String preferentialAmount) {
		this.set("preferentialAmount", preferentialAmount);
	}
	public String getOfferPrice() {
		return getString("offerPrice");
	}
	public void setOfferPrice(String offerPrice) {
		this.set("offerPrice", offerPrice);
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
