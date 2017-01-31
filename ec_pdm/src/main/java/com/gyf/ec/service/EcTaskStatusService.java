package com.gyf.ec.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcTaskStatusMapper;
import com.gyf.ec.model.EcTaskStatus;

@Service("ecTaskStatusService")
public class EcTaskStatusService extends ServiceMybatis<EcTaskStatus> {

	@Resource
	private EcTaskStatusMapper mapper;
	
}
