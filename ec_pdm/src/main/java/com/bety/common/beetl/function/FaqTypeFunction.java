package com.bety.common.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bety.web.model.FaqType;
import com.bety.web.service.FaqTypeService;

/**
 * 
 * 
 */
@Component
public class FaqTypeFunction{
	
	@Resource
	private FaqTypeService faqTypeService;
	

	/**
	 * 查找所有的faq类型
	* @param 
	 */
	public List<FaqType> getAllFaqType(){
		return (List<FaqType>) faqTypeService.findAll();
	}
	
	public FaqType getFaqTypeById(Long id){
		return faqTypeService.selectByPrimaryKey(id);
	}
}
