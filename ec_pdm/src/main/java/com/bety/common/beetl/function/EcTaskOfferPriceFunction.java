package com.bety.common.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gyf.ec.model.EcTaskOfferPrice;
import com.gyf.ec.service.EcTaskOfferPriceService;

@Component
public class EcTaskOfferPriceFunction {
	@Resource
	private EcTaskOfferPriceService eoService;
	public List<EcTaskOfferPrice> queryByProductId(Long productId){
		return eoService.queryByProductId(productId);
	}
}
