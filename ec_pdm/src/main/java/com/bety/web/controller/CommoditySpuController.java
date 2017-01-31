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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bety.web.model.CommoditySpu;
import com.bety.web.model.ImportExcel;
import com.bety.web.service.CommoditySpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.excel.EasyXls;
import com.gohuinuo.common.excel.ExcelUtils;
import com.gyf.ec.model.EcCatalogue;
import com.gyf.ec.service.EcCatalogueService;
import com.gyf.ec.service.EcSuppliersCommodityService;
import com.gyf.ec.service.EcSuppliersService;

@Controller
@RequestMapping("commoditySpu")
public class CommoditySpuController {
	@Resource
	private EcCatalogueService ecCatalogueService;
	@Resource
	private CommoditySpuService commoditySpuService;
	@Resource
	private EcSuppliersService ecSuppliersService;
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewInfo(Model model) {
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue())).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("spuList", commoditySpuService.findAllSpu());
		return "commoditySpu/commoditySpu";
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		EcCatalogue ecCatalogue = ecCatalogueService.selectByPrimaryKey(null);
		model.addAttribute("ecCatalogue", ecCatalogue);
		return "commoditySpu/add";
	}
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(Model model,Long id) {
		CommoditySpu pf = commoditySpuService.findById(id);
		model.addAttribute("commoditySpu", pf);
		return "commoditySpu/detail";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model,Long id) {
		CommoditySpu pf = commoditySpuService.findById(id);
		EcCatalogue ecCatalogue = ecCatalogueService.selectByPrimaryKey(pf.getType());
		model.addAttribute("commoditySpu", pf).addAttribute("ecCatalogue", ecCatalogue);
		return "commoditySpu/edit";
	}
	
	/**
	 * 列表
	* @param params
	* @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String findPageInfo(@RequestParam Map<String, Object> params, Model model) {
		
		PageHelper.startPage(params);
		PageInfo<CommoditySpu> list = commoditySpuService.findPageInfo(params);
		model.addAttribute("page", list);
		return "commoditySpu/list";
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute CommoditySpu obj) {
		return commoditySpuService.saveSpu(obj);
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return commoditySpuService.deleteSpuById(id);
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
//	@RequestMapping(value="{mode}/layer",method=RequestMethod.POST)
//	public String layer(Long id,@PathVariable("mode") String mode,Model model){
//		if(StringUtils.equalsIgnoreCase(mode, "edit")){
//			CommoditySpu pf = commoditySpuService.selectByPrimaryKey(id);
//			model.addAttribute("commoditySpu", pf);
//			return "commoditySpu/edit";
//		}else if(StringUtils.equalsIgnoreCase(mode, "add")){
//			return "commoditySpu/save";
//		}else{
//			CommoditySpu pf = commoditySpuService.selectByPrimaryKey(id);
//			model.addAttribute("commoditySpu", pf);
//			return "commoditySpu/detail";
//		}
//	}
	/***
	 * ajax验证唯一性
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateNameByAjax",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateNameByAjax(String param,Long id) throws IOException{
		return commoditySpuService.getSpuNoCountByName(param,id);
	}
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public @ResponseBody void importFile(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int count = 0;
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("");
			String xmlPath = rootPath + "/file/excelXml/importSpuExcel.xml";
			CommonsMultipartFile cf= (CommonsMultipartFile)myfile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File file = fi.getStoreLocation();
			
			List<CommoditySpu> list = (List<CommoditySpu>) EasyXls.xls2List(xmlPath, file);
			count = commoditySpuService.saveImportSpu(list);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("成功导入"+count + "条数据!");
		} catch (Exception e) {
			response.getWriter().write("导入失败");
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "downloadModel")
	public void downloadModel( HttpServletRequest request,HttpServletResponse response){
		String rootPath = request.getSession().getServletContext().getRealPath("");
		String templatePath = rootPath + "/file/model/spu.xls";
		try {
			ExcelUtils.exportExcelTemplate(response, templatePath, "spu模板.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
