package com.gyf.ec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_task_product")
public class EcTaskProduct extends BaseEntity{
	private Long productId;//产品ID
	private Long shopId;//店铺ID
	private String note;//备注
	private Long supplierId;//供应商Id
	private String updateBy; //update_by <更新>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private Long taskId;//任务Id
	@Transient
	private String salerPrice;//销售刊登价格
	@Transient
	private String supplyPrice;//供应商报价录入
	@Transient
	private String newPrice;//最新报价
	@Transient
	private String myUrl;//url
	@Transient
	private String status;//状态
	@Transient
	private String productDescr;//第四种模式的描述
	@Transient
	private Long taskUser;//执行任务的人
	@Transient
	private Long pid;//对应ec_task_product的ID
	 @Transient
	private String name;
	 @Transient
	private String showImg;
	 @Transient
	private String sku;
	 @Transient
	private Long doId;

	public void setTest(Long test) {
		this.set("test", test);
	}

	public Long getDoId() {
		return getLong("doId");
	}

	public void setDoId(Long doId) {
		this.set("doId",doId);
	}

	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		this.set("name",name);
	}

	public String getShowImg() {
		return getString("showImg");
	}

	public void setShowImg(String showImg) {
		this.set("showImg",showImg) ;
	}

	public String getSku() {
		return getString("sku");
	}

	public void setSku(String sku) {
		this.set("sku",sku);
	}

	public Long getTaskId() {
		return getLong("taskId");
	}

	public void setTaskId(Long taskId) {
		this.set("taskId",taskId);
	}

	public String getNote() {
		return this.getString("note");
	}

	public void setNote(String note) {
		this.set("note",note);
	}

	public Long getProductId() {
		return getLong("productId");
	}

	public void setProductId(Long productId) {
		this.set("productId",productId);
	}

	public Long getShopId() {
		return getLong("productId");
	}

	public void setShopId(Long shopId) {
		this.set("shopId",shopId);
	}



	public Long getSupplierId() {
		return getLong("supplierId");
	}

	public void setSupplierId(Long supplierId) {
		this.set("supplierId",supplierId);
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

	
	/***-----------------------------ec_task_do表字段------------------------------***/
	public Long getPid() {
		return getLong("pid");
	}

	public void setPid(Long pid) {
		this.set("pid", pid);
	}

	public String getSalerPrice() {
		return getString("salerPrice");
	}

	public void setSalerPrice(String salerPrice) {
		this.set("salerPrice",salerPrice);
	}

	public String getSupplyPrice() {
		return getString("supplyPrice");
	}

	public void setSupplyPrice(String supplyPrice) {
		this.set("supplyPrice",supplyPrice);
	}

	public String getNewPrice() {
		return getString("newPrice");
	}

	public void setNewPrice(String newPrice) {
		this.set("newPrice",newPrice);
	}

	public String getMyUrl() {
		return getString("myUrl");
	}

	public void setMyUrl(String myUrl) {
		this.set("myUrl",myUrl);
	}

	public String getStatus() {
		return getString("status");
	}

	public void setStatus(String status) {
		this.set("status",status);
	}

	public String getProductDescr() {
		return getString("productDescr");
	}

	public void setProductDescr(String productDescr) {
		this.set("productDescr", productDescr);
	}

	public Long getTaskUser() {
		return getLong("taskUser");
	}

	public void setTaskUser(Long taskUser) {
		this.set("taskUser",taskUser);
	}
	
}
