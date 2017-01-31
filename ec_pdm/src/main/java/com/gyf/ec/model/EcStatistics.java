package com.gyf.ec.model;

import javax.persistence.Table;
import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="")
public class EcStatistics extends BaseEntity {

	private String dateTime;
	
	private Integer num;
	
	public String getDateTime() {
		return getString("dateTime");
	}

	public void setDateTime(String dateTime) {
		this.set("dateTime", dateTime);
	}

	public Integer getNum() {
		return getInteger("num");
	}

	public void setNum(Integer num) {
		this.set("num", num);
	}
}
