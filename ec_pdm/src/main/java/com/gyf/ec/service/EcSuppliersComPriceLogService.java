package com.gyf.ec.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcSuppliersComPriceLogMapper;
import com.gyf.ec.model.EcSuppliersComPriceLog;

@Service("ecSuppliersComPriceLogService")
public class EcSuppliersComPriceLogService extends ServiceMybatis<EcSuppliersComPriceLog> {
	
	@Resource
	private EcSuppliersComPriceLogMapper mapper;

	public int save(EcSuppliersComPriceLog ecSuppliersComPriceLog) {
		return this.insertSelective(ecSuppliersComPriceLog);
	}
	
}
