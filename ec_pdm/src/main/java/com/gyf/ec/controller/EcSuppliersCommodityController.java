package com.gyf.ec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcSuppliers;
import com.gyf.ec.model.EcSuppliersCommodity;
import com.gyf.ec.service.EcCatalogueService;
import com.gyf.ec.service.EcSuppliersCommodityService;
import com.gyf.ec.service.EcSuppliersService;

/**
 * 供应商商品
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecSuppliersCommodity")
public class EcSuppliersCommodityController extends BaseController {
	
	@Resource
	private EcSuppliersCommodityService ecSuppliersCommodityService;
	
	@Resource
	private EcSuppliersService ecSuppliersService;
	
	@Resource
	private EcCatalogueService ecCatalogueService;
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcSuppliersCommodity(Model model){
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue()));
		return "ec/suppliers/suppliersCommodity";
	}
	
	/**
	 * 供应商分页显示
	* @param params
	* @return
	 */
	@RequestMapping(value="ecSuppliersList",method=RequestMethod.POST)
	public String ecSuppliersList(@RequestParam Map<String, Object> params,Model model){
		PageInfo<EcSuppliers> page = ecSuppliersService.findPageInfo(params);
		
		model.addAttribute("ecSuppliersPage", page);
		return "ec/suppliers/suppliersCommodity-list";
	}
	
	/**
	 * 供应商商品分页显示
	 * 
	 */
	@RequestMapping(value="ecCommodityList",method=RequestMethod.POST)
	public String ecCommodityList(@RequestParam Map<String, Object> params,Model model,HttpServletRequest request,
			HttpServletResponse response){
		PageHelper.startPage(params);
		HttpSession session = request.getSession();
		Map<Long, EcSuppliersCommodity> scMap = new HashMap<Long, EcSuppliersCommodity>();
		if(session.getAttribute("scMap") != null) {
			scMap = (Map<Long, EcSuppliersCommodity>) session.getAttribute("scMap");
		}
		List<EcSuppliersCommodity> ecSkuList = ecSuppliersCommodityService.findPageInfoSku(params);
		model.addAttribute("commodityPage", new PageInfo<EcSuppliersCommodity>(ecSkuList))
		.addAttribute("scMap", scMap);
		return "ec/suppliers/commodity-list";
	}
	
	/**
	 * 弹窗显示
	* @param params {"mode":"1.add 2.edit 3.detail}
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable String mode, Model model, HttpServletRequest request,
			HttpServletResponse response){
		String url = "";
		if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			EcSuppliersCommodity ecSuppliersCommodity = ecSuppliersCommodityService.findscDetail(map);
			model.addAttribute("ecSuppliersCommodity", ecSuppliersCommodity);
			url = "ec/suppliers/commodity-save";
		} else if(StringUtils.equalsIgnoreCase(mode, "editBatch")) {
			HttpSession session = request.getSession();
			Map<Long, EcSuppliersCommodity> scMap = new HashMap<Long, EcSuppliersCommodity>();
			if(session.getAttribute("scMap") != null) {
				scMap = (Map<Long,  EcSuppliersCommodity>) session.getAttribute("scMap");
			}
			model.addAttribute("scMap", scMap);
			url = "ec/suppliers/commodity-saveBatch";
		}
		return url;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcSuppliersCommodity ecSuppliersCommodity){
		int num = 0;
		num = ecSuppliersCommodityService.updatePurchasePrice(ecSuppliersCommodity);
		return num;
	}

	@RequestMapping(value="saveBatch",method=RequestMethod.POST)
	public @ResponseBody Integer saveBatch(@ModelAttribute EcSuppliersCommodity ecSuppliersCommodity, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Map<Long, EcSuppliersCommodity> scMap = new HashMap<Long, EcSuppliersCommodity>();
		if(session.getAttribute("scMap") != null) {
			scMap = (Map<Long,  EcSuppliersCommodity>) session.getAttribute("scMap");
		}
		int num = 0;
		for(EcSuppliersCommodity esc : scMap.values()) {
			esc.setPurchasePrice(ecSuppliersCommodity.getPurchasePrice());
			num = ecSuppliersCommodityService.updatePurchasePrice(esc);
		}
		if(num != 0) {
			scMap.clear();
		}
		return num;
	}
	
	// ajax存储被选择的用户(单个)
	@RequestMapping(value = "ajaxChooseUser", method = RequestMethod.POST)
	public @ResponseBody JSONObject ajaxChooseUser(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Map<Long, EcSuppliersCommodity> scMap = new HashMap<Long, EcSuppliersCommodity>();
		if(session.getAttribute("scMap") != null) {
			scMap = (Map<Long,  EcSuppliersCommodity>) session.getAttribute("scMap");
		}
		if("false".equals(params.get("flag")) && scMap.containsKey(Long.parseLong(params.get("id") + ""))) {
			scMap.remove(Long.parseLong(params.get("id") + ""));
		} else if("true".equals(params.get("flag"))) {
			EcSuppliersCommodity esc = ecSuppliersCommodityService.findscDetail(params);
			scMap.put(esc.getId(), esc);
		}
		session.setAttribute("scMap", scMap);
		JSONObject obj = new JSONObject();
		return obj;
	}
	
	// ajax存储被选择的用户(所有)
	@RequestMapping(value = "ajaxChooseAllUser", method = RequestMethod.POST)
	public @ResponseBody JSONObject ajaxChooseAllUser(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Map<Long, EcSuppliersCommodity> scMap = new HashMap<Long, EcSuppliersCommodity>();
		String[] ids = (params.get("id") + "").split(",");
		if(session.getAttribute("scMap") != null) {
			scMap = (Map<Long, EcSuppliersCommodity>) session.getAttribute("scMap");
		}
		for(int i = 0; i < ids.length; i++) {
			if("false".equals(params.get("flag")) && scMap.containsKey(Long.parseLong(ids[i]))) {
				scMap.remove(Long.parseLong(ids[i]));
			} else if("true".equals(params.get("flag")))  {
				params.put("id", ids[i]);
				EcSuppliersCommodity esc = ecSuppliersCommodityService.findscDetail(params);
				scMap.put(esc.getId(), esc);
			}
		}
		session.setAttribute("scMap", scMap);
		JSONObject obj = new JSONObject();
		return obj;
	}
	
}
