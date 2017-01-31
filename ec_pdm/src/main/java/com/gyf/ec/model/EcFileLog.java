package com.gyf.ec.model;
import java.util.Date;

import javax.persistence.Table;

import com.gohuinuo.common.base.BaseEntity;

/**
 * 文件路径对应表
 */
@SuppressWarnings({ "unused"})
@Table(name="ec_file_log")
public class EcFileLog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private Long ecFileId; //ec_file_id<对应文件管理表ID>
	
	private Integer type; // type<操作的类型0上传文件 1删除文件>
	
	public Integer getType() {
		return getInteger("type");
	}

	public void setType(Integer type) {
		this.set("type", type);
	}

	public Long getEcFileId() {
		return getLong("ecFileId");
	}

	public void setEcFileId(Long ecFileId) {
		this.set("ecFileId", ecFileId);
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
	
	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
	}
	
}
