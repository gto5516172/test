package com.bety.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gyf.ec.model.EcSuppliersCommodity;
import com.gyf.ec.service.EcSuppliersCommodityService;
@Component
public class EcSuppliersCommodityFunction {
	@Resource
	private EcSuppliersCommodityService service;
	public Map<Long,EcSuppliersCommodity>  getAllSuppliersMap(){
		 List<EcSuppliersCommodity> list =  service.findEcSuppliersCommodityBySku(0l);
		 Map<Long,EcSuppliersCommodity> map = new HashMap<Long,EcSuppliersCommodity>();
		 for(int i=0;i<list.size();i++){
			 EcSuppliersCommodity s = list.get(i);
			 map.put(s.getId(),s);
		 }
		 return map;
	}
}
