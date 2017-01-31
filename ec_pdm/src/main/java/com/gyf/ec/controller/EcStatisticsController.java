package com.gyf.ec.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcStatisticsBySku;
import com.gyf.ec.model.EcStatisticsBySupp;
import com.gyf.ec.service.EcCatalogueService;
import com.gyf.ec.service.EcStatisticsService;
import com.gyf.ec.service.EcSuppliersService;

@Controller
@RequestMapping("ecStatistics")
public class EcStatisticsController extends BaseController {
	
	@Resource
	private EcStatisticsService service;
	
	@Resource
	private EcCatalogueService ecCatalogueService;
	
	@Resource
	private EcSuppliersService ecSuppliersService;
	/**
	 * 柱状
	 * @param model
	 * @return 模块html
	 */
	@RequestMapping
	public String toEcStatistics(Model model){
		return "ec/statistics/ecStatistics";
	}
	
	/**
	 * 饼状
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "toEcStatistics2", method = RequestMethod.GET)
	public String toEcStatistics2(Model model) {
		return "ec/statistics/ecStatistics2";
	}
	
	/**
	 * 曲线图
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "toEcStatistics3", method = RequestMethod.GET)
	public String toEcStatistics3(Model model) {
		return "ec/statistics/ecStatistics3";
	}
	
	
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findEcStatistics", method = RequestMethod.POST)
	public @ResponseBody JSONObject findEcStatistics(@RequestParam Map<String, Object> params) {
		return service.findEcStatistics(params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findEcStatistics2", method = RequestMethod.POST)
	public @ResponseBody JSONObject findEcStatistics2(@RequestParam Map<String, Object> params) {
		return service.findEcStatistics2(params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findEcStatistics3", method = RequestMethod.POST)
	public @ResponseBody JSONObject findEcStatistics3(@RequestParam Map<String, Object> params) {
		return service.findEcStatistics3(params);
	}
	
	
	/**
	 * sku报表
	 * @param model
	 * @return 模块html
	*/ 
	@RequestMapping(value = "toEcStatisticsBySku", method = RequestMethod.GET)
	public String toEcStatisticsBySku(Model model){
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue()));
		return "ec/statistics/ecStatisticsSku";
	}
	
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findEcStatisticsBySku", method = RequestMethod.POST)
	public String findEcStatisticsBySku(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<EcStatisticsBySku> list = service.findEcStatisticsBySku(params);
		model.addAttribute("ecStatisticsBySkuPage", new PageInfo<EcStatisticsBySku>(list));
		return "ec/statistics/ecStatisticsSku-list";
	}
	
	/**
	 * 供应商报表
	 * @param model
	 * @return 模块html
	 */
	@RequestMapping(value = "toEcStatisticsBySupp", method = RequestMethod.GET)
	public String toEcStatisticsBySupp(Model model){
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue())).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers());
		return "ec/statistics/ecStatisticsSupp";
	}
	
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findEcStatisticsBySupp", method = RequestMethod.POST)
	public String findEcStatisticsBySupp(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<EcStatisticsBySupp> list = service.findEcStatisticsBySupp(params);
		model.addAttribute("ecStatisticsBySuppPage", new PageInfo<EcStatisticsBySupp>(list));
		return "ec/statistics/ecStatisticsSupp-list";
	}
	
}
