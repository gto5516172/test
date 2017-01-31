package com.gyf.ec.model;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONArray;
import com.gohuinuo.common.base.BaseEntity;

/**
 * 
 */
@SuppressWarnings({ "unused"})
@Table(name="ec_sku")
public class EcSku extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String updateBy; //update_by <更新者>
	
	private Date updateDate; //update_date <更新时间>
	
	private String createBy; //create_by <创建者>
	
	private Date createDate; //create_date <创建时间>
	
	private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
	
	private String remarks; //remarks <备注信息>
	
	private String sku; //sku<商品sku>
	
	private Integer attr; //attr<产品属性>
	
	private String name; //name<商品名称>
	
	private Integer status; //status<商品状态>
	
	private String placeOfOrigin; //placeOfOrigin<原产地>
	
	private String customs; //customs<海关>
	
	private String saleSku; //saleSku<销售sku>
	
	private Long ecCatalogueId; //ecCatalogueId<商品细类目录ID>
	
	private String commodityLong; //commodityLong<商品长>
	
	private String commodityWidth; //commodityWidth<商品宽>
	
	private String commodityHeight; //commodityHeight<商品高>
	
	private String commodityVolume; //commodityVolume<商品体积>
	
	private String commodityCompany; //commodityCompany<商品单位>
	
	private String packingLong; //packingLong<包装长>
	
	private String packingWidth; //packingWidth<包装宽>
	
	private String packingHeight; //packingHeight<包装高>
	
	private String packingVolume; //packingVolume<包装体积>
	
	private String packingCompany; //packingCompany<包装单位>
	
	private Integer fclNum; //fclNum<装箱数量>
	
	private String fclWeight; //fclWeight<整箱重量>
	
	private String fclVolumne; //fclVolumne<整箱体积>
	
	private Integer packageType; //packageType<包材类型>
	
	private String discountRangeStart; //discountRangeStart<折扣浮动起点>
	
	private String discountRangeEnd; //discountRangeEnd<折扣浮动终点>
	
	private String productLabelConfiguration; //productLabelConfiguration<商品标签配置>
	
	private String salesStaff; //salesStaff<销售员>
	
	private String releaseDate; //releaseDate<销售时间>
	
	private String company; //company<单位>
	
	private String shippingWeight; //shippingWeight<商品毛重>
	
	private Integer shippingWeightCompany; //shippingWeightCompany<毛重单位>
	
	private String itemWeight; //itemWeight<商品净重>
	
	private Integer itemWeightCompany; //itemWeightCompany<净重单位>
	
	private Long suppliersId; //suppliersId<首选供应商ID>
	
	private String showImg; //showImg<首图> 
	
	private String basicImgs; //基本图片集合（Json数据）
	
	private String model; //model<型号>
	
	private String specifications; //specifications<规格>
	
	private String suggestedRetailPrice; //suggestedRetailPrice<建议零售价>
	
	private String costPrice; //costPrice<成本价>
	
	private Long spuId; //spuId(spuId)
	
	private String spuNo; //spuNo<SPU编码>

	@Transient
	private Long[] suppliersIds; //suppliersIds<供应商>
	
	@Transient
	private String[] factoryNo; //factoryNo<原厂编号>
	
	@Transient
	private String[] purchasePrice; //purchasePrice<采购价格>

	@Transient
	private String[] minNum; //minNum<最小采购量>
	
	@Transient
	private Long findCataloueId; //findCataloueId<查询条件商品目录ID>
	
	@Transient
	private Integer queryCondition; //queryCondition<查询条件类型>
	
	@Transient
	private String findTxt; //findTxt<查询关键字>
	
	@Transient
	private String brand; //brand<品牌>
	
	@Transient
	private String fileList;
	
	@Transient
	private String suppliersName; //首选供应商
	
	
	public String getSuppliersName() {
		return getString("suppliersName");
	}
	public void setSuppliersName(String suppliersName) {
		this.set("suppliersName", suppliersName);
	}
	public String getFileList() {
		return getString("fileList");
	}
	public void setFileList(String fileList) {
		this.set("fileList", fileList);
	}
	public String getSpuNo() {
		return getString("spuNo");
	}
	public void setSpuNo(String spuNo) {
		this.set("spuNo", spuNo);
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getSpuId() {
		return getLong("spuId");
	}
	public void setSpuId(Long spuId) {
		this.set("spuId", spuId);
	}
	public String getModel() {
		return getString("model");
	}
	public void setModel(String model) {
		this.set("model", model);
	}
	public String getSpecifications() {
		return getString("specifications");
	}
	public void setSpecifications(String specifications) {
		this.set("specifications", specifications);
	}
	public String getSuggestedRetailPrice() {
		return getString("suggestedRetailPrice");
	}
	public void setSuggestedRetailPrice(String suggestedRetailPrice) {
		this.set("suggestedRetailPrice", suggestedRetailPrice);
	}
	public String getCostPrice() {
		return getString("costPrice");
	}
	public void setCostPrice(String costPrice) {
		this.set("costPrice", costPrice);
	}
	public Long getFindCataloueId() {
		return getLong("findCataloueId");
	}
	public void setFindCataloueId(Long findCataloueId) {
		this.set("findCataloueId", findCataloueId);
	}
	public Integer getQueryCondition() {
		return getInteger("queryCondition");
	}
	public void setQueryCondition(Integer queryCondition) {
		this.set("queryCondition", queryCondition);
	}
	public String getFindTxt() {
		return getString("findTxt");
	}
	public void setFindTxt(String findTxt) {
		this.set("findTxt", findTxt);
	}
	public String getShowImg() {
		return getString("showImg");
	}
	public void setShowImg(String showImg) {
		this.set("showImg", showImg);
	}
	public String getBasicImgs() {
		return getString("basicImgs");
	}
	public void setBasicImgs(String basicImgs) {
		this.set("basicImgs", basicImgs);
	}
	
	public String getCommodityCompany() {
		return getString("commodityCompany");
	}

	public void setCommodityCompany(String commodityCompany) {
		this.set("commodityCompany", commodityCompany);
	}

	public String getPackingCompany() {
		return getString("packingCompany");
	}

	public void setPackingCompany(String packingCompany) {
		this.set("packingCompany", packingCompany);
	}
	
	public String[] getFactoryNo() {
		return (String[])this.get("factoryNo");
	}

	public void setFactoryNo(String[] factoryNo) {
		this.set("factoryNo", factoryNo);
	}

	public String[] getPurchasePrice() {
		return (String[])this.get("purchasePrice");
	}

	public void setPurchasePrice(String[] purchasePrice) {
		this.set("purchasePrice", purchasePrice);
	}

	public String[] getMinNum() {
		return (String[])this.get("minNum");
	}

	public void setMinNum(String[] minNum) {
		this.set("minNum", minNum);
	}
	
	public Long[] getSuppliersIds() {
		return (Long[])this.get("suppliersIds");
	}

	public void setSuppliersIds(Long[] suppliersIds) {
		this.set("suppliersIds", suppliersIds);
	}

	public Long getSuppliersId() {
		return getLong("suppliersId");
	}

	public void setSuppliersId(Long suppliersId) {
		this.set("suppliersId", suppliersId);
	}

	public String getSku() {
		return getString("sku");
	}

	public void setSku(String sku) {
		this.set("sku", sku);
	}

	public Integer getAttr() {
		return getInteger("attr");
	}

	public void setAttr(Integer attr) {
		this.set("attr", attr);
	}

	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		this.set("name", name);
	}

	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		this.set("status", status);
	}

	public String getPlaceOfOrigin() {
		return getString("placeOfOrigin");
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.set("placeOfOrigin", placeOfOrigin);
	}

	public String getCustoms() {
		return getString("customs");
	}

	public void setCustoms(String customs) {
		this.set("customs", customs);
	}

	public String getSaleSku() {
		return getString("saleSku");
	}

	public void setSaleSku(String saleSku) {
		this.set("saleSku", saleSku);
	}

	public Long getEcCatalogueId() {
		return getLong("ecCatalogueId");
	}

	public void setEcCatalogueId(Long ecCatalogueId) {
		this.set("ecCatalogueId", ecCatalogueId);
	}

	public String getCommodityLong() {
		return getString("commodityLong");
	}

	public void setCommodityLong(String commodityLong) {
		this.set("commodityLong", commodityLong);
	}

	public String getCommodityWidth() {
		return getString("commodityWidth");
	}

	public void setCommodityWidth(String commodityWidth) {
		this.set("commodityWidth", commodityWidth);
	}

	public String getCommodityHeight() {
		return getString("commodityHeight");
	}

	public void setCommodityHeight(String commodityHeight) {
		this.set("commodityHeight", commodityHeight);
	}

	public String getCommodityVolume() {
		return getString("commodityVolume");
	}

	public void setCommodityVolume(String commodityVolume) {
		this.set("commodityVolume", commodityVolume);
	}

	public String getPackingLong() {
		return getString("packingLong");
	}

	public void setPackingLong(String packingLong) {
		this.set("packingLong", packingLong);
	}

	public String getPackingWidth() {
		return getString("packingWidth");
	}

	public void setPackingWidth(String packingWidth) {
		this.set("packingWidth", packingWidth);
	}

	public String getPackingHeight() {
		return getString("packingHeight");
	}

	public void setPackingHeight(String packingHeight) {
		this.set("packingHeight", packingHeight);
	}

	public String getPackingVolume() {
		return getString("packingVolume");
	}

	public void setPackingVolume(String packingVolume) {
		this.set("packingVolume", packingVolume);
	}

	public Integer getFclNum() {
		return getInteger("fclNum");
	}

	public void setFclNum(Integer fclNum) {
		this.set("fclNum", fclNum);
	}

	public String getFclWeight() {
		return getString("fclWeight");
	}

	public void setFclWeight(String fclWeight) {
		this.set("fclWeight", fclWeight);
	}

	public String getFclVolumne() {
		return getString("fclVolumne");
	}

	public void setFclVolumne(String fclVolumne) {
		this.set("fclVolumne", fclVolumne);
	}

	public Integer getPackageType() {
		return getInteger("packageType");
	}

	public void setPackageType(Integer packageType) {
		this.set("packageType", packageType);
	}

	public String getDiscountRangeStart() {
		return getString("discountRangeStart");
	}

	public void setDiscountRangeStart(String discountRangeStart) {
		this.set("discountRangeStart", discountRangeStart);
	}

	public String getDiscountRangeEnd() {
		return getString("discountRangeEnd");
	}

	public void setDiscountRangeEnd(String discountRangeEnd) {
		this.set("discountRangeEnd", discountRangeEnd);
	}

	public String getProductLabelConfiguration() {
		return getString("productLabelConfiguration");
	}

	public void setProductLabelConfiguration(String productLabelConfiguration) {
		this.set("productLabelConfiguration", productLabelConfiguration);
	}

	public String getSalesStaff() {
		return getString("salesStaff");
	}

	public void setSalesStaff(String salesStaff) {
		this.set("salesStaff", salesStaff);
	}

	public String getReleaseDate() {
		return getString("releaseDate");
	}

	public void setReleaseDate(String releaseDate) {
		this.set("releaseDate", releaseDate);
	}

	public String getCompany() {
		return getString("company");
	}

	public void setCompany(String company) {
		this.set("company", company);
	}

	public String getShippingWeight() {
		return getString("shippingWeight");
	}

	public void setShippingWeight(String shippingWeight) {
		this.set("shippingWeight", shippingWeight);
	}

	public Integer getShippingWeightCompany() {
		return getInteger("shippingWeightCompany");
	}

	public void setShippingWeightCompany(Integer shippingWeightCompany) {
		this.set("shippingWeightCompany", shippingWeightCompany);
	}

	public String getItemWeight() {
		return getString("itemWeight");
	}

	public void setItemWeight(String itemWeight) {
		this.set("itemWeight", itemWeight);
	}

	public Integer getItemWeightCompany() {
		return getInteger("itemWeightCompany");
	}

	public void setItemWeightCompany(Integer itemWeightCompany) {
		this.set("itemWeightCompany", itemWeightCompany);
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
