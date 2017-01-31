package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_suppliers")
public class EcSuppliers extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String suppliersName; //suppliers_name <供应商名称)>
	
	private String abbreviation; //abbreviation <供应商简称)>
	
	private Integer suppliersLevel; //suppliers_level <供应商等级)>
	
	private Integer suppliersType; //suppliers_type <供应商类型)>
	
	private String suppliersAddr; //suppliers_addr <供应商地址)>
	
	private String suppliersWeb; //suppliers_web <供应商官方网址)>
	
	private String taxIdentificationNumber; //tax_identification_number <供应商纳税识别号)>
	
	private String taxTime; //tax_time <供应商一般纳税认定时间)>
	
	private String personName1; //person_name_1 <供应商联系人1)>
	
	private String personPhone1; //person_phone_1 <供应商联系电话1)>
	
	private String personName2; //person_name_2 <供应商联系人2)>
	
	private String personPhone2; //person_phone_2 <供应商联系电话2)>
	
	private String faxNumber; //fax_number <供应商传真号)>
	
	private String qq; //qq <供应商qq)>
	
	private String email; //email <供应商email)>
	
	private String aliWangwang; //ali_wangwang <供应商阿里旺旺	)>
	
	private Long areaId; // areaId <供应商地址ID>
	
	private Integer settlementMethod; //settlement_method <供应商结算方式)>
	
	private Integer settlementPeriod; //settlement_period <供应商结算周期)>
	
	private Integer settlementPeriodNum; //settlement_period_num <供应商结算周期类型)>
	
	private Integer paymentMethod; //payment_method <供应商结算方法)>
	
	private String remittanceCompanyName; //remittance_company_name <供应商汇款公司名)>
	
	private String payeeName; //payee_name <供应商收款人)>
	
	private String remittanceBankName; //remittance_bank_name <供应商汇款银行名)>
	
	private String remittanceBankAccount; //remittance_bank_account <供应商汇款银行账号)>
	
	private String remittanceBankAddr; //remittance_bank_addr <供应商汇款银行地址)>
	
	private String clearingNotes; //clearing_notes <供应商结算备注)>
	
	private String averageDeliveryCycle; //average_delivery_cycle <供应商平均交货周期)>
	
	private String swiftCode; //swift_code <供应商银行代码)>
	
	private String delivery; //delivery <供应商货期)>
	
	private String operatingProducts; //operating_products <供应商经营产品)>
	
	private String productCategory; //product_category <供应商产品类别)>
	
	private String remarks; //remarks <备注信息>
	
	private Integer status; //status <供应商状态 0 启用 1停用>
	
	@Transient
	private Integer comSize; //comSize<供应商对应的商品数量>
	
	public Integer getComSize() {
		return getInteger("comSize");
	}

	public void setComSize(Integer comSize) {
		this.set("comSize", comSize);
	}

	@Transient
	private String findType; //findType<页面条件查询类型 0供应商名称 1联系人 2联系电话>
	
	@Transient
	private String findTxt; //findTxt<页面条件查询>
	
	public Long getAreaId() {
		return getLong("areaId");
	}

	public void setAreaId(Long areaId) {
		this.set("areaId", areaId);
	}
	
	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		this.set("status", status);
	}

	@Transient
	private Long findAreaId; //findOfficeId<页面地址查询>
	
	public Long getFindAreaId() {
		return getLong("findAreaId");
	}

	public void setFindAreaId(Long findAreaId) {
		this.set("findAreaId", findAreaId);
	}

	public String getAbbreviation() {
		return getString("abbreviation");
	}

	public void setAbbreviation(String abbreviation) {
		this.set("abbreviation", abbreviation);
	}
	
	public String getFindType() {
		return getString("findType");
	}

	public void setFindType(String findType) {
		this.set("findType",findType);
	}

	public String getFindTxt() {
		return getString("findTxt");
	}

	public void setFindTxt(String findTxt) {
		this.set("findTxt", findTxt);
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

	public String getSuppliersName() {
		return getString("suppliersName");
	}

	public void setSuppliersName(String suppliersName) {
		this.set("suppliersName", suppliersName);
	}

	public Integer getSuppliersLevel() {
		return getInteger("suppliersLevel");
	}

	public void setSuppliersLevel(Integer suppliersLevel) {
		this.set("suppliersLevel", suppliersLevel);
	}

	public Integer getSuppliersType() {
		return getInteger("suppliersType");
	}

	public void setSuppliersType(Integer suppliersType) {
		this.set("suppliersType", suppliersType);
	}

	public String getSuppliersAddr() {
		return getString("suppliersAddr");
	}

	public void setSuppliersAddr(String suppliersAddr) {
		this.set("suppliersAddr", suppliersAddr);
	}

	public String getSuppliersWeb() {
		return getString("suppliersWeb");
	}

	public void setSuppliersWeb(String suppliersWeb) {
		this.set("suppliersWeb", suppliersWeb);
	}

	public String getTaxIdentificationNumber() {
		return getString("taxIdentificationNumber");
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.set("taxIdentificationNumber", taxIdentificationNumber);
	}

	public String getTaxTime() {
		return getString("taxTime");
	}

	public void setTaxTime(String taxTime) {
		this.set("taxTime", taxTime);
	}

	public String getPersonName1() {
		return getString("personName1");
	}

	public void setPersonName1(String personName1) {
		this.set("personName1", personName1);
	}

	public String getPersonPhone1() {
		return getString("personPhone1");
	}

	public void setPersonPhone1(String personPhone1) {
		this.set("personPhone1", personPhone1);
	}

	public String getPersonName2() {
		return getString("personName2");
	}

	public void setPersonName2(String personName2) {
		this.set("personName2", personName2);
	}

	public String getPersonPhone2() {
		return getString("personPhone2");
	}

	public void setPersonPhone2(String personPhone2) {
		this.set("personPhone2", personPhone2);
	}

	public String getFaxNumber() {
		return getString("faxNumber");
	}

	public void setFaxNumber(String faxNumber) {
		this.set("faxNumber", faxNumber);
	}

	public String getQq() {
		return getString("qq");
	}

	public void setQq(String qq) {
		this.set("qq", qq);
	}

	public String getEmail() {
		return getString("email");
	}

	public void setEmail(String email) {
		this.set("email", email);
	}

	public String getAliWangwang() {
		return getString("aliWangwang");
	}

	public void setAliWangwang(String aliWangwang) {
		this.set("aliWangwang", aliWangwang);
	}

	public String getCountry() {
		return getString("country");
	}

	public void setCountry(String country) {
		this.set("country", country);
	}

	public String getProvinceCityState() {
		return getString("provinceCityState");
	}

	public void setProvinceCityState(String provinceCityState) {
		this.set("provinceCityState", provinceCityState);
	}

	public String getDistrictCityCounty() {
		return getString("districtCityCounty");
	}

	public void setDistrictCityCounty(String districtCityCounty) {
		this.set("districtCityCounty", districtCityCounty);
	}

	public Integer getSettlementMethod() {
		return getInteger("settlementMethod");
	}

	public void setSettlementMethod(Integer settlementMethod) {
		this.set("settlementMethod", settlementMethod);
	}

	public Integer getSettlementPeriod() {
		return getInteger("settlementPeriod");
	}

	public void setSettlementPeriod(Integer settlementPeriod) {
		this.set("settlementPeriod", settlementPeriod);
	}

	public Integer getSettlementPeriodNum() {
		return getInteger("settlementPeriodNum");
	}

	public void setSettlementPeriodNum(Integer settlementPeriodNum) {
		this.set("settlementPeriodNum", settlementPeriodNum);
	}

	public Integer getPaymentMethod() {
		return getInteger("paymentMethod");
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.set("paymentMethod", paymentMethod);
	}

	public String getRemittanceCompanyName() {
		return getString("remittanceCompanyName");
	}

	public void setRemittanceCompanyName(String remittanceCompanyName) {
		this.set("remittanceCompanyName", remittanceCompanyName);
	}

	public String getPayeeName() {
		return getString("payeeName");
	}

	public void setPayeeName(String payeeName) {
		this.set("payeeName", payeeName);
	}

	public String getRemittanceBankName() {
		return getString("remittanceBankName");
	}

	public void setRemittanceBankName(String remittanceBankName) {
		this.set("remittanceBankName", remittanceBankName);
	}

	public String getRemittanceBankAccount() {
		return getString("remittanceBankAccount");
	}

	public void setRemittanceBankAccount(String remittanceBankAccount) {
		this.set("remittanceBankAccount", remittanceBankAccount);
	}

	public String getRemittanceBankAddr() {
		return getString("remittanceBankAddr");
	}

	public void setRemittanceBankAddr(String remittanceBankAddr) {
		this.set("remittanceBankAddr", remittanceBankAddr);
	}

	public String getClearingNotes() {
		return getString("clearingNotes");
	}

	public void setClearingNotes(String clearingNotes) {
		this.set("clearingNotes", clearingNotes);
	}

	public String getAverageDeliveryCycle() {
		return getString("averageDeliveryCycle");
	}

	public void setAverageDeliveryCycle(String averageDeliveryCycle) {
		this.set("averageDeliveryCycle", averageDeliveryCycle);
	}

	public String getSwiftCode() {
		return getString("swiftCode");
	}

	public void setSwiftCode(String swiftCode) {
		this.set("swiftCode", swiftCode);
	}

	public String getDelivery() {
		return getString("delivery");
	}

	public void setDelivery(String delivery) {
		this.set("delivery", delivery);
	}

	public String getOperatingProducts() {
		return getString("operatingProducts");
	}

	public void setOperatingProducts(String operatingProducts) {
		this.set("operatingProducts", operatingProducts);
	}

	public String getProductCategory() {
		return getString("productCategory");
	}

	public void setProductCategory(String productCategory) {
		this.set("productCategory", productCategory);
	}

	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
	}
	
}
