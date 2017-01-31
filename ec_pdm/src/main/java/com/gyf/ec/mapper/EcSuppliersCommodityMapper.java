package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcSuppliersCommodity;

public interface EcSuppliersCommodityMapper extends Mapper<EcSuppliersCommodity> {

	public int saveEcSuppliersCommodity(List<EcSuppliersCommodity> escList);
	
	public int delEcSuppliersCommodityBySku(long id);
	
	public List<EcSuppliersCommodity> findEcSuppliersCommodityBySku(long id);
	
	public List<EcSuppliersCommodity> findPageInfoSku(Map<String, Object> params);
	
	public EcSuppliersCommodity findscDetail(Map<String, Object> params);
	
	public int updatePurchasePrice(EcSuppliersCommodity ecSuppliersCommodity);
	
}
