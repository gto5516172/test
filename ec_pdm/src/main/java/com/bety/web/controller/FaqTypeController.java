package com.bety.web.controller;

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

import com.bety.web.model.CustomFaq;
import com.bety.web.model.FaqType;
import com.bety.web.service.FaqTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("faqType")
public class FaqTypeController {
	@Resource
	private FaqTypeService faqTypeService;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "faq/faqType/faqType";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<FaqType> list = faqTypeService.findPageInfo(params);
		model.addAttribute("page", list);
		return "faq/faqType/list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute FaqType obj) {
		if(obj.getStatus()==null||"".equals(obj.getStatus())){
			obj.setStatus("0");
		}
		return faqTypeService.saveFaqType(obj);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return faqTypeService.deleteType(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/layer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable("mode") String mode,Model model){
		if(id!=null){
			FaqType pf = faqTypeService.selectByPrimaryKey(id);
			model.addAttribute("faqType", pf);
		}
		if(StringUtils.equalsIgnoreCase(mode, "detail")){
			return "faq/faqType/detail";
		}
		return "faq/faqType/save";
	}
}