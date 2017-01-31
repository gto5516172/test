package com.bety.common.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gyf.ec.model.EcTaskType;
import com.gyf.ec.service.EcTaskTypeService;

@Component
public class TaskTypeFunction {
	
	@Resource
	private EcTaskTypeService service;
	public List<EcTaskType> getAllTaskType(){
		return service.findAll();
	}
	public EcTaskType getTaskTypeById(Long id){
		return service.selectByPrimaryKey(id);
	}
	
}

