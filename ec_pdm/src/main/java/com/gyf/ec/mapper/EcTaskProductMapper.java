package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.Shop;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcTaskProduct;

public interface EcTaskProductMapper extends Mapper<EcTaskProduct>  {
	public List<EcTaskProduct> findPageInfo(Map<String, Object> params);
	public Integer findCountByShop(Long id);
	public Integer findCountBySku(Long id);
}
