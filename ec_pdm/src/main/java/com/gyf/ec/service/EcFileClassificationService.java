package com.gyf.ec.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcFileClassificationMapper;
import com.gyf.ec.model.EcFileClassification;


@Service("ecFileClassificationService")
@CacheConfig(cacheNames="ecFileClassification_cache")
public class EcFileClassificationService extends ServiceMybatis<EcFileClassification> {

	@Resource
	private EcFileClassificationMapper ecFileClassificationMapper;
	
	/**
	 *新增or更新SysArea
	 */
	
	@CacheEvict(allEntries=true)
	public int saveEcFileClassification(EcFileClassification ecFileClassification){
		int count = 0;
		//新的parentIds
		ecFileClassification.setParentIds(ecFileClassification.getParentIds()+ecFileClassification.getParentId()+","); 
		if(null == ecFileClassification.getId()){
			count = this.insertSelective(ecFileClassification);
		}else{
			//getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			//先更新parentId，此节点的parentIds以更新
			count = this.updateByPrimaryKeySelective(ecFileClassification); 
			//不移动节点不更新子节点的pids
			if(!StringUtils.equals(ecFileClassification.getOldParentIds(), ecFileClassification.getParentIds())){
				ecFileClassificationMapper.updateParentIds(ecFileClassification); //批量更新子节点的parentIds
			}
		}
		return count;
	}
	
	/**
	 * 查询全部的区域
	* @return
	 */
	@Cacheable(key="'ecFileClassification_all'")
	public List<EcFileClassification> findAllFileClassification(){
		return this.select(new EcFileClassification());
	}
	
	/**
	 * 文档分类管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"name":"文档分类名字","id":"文档分类id"}
	 * @return
	 */
	public List<EcFileClassification> findFileClassificationList(Map<String, Object> params){
		List<EcFileClassification> list = ecFileClassificationMapper.findFileClassificationList(params);
		return list;
	}
	
	/**
	 * 根据父id删除自身已经所有子节点
	* @param id
	* @return
	 */
	@CacheEvict(allEntries=true)	
	public int deleteEcFileClassificationByRootId(Long id){
		//int count = this.beforeDeleteTreeStructure(id,"areaId", SysOffice.class,SysArea.class);
		//int count2 = this.beforeDeleteTreeStructure(id, "areaId", EcSuppliers.class,SysArea.class);
		int num = -1;
		//if(count != -1 && count2 != -1) {
			num = ecFileClassificationMapper.deleteEcFileClassificationByRootId(id);
		//}
	
		return num;
	}
	
	/**
	 * 查询全部的区域
	* @return
	 */
	@Cacheable(key="'ecFileClassification_all'")
	public List<EcFileClassification> findAllEcFileClassification(){
		return this.select(new EcFileClassification());
	}
}
