package com.bety.common.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bety.web.model.Platform;
import com.bety.web.service.PlatformService;
import com.gohuinuo.web.sys.model.SysDict;

/**
 * 
 * 
 */
@Component
public class PlatformFunction{
	
	@Resource
	private PlatformService platformService;
	

	/**
	 * 查找所有的平台
	* @param 
	 */
	public List<Platform> getAllPlatform(){
		return (List<Platform>) platformService.findAll();
	}
	
	public Platform getPlatformById(Long id){
		return platformService.selectByPrimaryKey(id);
	}
	
}
