package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONArray;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_bom")
public class EcBom extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private Long codeSkuId; // codeSkuId<物料编码对应的skuId>
	
	@Transient
	private String itemCodes; // itemCodes<物料编码>
	
	@Transient
	private String itemName; //itemName<物料名称>
	
	private String requirements; //requirements<需求数量>
	
	@Transient
	private String cost; //cost<成本价>
	
	@Transient
	private String company; //company<单位>
	
	@Transient
	private String basicImgs; //基本图片集合（Json数据）
	
	private Long skuId; //skuId<skuId>
	
	@Transient
	private String showImg; //showImg<显示图片>
	
	@Transient
	private String findBomTxt; //findTxt<搜索条件>
	
	public Long getCodeSkuId() {
		return getLong("codeSkuId");
	}
	public void setCodeSkuId(Long codeSkuId) {
		this.set("codeSkuId", codeSkuId);
	}
	
	public String getBasicImgs() {
		return getString("basicImgs");
	}
	public void setBasicImgs(String basicImgs) {
		this.set("basicImgs", basicImgs);
	}
	
	public String getShowImg() {
		return getString("showImg");
	}

	public void setShowImg(String showImg) {
		this.set("showImg", showImg);
	}
	
	public String getFindBomTxt() {
		return getString("findBomTxt");
	}

	public void setFindBomTxt(String findBomTxt) {
		this.set("findBomTxt", findBomTxt);
	}

	public Long getSkuId() {
		return getLong("skuId");
	}

	public void setSkuId(Long skuId) {
		this.set("skuId", skuId);
	}

	public String getItemCodes() {
		return getString("itemCodes");
	}

	public void setItemCodes(String itemCodes) {
		this.set("itemCodes", itemCodes);
	}

	public String getItemName() {
		return getString("itemName");
	}

	public void setItemName(String itemName) {
		this.set("itemName", itemName);
	}

	public String getRequirements() {
		return getString("requirements");
	}

	public void setRequirements(String requirements) {
		this.set("requirements", requirements);
	}

	public String getCost() {
		return getString("cost");
	}

	public void setCost(String cost) {
		this.set("cost", cost);
	}

	public String getCompany() {
		return getString("company");
	}

	public void setCompany(String company) {
		this.set("company", company);
	}

	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
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
