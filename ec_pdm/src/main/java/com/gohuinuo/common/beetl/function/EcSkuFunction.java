package com.gohuinuo.common.beetl.function;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.service.EcSkuService;

@Component
public class EcSkuFunction {
	@Resource
	private EcSkuService servier;
	
	public List<EcSku> getAllSkuList(){
		return servier.findAllSku();
	}
}
