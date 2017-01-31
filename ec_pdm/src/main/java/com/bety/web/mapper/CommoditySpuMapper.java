package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.CommoditySpu;
import com.github.abel533.mapper.Mapper;

public interface CommoditySpuMapper extends Mapper<CommoditySpu>{
	public List<CommoditySpu> findPageInfo(Map<String, Object> params);
	public CommoditySpu findById(Long id);
	public Integer findCountByName(String name);
	public List<CommoditySpu> findAllSpu();
	public Integer findCountByType(Long type);
}
