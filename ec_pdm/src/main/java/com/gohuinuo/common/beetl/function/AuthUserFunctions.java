package com.gohuinuo.common.beetl.function;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gohuinuo.common.beetl.utils.BeetlUtils;
import com.gohuinuo.common.constant.Constant;
import com.gohuinuo.web.sys.model.SysResource;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.service.SysUserService;
import com.gohuinuo.web.sys.utils.SysUserUtils;

@Component
public class AuthUserFunctions {
	@Resource
	private SysUserService service;
	/**
	 * 判断用户是否具有指定权限
	 */
	public boolean hasPermission(String url) {
		Map<String, SysResource> allRes = BeetlUtils
				.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
		SysResource sysResource = allRes.get(url);
		if (sysResource == null
				|| Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
			return true;
		}

		Map<String, SysResource> userRes = SysUserUtils.getUserResources();
		if (userRes.containsKey(url)) return true;
		return false;
	}
	
	/**
	 * 登录用户
	* @return
	 */
	public SysUser getLoginUser(){
		return SysUserUtils.getCacheLoginUser();
	}
	
	/**
	 * 是否为超级管理员
	* @return
	 */
	public boolean isSuper(){
		return getLoginUser().getUserType().equals(Constant.SUPER_ADMIN)?true:false;
	}
	
	/**
	 * 是否持有所有数据(数据范围，可认为是总管理)
	 */
	public boolean hasAllDataScope(){
		return SysUserUtils.getUserDataScope().contains(Constant.DATA_SCOPE_ALL);
	}
	/***
	 * 验证数据权限
	 * @return
	 */
	public boolean hasData(Long userId,int model){
		boolean flag = false;
		SysUser su = service.findUserById(userId);
		String permission = su.getDataPermission();
		if(permission!=null&&!permission.equals("")){
			String[] models = permission.split(",");
			for(int i=0;i<models.length;i++){
				int m = Integer.parseInt(models[i]);
				if(m==model){
					flag= true;
				}
			}
		}
		return flag;
	}
}
