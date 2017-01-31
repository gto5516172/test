package com.bety.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bety.web.model.CommoditySpuDesc;
import com.bety.web.service.CommoditySpuDescService;

/**
 * 
 * 
 */
@Component
public class CommoditySpuDescFunction{
	
	@Resource
	private CommoditySpuDescService commoditySpuDescService;
	

	/**
	 * 
	* @param 
	 */
	public List<CommoditySpuDesc> getDescBySpu(Long spuId,Integer pageNum){
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("spuId", spuId);
		map.put("pageNum",pageNum);
		map.put("pageSize", 10000);
		return commoditySpuDescService.findPageInfo1(map);
	}
	
	
}
