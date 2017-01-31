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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcTaskType;
import com.gyf.ec.service.EcTaskTypeService;

/**
 * 任务类型管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecTaskType")
public class EcTaskTypeController extends BaseController {
	
	@Resource
	private EcTaskTypeService service;
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcTaskType(Model model){
		return "ec/taskType/ecTaskType";
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
		long userId = SysUserUtils.getCacheLoginUser().getId();
		params.put("userId", userId);
		List<EcTaskType> list = service.findTaskTypeList(params);
		model.addAttribute("page", new PageInfo<EcTaskType>(list));
		return "ec/taskType/ecTaskType-list";
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
		EcTaskType ecTaskType = null;
		String url = "";
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			url =  "ec/taskType/ecTaskType-save";
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			ecTaskType = service.findTaskType(id);
			url =  "ec/taskType/ecTaskType-edit";
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			ecTaskType = service.findTaskType(id);
			url =  "ec/taskType/ecTaskType-detail";
		}
		model.addAttribute("ecTaskType", ecTaskType);
		return url;
	}
	
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcTaskType ecTaskType){
		return service.saveEcTaskType(ecTaskType);
	}
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return service.deleteEcTaskType(id);
	}
	
	
	
	
}
