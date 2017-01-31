

package com.bety.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bety.web.mapper.ShopMapper;
import com.bety.web.model.Shop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.service.EcTaskProductService;

/**
 * 
 * @author BetyChen
 */

@Service("shopService")
public class ShopService extends ServiceMybatis<Shop>{

	@Resource
	private ShopMapper shopMapper;
	@Resource
	private EcTaskProductService taskProductService;

	
	
	/**
	 * 添加或更新
	* @param 
	* @return
	 */
	public int saveShop(Shop obj){
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
	public int deleteShop(Long id){
		int count =  taskProductService.findCountByShop(id);
		if(count>0){
			return -1;
		}
		return this.deleteByPrimaryKey(id);
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	public PageInfo<Shop> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Shop> list = shopMapper.findPageInfo(params);
		return new PageInfo<Shop>(list);
	}
	
	public List<Shop> findAll() {
		List<Shop> list = shopMapper.select(null);
		return list;
	}
	public Integer findCountBySitId(Long siteId){
		return shopMapper.findCountBySitId(siteId);
	}
}
