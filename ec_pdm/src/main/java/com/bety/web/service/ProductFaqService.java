

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.mapper.ProductFaqMapper;
import com.bety.web.model.CustomFaq;
import com.bety.web.model.ProductFaq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.service.EcSkuService;

/**
 * 
 * @author BetyChen
 */

@Service("productFaqService")
public class ProductFaqService extends ServiceMybatis<ProductFaq>{

	@Resource
	private ProductFaqMapper faqMapper;
	@Resource
	private CommoditySpuService commoditySpuService;
	@Resource
	private EcSkuService ecSkuService;

	
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int saveFaq(ProductFaq obj){
		int count = 0;
		if(null == obj.getId()){
 			count = this.insertSelective(obj);
		}else{
			count = this.updateByPrimaryKeySelective(obj);
		}
	    return count;
	}
	
	/**
	 * 删除
	* @param userId
	* @return
	 */
	public int deleteFaq(Long userId){
		return this.updateDelFlagToDelStatusById(CustomFaq.class, userId);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<ProductFaq> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<ProductFaq> list = faqMapper.findPageInfo(params);
		return new PageInfo<ProductFaq>(list);
	}
	public JSONObject validateGoodNoByAjax(Integer type,String no){
		JSONObject obj = null;
		if(2==type){
			obj= commoditySpuService.getSpuNoCountByName(no, null);
			if("y".equals(obj.get("status"))){
				obj.put("status", "n");
				obj.put("info", "SPU码不存在！");
			}else{
				obj.put("status", "y");
				obj.put("info", "");
				
			}
			
		}else{
			obj = ecSkuService.getSkuNoCountByName(no, null);
			if("y".equals(obj.get("status"))){
				obj.put("status", "n");
				obj.put("info", "SKU码不存在！");
			}else{
				obj.put("status", "y");
				obj.put("info", "");
			}
		}
		
		return obj;
	}
	
	public Integer findFaqProductCountByType(Long typeId){
		return faqMapper.findFaqProductCountByType(typeId);
	}
	public Integer findFaqProductCountBySpu(String goods_typ,String good_no){
		return faqMapper.findFaqProductCountBySpu(goods_typ, good_no);
	}
}
