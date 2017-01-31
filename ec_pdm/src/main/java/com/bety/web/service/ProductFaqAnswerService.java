

package com.bety.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.mapper.ProductFaqAnswerMapper;
import com.bety.web.model.ProductFaqAnswer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

/**
 * 
 * @author BetyChen
 */

@Service("productFaqAnswerService")
public class ProductFaqAnswerService extends ServiceMybatis<ProductFaqAnswer>{

	@Resource
	private ProductFaqAnswerMapper mapper;
	

	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<ProductFaqAnswer> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<ProductFaqAnswer> list = mapper.findPageInfo(params);
		for(int i=0;i<list.size();i++){
			ProductFaqAnswer fa= list.get(i);
			fa.setTimeDistance(getDateDistance(new Date(),fa.getCreateDate()));
		}
		return new PageInfo<ProductFaqAnswer>(list);
	}
	private String getDateDistance(Date now,Date date){
		 StringBuffer sb = new StringBuffer();
		try {
		   long l=now.getTime()-date.getTime();
		   long day=l/(24*60*60*1000);
		   long hour=(l/(60*60*1000)-day*24);
		   long min=((l/(60*1000))-day*24*60-hour*60);
		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		   
		   if(day > 0)
			   sb.append(day+"天");
		   if(hour > 0 )
			   sb.append(hour+"小时");
		   if(min > 0 )
			   sb.append(min+"分");
		   sb.append(s+"秒前");
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	public JSONObject saveAnswer(ProductFaqAnswer answer) {
		Integer count = super.save(answer);
		JSONObject obj = new JSONObject();
		if(count==0){
			obj.put("status", "y");
			obj.put("info", "");
		}else{
			obj.put("status", "n");
			obj.put("info", "保存失败，请重试！");
		}
		return obj;
	
	}
	public JSONObject delete(Long id) {
		Integer count = super.deleteByPrimaryKey(id);
		JSONObject obj = new JSONObject();
		if(count==0){
			obj.put("status", "y");
			obj.put("info", "");
		}else{
			obj.put("status", "n");
			obj.put("info", "删除失败，请重试！");
		}
		return obj;
	
	}
}
