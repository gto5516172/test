package com.gyf.ec.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.service.EcTaskDoService;
@Controller
@RequestMapping("ecTaskDo")
public class EcTaskDoController extends BaseController {
	@Resource
	private EcTaskDoService service;
	//ajax保存销售刊登价格
	@RequestMapping(value="ajaxSaveSalerPrice",method=RequestMethod.POST)
	public  @ResponseBody JSONObject ajaxSaveSalerPrice(Long pid,Long id,String salerPrice,String myUrl,String status,Long taskId){
		return service.saveSalerPrice(pid, salerPrice, myUrl,status,id,taskId);
	}
	//ajax保存最新报价
	@RequestMapping(value="ajaxSaveNewPrice",method=RequestMethod.POST)
	public  @ResponseBody JSONObject ajaxUpdateNewPrice(Long pid,Long id,String newPrice,Long taskId){
		return service.saveNewPrice(pid, newPrice,id,taskId);
	}
	
	//ajax保存供应商截止状态
	@RequestMapping(value="ajaxUpdateStatus",method=RequestMethod.POST)
	public @ResponseBody JSONObject ajaxUpdateStatus(Long id){
		return service.saveSupplyPrice(id);
	}
	//ajax更新
	@RequestMapping(value="ajaxSaveOther",method=RequestMethod.POST)
	public @ResponseBody JSONObject ajaxSaveOther(String productDescr,Long doId,Long taskId){
		return service.saveOther(taskId, productDescr,doId);
	}

	//点击确认按钮
	@RequestMapping(value="ajaxFinishTask",method=RequestMethod.POST)
	public  @ResponseBody JSONObject ajaxFinishTask(Long taskId){
		return service.finishTask(taskId);
	}
}

