package com.gohuinuo.common.beetl.function;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.bety.web.model.CommoditySpu;
import com.bety.web.service.CommoditySpuService;

@Component
public class EcSpuFunction {
	@Resource
	private CommoditySpuService servier;
	
	public List<CommoditySpu> getAllSpuList(){
		return servier.findAllSpu();
	}
}
