package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcFileUrl;

public interface EcFileUrlMapper extends Mapper<EcFileUrl> {

	public List<EcFileUrl> findFileUrlList(Map<String, Object> params);
	
	public List<EcFileUrl> findFileUrlListTwo(Map<String, Object> params);
	
	public int deleteEcFileUrlByRootId(Long id);
	
	public List<EcFileUrl> findFileUrlMain();
}
