//Powered By if, Since 2014 - 2020

package com.gohuinuo.web.sys.mapper;


import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.gohuinuo.web.sys.model.SysMain;
import com.gohuinuo.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

public interface SysUserMapper extends Mapper<SysUser>{
	
	public List<SysUser> findPageInfo(Map<String, Object> params);
	
	public List<SysUser> findUsers(Map<String, Object> params);
	
	public Integer findCountByName(String name);
	
	public SysMain findMain(Map<String, Object> params);
	
}
