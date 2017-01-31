package com.bety.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bety.web.model.ImportExcel;
import com.bety.web.service.ImportExcelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.excel.EasyXls;
import com.gohuinuo.web.sys.model.SysArea;
@Controller
@RequestMapping("importExcel")
public class ImportExcelController {
	@Resource
	private ImportExcelService service;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		return "importExcel/importExcel";
	}
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		PageInfo<ImportExcel> list = service.findPageInfo(params);
		model.addAttribute("page", list);
		return "importExcel/list";
	}
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public @ResponseBody void importFile(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Long count = 0l;
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("");
			String xmlPath = rootPath + "/file/excelXml/importExcel.xml";
			CommonsMultipartFile cf= (CommonsMultipartFile)myfile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File file = fi.getStoreLocation();
			
			List<ImportExcel> list = (List<ImportExcel>) EasyXls.xls2List(xmlPath, file);
			count = service.insertExcelBatch(list);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("成功导入"+count + "条数据!");
		} catch (Exception e) {
			response.getWriter().write("导入失败");
			e.printStackTrace();
		}
	}
}
