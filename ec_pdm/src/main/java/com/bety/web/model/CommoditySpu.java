package com.bety.web.model;

import java.util.Date;

import javax.persistence.Table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.BaseEntity;
@SuppressWarnings({ "unused"})
@Table(name="ec_commodity_spu")
public class CommoditySpu extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//商品名称
	private Long type;//分类
	private String spuNo;//商品SPU码
	private String price;//建议零售价
	private String brand;//品牌
	private String manufactorNo;//原厂编号
	private String units;//单位
	private String originPlace;//商品产地
	private String descr;//商品备注
	private String sellDescr;//销售信息
	private String otherDescr;//其他备注
    private String updateBy; //update_by <更新者>
	private Date updateDate; //update_date <更新时间>
	private String createBy; //create_by <创建者>
	private Date createDate; //create_date <创建时间>
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	private String basicImgs[];//基本图片集合
	private String title;//标题
	private String keywords;//关键字
	private String backImgs[];
	private String noBackImgs[];
	private String platforms;
	private String  descs;
	private String pf[];
	private String typeTmp;//临时变量
	private String plfTmp;
	
	public String getPlfTmp() {
		return plfTmp;
	}

	public void setPlfTmp(String plfTmp) {
		this.plfTmp = plfTmp;
	}

	public String getTypeTmp() {
		return typeTmp;
	}

	public void setTypeTmp(String typeTmp) {
		this.typeTmp = typeTmp;
	}

	public void setPf(String[] pf) {
		String str = "";
		for(int i=0;i<pf.length;i++){
			str= str+pf[i]+",";
		}
		this.set("platforms", str);
	}

	public String getPlatforms() {
		return this.getString("platforms");
	}
	
	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public String getTitle() {
		return getString("title");
	}
	public void setTitle(String title) {
		this.set("title",title);
	}
	public String getKeywords() {
		return getString("keywords");
	}
	public void setKeywords(String keywords) {
		this.set("keywords",keywords);
	}

	
	public String[] getBasicImgs() {
		return basicImgs;
	}

	public void setBasicImgs(String[] basicImgs) {
		this.basicImgs = basicImgs;
	}

	public String[] getBackImgs() {
		return backImgs;
	}

	public void setBackImgs(String[] backImgs) {
		this.backImgs = backImgs;
	}

	public String[] getNoBackImgs() {
		return noBackImgs;
	}

	public void setNoBackImgs(String[] noBackImgs) {
		this.noBackImgs = noBackImgs;
	}

	public Long getType() {
		return this.getLong("type");
	}
	public void setType(Long type) {
		this.set("type", type);
	}
	public String getSpuNo() {
		return this.getString("spuNo");
	}
	public void setSpuNo(String spuNo) {
		this.set("spuNo", spuNo);
	}
	public String getPrice() {
		return this.getString("price");
	}
	public void setPrice(String price) {
		this.set("price",price);
	}
	public String getBrand() {
		return this.getString("brand");
	}
	public void setBrand(String brand) {
		this.set("brand",brand);
	}
	public String getManufactorNo() {
		return this.getString("manufactorNo");
	}
	public void setManufactorNo(String manufactorNo) {
		this.set("manufactorNo", manufactorNo);
	}
	public String getUnits() {
		return this.getString("units");
	}
	public void setUnits(String units) {
		this.set("units", units);
	}
	public String getOriginPlace() {
		return this.getString("originPlace");
	}
	public void setOriginPlace(String originPlace) {
		this.set("originPlace",originPlace);
	}
	public String getSellDescr() {
		return this.getString("sellDescr");
	}
	public void setSellDescr(String sellDescr) {
		this.set("sellDescr",sellDescr);
	}
	public String getOtherDescr() {
		return this.getString("otherDescr");
	}
	public void setOtherDescr(String otherDescr) {
		this.set("otherDescr", otherDescr);
	}
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
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
