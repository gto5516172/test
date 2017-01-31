package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONArray;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="")
public class EcStatisticsUserOrType extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private Long eid;
	
	private String sname;
	
	private Integer num;
	
	private Double price;
	
	public Double getPrice() {
		return getDouble("price");
	}

	public void setPrice(Double price) {
		this.set("price", price);
	}

	public Long getEid() {
		return getLong("eid");
	}

	public String getSname() {
		return getString("sname");
	}

	public void setSname(String sname) {
		this.set("sname", sname);
	}

	public void setEid(Long eid) {
		this.set("eid", eid);
	}

	public Integer getNum() {
		return getInteger("num");
	}

	public void setNum(Integer num) {
		this.set("num", num);
	}
	
}
