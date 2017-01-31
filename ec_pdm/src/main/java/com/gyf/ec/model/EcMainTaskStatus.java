package com.gyf.ec.model;

import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="")
public class EcMainTaskStatus extends BaseEntity {
	/**
	 * 
	 */
	private Integer taskStatus0;
	
	private Integer taskStatus1;
	
	private Integer taskStatus2;
	
	private Integer taskStatus3;
	
	private Integer taskStatus4;
	
	private Integer taskStatus5;
	
	private Integer taskStatus6;
	
	private Integer taskStatus7;
	
	private Integer taskStatus8;
	
	private Integer statusNum;
	
	private Integer statusType;
	
	public Integer getStatusNum() {
		return getInteger("statusNum");
	}

	public void setStatusNum(Integer statusNum) {
		this.set("statusNum", statusNum);
	}

	public Integer getStatusType() {
		return getInteger("statusType");
	}

	public void setStatusType(Integer statusType) {
		this.set("statusType", statusType);
	}
	
	public Integer getTaskStatus0() {
		return getInteger("taskStatus0");
	}

	public void setTaskStatus0(Integer taskStatus0) {
		this.set("taskStatus0", taskStatus0);
	}

	public Integer getTaskStatus1() {
		return getInteger("taskStatus1");
	}

	public void setTaskStatus1(Integer taskStatus1) {
		this.set("taskStatus1", taskStatus1);
	}

	public Integer getTaskStatus2() {
		return getInteger("taskStatus2");
	}

	public void setTaskStatus2(Integer taskStatus2) {
		this.set("taskStatus2", taskStatus2);
	}

	public Integer getTaskStatus3() {
		return getInteger("taskStatus3");
	}

	public void setTaskStatus3(Integer taskStatus3) {
		this.set("taskStatus3", taskStatus3);
	}

	public Integer getTaskStatus4() {
		return getInteger("taskStatus4");
	}

	public void setTaskStatus4(Integer taskStatus4) {
		this.set("taskStatus4", taskStatus4);
	}

	public Integer getTaskStatus5() {
		return getInteger("taskStatus5");
	}

	public void setTaskStatus5(Integer taskStatus5) {
		this.set("taskStatus5", taskStatus5);
	}

	public Integer getTaskStatus6() {
		return getInteger("taskStatus6");
	}

	public void setTaskStatus6(Integer taskStatus6) {
		this.set("taskStatus6", taskStatus6);
	}

	public Integer getTaskStatus7() {
		return getInteger("taskStatus7");
	}

	public void setTaskStatus7(Integer taskStatus7) {
		this.set("taskStatus7", taskStatus7);
	}

	public Integer getTaskStatus8() {
		return getInteger("taskStatus8");
	}

	public void setTaskStatus8(Integer taskStatus8) {
		this.set("taskStatus8", taskStatus8);
	}
	
}
