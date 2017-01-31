//Powered By if, Since 2014 - 2020

package com.gohuinuo.web.sys.model;

import java.util.Date;
import java.util.List;

import com.gohuinuo.common.base.BaseEntity;
import com.gohuinuo.common.constant.Constant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
@Table(name="")
public class SysMain extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer spuCount; // 新增spu
	
	private Integer skuCount; // 新增sku
	
	private Integer supCount; // 新增供应商
	
	private Integer faqCount; // 新增faq
	
	private Integer fileCount; // 新增文件
	
	private Integer taskCount; // 新增任务
	
	private Integer userCount; // 用户总数
	
	private Integer loginCount; // 今日登录数
	
	private Integer upLoadCount; // 上传文件数
	
	private Integer taskSYCount; // 所有任务
	
	private Integer taskWWCCount; // 未完成任务 
	
	private Integer taskYWCCount; // 已完成任务
	
	public Integer getTaskSYCount() {
		return getInteger("taskSYCount");
	}

	public void setTaskSYCount(Integer taskSYCount) {
		this.set("taskSYCount", taskSYCount);
	}

	public Integer getTaskWWCCount() {
		return getInteger("taskWWCCount");
	}

	public void setTaskWWCCount(Integer taskWWCCount) {
		this.set("taskWWCCount", taskWWCCount);
	}

	public Integer getTaskYWCCount() {
		return getInteger("taskYWCCount");
	}

	public void setTaskYWCCount(Integer taskYWCCount) {
		this.set("taskYWCCount", taskYWCCount);
	}
	
	public Integer getUserCount() {
		return getInteger("userCount");
	}

	public void setUserCount(Integer userCount) {
		this.set("userCount", userCount);
	}

	public Integer getLoginCount() {
		return getInteger("loginCount");
	}

	public void setLoginCount(Integer loginCount) {
		this.set("loginCount", loginCount);
	}

	public Integer getUpLoadCount() {
		return getInteger("upLoadCount");
	}

	public void setUpLoadCount(Integer upLoadCount) {
		this.set("upLoadCount", upLoadCount);
	}
	
	public Integer getSpuCount() {
		return getInteger("spuCount");
	}

	public void setSpuCount(Integer spuCount) {
		this.set("spuCount", spuCount);
	}

	public Integer getSkuCount() {
		return getInteger("skuCount");
	}

	public void setSkuCount(Integer skuCount) {
		this.set("skuCount", skuCount);
	}

	public Integer getSupCount() {
		return getInteger("supCount");
	}

	public void setSupCount(Integer supCount) {
		this.set("supCount", supCount);
	}

	public Integer getFaqCount() {
		return getInteger("faqCount");
	}

	public void setFaqCount(Integer faqCount) {
		this.set("faqCount", faqCount);
	}

	public Integer getFileCount() {
		return getInteger("fileCount");
	}

	public void setFileCount(Integer fileCount) {
		this.set("fileCount", fileCount);
	}

	public Integer getTaskCount() {
		return getInteger("taskCount");
	}

	public void setTaskCount(Integer taskCount) {
		this.set("taskCount", taskCount);
	}
    
}
