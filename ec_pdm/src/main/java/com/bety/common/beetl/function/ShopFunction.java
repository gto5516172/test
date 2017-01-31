package com.bety.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bety.web.model.Shop;
import com.bety.web.service.ShopService;

@Component
public class ShopFunction {
	@Resource
	private ShopService service;
	public List<Shop> getAll(){
		return service.findAll();
	}
	public Map<Long,Shop> getAllMap(){
		 List<Shop> list =  service.findAll();
		 Map<Long,Shop> map = new HashMap<Long,Shop>();
		 for(int i=0;i<list.size();i++){
			 Shop s = list.get(i);
			 map.put(s.getId(),s);
		 }
		 return map;
	}
}
