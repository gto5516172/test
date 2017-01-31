package com.gyf.ec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.Photos;
import com.bety.web.service.PhotosService;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcSkuMapper;
import com.gyf.ec.mapper.EcSuppliersCommodityMapper;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.model.EcSkuCostPriceLog;
import com.gyf.ec.model.EcSuppliersComPriceLog;
import com.gyf.ec.model.EcSuppliersCommodity;

@Service("ecSkuService")
public class EcSkuService extends ServiceMybatis<EcSku> {
	
	@Resource
	private EcSkuMapper ecSkuMapper;
	
	@Resource
	private EcSuppliersCommodityMapper ecSuppliersCommodityMapper;
	
	@Resource
	private PhotosService photosService;
	@Resource
	private EcTaskProductService taskProductService;
	
	@Resource
	private EcSkuCostPriceLogService ecSkuCostPriceLogService;
	
	@Resource
	private EcSuppliersComPriceLogService ecSuppliersComPriceLogService;
	
	public Integer updateSuggestedRetailPrice(EcSku ecSku) {
		return this.updateByPrimaryKeySelective(ecSku);
	}
	
	public EcSku selectByPrimaryKeyMy(Long id) {
		return ecSkuMapper.selectByPrimaryKeyMy(id);
	}
	
	
	/**
	 *新增或更新sku
	 */
	/**
	 * @param ecSku
	 * @return
	 */
	public int saveEcSku(EcSku ecSku){
		int count = 0;
		if(ecSku.getSaleSku() != null) {
			String str = ecSku.getSaleSku();
			ecSku.setSaleSku(str.replaceAll("\\s*", ""));
		}
		if(null == ecSku.getId()){
			String ps = ecSku.getFileList();
			if(ecSku.getFileList() != null && !"".equals(ecSku.getFileList()) && !"[]".equals(ecSku.getFileList())) {
				JSONObject j = JSON.parseObject(ps);
				String img = j.getString("relativeUrl");
				ecSku.setShowImg(img);
				Map<String, String> basicImgs = new HashMap<String, String>();
				basicImgs.put("url", img);
				basicImgs.put("original", j.getString("originalFileName"));
				ecSku.setBasicImgs(JSON.toJSONString(basicImgs));
			}
			count = this.insertSelective(ecSku);
			// 保存成本价记录
			EcSkuCostPriceLog escpl = new EcSkuCostPriceLog();
			escpl.setSkuId(ecSku.getId());
			escpl.setOldCostPrice(ecSku.getCostPrice());
			escpl.setOldCostPriceTime(new Date());
			ecSkuCostPriceLogService.saveEcSkuCostPriceLog(escpl);
			//存储sku图片
 			if(ecSku.getBasicImgs()!=null){
 					Photos photos = new Photos();
 					JSONObject j = JSON.parseObject(ps);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("3");
 					photos.setImgFromId(ecSku.getId());
 					photosService.save(photos);
 			}
			List<EcSuppliersCommodity> escList = new ArrayList<EcSuppliersCommodity>();
			Long[] suppliersIds = ecSku.getSuppliersIds();
			if(suppliersIds!=null){
			String[] factoryNo = ecSku.getFactoryNo();
			String[] purchasePrice = ecSku.getPurchasePrice();
			String[] minNum = ecSku.getMinNum();
			for(int i = 0; i < suppliersIds.length; i++) {
				if(suppliersIds[i] > 0) {
					EcSuppliersCommodity esc = new EcSuppliersCommodity();
					esc.setSuppliersId(suppliersIds[i]);
					esc.setCommodityId(ecSku.getId());
					esc.setFactoryNo(factoryNo[i]);
					esc.setPurchasePrice(purchasePrice[i]);
					esc.setMinNum(minNum[i]);
					esc.setCreateBy(SysUserUtils.getCacheLoginUser().getId()+","+
							SysUserUtils.getCacheLoginUser().getName());
					esc.setCreateDate(new Date());
					escList.add(esc);
				}
			}
			if(escList.size() > 0) {
				ecSuppliersCommodityMapper.saveEcSuppliersCommodity(escList);
				for(int i = 0; i < escList.size(); i++) {
					EcSuppliersCommodity esc = escList.get(i);
					// 保存报价记录
					EcSuppliersComPriceLog ecSuppliersComPriceLog = new EcSuppliersComPriceLog();
					ecSuppliersComPriceLog.setSuppcomId(suppliersIds[i]);
					ecSuppliersComPriceLog.setOldPurchasePrice(esc.getPurchasePrice());
					ecSuppliersComPriceLog.setOldPurchasePriceTime(new Date());
					ecSuppliersComPriceLog.setSkuId(esc.getCommodityId());
					ecSuppliersComPriceLogService.save(ecSuppliersComPriceLog);
				}
			}
			}
		}else{
			String ps = ecSku.getFileList();
			if((ecSku.getShowImg() == null || "".equals(ecSku.getShowImg())) && ecSku.getFileList() != null && !"".equals(ecSku.getFileList()) && !"[]".equals(ecSku.getFileList())) {
				JSONObject j = JSON.parseObject(ps);
				String img = j.getString("relativeUrl");
				ecSku.setShowImg(img);
				Map<String, String> basicImgs = new HashMap<String, String>();
				basicImgs.put("url", img);
				basicImgs.put("original", j.getString("originalFileName"));
				ecSku.setBasicImgs(JSON.toJSONString(basicImgs));
			}
			if(ecSku.getSuppliersIds() != null && (ecSku.getSuppliersIds().length == 0 || ecSku.getSuppliersIds()[0] == 0)) {
				ecSku.setSuppliersId(Long.parseLong("0"));
			}
			count = this.updateByPrimaryKeySelective(ecSku);
			if(count > 0) {
					// 保存成本价记录
					EcSkuCostPriceLog escpl = new EcSkuCostPriceLog();
					escpl.setSkuId(ecSku.getId());
					escpl.setOldCostPrice(ecSku.getCostPrice());
					escpl.setOldCostPriceTime(new Date());
					ecSkuCostPriceLogService.saveEcSkuCostPriceLog(escpl);
			}
			//存储sku图片
 			if(ecSku.getBasicImgs()!=null && ps != null){
 					Photos photos = new Photos();
 					JSONObject j = JSON.parseObject(ps);
 					Map<String, Object> params = new HashMap<String, Object>();
 					params.put("imgFrom", "3");
 					params.put("imgFromId", ecSku.getId());
 					// 先清出之前的图片
 					photosService.deleteByFrom(params);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("3");
 					photos.setImgFromId(ecSku.getId());
 					// 再保存更新的图片
 					photosService.save(photos);
 			}
			List<EcSuppliersCommodity> escList = new ArrayList<EcSuppliersCommodity>();
			Long[] suppliersIds = ecSku.getSuppliersIds();
			String[] factoryNo = ecSku.getFactoryNo();
			String[] purchasePrice = ecSku.getPurchasePrice();
			String[] minNum = ecSku.getMinNum();
			if(suppliersIds!=null){
			for(int i = 0; i < suppliersIds.length; i++) {
				if(suppliersIds[i] > 0) {
					EcSuppliersCommodity esc = new EcSuppliersCommodity();
					esc.setSuppliersId(suppliersIds[i]);
					esc.setCommodityId(ecSku.getId());
					esc.setFactoryNo(factoryNo[i]);
					esc.setPurchasePrice(purchasePrice[i]);
					esc.setMinNum(minNum[i]);
					esc.setCreateBy(SysUserUtils.getCacheLoginUser().getId()+","+
							SysUserUtils.getCacheLoginUser().getName());
					esc.setCreateDate(new Date());
					escList.add(esc);
				}
			}
			}
			ecSuppliersCommodityMapper.delEcSuppliersCommodityBySku(ecSku.getId());
			if(escList.size() > 0) {
				ecSuppliersCommodityMapper.saveEcSuppliersCommodity(escList);
				for(int i = 0; i < escList.size(); i++) {
					EcSuppliersCommodity esc = escList.get(i);
					// 保存报价记录
					EcSuppliersComPriceLog ecSuppliersComPriceLog = new EcSuppliersComPriceLog();
					ecSuppliersComPriceLog.setSuppcomId(suppliersIds[i]);
					ecSuppliersComPriceLog.setOldPurchasePrice(esc.getPurchasePrice());
					ecSuppliersComPriceLog.setOldPurchasePriceTime(new Date());
					ecSuppliersComPriceLog.setSkuId(esc.getCommodityId());
					ecSuppliersComPriceLogService.save(ecSuppliersComPriceLog);
				}
			}
		}
	    return count;
	}
	
	/**
	 * 商品SKU分页显示筛选查询
	 * @param params
	 * @return
	 */
	public List<EcSku> findSkuList(Map<String, Object> params) {
		return ecSkuMapper.findSkuList(params);
	}
	
	
	/**
	 * 删除sku
	* @param id
	 */
	public int deleteEcSku(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		// 判断是否存在子集关联
		int num = ecSkuMapper.querySkuRelation(map);
		int num1 = taskProductService.findCountBySku(id);
		if(num >0||num1>0) {
			return -1;
		} else {
			int count = this.deleteByPrimaryKey(id);
			ecSuppliersCommodityMapper.delEcSuppliersCommodityBySku(id);
			return count;
			
		}
	}
	
	public JSONObject getSkuNoCountByName(String name,Long id){
		JSONObject obj = new JSONObject();
		if(id!=null){
			EcSku cs = ecSkuMapper.findById(id);
			if(cs.getSku().equals(name)){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				Integer count = ecSkuMapper.findCountByName(name);
				if(count==0){
					obj.put("status", "y");
					obj.put("info", "");
				}else{
					obj.put("status", "n");
					obj.put("info", "该商品SKU码已经存在！");
				}	
			}
		}else{
			Integer count = ecSkuMapper.findCountByName(name);
			if(count==0){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				obj.put("status", "n");
				obj.put("info", "该商品SKU码已经存在！");
			}
		}
		return obj;
	}
	
	public JSONObject getSaleSkuNoCountByName(String name,Long id){
		JSONObject obj = new JSONObject();
		String[] nameAry = name.split(",");
		if(id!=null){
			EcSku cs = ecSkuMapper.findById(id);
			if(cs.getSaleSku().equals(name)){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				obj.put("status", "y");
				obj.put("info", "");
				for(int i = 0; i < nameAry.length; i++){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("name", nameAry[i]);
					params.put("id", id);
					Integer count = ecSkuMapper.findCountByNameSale(params);
					if(count > 0){
						obj.put("status", "n");
						obj.put("info", "该销售SKU已经存在！");
						break;
					}
				}
			}
		}else{
			for(int i = 0; i < nameAry.length; i++){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", nameAry[i]);
				params.put("id", 0);
				Integer count = ecSkuMapper.findCountByNameSale(params);
				if(count > 0){
					obj.put("status", "n");
					obj.put("info", "该销售SKU已经存在！");
					break;
				} else {
					obj.put("status", "y");
					obj.put("info", "");
				}
			}
		}
		return obj;
	}
	
	
	public JSONObject getByAjaxSkuNo(String skuNo){
		JSONObject obj = new JSONObject();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sku",skuNo);
		EcSku sku = ecSkuMapper.selectBySku(param);
		if(sku!=null){
		List<EcSuppliersCommodity> esc = ecSuppliersCommodityMapper.findEcSuppliersCommodityBySku(sku.getId());
		String supplier = JSONArray.toJSONString(esc);
		obj = (JSONObject)JSONObject.toJSON(sku);
		obj.put("isExist", true);
		obj.put("supplier", supplier);
		}else{
			obj.put("isExist", false);
		}
		return obj;
	}
	public JSONObject getByAjaxIds(String ids){
		String[] idds = ids.split(",");
		List<Long> list= new ArrayList<Long>();
		for(int i=0;i<idds.length;i++){
			list.add(Long.parseLong(idds[i]));
		}
		List<JSONObject> array = new ArrayList<JSONObject>();
		List<EcSku> skus =  ecSkuMapper.findSkuListByIds(list);
		for(int i=0;i<skus.size();i++){
			JSONObject obj = new JSONObject();
			List<EcSuppliersCommodity> esc = ecSuppliersCommodityMapper.findEcSuppliersCommodityBySku(skus.get(i).getId());
			String supplier = JSONArray.toJSONString(esc);
			obj = (JSONObject)JSONObject.toJSON(skus.get(i));
			obj.put("supplier", supplier);
			array.add(obj);
		}
		JSONObject obj = new JSONObject();
		if(array.size()>0){
			String ss = JSONArray.toJSONString(array);
			obj.put("isExist", true);
			obj.put("skus", ss);
		}else{
			obj.put("isExist", false);
			obj.put("skus", "");
		}
		return obj;
	}
	public Integer querySkuByCatalogue(Long cataLogueId){
		return ecSkuMapper.querySkuByCatalogue(cataLogueId);
	}
	
	public List<EcSku> findMainSku() {
		return ecSkuMapper.findMainSku();
	}
	
	public List<EcSku> findAllSku() {
		return ecSkuMapper.findAllSku();
	}
}

