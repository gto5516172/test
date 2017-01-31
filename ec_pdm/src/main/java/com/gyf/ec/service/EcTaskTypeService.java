package com.gyf.ec.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gyf.ec.mapper.EcTaskTypeMapper;
import com.gyf.ec.model.EcTaskType;

@Service("ecTaskTypeService")
@CacheConfig(cacheNames="ecTaskType_cache")
public class EcTaskTypeService extends ServiceMybatis<EcTaskType> {
	
	@Resource
	private EcTaskTypeMapper mapper;
	@Resource
	private EcTaskService ecTaskService;
	public List<EcTaskType> findTaskTypeList(Map<String, Object> params) { 
		return mapper.findTaskTypeList(params);
	}
	
	public EcTaskType findTaskType(Long id) {
		return mapper.findTaskType(id);
	}
	
	public Integer saveEcTaskType(EcTaskType ecTaskType) {
		int count = 0;
		if(null == ecTaskType.getId()){
			count = this.insertSelective(ecTaskType);
		} else {
			count = this.updateByPrimaryKeySelective(ecTaskType);
		}
		return count;
	}
	
	public int deleteEcTaskType(long id) {
		int count = ecTaskService.findCountByType(id);
		if(count>0){
			return -1;
		}
		return this.deleteByPrimaryKey(id);
	}
	public List<EcTaskType> findAll(){
		return mapper.select(null);
	}
	
	/**
	 * 查询任务类型
	* @return
	 */
	@Cacheable(key="'ecTaskType_all'")
	public List<EcTaskType> findAllEcTaskType(){
		return this.select(new EcTaskType());
	}
	
}
