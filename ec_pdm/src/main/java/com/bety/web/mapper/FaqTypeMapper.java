package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.FaqType;
import com.github.abel533.mapper.Mapper;

public interface FaqTypeMapper extends Mapper<FaqType>{
	public List<FaqType> findPageInfo(Map<String, Object> params);
}
