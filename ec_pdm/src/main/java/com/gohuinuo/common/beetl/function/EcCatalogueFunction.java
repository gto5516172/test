package com.gohuinuo.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;
import com.gyf.ec.model.EcCatalogue;
import com.gyf.ec.service.EcCatalogueService;

@Component
public class EcCatalogueFunction implements Function{

	@Resource
	private EcCatalogueService ecCatalogueService;
	
	@Override
	public Object call(Object[] params, Context ctx) {
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<EcCatalogue> list = null;
		try {
			list = ecCatalogueService.findAllEcCatalogue();
			for(EcCatalogue ecCatalogue : list){
				map.put(ecCatalogue.getId(), ecCatalogue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public List<EcCatalogue> getecCatalogueList() {
		List<EcCatalogue> list = ecCatalogueService.findAllEcCatalogue();
		return list;
	}
	

}
