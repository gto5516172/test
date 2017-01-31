package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.ProductFaqAnswer;
import com.github.abel533.mapper.Mapper;

public interface ProductFaqAnswerMapper extends Mapper<ProductFaqAnswer>{
	public List<ProductFaqAnswer> findPageInfo(Map<String, Object> params);
}
