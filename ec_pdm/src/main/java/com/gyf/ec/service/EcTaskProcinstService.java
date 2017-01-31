package com.gyf.ec.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcTaskProcinstMapper;
import com.gyf.ec.model.EcTaskProcinst;

@Service("ecTaskProcinstService")
public class EcTaskProcinstService extends ServiceMybatis<EcTaskProcinst> {
	
	@Resource
	private EcTaskProcinstMapper ecTaskProcinstMapper;
	
	public Integer saveEcTaskProcinst(EcTaskProcinst ecTaskProcinst) {
		int count = this.insertSelective(ecTaskProcinst);
		return count;
	}
	
	public Integer updateEcTaskProcinst(Map<String, Object> param) {
		int count = ecTaskProcinstMapper.updateEcTaskProcinst(param);
		return count;
	}
}
