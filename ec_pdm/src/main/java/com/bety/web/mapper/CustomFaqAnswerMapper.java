package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.CustomFaqAnswer;
import com.bety.web.model.ProductFaqAnswer;
import com.github.abel533.mapper.Mapper;

public interface CustomFaqAnswerMapper extends Mapper<CustomFaqAnswer>{
	public List<CustomFaqAnswer> findPageInfo(Map<String, Object> params);
}
