package com.gyf.ec.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gohuinuo.common.base.BaseEntity;

@SuppressWarnings({ "unused"})
@Table(name="ec_file_classification")
public class EcFileClassification extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name; //name <分类名称>

    private Long parentId; //parent_id <父级编号>

    private String parentIds; //parent_ids <所有父级编号>
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private String type; //type <分类类型>
	
	private String icon; //icon <图标>
	
	@Transient
    private String oldParentIds; //旧的pids,非表中字段，用作更新用
	
	public String getOldParentIds() {
		return this.getString("oldParentIds");
    }
   
    public void setOldParentIds(String oldParentIds) {
		this.set("oldParentIds", oldParentIds);
    }
	
	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
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
	
	public Long getParentId() {
		return this.getLong("parentId");
    }
   
    public void setParentId(Long parentId) {
		this.set("parentId", parentId);
    }

	public String getParentIds() {
		return this.getString("parentIds");
    }
   
    public void setParentIds(String parentIds) {
		this.set("parentIds", parentIds);
    }
    
    public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
		this.set("type", type);
    }
    
    public String getIcon() {
		return this.getString("icon");
    }
   
    public void setIcon(String icon) {
		this.set("icon", icon);
    }
	
}
