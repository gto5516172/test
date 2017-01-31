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
import com.bety.web.model.ProductFaq;
import com.bety.web.service.ProductFaqService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("productFaq")
public class ProductFaqController {
	@Resource
	private ProductFaqService productFaqService;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "faq/productFaq/faq";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<ProductFaq> list = productFaqService.findPageInfo(params);
		model.addAttribute("page", list);
		return "faq/productFaq/list";
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "listBySpuNo", method = RequestMethod.POST)
	public String findPageInfoBySpuNo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<ProductFaq> list = productFaqService.findPageInfo(params);
		model.addAttribute("isEdit" ,params.get("isEdit"));
		model.addAttribute("page", list);
		return "commoditySpu/faq_list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute ProductFaq obj) {
		return productFaqService.saveFaq(obj);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return productFaqService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/layer")
	public String layer(Long id,@PathVariable("mode") String mode,Model model,String goodsNo){
		if(StringUtils.equalsIgnoreCase(mode, "edit")){
		ProductFaq pf = productFaqService.selectByPrimaryKey(id);
		model.addAttribute("faq", pf);
		return "faq/productFaq/edit";
		}else if(StringUtils.equalsIgnoreCase(mode, "add")){
			return "faq/productFaq/save";
		}else if(StringUtils.equalsIgnoreCase(mode, "addBySpuNo")){
			model.addAttribute("goodsNo", goodsNo);
			return "commoditySpu/faq_save";
		}else if(StringUtils.equalsIgnoreCase(mode, "editBySpuNo")){
			ProductFaq pf = productFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "commoditySpu/faq_edit";
		}else if(StringUtils.equalsIgnoreCase(mode, "status")){
			ProductFaq pf = productFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "faq/productFaq/status";
		}else{
			ProductFaq pf = productFaqService.selectByPrimaryKey(id);
			model.addAttribute("faq", pf);
			return "faq/productFaq/detail";
		}
	}

	/***
	 * ajax验证是否存在
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateGoodNoByAjax",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateGoodNoByAjax(String param,Integer type) throws IOException{
		return productFaqService.validateGoodNoByAjax(type,param);
	}
}
