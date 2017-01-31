package com.gyf.ec.model;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

/**
 * 文件路径对应表
 */
@SuppressWarnings({ "unused"})
@Table(name="ec_file_url")
public class EcFileUrl extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private Long ecFileId; //ec_file_id<对应文件管理表ID>
	
	private String saveDirectory; // saveDirectory<文件绝对路径>
	
	private String fileName; // fileName<上传后文件名称>
	
	private String originalFileName; // originalFileName<上传前文件名>
	
	private String relativeUrl; // relativeUrl<相对路径>
	
	private String contentType; // contentType<上传的文件的类型>
	
	private String suffix; // suffix<后缀>
	
	private Long userId; // user_id<上传用户ID>
	
	@Transient
	private String type;
	
	public String getType() {
		return getString("type");
	}

	public void setType(String type) {
		this.set("type", type);
	}
	
	public Long getUserId() {
		return getLong("userId");
	}

	public void setUserId(Long userId) {
		this.set("userId", userId);
	}
	
	public String getSuffix() {
		return getString("suffix");
	}

	public void setSuffix(String suffix) {
		this.set("suffix", suffix);
	}
	
	
	
	public Long getEcFileId() {
		return getLong("ecFileId");
	}

	public void setEcFileId(Long ecFileId) {
		this.set("ecFileId", ecFileId);
	}

	public String getSaveDirectory() {
		return getString("saveDirectory");
	}

	public void setSaveDirectory(String saveDirectory) {
		this.set("saveDirectory", saveDirectory);
	}

	public String getFileName() {
		return getString("fileName");
	}

	public void setFileName(String fileName) {
		this.set("fileName", fileName);
	}

	public String getOriginalFileName() {
		return getString("originalFileName");
	}

	public void setOriginalFileName(String originalFileName) {
		this.set("originalFileName", originalFileName);
	}

	public String getRelativeUrl() {
		return getString("relativeUrl");
	}

	public void setRelativeUrl(String relativeUrl) {
		this.set("relativeUrl", relativeUrl);
	}

	public String getContentType() {
		return getString("contentType");
	}

	public void setContentType(String contentType) {
		this.set("contentType", contentType);
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
