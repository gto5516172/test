package com.gyf.ec.mapper;

import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcTaskProcinst;

public interface EcTaskProcinstMapper extends Mapper<EcTaskProcinst> {
	public Integer updateEcTaskProcinst(Map<String, Object> param);
}
