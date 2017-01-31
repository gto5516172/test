package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcSkuCostPriceLog;

public interface EcSkuCostPriceLogMapper extends Mapper<EcSkuCostPriceLog> {
	
	public List<EcSkuCostPriceLog> findEcSkuCostPriceLog(Map<String, Object> params);
	
}
