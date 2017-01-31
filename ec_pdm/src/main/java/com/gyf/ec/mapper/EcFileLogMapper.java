package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcFileLog;

public interface EcFileLogMapper extends Mapper<EcFileLog> {

	public List<EcFileLog> findFileLogList(Map<String, Object> params);
	
}
