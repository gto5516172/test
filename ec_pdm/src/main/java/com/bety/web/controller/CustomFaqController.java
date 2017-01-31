package com.bety.web.controller;

import java.io.IOException;
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

import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.CustomFaq;
import com.bety.web.model.ProductFaq;
import com.bety.web.service.CustomFaqService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("customFaq")
public class CustomFaqController {
	@Resource
	private CustomFaqService customFaqService;
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "faq/customFaq/faq";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<CustomFaq> list = customFaqService.findPageInfo(params);
		
		model.addAttribute("page", list);
		return "faq/customFaq/list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute CustomFaq obj) {
		return customFaqService.saveFaq(obj);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return customFaqService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/layer")
	public String layer(Long id,@PathVariable("mode") String mode,Model model){
		if(StringUtils.equalsIgnoreCase(mode, "edit")){
			CustomFaq pf = customFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "faq/customFaq/edit";
		}else if(StringUtils.equalsIgnoreCase(mode, "add")){
			return "faq/customFaq/save";
		}else if(StringUtils.equalsIgnoreCase(mode, "status")){
			CustomFaq pf = customFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "faq/customFaq/status";
		}else {
			CustomFaq pf = customFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "faq/customFaq/detail";
			}
	}
	
	/***
	 * ajax验证是否存在
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateGoodNoByAjax",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateGoodNoByAjax(String param,Integer type) throws IOException{
		return customFaqService.validateGoodNoByAjax(type,param);
	}
}
