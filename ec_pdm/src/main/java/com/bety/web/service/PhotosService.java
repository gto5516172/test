

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bety.web.mapper.PhotosMapper;
import com.bety.web.model.Photos;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

/**
 * 
 * @author BetyChen
 */

@Service("photosService")
public class PhotosService extends ServiceMybatis<Photos>{

	@Resource
	private PhotosMapper photosMapper;
	

	
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int saveShop(Photos obj){
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
	public int deleteShop(Long userId){
		return this.updateDelFlagToDelStatusById(Photos.class, userId);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<Photos> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Photos> list = photosMapper.findPageInfo(params);
		return new PageInfo<Photos>(list);
	}
	public List<Photos> findPageInfo1(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Photos> list = photosMapper.findPageInfo(params);
		return list;
	}
	
	public List<Photos> findAll() {
		List<Photos> list = photosMapper.select(null);
		return list;
	}
	
	public int deleteByFrom(Map<String, Object> params){
		return photosMapper.deleteByFrom( params);
	}
	public Photos queryOneByFrom(Map<String, Object> params){
		return photosMapper.queryOneByFrom(params);
	}
}
