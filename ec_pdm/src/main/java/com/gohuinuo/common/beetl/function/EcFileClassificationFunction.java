package com.gohuinuo.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;
import com.gyf.ec.model.EcFileClassification;
import com.gyf.ec.service.EcFileClassificationService;

@Component
public class EcFileClassificationFunction implements Function{

	@Resource
	private EcFileClassificationService ecFileClassificationService;
	
	@Override
	public Object call(Object[] params, Context ctx) {
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<EcFileClassification> list = null;
		try {
			list = ecFileClassificationService.findAllEcFileClassification();
			for(EcFileClassification ecFileClassification : list){
				map.put(ecFileClassification.getId(), ecFileClassification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public List<EcFileClassification> getEcFileClassificationList() {
		List<EcFileClassification> list = ecFileClassificationService.findAllEcFileClassification();
		return list;
	}
	

}
