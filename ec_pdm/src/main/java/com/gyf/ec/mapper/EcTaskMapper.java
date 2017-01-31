package com.gyf.ec.mapper;

import java.util.List;
import java.util.Map;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.Page;
import com.gohuinuo.web.sys.model.SysUser;
import com.gyf.ec.model.EcMainTaskStatus;
import com.gyf.ec.model.EcTask;

public interface EcTaskMapper extends Mapper<EcTask> {
	public List<SysUser> findUserByEcTaskId(Map<String, Object> map);
	
	public List<SysUser> findUserByIds(String[] ids);
	
	public List<EcTask> queryActivitiTaskList(Map<String, Object> param);
	
	public List<EcTask> queryActivitiTaskByCreate(Map<String, Object> params);
	
	public List<EcTask> queryActivitiTaskByFinish(Map<String, Object> params);
	
	public List<EcTask> queryActivitiTaskByAbnormal(Map<String, Object> params);
	public EcTask queryDetailById(Map<String, Object> params);
	public Integer updateDaysLate(Map<String, Object> map);
	
	public List<EcTask> queryActivitiTask(Map<String, Object> map);
	
	public Page<EcTask> queryActivitiAll(Map<String, Object> params);
	
	public Integer findCountByType(Long typeId);
	
	public List<EcMainTaskStatus> findMainTaskStatus(Map<String, Object> params);
}
