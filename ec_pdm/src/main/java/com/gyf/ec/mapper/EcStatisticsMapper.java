package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcStatistics;
import com.gyf.ec.model.EcStatisticsBySku;
import com.gyf.ec.model.EcStatisticsBySupp;
import com.gyf.ec.model.EcStatisticsUserOrType;

public interface EcStatisticsMapper extends Mapper<EcStatistics> {
	public List<EcStatistics> findEcStatistics(Map<String, Object> params);
	
	public List<EcStatisticsUserOrType> findEcStatisticsByUuser(Map<String, Object> params);
	
	public List<EcStatisticsUserOrType> findEcStatisticsByTaskType(Map<String, Object> params);
	
	public List<EcStatisticsUserOrType> findEcStatisticsBySuppliersCommodity(Map<String, Object> params);
	
	public List<EcStatisticsBySku> findEcStatisticsBySku0(Map<String, Object> params);
	
	public List<EcStatisticsBySku> findEcStatisticsBySku1(Map<String, Object> params);
	
	public List<EcStatisticsBySku> findEcStatisticsBySku2(Map<String, Object> params);
	
	public List<EcStatisticsBySku> findEcStatisticsBySku3(Map<String, Object> params);
	
	public List<EcStatisticsBySku> findEcStatisticsBySku4(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findEcStatisticsBySupp0(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findEcStatisticsBySupp1(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findEcStatisticsBySupp2(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findEcStatisticsBySupp3(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findEcStatisticsBySupp4(Map<String, Object> params);
	
	public List<EcStatisticsBySupp> findMainPrice(Map<String, Object> params);
}
