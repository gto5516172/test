package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcSku;

public interface EcSkuMapper extends Mapper<EcSku> {
	
	public List<EcSku> findSkuList(Map<String, Object> params);
	
	public EcSku selectByPrimaryKeyMy(Long id);
	
	public EcSku selectBySku(Map<String, Object> params);
	
	public EcSku findById(Long id);
	
	public Integer findCountByName(String name);
	
	public Integer findCountByNameSale(Map<String, Object> params);
	
	public List<EcSku> findSkuListByIds(List<Long> ids);
	
	public Integer querySkuRelation(Map<String, Object> params);
	
	public Integer querySkuByCatalogue(Long cataLogueId);
	
	public List<EcSku> findMainSku();
	
	public List<EcSku> findAllSku();
}
