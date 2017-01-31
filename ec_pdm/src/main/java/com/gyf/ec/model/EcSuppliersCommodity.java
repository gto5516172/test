package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({"unused"})
@Table(name="ec_suppliers_commodity")
public class EcSuppliersCommodity extends BaseEntity {
	
private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private Long suppliersId; //suppliersId<供应商ID>
	
	private Long commodityId; //commodityId<商品sku>
	
	private String factoryNo; //factoryNo<原厂编号>
	
	private String purchasePrice; //purchasePrice<采购价格>

	private String minNum; //minNum<最小采购量>
	
	private String suppliersName; //suppliersName<供应商名称>
	
	private String sku; //sku<sku>
	
	private String skuName; //skuName<sku名称>
	
	private Integer skuStatus; //skuStatus<sku状态>
	
	private String brand; //brand<品牌>
	
	private Long ecCatalogueId; //ecCatalogueId<商品分类ID>
	
	@Transient
	private String costPrice; //costPrice<成本价>
	
	@Transient
	private String findTxt; //findTxt<查询条件>
	
	public String getCostPrice() {
		return getString("costPrice");
	}

	public void setCostPrice(String costPrice) {
		this.set("costPrice", costPrice);
	}
	
	public String getFindTxt() {
		return getString("findTxt");
	}

	public void setFindTxt(String findTxt) {
		this.set("findTxt", findTxt);
	}

	public Long getEcCatalogueId() {
		return getLong("ecCatalogueId");
	}

	public void setEcCatalogueId(Long ecCatalogueId) {
		this.set("ecCatalogueId", ecCatalogueId);
	}

	public String getBrand() {
		return getString("brand");
	}

	public void setBrand(String brand) {
		this.set("brand", brand);
	}
	
	public Integer getSkuStatus() {
		return getInteger("skuStatus");
	}

	public void setSkuStatus(Integer skuStatus) {
		this.set("skuStatus", skuStatus);
	}

	public String getSku() {
		return getString("sku");
	}

	public void setSku(String sku) {
		this.set("sku", sku);
	}

	public String getSkuName() {
		return getString("skuName");
	}

	public void setSkuName(String skuName) {
		this.set("skuName", skuName);
	}
	
	public String getSuppliersName() {
		return getString("suppliersName");
	}

	public void setSuppliersName(String suppliersName) {
		this.set("suppliersName", suppliersName);
	}

	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
	}

	public Long getSuppliersId() {
		return getLong("suppliersId");
	}

	public void setSuppliersId(Long suppliersId) {
		this.set("suppliersId", suppliersId);
	}

	public Long getCommodityId() {
		return getLong("commodityId");
	}

	public void setCommodityId(Long commodityId) {
		this.set("commodityId", commodityId);
	}

	public String getFactoryNo() {
		return getString("factoryNo");
	}

	public void setFactoryNo(String factoryNo) {
		this.set("factoryNo", factoryNo);
	}

	public String getPurchasePrice() {
		return getString("purchasePrice");
	}

	public void setPurchasePrice(String purchasePrice) {
		this.set("purchasePrice", purchasePrice);
	}

	public String getMinNum() {
		return getString("minNum");
	}

	public void setMinNum(String minNum) {
		this.set("minNum", minNum);
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
