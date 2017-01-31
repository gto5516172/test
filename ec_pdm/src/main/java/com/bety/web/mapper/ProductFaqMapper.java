package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.CustomFaq;
import com.bety.web.model.ProductFaq;
import com.github.abel533.mapper.Mapper;

public interface ProductFaqMapper extends Mapper<ProductFaq>{
	public List<ProductFaq> findPageInfo(Map<String, Object> params);
	public Integer findFaqProductCountByType(Long typeId);
	public Integer findFaqProductCountBySpu(String goods_typ,String good_no);
}
