package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcCatalogue;

public interface EcCatalogueMapper extends Mapper<EcCatalogue> {
	
	public List<EcCatalogue> findEcCatalogueList(Map<String, Object> params);
	
	public int deleteEcCatalogueByRootId(Long id);
	
	public int updateParentIds(EcCatalogue ecCatalogue);
	public EcCatalogue findOneByName(@Param("name") String name);
}
