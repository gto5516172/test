package com.bety.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.CustomFaqAnswer;
import com.bety.web.service.CustomFaqAnswerService;
import com.bety.web.service.CustomFaqService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.web.sys.utils.SysUserUtils;
@Controller
@RequestMapping("customFaqAnswer")
public class CustomFaqAnswerController {
	@Resource
	private CustomFaqAnswerService service;
	@Resource
	private CustomFaqService faqService;
	@RequestMapping(value = "view")
	public String viewInfo(Model model,Long faqId) {
		model.addAttribute("faqId", faqId);
		return "faq/customFaq/answer";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		params.put("pageSize", 10000000);
		PageHelper.startPage(params);
		PageInfo<CustomFaqAnswer> list = service.findPageInfo(params);
		model.addAttribute("page", list);
		model.addAttribute("faqId",params.get("faqId"));
		model.addAttribute("faq",faqService.selectByPrimaryKey(Long.parseLong((String)params.get("faqId"))));
		return "faq/customFaq/answer-list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody JSONObject save(@ModelAttribute CustomFaqAnswer obj) {
		obj.setUserId(SysUserUtils.getCacheLoginUser().getId());
		return service.saveAnswer(obj);
	}
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject save(Long id) {
		return service.delete(id);
	}
}
