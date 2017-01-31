

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.mapper.CustomFaqMapper;
import com.bety.web.model.CustomFaq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.service.EcSkuService;

/**
 * 
 * @author BetyChen
 */

@Service("customFaqService")
public class CustomFaqService extends ServiceMybatis<CustomFaq>{

	@Resource
	private CustomFaqMapper faqMapper;
	@Resource
	private CommoditySpuService commoditySpuService;
	@Resource
	private EcSkuService ecSkuService;
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int saveFaq(CustomFaq obj){
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
	public PageInfo<CustomFaq> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<CustomFaq> list = faqMapper.findPageInfo(params);
		return new PageInfo<CustomFaq>(list);
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
	public Integer findFaqCoustomCountByType(Long typeId){
		return faqMapper.findFaqCoustomCountByType(typeId);
	}
	public Integer findFaqCoustomCountBySpu(String goods_typ,String good_no){
		return faqMapper.findFaqCoustomCountBySpu(goods_typ, good_no);
	}
	
	public List<CustomFaq> findMainFaq(Map<String, Object> params) {
		return faqMapper.findMainFaq(params);
	}
}
