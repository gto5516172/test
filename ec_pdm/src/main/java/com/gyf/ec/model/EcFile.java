package com.gyf.ec.model;

import java.io.File;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

/**
 * 文件表
 * @author Administrator
 *
 */
@SuppressWarnings({ "unused"})
@Table(name="ec_file")
public class EcFile extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private String documentTheme; //documentTheme <文件主题>
	
	private Long ecFileClassificationId; //ecFileClassificationId <文件分类ID>
	
	private Integer showType; //showType <0公开 1隐私>
	
	private Integer goodsType; //goodsType <0:SPU 1:SKU>
	
	private String goodsNo; //goodsNo <关联的SPU/SKU>
	
	private Integer status; //status <0启用 1停用>
	
	private Long userId; // userId<创建者ID>

	@Transient
    private Long[] userIds; //绑定的用户
	
	@Transient
	private String fileList;
	
	public Long getUserId() {
		return getLong("userId");
	}

	public void setUserId(Long userId) {
		this.set("userId", userId);
	}
    
    public Long[] getUserIds(){
    	return (Long[])this.get("userIds");
    }
    
    public void setUserIds(Long[] userIds){
    	this.set("userIds", userIds);
    }
	
	public String getFileList() {
		return (String)this.get("fileList");
	}
	
	public void setFileList(String fileList) {
		this.set("fileList", fileList);
	}
	
	public String getRemarks() {
		return getString("remarks");
	}

	public void setRemarks(String remarks) {
		this.set("remarks", remarks);
	}

	public String getDocumentTheme() {
		return getString("documentTheme");
	}

	public void setDocumentTheme(String documentTheme) {
		this.set("documentTheme", documentTheme);
	}

	public Long getEcFileClassificationId() {
		return getLong("ecFileClassificationId");
	}

	public void setEcFileClassificationId(Long ecFileClassificationId) {
		this.set("ecFileClassificationId", ecFileClassificationId);
	}

	public int getShowType() {
		return getInteger("showType");
	}

	public void setShowType(int showType) {
		this.set("showType", showType);
	}

	public int getStatus() {
		return getInteger("status");
	}

	public void setStatus(int status) {
		this.set("status", status);
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

	public Integer getGoodsType() {
		return getInteger("goodsType");
	}

	public void setGoodsType(Integer goodsType) {
		this.set("goodsType", goodsType);
	}

	public String getGoodsNo() {
		return getString("goodsNo");
	}

	public void setGoodsNo(String goodsNo) {
		this.set("goodsNo", goodsNo);
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
