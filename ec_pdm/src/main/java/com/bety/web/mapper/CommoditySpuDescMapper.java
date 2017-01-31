package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.CommoditySpuDesc;
import com.github.abel533.mapper.Mapper;

public interface CommoditySpuDescMapper extends Mapper<CommoditySpuDesc>{
	public List<CommoditySpuDesc> findPageInfo(Map<String, Object> params);
}
