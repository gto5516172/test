package com.gyf.ec.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcBom;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.service.EcBomService;

/**
 * 商品配件bom
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecBom")
public class EcBomController extends BaseController {
	
	@Resource
	private EcBomService ecBomService;
	
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<EcBom> list = ecBomService.findBomList(params);
		model.addAttribute("ecBomPage", new PageInfo<EcBom>(list));
		return "ec/sku/ecBom-list";
	}
	
	/**
	 * 弹窗
	 * 
	 * @param id
	 * @param skuId
	 *            skuId
	 * @param mode
	 *            模式(add,edit,detail)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{mode}/showlayer", method = RequestMethod.POST)
	public String showLayer(Long id, Long skuId,
			@PathVariable("mode") String mode, Model model) {
		EcBom ecBom = null;
		String url = "";
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			url =  "ec/sku/ecBom-save";
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			ecBom = ecBomService.findBom(id);
			url =  "ec/sku/ecBom-edit";
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			ecBom = ecBomService.findBom(id);
			url =  "ec/sku/ecBom-detail";
		}
		model.addAttribute("ecBom", ecBom).addAttribute("skuId", skuId);
		return url;
	}
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcBom ecBom){
		return ecBomService.saveEcBom(ecBom);
	}
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return ecBomService.deleteEcBom(id);
	}
	
	@RequestMapping(value="querySku",method=RequestMethod.POST)
	public @ResponseBody JSONObject querySku(@ModelAttribute EcBom bom) {
		return ecBomService.querySku(bom);
	}
}
