package com.gyf.ec.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcTaskProductMapper;
import com.gyf.ec.model.EcTaskProduct;

@Service("ecTaskProductService")
public class EcTaskProductService extends ServiceMybatis<EcTaskProduct> {

	@Resource
	private EcTaskProductMapper mapper;
	@Resource
	private EcTaskOfferPriceService eoService;
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<EcTaskProduct> findPageInfo(Map<String, Object> params) {
		params.put("taskUser", SysUserUtils.getCacheLoginUser().getId());
		PageHelper.startPage(params);
		List<EcTaskProduct> list = mapper.findPageInfo(params);
		return new PageInfo<EcTaskProduct>(list);
	}
	public Integer findCountByShop(Long shopid){
		return mapper.findCountByShop(shopid);
	}
	public Integer findCountBySku(Long skuId){
		return mapper.findCountBySku(skuId);
	}
}

