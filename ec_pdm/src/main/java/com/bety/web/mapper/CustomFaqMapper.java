package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.CustomFaq;
import com.github.abel533.mapper.Mapper;

public interface CustomFaqMapper extends Mapper<CustomFaq>{
	public List<CustomFaq> findPageInfo(Map<String, Object> params);
	public Integer findFaqCoustomCountByType(Long typeId);
	public Integer findFaqCoustomCountBySpu(String goods_typ,String good_no);
	public List<CustomFaq> findMainFaq(Map<String, Object> params);
}
