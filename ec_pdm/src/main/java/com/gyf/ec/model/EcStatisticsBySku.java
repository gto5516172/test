package com.gyf.ec.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="")
public class EcStatisticsBySku extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String sku; // sku编号
	
	private String skuName; // sku名称
	
	private String firstSupName; // 首选供应商名称
	
	private String costPriceList; // 成本价变动
	
	private Double second; // 上一次价格
	
	private Double first; // 最新价格
	
	private Double floatPrice; //浮动金额
	
	private Double floatProportion; //浮动比例
	
	private Date newTime; // 最新更新时间
	
	private Long skuId; // sku_id
	
	public Double getSecond() {
		return getDouble("second");
	}

	public void setSecond(Double second) {
		this.set("second", second);
	}

	public Double getFirst() {
		return getDouble("first");
	}

	public void setFirst(Double first) {
		this.set("first", first);
	}
	
	public Long getSkuId() {
		return getLong("skuId");
	}

	public void setSkuId(Long skuId) {
		this.set("skuId", skuId);
	}

	public String getCostPriceList() {
		return getString("costPriceList");
	}

	public void setCostPriceList(String costPriceList) {
		this.set("costPriceList", costPriceList);
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

	public String getFirstSupName() {
		return getString("firstSupName");
	}

	public void setFirstSupName(String firstSupName) {
		this.set("firstSupName", firstSupName);
	}

	public Double getFloatPrice() {
		return getDouble("floatPrice");
	}

	public void setFloatPrice(Double floatPrice) {
		this.set("floatPrice", floatPrice);
	}

	public Double getFloatProportion() {
		return getDouble("floatProportion");
	}

	public void setFloatProportion(Double floatProportion) {
		this.set("floatProportion", floatProportion);
	}

	public Date getNewTime() {
		return getDate("newTime");
	}

	public void setNewTime(Date newTime) {
		this.set("newTime", newTime);
	}
}
