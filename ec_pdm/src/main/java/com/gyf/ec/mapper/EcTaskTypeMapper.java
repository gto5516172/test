package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcTaskType;

public interface EcTaskTypeMapper extends Mapper<EcTaskType> {
	
	public List<EcTaskType> findTaskTypeList(Map<String, Object> params);
	
	public EcTaskType findTaskType(Long id);
}
