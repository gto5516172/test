package com.gyf.ec.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.web.sys.model.SysArea;
import com.gohuinuo.web.sys.service.SysAreaService;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcTask;
import com.gyf.ec.model.EcTaskDo;
import com.gyf.ec.model.EcTaskOfferPrice;
import com.gyf.ec.service.EcTaskOfferPriceService;
import com.gyf.ec.service.EcTaskService;

/***
 * 报价单
 * @author BetyChen
 *
 */
@Controller
@RequestMapping("ecTaskOfferPrice")
public class EcTaskOfferPriceController extends BaseController {

	@Resource
	private EcTaskOfferPriceService service;
	@Resource
	private EcTaskService ecTaskService;
	@Resource
	private SysAreaService sysAreaService;
	@RequestMapping(value = "{mode}/showlayer")
	public String showLayer(Long skuId,Long id,Integer index,Long doId,Long pid,Long taskId,
			@PathVariable("mode") String mode, Model model) {
		model.addAttribute("skuId",skuId);
		model.addAttribute("doId",doId);
 		model.addAttribute("index",index);
 		model.addAttribute("pid",pid);
 		model.addAttribute("taskId",taskId);
		if(StringUtils.equalsIgnoreCase(mode, "compare")){
			List<EcTaskOfferPrice> list = service.queryByProductId(doId);
			model.addAttribute("eops",list);
			return "ec/task/ecTask-offerPrice-compare";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("sku",service.getSku(skuId));
		EcTaskOfferPrice ep = null;
		EcTaskDo taskDo = service.queryTaskDoById(doId);
		model.addAttribute("taskDo",taskDo);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("taskId", taskId);
		params.put("taskUser", SysUserUtils.getCacheLoginUser().getId());
		EcTask ecTask = ecTaskService.queryDetailById(params);
		model.addAttribute("doStatus",ecTask.getDoStatus());
		String url = "";
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			model.addAttribute("date", sdf.format(new Date()));
			model.addAttribute("no",service.generateOfferNo());
			model.addAttribute("ep",ep);
		
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			ep = service.selectByPrimaryKey(id);
			SysArea  area = sysAreaService.selectByPrimaryKey(ep.getAreaId());
			model.addAttribute("no",ep.getOfferNo());
			model.addAttribute("date",ep.getOfferDate());
			model.addAttribute("ep",ep);
			model.addAttribute("area",area);
		}
		url =  "ec/task/ecTask-offerPrice-save";
		return url;
	}
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute  EcTaskOfferPrice ep,Long pid,Long taskId){
		return service.save(ep,pid,taskId);
	}
	@RequestMapping(value="ajaxDelete",method=RequestMethod.POST)
	public  @ResponseBody JSONObject ajaxDelete(Long id){
		return service.delete(id);
	}
	@RequestMapping(value="compare",method=RequestMethod.POST)
	public  @ResponseBody JSONObject compare(Long id){
		return service.compare(id);
	}
}
