package com.gyf.ec.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gohuinuo.common.base.ServiceMybatis;
import com.gohuinuo.web.sys.mapper.SysUserMapper;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.mapper.EcTaskMapper;
import com.gyf.ec.mapper.EcTaskProcinstMapper;
import com.gyf.ec.model.EcMainTaskStatus;
import com.gyf.ec.model.EcSuppliers;
import com.gyf.ec.model.EcTask;
import com.gyf.ec.model.EcTaskProcinst;
import com.gyf.ec.model.EcTaskProduct;

@Service("ecTaskService")
public class EcTaskService extends ServiceMybatis<EcTask> {
	
	@Autowired  
    RepositoryService repositoryService;
    
    @Resource
    RuntimeService runtimeService;
    
    @Resource
    TaskService taskService;
    
    @Resource
    HistoryService historyService;
    
    @Resource
    ProcessEngine processEngine;
    
    @Resource
    IdentityService identityService;
	
	@Resource
	private EcTaskMapper mapper;
	
	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private EcTaskProductService ecTaskProductService;
	
	@Resource
	private EcTaskProcinstService ecTaskProcinstService;
	
	/**
	 * 创建流程实例
	 */
	public void startActiviti(EcTask ecTask) {
	   Map<String, Object> variables = new HashMap<String, Object>();  
	   long userId = ecTask.getResponsibilityUser();
       variables.put("userID", userId);
       variables.put("ecTask", ecTask);
	   ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess" , variables);
	   identityService.setAuthenticatedUserId(userId + "");
	   System.out.println("流程实例ID:" + pi.getId());  
	   System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
	   
	   EcTaskProcinst etp = new EcTaskProcinst();
	   etp.setTaskId(ecTask.getId());
	   etp.setProcInstId(pi.getId());
	   etp.setCreateDate(new Date());
	   etp.setCreateUser(ecTask.getCreateUser());
	   etp.setResponsibilityUser(ecTask.getResponsibilityUser());
	   etp.setStatus(0);
	   ecTaskProcinstService.saveEcTaskProcinst(etp);
	   
	}
	
	public Page<EcTask> queryActivitiAll(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<EcTask> list = mapper.queryActivitiAll(params); 
		return (Page<EcTask>) list;
	}
	
	/**
	 * 查询我的任务
	 * @return
	 */
	public Page<EcTask> queryActivitiTask(Map<String, Object> params) {
		/*long userId = SysUserUtils.getCacheLoginUser().getId();
	    List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId + "").list();
	    Page<EcTask> list = new Page<EcTask>();
	    for(int i = 0; i < tasks.size(); i++) {
		    Task task = tasks.get(i);
		    Map<String,Object> vars = taskService.getVariables(task.getId());
		    EcTask ecTask = (EcTask) vars.get("ecTask");
		    if(ecTask != null) {
		    	ecTask.setTaskId(task.getId());
		    	ecTask.setTaskName(task.getName());
		    	SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		    	ecTask.setUserName(user.getName());
		    	ecTask.setResponsibilityUser(Long.parseLong(task.getAssignee()));
		    }
		    list.add((EcTask) vars.get("ecTask"));
		    System.out.println("执行任务ID� task.getId() + ";任务名称:" + task.getName() + ";流程变量:" + vars.get("ecTask"));
	    }
	    return list;*/
		long userId = SysUserUtils.getCacheLoginUser().getId();
		PageHelper.startPage(params);
		params.put("userId", userId);
		List<EcTask> list = mapper.queryActivitiTask(params); 
		return (Page<EcTask>) list;
	}
	
	/**
	 * 查询自己发布的任�
	 * @param params
	 * @return
	 */
	public Page<EcTask> queryActivitiTaskByCreate(Map<String, Object> params) {
		long userId = SysUserUtils.getCacheLoginUser().getId();
		PageHelper.startPage(params);
		List<EcTask> list = mapper.queryActivitiTaskByCreate(params); 
		return (Page<EcTask>) list;
	}
	
	/**
	 * 查看已经完成的任�
	 * @param params
	 * @return
	 */
	public Page<EcTask> queryActivitiTaskByFinish(Map<String, Object> params) {
		long userId = SysUserUtils.getCacheLoginUser().getId();
		PageHelper.startPage(params);
		List<EcTask> list = mapper.queryActivitiTaskByFinish(params); 
		return (Page<EcTask>) list;
	}
	
	/**
	 * 查看异常任务
	 * @param params
	 * @return
	 */
	public Page<EcTask> queryActivitiTaskByAbnormal(Map<String, Object> params) {
		long userId = SysUserUtils.getCacheLoginUser().getId();
		PageHelper.startPage(params);
		List<EcTask> list = mapper.queryActivitiTaskByAbnormal(params); 
		return (Page<EcTask>) list;
	}
	
	/**
	 * 改派任务
	 * @param taskId
	 * @param userId
	 */
	public void gaipai(String taskId, String userId) {
	   processEngine.getTaskService().setAssignee(taskId, userId);
	   System.out.println("改派成功");
    }
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param input
	 */
	public void completeTask(String taskId, String input, String userId, int status) {
	   Map<String, Object> variables = new HashMap<String, Object>();
	   Map<String, Object> map = new HashMap<String, Object>();
	   if(userId != null && !"".equals(userId)) {
		   variables.put("userID", userId);
		   map.put("responsibilityUser", userId);
	   }
	   if(input != null && !"".equals(input)) {
		   variables.put("input", input);
	   }
	   Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	   taskService.complete(taskId, variables);
	   String piid = task.getProcessInstanceId();
	   map.put("procInstId", piid);
	   map.put("status", status);
	   ecTaskProcinstService.updateEcTaskProcinst(map);
	   
	   System.out.println("完成任务");
    }
	
	/**
	 * 保存流程
	 * @param ecTask
	 * @return
	 */
	public Integer saveEcTask(EcTask ecTask) {
		long userId = SysUserUtils.getCacheLoginUser().getId();
		ecTask.setCreateUser(userId);
		ecTask.setStatus(0);
		int count = this.insertSelective(ecTask);
		saveProduct(ecTask);
		if(count > 0) {
			if(StringUtils.isNotEmpty(ecTask.getUserIds())) {
				String[] ary = ecTask.getUserIds().split(",");
				for(int i = 0; i < ary.length; i++) {
					ecTask.setResponsibilityUser(Long.parseLong(ary[i]));
					startActiviti(ecTask);
				}
			}
		}
		return count;
	}
	private void saveProduct(EcTask ecTask){
		Integer categoryType = ecTask.getCategoryType();
		if(categoryType==1){
			String[] product = ecTask.getProduct1();
			String[] notes = ecTask.getNotes1();
			String[] shopIds = ecTask.getShopIds();
			for(int i=0;i<product.length;i++){
				EcTaskProduct et = new EcTaskProduct();
				et.setTaskId(ecTask.getId());
				et.setProductId(Long.parseLong(product[i]));
				et.setShopId(Long.parseLong(shopIds[i]));
				if(notes.length>i)
				et.setNote(notes[i]);
				ecTaskProductService.insert(et);
			}
		}else if(categoryType==2){
			String[] product = ecTask.getProduct2();
			String[] notes = ecTask.getNotes2();
			for(int i=0;i<product.length;i++){
				EcTaskProduct et = new EcTaskProduct();
				et.setTaskId(ecTask.getId());
				et.setProductId(Long.parseLong(product[i]));
				if(notes.length>i)
				et.setNote(notes[i]);
				ecTaskProductService.insert(et);
			}
		}else if(categoryType==3){
			String[] product = ecTask.getProduct3();
			String[] notes = ecTask.getNotes3();
			String[] suppliers = ecTask.getSuppliers();
			for(int i=0;i<product.length;i++){
				EcTaskProduct et = new EcTaskProduct();
				et.setTaskId(ecTask.getId());
				et.setProductId(Long.parseLong(product[i]));
				if(notes.length>i)
				et.setNote(notes[i]);
				et.setSupplierId(Long.parseLong(suppliers[i]));
				ecTaskProductService.insert(et);
			}
		}
	}
	public List<SysUser> findUserByEcTaskId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return mapper.findUserByEcTaskId(map);
	}
	
	public List<SysUser> findUserByIds(String userIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		String[] ary = userIds.split(",");
		return mapper.findUserByIds(ary);
	}
	
	public List<EcTask> queryActivitiTaskList(Map<String, Object> param) {
		return mapper.queryActivitiTaskList(param);
	}
	
	public String getCreate(String taskId) {
		Map<String,Object> vars = (Map<String, Object>) taskService.getVariable(taskId, "ecTask");
		System.out.println((Long) vars.get("createUser") + "");
		return (Long) vars.get("createUser") + "";
	}
	
	public EcTask queryDetailById(Map<String, Object> params){
		return mapper.queryDetailById(params);
	}
	public boolean isFinish(Long taskId){
		return false;
	}
	
	/**
	 * 计算超时时间
	 * @throws ParseException 
	 */
	public boolean calculationTime(Long tid) throws ParseException {
		EcTask ecTask = this.selectByPrimaryKey(tid);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        d1 = format.parse(ecTask.getEndTime());
        d2 = new Date();
        if(d2.getTime() > d1.getTime()) {
	        //毫秒ms
	        long diff = d2.getTime() - d1.getTime();
	        long diffSeconds = diff / 1000 % 60;
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        String timeStr = diffDays + "延时 "+ diffHours + "小时" + diffMinutes + "分钟" + diffSeconds + "秒";
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("id", tid);
	        map.put("daysLate", timeStr);
	        mapper.updateDaysLate(map);
	        return false;
        } else {
        	String timeStr = "未延迟";
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("id", tid);
	        map.put("daysLate", timeStr);
	        mapper.updateDaysLate(map);
	        return true;
        }
    
	}
	
	public JSONObject isTaskFinish(Long taskId){
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("taskUser", SysUserUtils.getCacheLoginUser().getId());
		params.put("taskId",taskId);
		EcTask ectask = queryDetailById(params);
		JSONObject obj = new JSONObject();
		if(ectask.getDoStatus().equals("-1")){
			obj.put("code", "-1");
			obj.put("message", "未完成");
		}else{
			obj.put("code", "0");
			obj.put("message", "已完成");
		}
		return obj;
	}
	public Integer findCountByType(Long typeId){
		return mapper.findCountByType(typeId);
	}
	
	public List<EcMainTaskStatus> findMainTaskStatus(Map<String, Object> params) {
		return mapper.findMainTaskStatus(params);
	}
}
