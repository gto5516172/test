package com.gyf.ec.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.common.excel.EasyXls;
import com.gohuinuo.common.excel.ExcelUtils;
import com.gohuinuo.web.sys.model.SysArea;
import com.gohuinuo.web.sys.model.SysDict;
import com.gohuinuo.web.sys.service.SysAreaService;
import com.gohuinuo.web.sys.service.SysDictService;
import com.google.common.collect.Maps;
import com.gyf.ec.model.EcSuppliers;
import com.gyf.ec.service.EcSuppliersService;

/**
 * 供应商
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecsuppliers")
public class EcSuppliersController extends BaseController {
	
	@Resource
	private EcSuppliersService ecSuppliersService;
	@Resource
	private SysAreaService sysAreaService;
	@Resource
	private SysDictService sysDictService;



	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcSuppliers(Model model){
		return "ec/suppliers/suppliers";
	}
	
	@RequestMapping(value = "toEcSuppliersAdd", method = RequestMethod.GET)
	public String toEcSuppliersAdd(Model model) {
		return "ec/suppliers/suppliers-add";
	}

	/**
	 * 分页显示
	* @param params
	* @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params,Model model){
		PageInfo<EcSuppliers> page = ecSuppliersService.findPageInfo(params);
		model.addAttribute("page", page);
		return "ec/suppliers/suppliers-list";
	}
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcSuppliers ecSuppliers){
		return ecSuppliersService.saveEcSuppliers(ecSuppliers);
	}
	
	/**
	 * 弹窗显示
	* @param params {"mode":"1.add 2.edit 3.detail}
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable String mode, Model model){
		EcSuppliers ecSuppliers = null;
		SysArea area = null;
		if(StringUtils.equals("edit", mode) || StringUtils.equals("detail", mode)){
			ecSuppliers = ecSuppliersService.selectByPrimaryKey(id);
			area = sysAreaService.selectByPrimaryKey(ecSuppliers.getAreaId());
		}
		model.addAttribute("ecSuppliers", ecSuppliers).addAttribute("area", area);
		return mode.equals("detail")?"ec/suppliers/suppliers-detail":"ec/suppliers/suppliers-save";
	}
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return ecSuppliersService.deleteEcSuppliers(id);
	}
	
	/**
	 * 更新状态
	 * @param 
	 * @return
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	public @ResponseBody Integer updateStatus(Long id, Integer status) {
		EcSuppliers ecSuppliers = null;
		ecSuppliers = ecSuppliersService.selectByPrimaryKey(id);
		ecSuppliers.setStatus(status);
		return ecSuppliersService.updateStatus(ecSuppliers);
	}
	
	/**
	 * 导出execl (示例)
	 */
	@RequestMapping(value = "export",method = RequestMethod.POST)
	public void exportFile(@RequestParam Map<String, Object> params,
			HttpServletResponse response){

		List<EcSuppliers> list = ecSuppliersService.findAllSuppliers();
		
		Map<String, String> titleMap = Maps.newLinkedHashMap();
		titleMap.put("供应商名称","suppliersName");
		titleMap.put("助记简称","abbreviation");
		titleMap.put("联系人1","personName1");
		titleMap.put("电话1","personPhone1");
		titleMap.put("联系人2","personName2");
		titleMap.put("电话2","personPhone2");
		titleMap.put("地址","suppliersAddr");
		titleMap.put("供应商类型","suppliersType");
		titleMap.put("供应商品数","comSize");
		titleMap.put("状态","status");
		
		try {
			//流的方式直接下载
			ExcelUtils.exportExcel(response, "供应商.xls", list, titleMap);
			
			//生成excel先存到服务器，之后客户端再下载
			/*String fileName = "区域.xls";
			String filePath = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/"+fileName;
			ExcelUtils.exportExcel(response, filePath, fileName, list, titleMap);*/
			
			//模板形式
			/*String fileName = "区域.xlsx";
			String templatePath = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/test.xlsx";
			String outPath = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/"+fileName;
			Map<String, Object> data = Maps.newHashMap();
			data.put("username", "张三");
			data.put("name", "韩流");
			//方式1
			ExcelUtils.exportExcel(response, templatePath, "区域.xlsx", data);
			//方式2
			ExcelUtils.exportExcel(response, templatePath, outPath, fileName, data);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * execl导入数据
	 * @throws IOException
	 */
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public @ResponseBody void importFile(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		int count = 0;
		int failCount = 0;
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("");
			String xmlPath = rootPath + "/file/excelXml/ecSuppliers.xml";
			CommonsMultipartFile cf= (CommonsMultipartFile)myfile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File file = fi.getStoreLocation();
	        List<SysDict> dlist = (List<SysDict>) sysDictService.findAllMultimap().get("suppliers_type");
			List<String> vlist = new ArrayList<String>();
			for(SysDict sd : dlist) {
				vlist.add(sd.getValue());
			}
			List<EcSuppliers> list = (List<EcSuppliers>) EasyXls.xls2List(xmlPath, file);
			Map<String, String> errorMap = new HashMap<String, String>();
			for (int i = 1; i <= list.size(); i++) {
				EcSuppliers ecSuppliers = list.get(i - 1);
				if(StringUtils.isEmpty(ecSuppliers.getSuppliersName())) {
					errorMap.put("第" + i + "行", "供应商名称为空");
					failCount++;
					continue;
				}
				ecSuppliers.setSuppliersLevel(0);
				if(ecSuppliers.getSuppliersType() == null || !vlist.contains(ecSuppliers.getSuppliersType() + "")) {
					if(vlist.size() > 0) {
						ecSuppliers.setSuppliersType(Integer.parseInt(vlist.get(0)));
					} else {
						ecSuppliers.setSuppliersType(0);
					}
				}
				ecSuppliersService.insertSelective(ecSuppliers);
				count++;
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("成功导入"+count + "条数据!失败" + failCount + "条数据!失败原因:" + JSONObject.toJSONString(errorMap));
		} catch (Exception e) {
			response.getWriter().write("导入失败");
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "downloadModel")
	public void downloadModel( HttpServletRequest request,HttpServletResponse response){
		String rootPath = request.getSession().getServletContext().getRealPath("");
		String templatePath = rootPath + "/file/model/suppliers.xls";
		try {
			ExcelUtils.exportExcelTemplate(response, templatePath, "供应商模板.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
