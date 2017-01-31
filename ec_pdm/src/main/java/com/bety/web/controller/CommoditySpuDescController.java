package com.bety.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.CommoditySpuDesc;
import com.bety.web.service.CommoditySpuDescService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("commoditySpuDesc")
public class CommoditySpuDescController {
	@Resource
	private CommoditySpuDescService commoditySpuDescService;

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(Model model,Long spuId,Long id) {
		if(id!=null&&id>0){
		CommoditySpuDesc pf = commoditySpuDescService.findById(id);
		model.addAttribute("cs", pf);	
		}
		model.addAttribute("spuId", spuId);
		
		return "commoditySpu/desc";
	}
	@RequestMapping(value = "view", method = RequestMethod.POST)
	public String view(Model model,Long spuId,Long id) {
		if(id!=null&&id>0){
		CommoditySpuDesc pf = commoditySpuDescService.findById(id);
		model.addAttribute("cs", pf);	
		}
		
		return "commoditySpu/desc";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Long save(@ModelAttribute CommoditySpuDesc obj,HttpServletRequest request) {
		return commoditySpuDescService.saveSpuDesc(obj,request);
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return commoditySpuDescService.deleteByPrimaryKey(id);
	}
/*	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		params.put("pageSize", 100000);
		PageHelper.startPage(params);
		PageInfo<CommoditySpuDesc> page = commoditySpuDescService.findPageInfo(params);
		return JSONObject.toJSONString(page);
	}*/
	@RequestMapping(value = "toDetailList", method = RequestMethod.GET)
	public String toDetailList(Model model,Long spuId) {
		model.addAttribute("spuId", spuId);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("spuId", spuId);
		map.put("pageNum",1);
		map.put("pageSize", 10000);
	  List<CommoditySpuDesc> list = commoditySpuDescService.findPageInfo1(map);
	  list.size();
	  model.addAttribute("list",list);
		return "commoditySpu/descDetailList";
	}
	@RequestMapping(value = "toDetail", method = RequestMethod.GET)
	public String toDetail(Model model,Long id) {
		CommoditySpuDesc cs = commoditySpuDescService.findById(id);
		model.addAttribute("detail",cs );
		return "commoditySpu/descDetail";
	}
}
