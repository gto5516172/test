package com.gyf.ec.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcSkuCostPriceLogMapper;
import com.gyf.ec.model.EcSkuCostPriceLog;

@Service("ecSkuCostPriceLogService")
public class EcSkuCostPriceLogService extends ServiceMybatis<EcSkuCostPriceLog> {
	
	@Resource
	private EcSkuCostPriceLogMapper mapper;
	
	public Integer saveEcSkuCostPriceLog(EcSkuCostPriceLog ecSkuCostPriceLog) {
		return this.insertSelective(ecSkuCostPriceLog);
	}

}
