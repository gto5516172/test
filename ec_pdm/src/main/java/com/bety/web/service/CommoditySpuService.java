

package com.bety.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.bety.web.mapper.CommoditySpuMapper;
import com.bety.web.model.CommoditySpu;
import com.bety.web.model.CommoditySpuDesc;
import com.bety.web.model.Photos;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.model.EcCatalogue;
import com.gyf.ec.service.EcCatalogueService;

/**
 * 
 * @author BetyChen
 */

@Service("commoditySpuService")
public class CommoditySpuService extends ServiceMybatis<CommoditySpu>{

	@Resource
	private CommoditySpuMapper commoditySpuMapper;
	@Resource
	private CommoditySpuDescService commoditySpuDescService;
	@Resource
	private PhotosService photosService;
	@Resource
	private ProductFaqService productFaqService;
	@Resource
	private CustomFaqService coustomFaqService;
	@Resource
	private EcCatalogueService ecCatalogueService;
	/**
	 * 添加或更�
	* @param 
	* @return
	 */
	public int saveSpu(CommoditySpu obj){
		int count = 0;
		if(null == obj.getId()){
 			count = this.insertSelective(obj);
 			String descs = obj.getDescs();
 			if(descs!=null&&descs!=""){
				CommoditySpuDesc csd = new CommoditySpuDesc();
				try {
					csd.setDescr(URLEncoder.encode(descs,"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				csd.setSpuId(obj.getId());
				commoditySpuDescService.insertSelective(csd);
 			}
 			//存储基本图片
 			if(obj.getBasicImgs()!=null){
 				String ps[] = obj.getBasicImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("0");
 					photos.setImgFromId(obj.getId());
 					photosService.save(photos);
 				}
 				
 			}
 			//存储详细无背景图�
 			if(obj.getNoBackImgs()!=null){
 				String ps[] = obj.getNoBackImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("1");
 					photos.setImgFromId(obj.getId());
 					photosService.save(photos);
 				}
 				
 			}
 			//存储详细有背景图�
 			if(obj.getBackImgs()!=null){
 				String ps[] = obj.getBackImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("2");
 					photos.setImgFromId(obj.getId());
 					photos.setCreateDate(new Date());
 					photosService.save(photos);
 				}
 				
 			}
 			
		}else{
			count = this.updateByPrimaryKeySelective(obj);
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("imgFrom","0");
			param.put("imgFromId",obj.getId());
			//删除所有相关图�
			photosService.deleteByFrom(param);
			param.put("imgFrom","1");
			photosService.deleteByFrom(param);
			param.put("imgFrom","2");
			photosService.deleteByFrom(param);
			//存储基本图片
 			if(obj.getBasicImgs()!=null){
 				String ps[] = obj.getBasicImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("0");
 					photos.setImgFromId(obj.getId());
 					photosService.save(photos);
 				}
 				
 			}
 			//存储详细无背景图�
 			if(obj.getNoBackImgs()!=null){
 				String ps[] = obj.getNoBackImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("1");
 					photos.setImgFromId(obj.getId());
 					photosService.save(photos);
 				}
 				
 			}
 			//存储详细有背景图�
 			if(obj.getBackImgs()!=null){
 				String ps[] = obj.getBackImgs();
 				for(int i=0;i<ps.length;i++){
 					if(ps[i]==null||"".equals(ps[i]))
 						continue;
 					Photos photos = new Photos();
 					JSONObject j = JSONObject.parseObject(ps[i]);
 					photos.setImgUrl(j.getString("url"));
 					photos.setOriginal(j.getString("original"));
 					photos.setImgFrom("2");
 					photos.setImgFromId(obj.getId());
 					photos.setCreateDate(new Date());
 					photosService.save(photos);
 				}
 				
 			}
		}
	    return count;
	}
	/**
	 * 保存导入spu
	* @param 
	* @return
	 */
	public int saveImportSpu(List<CommoditySpu> list){
		int count = 0;
		for(int i=0;i<list.size();i++){
			CommoditySpu obj = list.get(i);
			if(StringUtils.isEmpty(obj.getTypeTmp())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getName())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getSpuNo())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getPrice())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getBrand())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getManufactorNo())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getUnits())){
				continue;
			}
			if(StringUtils.isEmpty(obj.getOriginPlace())){
				continue;
			}if(StringUtils.isEmpty(obj.getDescr())){
				continue;
			}
			EcCatalogue ca = ecCatalogueService.findOneByName(obj.getTypeTmp());
			if(ca==null){
				continue;
			}
			Integer count1 = commoditySpuMapper.findCountByName(obj.getName());
			if(count1>0){
				continue;
			}
			obj.setType(ca.getId());
			if(!StringUtils.isEmpty(obj.getPlfTmp())){
				boolean flag = true;
				String args[] =obj.getPlfTmp().split(",");
				String tmp = "1,2,3,4";
				for(int j=0;j<args.length;j++){
					if(tmp.indexOf(args[j])<0){
						flag=false;
						break;
					}
				}
				obj.setPf(args);
				if(!flag){
					continue;
				}
			}
 			count = this.insertSelective(obj);
 			String descs = obj.getDescs();
 			if(descs!=null&&descs!=""){
				CommoditySpuDesc csd = new CommoditySpuDesc();
				try {
					csd.setDescr(URLEncoder.encode(descs,"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				csd.setSpuId(obj.getId());
				commoditySpuDescService.insertSelective(csd);
 			}
		}
 			
	
	    return count;
	}
	
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<CommoditySpu> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<CommoditySpu> list = commoditySpuMapper.findPageInfo(params);
		return new PageInfo<CommoditySpu>(list);
	}
	public CommoditySpu findById(Long id){
		return commoditySpuMapper.findById(id);
	}
	
	public List<CommoditySpu> findAllSpu() {
		return commoditySpuMapper.findAllSpu();
	}
	public JSONObject getSpuNoCountByName(String name,Long id){
		JSONObject obj = new JSONObject();
		if(id!=null){
			CommoditySpu cs = commoditySpuMapper.findById(id);
			if(cs.getSpuNo().equals(name)){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				Integer count = commoditySpuMapper.findCountByName(name);
				if(count==0){
					obj.put("status", "y");
					obj.put("info", "");
				}else{
					obj.put("status", "n");
					obj.put("info", "该商品SPU码已经存在！");
				}	
			}
		}else{
			Integer count = commoditySpuMapper.findCountByName(name);
			if(count==0){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				obj.put("status", "n");
				obj.put("info", "该商品SPU码已经存在！");
			}
		}
		return obj;
	}
	public Integer deleteSpuById(Long id){
		CommoditySpu spu = findById(id);
		int productFaqCount = productFaqService.findFaqProductCountBySpu("2", spu.getSpuNo());
		int coustomFaqCount = coustomFaqService.findFaqCoustomCountBySpu("2", spu.getSpuNo());
		if(productFaqCount>0||coustomFaqCount==0){
			return -1;
		}
		return deleteByPrimaryKey(id);
	}
	public Integer findCountByType(Long type){
		return commoditySpuMapper.findCountByType(type);
	}
	public Integer findCountByName(String sku) {
		return commoditySpuMapper.findCountByName(sku);
	}
}
