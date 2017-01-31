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
import com.gyf.ec.model.EcFileClassification;
import com.gyf.ec.service.EcFileClassificationService;

/**
 * 文件分类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecFileClassification")
public class EcFileClassificationController extends BaseController {
	
	@Resource
	private EcFileClassificationService ecFileClassificationService;
	
	/**
	 * 添加或更新区域
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcFileClassification ecFileClassification) {
		return ecFileClassificationService.saveEcFileClassification(ecFileClassification);
	}
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcFileClassification(Model model){
		model.addAttribute("treeList", JSON.toJSONString(ecFileClassificationService.findAllFileClassification()));
		return "ec/file/fileClassification";
	}
	
	/**
	 * 文档分类树
	 * @return
	 */
	@RequestMapping(value = "tree", method = RequestMethod.POST)
	public @ResponseBody List<EcFileClassification> getAreaTreeList() {
		List<EcFileClassification> list = ecFileClassificationService.findAllFileClassification();
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
		List<EcFileClassification> list = ecFileClassificationService.findFileClassificationList(params);
		model.addAttribute("page", new PageInfo<EcFileClassification>(list));
		return "ec/file/fileClassification-list";
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
	public String showLayer(Long id, Long parentId,
			@PathVariable("mode") String mode, Model model) {
		EcFileClassification ecFileClassification = null, pEcFileClassification = null;
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			pEcFileClassification = ecFileClassificationService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			ecFileClassification = ecFileClassificationService.selectByPrimaryKey(id);
			pEcFileClassification = ecFileClassificationService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			ecFileClassification = ecFileClassificationService.selectByPrimaryKey(id);
			pEcFileClassification = ecFileClassificationService.selectByPrimaryKey(ecFileClassification.getParentId());
		}
		model.addAttribute("pEcFileClassification", pEcFileClassification).addAttribute("ecFileClassification", ecFileClassification);
		return mode.equals("detail") ? "ec/file/fileClassification-detail"
				: "ec/file/fileClassification-save";
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
			count = ecFileClassificationService.deleteEcFileClassificationByRootId(id);
		}
		return count;
	}
}
