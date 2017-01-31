package com.gohuinuo.common.beetl.function;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.service.SysUserService;

@Component
public class SysUserFunction {
	@Resource
	private SysUserService service;
	public  SysUser getUserById(Long id){
		return service.selectByPrimaryKey(id);
	}
	public boolean isDataPersion(Long id,String model){
		SysUser user = service.selectByPrimaryKey(id);
		if(user!=null){
			String dataPermison = user.getDataPermission();
			if("".equals(dataPermison)||dataPermison==null){
				return false;
			}else{
				String[] dataPermisons = dataPermison.split(",");
				for(int i=0;i<dataPermisons.length;i++){
					if(dataPermisons[i].equals(model)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
