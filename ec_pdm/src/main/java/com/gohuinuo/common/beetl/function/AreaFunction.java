package com.gohuinuo.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import com.gohuinuo.web.sys.model.SysArea;
import com.gohuinuo.web.sys.service.SysAreaService;

@Component
public class AreaFunction implements Function{

	@Resource
	private SysAreaService sysAreaService;
	
	@Override
	public Object call(Object[] params, Context ctx) {
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<SysArea> list = null;
		try {
			list = sysAreaService.findAllArea(new SysArea());
			for(SysArea area : list){
				map.put(area.getId(), area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public List<SysArea> getAreaList() {
		List<SysArea> list = sysAreaService.findAllArea(new SysArea());
		return list;
	}
	
	public List<SysArea> getAreaList(String type) {
		SysArea sa = new SysArea();
		sa.setType(type);
		List<SysArea> list = sysAreaService.findAllArea(sa);
		return list;
	}
	
}
