

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.mapper.PlatformMapper;
import com.bety.web.model.Platform;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

/**
 * 
 * @author BetyChen
 */

@Service("platformService")
public class PlatformService extends ServiceMybatis<Platform>{

	@Resource
	private PlatformMapper platformMapper;
	

	
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int savePlatform(Platform obj){
		int count = 0;
		if(null == obj.getId()){
			count = this.insertSelective(obj);
		}else{
			count = this.updateByPrimaryKeySelective(obj);
		}
	    return count;
	}
	
	/**
	 * 删除
	* @param userId
	* @return
	 */
	public int deletePlatform(Long userId){
		return this.updateDelFlagToDelStatusById(Platform.class, userId);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<Platform> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Platform> list = platformMapper.findPageInfo(params);
		return new PageInfo<Platform>(list);
	}
	
	public List<Platform> findAll() {
		List<Platform> list = platformMapper.select(null);
		return list;
	}
	public JSONObject getPaltformCountByName(String name){
		Integer count = platformMapper.findCountByName(name);;
		JSONObject obj = new JSONObject();
		if(count==0){
			obj.put("status", "y");
			obj.put("info", "");
		}else{
			obj.put("status", "n");
			obj.put("info", "该平台已经存在！");
		}
		return obj;
	}
}
