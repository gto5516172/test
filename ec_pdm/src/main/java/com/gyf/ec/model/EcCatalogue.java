package com.gyf.ec.model;

import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_catalogue")
public class EcCatalogue extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private String name; //name <目录中文名称> 
	
	private String englishDirectory; //english_directory <目录英文名称>
	
	private String chCustoms; //ch_customs <中文报关名称>
	
	private String enCustoms; //en_customs <英文报关名称>
	
	private Long parentId; // parent_id <父级编号>
	
	private String parentIds; // parent_ids <所有父级编号>
	
	private Integer templateType; //template_type <模板类型>
	
	private String icon; //icon <图标>
	
	@Transient
    private String oldParentIds; //旧的pids,非表中字段，用作更新用
	
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		this.set("name", name);
	}
	
	public String getOldParentIds() {
		return this.getString("oldParentIds");
    }
   
    public void setOldParentIds(String oldParentIds) {
		this.set("oldParentIds", oldParentIds);
    }
	
	public String getIcon() {
		return this.getString("icon");
    }
   
    public void setIcon(String icon) {
		this.set("icon", icon);
    }

	public String getEnglishDirectory() {
		return getString("englishDirectory");
	}

	public void setEnglishDirectory(String englishDirectory) {
		this.set("englishDirectory", englishDirectory);
	}

	public String getChCustoms() {
		return getString("chCustoms");
	}

	public void setChCustoms(String chCustoms) {
		this.set("chCustoms", chCustoms);
	}

	public String getEnCustoms() {
		return getString("enCustoms");
	}

	public void setEnCustoms(String enCustoms) {
		this.set("enCustoms", enCustoms);
	}

	public Long getParentId() {
		return getLong("parentId");
	}

	public void setParentId(Long parentId) {
		this.set("parentId", parentId);
	}

	public String getParentIds() {
		return getString("parentIds");
	}

	public void setParentIds(String parentIds) {
		this.set("parentIds", parentIds);
	}

	public Integer getTemplateType() {
		return getInteger("templateType");
	}

	public void setTemplateType(Integer templateType) {
		this.set("templateType", templateType);
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
