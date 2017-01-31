package com.gyf.ec.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcTaskDoMapper;
import com.gyf.ec.model.EcTask;
import com.gyf.ec.model.EcTaskDo;
import com.gyf.ec.model.EcTaskStatus;

@Service("ecTaskDoService")
public class EcTaskDoService extends ServiceMybatis<EcTaskDo> {

	@Resource
	private EcTaskDoMapper mapper;
	@Resource
	private EcTaskOfferPriceService eoService;
	@Resource
	private EcTaskService ecTaskService;
	@Resource
	private EcTaskStatusService statusService;
	public JSONObject saveSalerPrice(Long pid, String salerPrice, String myUrl, String status, Long id,Long taskId) {
		int saveStatus = 0;
		if (id == null) {
			EcTaskDo taskDo = new EcTaskDo();
			taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
			taskDo.setPid(pid);
			taskDo.setMyUrl(myUrl);
			taskDo.setStatus(status);
			taskDo.setTaskId(taskId);
			taskDo.setSalerPrice(salerPrice);
			saveStatus = mapper.insert(taskDo);
		} else {
			EcTaskDo taskDo = mapper.selectByPrimaryKey(id);
			if (taskDo != null) {
				// taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
				// taskDo.setPid(pid);
				taskDo.setMyUrl(myUrl);
				taskDo.setStatus(status);
				taskDo.setSalerPrice(salerPrice);
				saveStatus = mapper.updateByPrimaryKey(taskDo);
			} else {
				saveStatus = -1;
			}
		}
		JSONObject obj = new JSONObject();
		if (saveStatus > 0) {
			obj.put("status", "0");
			obj.put("message", "修改成功");
		} else {
			obj.put("status", "-1");
			obj.put("message", "修改失败");
		}
		return obj;
	}

	public JSONObject saveNewPrice(Long pid, String newPrice, Long id,Long taskId) {
		int saveStatus = 0;
		if (id == null) {
			EcTaskDo taskDo = new EcTaskDo();
			taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
			taskDo.setPid(pid);
			taskDo.setNewPrice(newPrice);
			taskDo.setStatus("1");
			taskDo.setTaskId(taskId);
			saveStatus = mapper.insert(taskDo);
		} else {
			EcTaskDo taskDo = mapper.selectByPrimaryKey(id);
			// taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
			// taskDo.setPid(pid);
			taskDo.setNewPrice(newPrice);
			taskDo.setStatus("1");
			saveStatus = mapper.updateByPrimaryKey(taskDo);
		}
		JSONObject obj = new JSONObject();
		if (saveStatus > 0) {
			obj.put("status", "0");
			obj.put("message", "修改成功");
		} else {
			obj.put("status", "-1");
			obj.put("message", "修改失败");
		}
		return obj;
	}

	public JSONObject saveSupplyPrice(Long id) {
		JSONObject obj = new JSONObject();
		if (id == null) {
			obj.put("status", "-1");
			obj.put("message", "修改失败，请先添加询价");
		} else {
			EcTaskDo taskDo = mapper.selectByPrimaryKey(id);
			taskDo.setStatus("1");
			mapper.updateByPrimaryKey(taskDo);
			obj.put("status", "0");
			obj.put("message", "修改成功");
		}
		return obj;
	}

	public JSONObject saveOther(Long pid, String productDescr, Long id) {
		int saveStatus = 0;
		EcTaskDo taskDo = null;
		if (id == null) {
			taskDo = new EcTaskDo();
			taskDo.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
			taskDo.setTaskId(pid);
			taskDo.setProductDescr(productDescr);
			saveStatus = mapper.insert(taskDo);
		} else {
			taskDo = mapper.selectByPrimaryKey(id);
			taskDo.setProductDescr(productDescr);
			saveStatus = mapper.updateByPrimaryKey(taskDo);
		}
		JSONObject obj = new JSONObject();
		if (saveStatus > 0) {
			obj.put("status", "0");
			obj.put("message", "修改成功");
		} else {
			obj.put("status", "-1");
			obj.put("message", "修改失败");
			obj.put("doId", "0");
		}
		return obj;
	}

	public EcTaskDo queryOtherDoByUserId(long taskUser, long taskId) {
		return mapper.queryOtherDoByUserId(taskUser, taskId);
	}
	private boolean checkIsEnd(Long taskId){
		int count = mapper.queryCountByStatus(SysUserUtils.getCacheLoginUser().getId(), taskId, "1", 2);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	public JSONObject finishTask(Long taskId){
		JSONObject obj = new JSONObject();
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("taskUser", SysUserUtils.getCacheLoginUser().getId());
		params.put("taskId",taskId);
		EcTask ectask = ecTaskService.queryDetailById(params);
		//没有点击过确定按钮才能点击
		if(ectask.getDoStatus().equals("-1")){
		if(ectask.getCategoryType()==2){
			//查询是否都点击截止按钮
			if(!checkIsEnd(taskId)){
				obj.put("status", "-1");
				obj.put("message", "部分任务未截止，请先截止！");
				return obj;
			}
		}
		EcTaskStatus status = new EcTaskStatus();
		status.setTaskId(taskId);
		status.setTaskUser(SysUserUtils.getCacheLoginUser().getId());
		status.setDoStatus("0");
		int updateStatus = statusService.insert(status);
		if(updateStatus>0){
			obj.put("status", "0");
			obj.put("message", "操作成功");
		}else{
			obj.put("status", "-2");
			obj.put("message", "操作失败");
		}
		}else{
			obj.put("status", "-3");
			obj.put("message", "已结束，操作失败");
		}
		return obj;
	}
	//查询用户是否点击确定按钮
//	public boolean queryTaskDoStatus(Long taskId){
//		int count = mapper.queryTaskDoStatus("1", SysUserUtils.getCacheLoginUser().getId(), taskId);
//		if(count>0){
//			return true;
//		}else{
//			return false;
//		}
//	}
}
