package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcFileClassification;

public interface EcFileClassificationMapper extends Mapper<EcFileClassification> {

	public List<EcFileClassification> findFileClassificationList(Map<String, Object> params);
	
	public int deleteEcFileClassificationByRootId(Long id);
	
	public int updateParentIds(EcFileClassification ecFileClassification);
	
}
