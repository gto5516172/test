package com.gyf.ec.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bety.web.service.CommoditySpuService;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcCatalogueMapper;
import com.gyf.ec.model.EcCatalogue;


@Service("ecCatalogueService")
@CacheConfig(cacheNames="ecCatalogue_cache")
public class EcCatalogueService extends ServiceMybatis<EcCatalogue> {
	
	@Resource
	private EcCatalogueMapper ecCatalogueMapper;
	@Resource
	private EcSkuService skuService;
	@Resource
	private CommoditySpuService spuService;
	/**
	 *新增or更新SysArea
	 */
	
	@CacheEvict(allEntries=true)
	public int saveEcCatalogue(EcCatalogue ecCatalogue){
		int count = 0;
		//新的parentIds
		ecCatalogue.setParentIds(ecCatalogue.getParentIds()+ecCatalogue.getParentId()+","); 
		if(null == ecCatalogue.getId()){
			count = this.insertSelective(ecCatalogue);
		}else{
			//getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			//先更新parentId，此节点的parentIds以更�
			count = this.updateByPrimaryKeySelective(ecCatalogue); 
			//不移动节点不更新子节点的pids
			if(!StringUtils.equals(ecCatalogue.getOldParentIds(), ecCatalogue.getParentIds())){
				ecCatalogueMapper.updateParentIds(ecCatalogue); //批量更新子节点的parentIds
			}
		}
		return count;
	}
	
	/**
	 * 查询全部的区�
	* @return
	 */
	@Cacheable(key="'ecCatalogue_all'")
	public List<EcCatalogue> findAllEcCatalogue(){
		return this.select(new EcCatalogue());
	}
	
	/**
	 * 文档分类管理分页显示筛选查�
	 * 
	 * @param params
	 *            {"name":"文档分类名字","id":"文档分类id"}
	 * @return
	 */
	public List<EcCatalogue> findEcCatalogueList(Map<String, Object> params){
		List<EcCatalogue> list = ecCatalogueMapper.findEcCatalogueList(params);
		return list;
	}
	
	/**
	 * 根据父id删除自身已经所有子节点
	* @param id
	* @return
	 */
	@CacheEvict(allEntries=true)	
	public int deleteEcCatalogueByRootId(Long id){
		//int count = this.beforeDeleteTreeStructure(id,"areaId", SysOffice.class,SysArea.class);
		//int count2 = this.beforeDeleteTreeStructure(id, "areaId", EcSuppliers.class,SysArea.class);
		int count1 = skuService.querySkuByCatalogue(id);
		int count2 = spuService.findCountByType(id);
		if(count1>0||count2>0){
			return -1;
		}
		int num = -1;
		//if(count != -1 && count2 != -1) {
			num = ecCatalogueMapper.deleteEcCatalogueByRootId(id);
		//}
	
		return num;
	}
	public EcCatalogue findOneByName(String name){
		return ecCatalogueMapper.findOneByName(name);
	}
	


}
