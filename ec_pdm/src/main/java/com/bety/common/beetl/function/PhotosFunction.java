package com.bety.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bety.web.model.Photos;
import com.bety.web.service.PhotosService;

/**
 * 
 * 
 */
@Component
public class PhotosFunction{
	
	@Resource
	private PhotosService pPhotoService;
	

	/**
	 * 
	* @param 
	 */
	public List<Photos> getPhotosByFrom(String from,Long imgFromId,Integer pageNum){
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("imgFrom", from);
		map.put("imgFromId", imgFromId);
		map.put("pageNum",pageNum);
		map.put("pageSize", 10000000);
		return pPhotoService.findPageInfo1(map);
	}
	public Photos getPhotosOneByFrom(String from,Long imgFromId){
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("imgFrom", from);
		map.put("imgFromId", imgFromId);
		return pPhotoService.queryOneByFrom(map);
	}
}
