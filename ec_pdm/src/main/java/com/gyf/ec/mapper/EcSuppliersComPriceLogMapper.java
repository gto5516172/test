package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcSuppliersComPriceLog;

public interface EcSuppliersComPriceLogMapper extends Mapper<EcSuppliersComPriceLog> {

	public List<EcSuppliersComPriceLog> findEcSuppliersComPriceLog(Map<String, Object> params);
	
}
