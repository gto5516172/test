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
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcCatalogue;
import com.gyf.ec.service.EcCatalogueService;

/**
 * 商品目录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecCatalogue")
public class EcCatalogueController extends BaseController {
	
	
	@Resource
	private EcCatalogueService ecCatalogueService;
	
	
	/**
	 * 添加或更新区域
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcCatalogue ecCatalogue, String mytid, Model model) {
		model.addAttribute("mytid", mytid);
		return ecCatalogueService.saveEcCatalogue(ecCatalogue);
	}
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcCatalogue(Model model){
		model.addAttribute("treeList", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue()));
		return "ec/commodity/ecCatalogue";
	}
	
	/**
	 * 文档分类树
	 * @return
	 */
	@RequestMapping(value = "tree", method = RequestMethod.POST)
	public @ResponseBody List<EcCatalogue> getEcCatalogueTreeList() {
		List<EcCatalogue> list = ecCatalogueService.findAllEcCatalogue();
		return list;
	}
	
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<EcCatalogue> list = ecCatalogueService.findEcCatalogueList(params);
		model.addAttribute("page", new PageInfo<EcCatalogue>(list));
		return "ec/commodity/ecCatalogue-list";
	}

	/**
	 * 弹窗
	 * 
	 * @param id
	 * @param parentId
	 *            父类id
	 * @param mode
	 *            模式(add,edit,detail)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{mode}/showlayer", method = RequestMethod.POST)
	public String showLayer(Long id, Long parentId, String mytid, 
			@PathVariable("mode") String mode, Model model) {
		EcCatalogue ecCatalogue = null, pEcCatalogue = null;
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			pEcCatalogue = ecCatalogueService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			ecCatalogue = ecCatalogueService.selectByPrimaryKey(id);
			pEcCatalogue = ecCatalogueService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			ecCatalogue = ecCatalogueService.selectByPrimaryKey(id);
			pEcCatalogue = ecCatalogueService.selectByPrimaryKey(ecCatalogue.getParentId());
		}
		model.addAttribute("mytid", mytid).addAttribute("pEcCatalogue", pEcCatalogue).addAttribute("ecCatalogue", ecCatalogue);
		return mode.equals("detail") ? "ec/commodity/ecCatalogue-detail"
				: "ec/commodity/ecCatalogue-save";
	}
	
	/**
	 * 删除文档分类及其子文档分类
	 * 
	 * @param resourceId
	 *            文档分类id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Integer dels(Long id) {
		Integer count = 0;
		if (null != id) {
			count = ecCatalogueService.deleteEcCatalogueByRootId(id);
		}
		return count;
	}
}
