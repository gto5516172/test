//Powered By if, Since 2014 - 2020

package com.gohuinuo.web.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.common.constant.Constant;
import com.gohuinuo.common.utils.CacheUtils;
import com.gohuinuo.common.utils.PasswordEncoder;
import com.gohuinuo.web.sys.mapper.SysOfficeMapper;
import com.gohuinuo.web.sys.mapper.SysRoleMapper;
import com.gohuinuo.web.sys.mapper.SysUserMapper;
import com.gohuinuo.web.sys.model.SysMain;
import com.gohuinuo.web.sys.model.SysOffice;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.utils.SysUserUtils;

/**
 * 
 * @author 
 */
@Service("sysUserService")
public class SysUserService extends ServiceMybatis<SysUser>{

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Resource
	private SysOfficeMapper sysOfficeMapper;
	
	@Resource
	private IdentityService identityService;
	
	
	/**
	 * 添加或更新用户
	* @param sysUser
	* @return
	 */
	public int saveSysUser(SysUser sysUser){
		int count = 0;
		SysOffice sysOffice = sysOfficeMapper.findOfficeCompanyIdByDepId(sysUser.getOfficeId());
		Long companyId = sysUser.getOfficeId();
		if(sysOffice != null){
			companyId = sysOffice.getId();
		}
		sysUser.setCompanyId(companyId);
		if(StringUtils.isNotBlank(sysUser.getPassword())){
			String encryptPwd = PasswordEncoder.encrypt(sysUser.getPassword(), sysUser.getUsername());
			sysUser.setPassword(encryptPwd);
		}else{
			sysUser.remove("password");
		}
		if(null == sysUser.getId()){
			count = this.insertSelective(sysUser);
			//同步activiti用户表 
			List<org.activiti.engine.identity.User> activitiUsers = identityService.createUserQuery().userId(sysUser.getId() + "").list();
			if (activitiUsers.size() == 1) {
				//更新activiti用户
				org.activiti.engine.identity.User activitiUser = activitiUsers.get(0);
				activitiUser.setFirstName(sysUser.getUsername());
				activitiUser.setLastName(sysUser.getName());
				activitiUser.setPassword(sysUser.getPassword());
				activitiUser.setEmail(sysUser.getEmail());
				identityService.saveUser(activitiUser);
			} else {
				//新增activiti用户
				org.activiti.engine.identity.User newUser = identityService.newUser(sysUser.getId()  + "");
				newUser.setFirstName(sysUser.getUsername());
				newUser.setLastName(sysUser.getName());
				newUser.setPassword(sysUser.getPassword());
				newUser.setEmail(sysUser.getEmail());
				identityService.saveUser(newUser);
			}
		}else{
			sysRoleMapper.deleteUserRoleByUserId(sysUser.getId());
			count = this.updateByPrimaryKeySelective(sysUser);
			//清除缓存
			SysUserUtils.clearAllCachedAuthorizationInfo(Arrays.asList(sysUser.getId()));
			if(CacheUtils.isCacheByKey(Constant.CACHE_SYS_USER, sysUser.getId().toString())){
				String userType = this.selectByPrimaryKey(sysUser.getId()).getUserType();
				sysUser.setUserType(userType);
			
				SysUserUtils.cacheLoginUser(sysUser);
			}
		}
		if(sysUser.getRoleIds()!=null) sysRoleMapper.insertUserRoleByUserId(sysUser);
		return count;
	}
	
	/**
	 * 删除用户
	* @param userId
	* @return
	 */
	public int deleteUser(Long userId){
		sysRoleMapper.deleteUserRoleByUserId(userId);
		SysUserUtils.clearAllCachedAuthorizationInfo(Arrays.asList(userId));
		SysUserUtils.clearCacheUser(userId);
		return this.updateDelFlagToDelStatusById(SysUser.class, userId);
	}
	
	/**
	 * 用户列表
	* @param params
	* @return
	 */
	public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
		params.put(Constant.CACHE_USER_DATASCOPE, SysUserUtils.dataScopeFilterString("so", null));
		params.put("userType", SysUserUtils.getCacheLoginUser().getUserType());
		PageHelper.startPage(params);
		List<SysUser> list = sysUserMapper.findPageInfo(params);
		return new PageInfo<SysUser>(list);
	}
	
	/**
	 * 验证用户
	* @param username 用户名
	* @param password 密码
	* @return user
	 */
	public SysUser checkUser(String username,String password){
		SysUser sysUser = new SysUser();
		String secPwd = PasswordEncoder.encrypt(password, username);
		sysUser.setUsername(username);
		sysUser.setPassword(secPwd);
		List<SysUser> users = this.select(sysUser);
		if(users != null && users.size() == 1 && users.get(0) != null) {
			return users.get(0);
		}
		return null;
	}
	
	/**
	 * 用户模糊查询(用户名/邮箱)
	 * @param findTxt
	 * @return
	 */
	public List<SysUser> findUsers(String findTxt) {
		List<SysUser> users = new ArrayList<SysUser>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("findTxt", findTxt);
		users = sysUserMapper.findUsers(map);
		return users;
	}
	public JSONObject getUserNameCountByName(String name,Long id){
		JSONObject obj = new JSONObject();
		if(id!=null){
			SysUser cs = sysUserMapper.selectByPrimaryKey(id);
			if(cs.getUsername().equals(name)){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				Integer count = sysUserMapper.findCountByName(name);
				if(count==0){
					obj.put("status", "y");
					obj.put("info", "");
				}else{
					obj.put("status", "n");
					obj.put("info", "该登陆名已经存在！");
				}	
			}
		}else{
			Integer count = sysUserMapper.findCountByName(name);
			if(count==0){
				obj.put("status", "y");
				obj.put("info", "");
			}else{
				obj.put("status", "n");
				obj.put("info", "该登录名已经存在！");
			}
		}
		return obj;
	}
	public SysUser findUserById(Long userId){
		return sysUserMapper.selectByPrimaryKey(userId);
	}
	
	public SysMain findMain(Map<String, Object> params) {
		return sysUserMapper.findMain(params);
	}
}
