package com.gohuinuo.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;
import com.gyf.ec.model.EcTaskType;
import com.gyf.ec.service.EcTaskTypeService;

@Component
public class EcTaskTypeFunction implements Function{

	@Resource
	private EcTaskTypeService ecTaskTypeService;
	
	@Override
	public Object call(Object[] params, Context ctx) {
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<EcTaskType> list = null;
		try {
			list = ecTaskTypeService.findAll();
			for(EcTaskType ecTaskType : list){
				map.put(ecTaskType.getId(), ecTaskType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public List<EcTaskType> getEcTaskTypeList() {
		List<EcTaskType> list = ecTaskTypeService.findAllEcTaskType();
		return list;
	}
	

}
