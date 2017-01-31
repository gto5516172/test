package com.gyf.ec.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcTask;
import com.gyf.ec.model.EcTaskProduct;
import com.gyf.ec.service.EcTaskDoService;
import com.gyf.ec.service.EcTaskProductService;
import com.gyf.ec.service.EcTaskService;
@Controller
@RequestMapping("ecTaskProduct")
public class EcTaskProductController extends BaseController {
	@Resource
	private EcTaskProductService service;
	@Resource
	private EcTaskDoService taskDoService;
	@Resource
	private EcTaskService ecTaskService;
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<EcTaskProduct> pf = service.findPageInfo(params);
		model.addAttribute("page", pf);
		model.addAttribute("taskId",params.get("taskId"));
		model.addAttribute("categoryType",params.get("categoryType"));
		EcTask ecTask = ecTaskService.queryDetailById(params);
		model.addAttribute("doStatus",ecTask.getDoStatus());
		return "ec/task/ecTask_product_list";
	}
}

