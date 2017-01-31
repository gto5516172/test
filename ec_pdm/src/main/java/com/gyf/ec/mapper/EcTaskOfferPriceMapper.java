package com.gyf.ec.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.gyf.ec.model.EcTaskOfferPrice;

public interface EcTaskOfferPriceMapper extends Mapper<EcTaskOfferPrice>  {
	public List<EcTaskOfferPrice> queryByProductId(Long productId);
}
