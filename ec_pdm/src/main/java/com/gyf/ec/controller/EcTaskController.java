package com.gyf.ec.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcTask;
import com.gyf.ec.model.EcTaskDo;
import com.gyf.ec.service.EcTaskDoService;
import com.gyf.ec.service.EcTaskService;
import com.gyf.ec.service.EcTaskTypeService;

/**
 * 任务管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecTask")
public class EcTaskController extends BaseController {
	
	@Resource
	private EcTaskService service;
	@Resource
	private EcTaskDoService taskDoService;
	@Resource
	private EcTaskTypeService ecTaskTypeService;
	
	/**
	 * 跳转到任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toEcTask")
	public String toEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/ecTask";
	}
	
	
	/**
	 * 跳转到我的任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toMyEcTask")
	public String toMyEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/myEcTask";
	}
	
	/**
	 * 跳转到指派任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toZPEcTask")
	public String toZPEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/zpEcTask";
	}
	
	/**
	 * 跳转到完成任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toWCEcTask")
	public String toWCEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/wcEcTask";
	}
	
	/**
	 * 跳转到所有任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toAllEcTask")
	public String toAllEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/allEcTask";
	}
	
	/**
	 * 跳转到异常任务
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value="toECEcTask")
	public String toECEcTask(Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("ecTaskTypeList", ecTaskTypeService.findTaskTypeList(map));
		return "ec/task/ecEcTask";
	}
	
	/**
	 * 弹窗
	 * 
	 * @param id
	 * @param skuId
	 *            skuId
	 * @param mode
	 *            模式(add,edit,detail)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{mode}/showlayer")
	public String showLayer(Long id,
			@PathVariable("mode") String mode, Model model) {
		EcTask ecTask = null;
		String url = "";
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			url =  "ec/task/ecTask-save";
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			url =  "ec/task/ecTask-edit";
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("taskUser", SysUserUtils.getCacheLoginUser().getId());
			param.put("taskId",id);
			ecTask = service.queryDetailById(param);
			int type = ecTask.getCategoryType();
			if(type==4){
				//查询该用户的任务描述
				EcTaskDo taskDo = taskDoService.queryOtherDoByUserId(SysUserUtils.getCacheLoginUser().getId(), ecTask.getId());
				model.addAttribute("taskDo", taskDo);
			}
			//查询是否点击了确认按钮
			model.addAttribute("task", ecTask);
			url =  "ec/task/ecTask-detail";
		}
		return url;
	}
	
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		long userId = SysUserUtils.getCacheLoginUser().getId();
		params.put("userId", userId);
		String actQueryType =  (String) params.get("actQueryType");
		List<EcTask> list = new ArrayList<EcTask>();
		if("0".equals(actQueryType)) {
			list = service.queryActivitiTask(params);
		} else if("1".equals(actQueryType)) {
			list = service.queryActivitiTaskByCreate(params);
		} else if("2".equals(actQueryType)) {
			list = service.queryActivitiTaskByAbnormal(params);
		} else if("3".equals(actQueryType)) {
			list = service.queryActivitiTaskByFinish(params);
		} else if("4".equals(actQueryType)) {
			list = service.queryActivitiAll(params);
		}
		//List<EcTask> list = service.queryActivitiTaskList(params);
		model.addAttribute("page", new PageInfo<EcTask>(list));
		return "ec/task/ecTask-list";
	}
	
   /**
	* 添加或更�
	* @param params
	* @return
	*/
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcTask ecTask){
		return service.saveEcTask(ecTask);
	}
	
	/**
	 * 绑定用户界面
	* @return
	 */
	@RequestMapping(value="binduser",method=RequestMethod.POST)
	public String toBindUser(@ModelAttribute EcTask ecTask, Model model){
		List<SysUser> userList = new ArrayList<SysUser>();
		if(StringUtils.isNotEmpty(ecTask.getUserIds())) {
			userList = service.findUserByIds(ecTask.getUserIds());
		}
		if(userList.size() == 0) {
			model.addAttribute("userList", new ArrayList<SysUser>());
		} else {
			model.addAttribute("userList", userList);
		}
		return "ec/task/ecTask-user";
	}
	
	/**
	 * 改派任务界面
	* @return
	 */
	@RequestMapping(value="binduser2",method=RequestMethod.POST)
	public String toBindUser2(@ModelAttribute EcTask ecTask, Model model){
		model.addAttribute("ecTask", ecTask);
		return "ec/task/ecTask-user2";
	}
	
	@RequestMapping(value = "toTaskSave", method = RequestMethod.GET)
	public String toTaskSave(Model model) {
		return "ec/task/ecTask-save";
	}
	
	
	/**
	 * 流程流转
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="transfer",method=RequestMethod.POST)
	public @ResponseBody Integer transfer(Integer transferNum, String taskId, String gpUser, Long tid) throws ParseException {
		if(transferNum == 0) {
			// 改派过来重新创建指派
			service.completeTask(taskId, "0", gpUser, 0);
		} else if(transferNum == 1) {
			// 改派
			service.completeTask(taskId, "1", service.getCreate(taskId), 1);
		} else if(transferNum == 2) {
			// 开始任务
			service.completeTask(taskId, "2", "", 2);
		} else if(transferNum == 3) {
			// 任务驳回
			service.completeTask(taskId, "3", service.getCreate(taskId), 3);
		} else if(transferNum == 4) {
			// 异常任务
			service.completeTask(taskId, "", "", 4);
		} else if(transferNum == 5) {
			// 驳回某条
			service.completeTask(taskId, "", service.getCreate(taskId), 5);
		} else if(transferNum == 6) {
			// 计算超时时间 并返回是否超时
			boolean flag = service.calculationTime(tid);
			// 完成任务
			if(flag) {
				// 没超时 走审批
				service.completeTask(taskId, "1", service.getCreate(taskId), 6);
			} else {
				// 超时 走异常
				service.completeTask(taskId, "3", service.getCreate(taskId), 4);
			}
		} else if(transferNum == 7) {
			// 审批确认
			service.completeTask(taskId, "1", "", 7);
		} else if(transferNum == 8) {
			// 审批完成 任务结束
			service.completeTask(taskId, "2", "", 8);
		}
		return 1;
	}
	
}
