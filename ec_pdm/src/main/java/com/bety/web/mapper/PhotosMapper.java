package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.Photos;
import com.github.abel533.mapper.Mapper;

public interface PhotosMapper extends Mapper<Photos>{
	public List<Photos> findPageInfo(Map<String, Object> params);
	public int deleteByFrom(Map<String, Object> params);
	public Photos queryOneByFrom(Map<String, Object> params);
}
