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

import com.bety.web.model.Shop;
import com.bety.web.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("shop")
public class ShopController {
	@Resource
	private ShopService shopService;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "shop/shop";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<Shop> list = shopService.findPageInfo(params);
		model.addAttribute("page", list);
		return "shop/list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute Shop obj) {
		return shopService.saveShop(obj);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return shopService.deleteShop(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/layer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable("mode") String mode,Model model){
		Shop pf = shopService.selectByPrimaryKey(id);
		model.addAttribute("shop", pf);
		if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			return "shop/edit";
		}if(StringUtils.equalsIgnoreCase(mode, "add")){
			return "shop/save";
		}else{
		return "shop/detail";
		}
	}
}
