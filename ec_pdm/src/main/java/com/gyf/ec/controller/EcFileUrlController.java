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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcFile;
import com.gyf.ec.model.EcFileLog;
import com.gyf.ec.model.EcFileUrl;
import com.gyf.ec.service.EcFileLogService;
import com.gyf.ec.service.EcFileService;
import com.gyf.ec.service.EcFileUrlService;

/**
 * 文件地址
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecFileUrl")
public class EcFileUrlController extends BaseController {
	
	@Resource
	private EcFileUrlService ecFileUrlService;
	
	@Resource
	private EcFileLogService ecFileLogService; 
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping
	public String toEcFileUrl(Model model) {
		
		return "ec/file/ecFileUrl";
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
		List<EcFileUrl> list = ecFileUrlService.findFileUrlList(params);
		model.addAttribute("page", new PageInfo<EcFileUrl>(list)).addAttribute("myId", userId);
		return "ec/file/ecFileUrl-list";
	}
	
	@RequestMapping(value = "list2", method = RequestMethod.POST)
	public String list2(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		long userId = SysUserUtils.getCacheLoginUser().getId();
		params.put("userId", userId);
		List<EcFileUrl> list = ecFileUrlService.findFileUrlListTwo(params);
		model.addAttribute("page", new PageInfo<EcFileUrl>(list)).addAttribute("myId", userId);
		return "ec/file/ecFileUrl-list";
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
			EcFileUrl ecFileUrl = ecFileUrlService.selectByPrimaryKey(id);
			count = ecFileUrlService.deleteEcFileUrlByRootId(id);
			EcFileLog efl = new EcFileLog();
			efl.setEcFileId(ecFileUrl.getEcFileId());
			efl.setRemarks("删除:"+ecFileUrl.getOriginalFileName());
			efl.setType(1);
			ecFileLogService.saveEcFileLog(efl);
		}
		return count;
	}
	
}
