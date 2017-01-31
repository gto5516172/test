package com.gyf.ec.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gyf.ec.model.EcFileLog;
import com.gyf.ec.service.EcFileLogService;

/**
 * 文件日志
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecFileLog")
public class EcFileLogController extends BaseController {
	
	
	
	@Resource
	private EcFileLogService ecFileLogService;
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping(value = "tolist", method = RequestMethod.POST)
	public String tolist(@RequestParam Map<String, Object> params, Model model){
		model.addAttribute("ecFileId", params.get("ecFileId"));
		return "ec/file/fileLog";
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
		List<EcFileLog> list = ecFileLogService.findFileList(params);
		model.addAttribute("page", new PageInfo<EcFileLog>(list)).addAttribute("ecFileId", params.get("ecFileId"));
		return "ec/file/fileLog-list";
	}
}
