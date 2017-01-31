package com.bety.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.Platform;
import com.bety.web.service.PlatformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("platform")
public class PlatformController {
	@Resource
	private PlatformService platformService;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "platform/platform";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<Platform> list = platformService.findPageInfo(params);
		model.addAttribute("page", list);
		return "platform/list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute Platform obj) {
		if(obj.getStatus()==null||"".equals(obj.getStatus())){
			obj.setStatus("0");
		}
		return platformService.savePlatform(obj);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return platformService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/layer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable("mode") String mode,Model model){
		if(id!=null){
			Platform pf = platformService.selectByPrimaryKey(id);
			model.addAttribute("platform", pf);
		}
		return "platform/save";
	}
	
	
	/***
	 * ajax验证唯一性
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateNameByAjax",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateNameByAjax(String param) throws IOException{
		return platformService.getPaltformCountByName(param);
	}
}
