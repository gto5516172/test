

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bety.web.mapper.FaqTypeMapper;
import com.bety.web.model.FaqType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;

/**
 * 
 * @author BetyChen
 */

@Service("faqTypeService")
public class FaqTypeService extends ServiceMybatis<FaqType>{

	@Resource
	private FaqTypeMapper faqTypeMapper;
	@Resource
	private ProductFaqService productFaqService;
	@Resource
	private CustomFaqService coustomFaqService;
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int saveFaqType(FaqType obj){
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
	public int deleteFaqType(Long userId){
		return this.updateDelFlagToDelStatusById(FaqType.class, userId);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<FaqType> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<FaqType> list = faqTypeMapper.findPageInfo(params);
		return new PageInfo<FaqType>(list);
	}
	
	public List<FaqType> findAll() {
		List<FaqType> list = faqTypeMapper.select(null);
		return list;
	}
	public int deleteType(Long id){
		//查询是否有占用的type
		int coustomCount = coustomFaqService.findFaqCoustomCountByType(id);
		int productCount = productFaqService.findFaqProductCountByType(id);
		if(coustomCount>0||productCount>0){
			return -1;
		}
		return this.deleteByPrimaryKey(id);
	}
}
