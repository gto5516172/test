package com.bety.web.mapper;

import java.util.List;
import java.util.Map;

import com.bety.web.model.Platform;
import com.github.abel533.mapper.Mapper;

public interface PlatformMapper extends Mapper<Platform>{
	public List<Platform> findPageInfo(Map<String, Object> params);
	public Integer findCountByName(String name);
}
