package com.gyf.ec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcFileUrlMapper;
import com.gyf.ec.model.EcFile;
import com.gyf.ec.model.EcFileUrl;

@Service("ecFileUrlService")
public class EcFileUrlService extends ServiceMybatis<EcFileUrl> {

	@Resource
	private EcFileUrlMapper ecFileUrlMapper;
	
	
	public int saveEcFileUrl(EcFileUrl ecFileUrl) {
		return this.insertSelective(ecFileUrl);
	}
	
	public List<EcFileUrl> findFileUrlList(Map<String, Object> params) {
		return ecFileUrlMapper.findFileUrlList(params);
	}
	
	public List<EcFileUrl> findFileUrlListTwo(Map<String, Object> params) {
		return ecFileUrlMapper.findFileUrlListTwo(params);
	}
	
	/**
	 * 根据id删除自身
	* @param id
	* @return
	 */
	public int deleteEcFileUrlByRootId(Long id){
		int num = -1;
		num = ecFileUrlMapper.deleteEcFileUrlByRootId(id);
	
		return num;
	}
	
	public List<EcFileUrl> findFileUrlMain() {
		return ecFileUrlMapper.findFileUrlMain();
	}
}
