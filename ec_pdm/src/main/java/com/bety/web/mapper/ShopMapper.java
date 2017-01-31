package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.Shop;
import com.github.abel533.mapper.Mapper;

public interface ShopMapper extends Mapper<Shop>{
	public List<Shop> findPageInfo(Map<String, Object> params);
	public Integer findCountBySitId(Long siteId);
}
