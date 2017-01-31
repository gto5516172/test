package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcBom;

public interface EcBomMapper extends Mapper<EcBom> {
	
	public List<EcBom> findBomList(Map<String, Object> params);
	
	public EcBom findBom(Long id);
}
