package com.gyf.ec.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcSuppliersCommodityMapper;
import com.gyf.ec.model.EcSuppliersComPriceLog;
import com.gyf.ec.model.EcSuppliersCommodity;


@Service("ecSuppliersCommodityService")
public class EcSuppliersCommodityService extends ServiceMybatis<EcSuppliersCommodity> {

	@Resource
	private EcSuppliersCommodityMapper ecSuppliersCommodityMapper;
	
	@Resource
	private EcSuppliersComPriceLogService ecSuppliersComPriceLogService;
	
	/**
	 * 根据sku来删除
	 * @param id
	 * @return
	 */
	public int delEcSuppliersCommodityBySku(long id) {
		return ecSuppliersCommodityMapper.delEcSuppliersCommodityBySku(id);
	}
	
	
	/**
	 * 根据Sku查询关联商品
	 * @param id
	 * @return
	 */
	public List<EcSuppliersCommodity> findEcSuppliersCommodityBySku(long id) {
		return ecSuppliersCommodityMapper.findEcSuppliersCommodityBySku(id);
	}
	
	/**
	 * 根据供应商查询关联商品
	 * @param params
	 * @return
	 */
	public List<EcSuppliersCommodity> findPageInfoSku(Map<String, Object> params) {
		return ecSuppliersCommodityMapper.findPageInfoSku(params);
	}
	public List<EcSuppliersCommodity> findAll(){
		return mapper.select(null);
	}
	
	public EcSuppliersCommodity findscDetail(Map<String, Object> params) {
		return ecSuppliersCommodityMapper.findscDetail(params);
	}
	
	public int updatePurchasePrice(EcSuppliersCommodity ecSuppliersCommodity) {
		int num = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ecSuppliersCommodity.getId());
		EcSuppliersCommodity ecs = ecSuppliersCommodityMapper.findscDetail(map);
		ecs.setPurchasePrice(ecSuppliersCommodity.getPurchasePrice());
		ecs.setUpdateBy(SysUserUtils.getCacheLoginUser().getName());
		num = ecSuppliersCommodityMapper.updatePurchasePrice(ecs);
		// 保存报价记录
		EcSuppliersComPriceLog ecSuppliersComPriceLog = new EcSuppliersComPriceLog();
		ecSuppliersComPriceLog.setSuppcomId(ecs.getId());
		ecSuppliersComPriceLog.setOldPurchasePrice(ecSuppliersCommodity.getPurchasePrice());
		ecSuppliersComPriceLog.setOldPurchasePriceTime(new Date());
		ecSuppliersComPriceLog.setSkuId(ecs.getCommodityId());
		ecSuppliersComPriceLogService.save(ecSuppliersComPriceLog);
		return num;
	}
}
