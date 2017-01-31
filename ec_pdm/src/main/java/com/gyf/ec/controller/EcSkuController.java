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
import javax.servlet.http.HttpSession;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bety.web.service.CommoditySpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.common.excel.EasyXls;
import com.gohuinuo.common.excel.ExcelUtils;
import com.gohuinuo.web.sys.model.SysDict;
import com.gohuinuo.web.sys.service.SysDictService;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcCatalogue;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.model.EcSuppliers;
import com.gyf.ec.model.EcSuppliersCommodity;
import com.gyf.ec.service.EcCatalogueService;
import com.gyf.ec.service.EcSkuService;
import com.gyf.ec.service.EcSuppliersCommodityService;
import com.gyf.ec.service.EcSuppliersService;

/**
 *  
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecSku")
public class EcSkuController extends BaseController {
	
	@Resource
	private EcCatalogueService ecCatalogueService;
	
	@Resource
	private EcSuppliersService ecSuppliersService;
	
	@Resource
	private EcSkuService ecSkuService;
	
	@Resource
	private EcSuppliersCommodityService ecSuppliersCommodityService;
	
	@Resource
	private CommoditySpuService commoditySpuService;
	
	@Resource
	private SysDictService sysDictService;
	
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcSku(Model model){
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue())).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("spuList", commoditySpuService.findAllSpu());
		return "ec/sku/ecSku";
	}
	
	@RequestMapping(value = "toSkuSave", method = RequestMethod.GET)
	public String toSkuSave(Model model) {
		model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue())).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("spuList", commoditySpuService.findAllSpu());
		return "ec/sku/ecSku-save";
	}
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcSku ecSku){
		return ecSkuService.saveEcSku(ecSku);
	}
	
	/**
	 */
	@RequestMapping(value="layer",method=RequestMethod.GET)
	public String layer(Long id, Model model){
		EcSku ecSku = null;
		List<EcSuppliersCommodity> esc = null;
		EcCatalogue ecCatalogue = null;
		ecSku = ecSkuService.selectByPrimaryKey(id);
		ecCatalogue = ecCatalogueService.selectByPrimaryKey(ecSku.getEcCatalogueId());
		esc = ecSuppliersCommodityService.findEcSuppliersCommodityBySku(id);
		model.addAttribute("ecSku", ecSku).addAttribute("esc", esc).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("ecCatalogue", ecCatalogue).addAttribute("spuList", commoditySpuService.findAllSpu());
		return "ec/sku/ecSku-edit";
	}
	
	/**
	 */
	@RequestMapping(value="layer2",method=RequestMethod.GET)
	public String layer2(Long id, Model model){
		EcSku ecSku = null;
		List<EcSuppliersCommodity> esc = null;
		EcCatalogue ecCatalogue = null;
		ecSku = ecSkuService.selectByPrimaryKeyMy(id);
		ecCatalogue = ecCatalogueService.selectByPrimaryKey(ecSku.getEcCatalogueId());
		esc = ecSuppliersCommodityService.findEcSuppliersCommodityBySku(id);
		model.addAttribute("ecSku", ecSku).addAttribute("esc", esc).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("ecCatalogue", ecCatalogue).addAttribute("spuList", commoditySpuService.findAllSpu());
		return "ec/sku/ecSku-detail";
	}

	/**
	 */
	@RequestMapping(value="{mode}/layer3")
	public String layer3(Long id,String spuNo,Integer category,@PathVariable("mode") String mode,Model model, HttpServletRequest request,
			HttpServletResponse response){
		if(StringUtils.equalsIgnoreCase(mode, "edit")){
			EcSku ecSku = ecSkuService.selectByPrimaryKey(id);
			model.addAttribute("ecSku", ecSku);
			model.addAttribute("spuNo",ecSku.getSpuNo());
			return "commoditySpu/ecSku-save";
		}else if(StringUtils.equalsIgnoreCase(mode, "add")){
			model.addAttribute("spuNo",spuNo);
			return "commoditySpu/ecSku-save";
		}else if(StringUtils.equalsIgnoreCase(mode, "taskSearch")){
			model.addAttribute("category",category);
			model.addAttribute("ecCataloue", JSON.toJSONString(ecCatalogueService.findAllEcCatalogue())).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("spuList", commoditySpuService.findAllSpu());
			return "ec/task/ecSku";
		} else if(StringUtils.equalsIgnoreCase(mode, "editBatch")) {
			HttpSession session = request.getSession();
			Map<Long, EcSku> skuMap = new HashMap<Long, EcSku>();
			String str = "";
			if(session.getAttribute("skuMap") != null) {
				skuMap = (Map<Long,  EcSku>) session.getAttribute("skuMap");
				for(EcSku esku : skuMap.values()) {
					str = str + esku.getSku() + ",";
				}
				str = str.substring(0, str.length());
			}
			model.addAttribute("skuMap", skuMap).addAttribute("spuList", commoditySpuService.findAllSpu()).addAttribute("ecSuppliersList", ecSuppliersService.findAllSuppliers()).addAttribute("skuBatch", str);
			return "ec/sku/ecSku-saveBatch";
		}else{
			EcSku ecSku = ecSkuService.selectByPrimaryKey(id);
			model.addAttribute("ecSku", ecSku);
			return "commoditySpu/ecSku-detail";
		}
	}
	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model, HttpServletRequest request, HttpServletResponse response) {
		PageHelper.startPage(params);
		long userId = SysUserUtils.getCacheLoginUser().getId();
		params.put("userId", userId);
		HttpSession session = request.getSession();
		Map<Long, EcSuppliersCommodity> skuMap = new HashMap<Long, EcSuppliersCommodity>();
		if(session.getAttribute("skuMap") != null) {
			skuMap = (Map<Long, EcSuppliersCommodity>) session.getAttribute("skuMap");
		}
		List<EcSku> list = ecSkuService.findSkuList(params);
		model.addAttribute("page", new PageInfo<EcSku>(list)).addAttribute("myId", userId).addAttribute("skuMap", skuMap);
		return "ec/sku/ecSku-list";
	}
	
	@RequestMapping(value = "listBySpuNo", method = RequestMethod.POST)
	public String listBySpuNo(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		long userId = SysUserUtils.getCacheLoginUser().getId();
		params.put("userId", userId);
		List<EcSku> list = ecSkuService.findSkuList(params);
		model.addAttribute("isEdit" ,params.get("isEdit"));
		model.addAttribute("page", new PageInfo<EcSku>(list)).addAttribute("myId", userId);
		return "commoditySpu/ecSku-list";
	}
	
	@RequestMapping(value = "listBySearch", method = RequestMethod.POST)
	public String listBySearch(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<EcSku> list = ecSkuService.findSkuList(params);
		model.addAttribute("page", new PageInfo<EcSku>(list));
		model.addAttribute("category",params.get("category"));
		return "ec/task/ecSku-list";
	}
	
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return ecSkuService.deleteEcSku(id);
	}
	
	/***
	 * ajax验证唯一性
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateNameByAjax",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateNameByAjax(String param,Long id) throws IOException{
		return ecSkuService.getSkuNoCountByName(param,id);
	}
	
	/***
	 * ajax验证唯一性
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="validateNameByAjaxSale",method=RequestMethod.POST)
	public @ResponseBody JSONObject validateNameByAjaxSale(@ModelAttribute EcSku sku,Long id) throws IOException{
		return ecSkuService.getSaleSkuNoCountByName(sku.getSaleSku(),id);
	}
	
	/***
	 * ajax根据sku编码查找sku
	 * @param hsr
	 * @throws IOException
	 */
	@RequestMapping(value="getByAjaxSkuNo",method=RequestMethod.POST)
	public @ResponseBody JSONObject getByAjaxSkuNo(String skuNo) throws IOException{
		return ecSkuService.getByAjaxSkuNo(skuNo);
	}
	@RequestMapping(value="getByAjaxIds",method=RequestMethod.POST)
	public @ResponseBody JSONObject getByAjaxIds(String ids){
		return ecSkuService.getByAjaxIds(ids);
	}
	
	// ajax存储被选择的用户(单个)
	@RequestMapping(value = "ajaxChooseSku", method = RequestMethod.POST)
	public @ResponseBody JSONObject ajaxChooseSku(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Map<Long, EcSku> skuMap = new HashMap<Long, EcSku>();
		if(session.getAttribute("skuMap") != null) {
			skuMap = (Map<Long,  EcSku>) session.getAttribute("skuMap");
		}
		if("false".equals(params.get("flag")) && skuMap.containsKey(Long.parseLong(params.get("id") + ""))) {
			skuMap.remove(Long.parseLong(params.get("id") + ""));
		} else if("true".equals(params.get("flag"))) {
			EcSku esc = ecSkuService.selectByPrimaryKeyMy(Long.parseLong(params.get("id") + ""));
			skuMap.put(esc.getId(), esc);
		}
		session.setAttribute("skuMap", skuMap);
		JSONObject obj = new JSONObject();
		return obj;
	}
	
	// ajax存储被选择的用户(所有)
	@RequestMapping(value = "ajaxChooseAllSku", method = RequestMethod.POST)
	public @ResponseBody JSONObject ajaxChooseAllSku(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Map<Long, EcSku> skuMap = new HashMap<Long, EcSku>();
		String[] ids = (params.get("id") + "").split(",");
		if(session.getAttribute("skuMap") != null) {
			skuMap = (Map<Long, EcSku>) session.getAttribute("skuMap");
		}
		for(int i = 0; i < ids.length; i++) {
			if("false".equals(params.get("flag")) && skuMap.containsKey(Long.parseLong(ids[i]))) {
				skuMap.remove(Long.parseLong(ids[i]));
			} else if("true".equals(params.get("flag")))  {
				params.put("id", ids[i]);
				EcSku esc = ecSkuService.selectByPrimaryKeyMy(Long.parseLong(params.get("id") + ""));
				skuMap.put(esc.getId(), esc);
			}
		}
		session.setAttribute("skuMap", skuMap);
		JSONObject obj = new JSONObject();
		return obj;
	}
	
	@RequestMapping(value = "updateSuggestedRetailPrice", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateSuggestedRetailPrice(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		String suggestedRetailPrice = params.get("suggestedRetailPrice") + "";
		EcSku esc = ecSkuService.selectByPrimaryKeyMy(Long.parseLong(params.get("id") + ""));
		esc.setSuggestedRetailPrice(suggestedRetailPrice);
		Integer num = ecSkuService.updateSuggestedRetailPrice(esc);
		obj.put("num", num);
		return obj;
	}
	
	/**
	 * 批量修改
	* @param params
	* @return
	 */
	@RequestMapping(value="saveBatch",method=RequestMethod.POST)
	public @ResponseBody Integer saveBatch(@ModelAttribute EcSku ecSku, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Map<Long, EcSku> skuMap = new HashMap<Long, EcSku>();
		if(session.getAttribute("skuMap") != null) {
			skuMap = (Map<Long,  EcSku>) session.getAttribute("skuMap");
		}
		int num = 0;
		for(EcSku esc : skuMap.values()) {
			if(ecSku.getStatus() != -1) {
				esc.setStatus(ecSku.getStatus());
			}
			if(StringUtils.isNotEmpty(ecSku.getPlaceOfOrigin())) {
				esc.setPlaceOfOrigin(ecSku.getPlaceOfOrigin());
			}
			if(ecSku.getAttr() != -1) {
				esc.setAttr(ecSku.getAttr());
			}
			if(!"0".equals(ecSku.getSpuNo())) {
				esc.setSpuNo(ecSku.getSpuNo());
			}
			if(ecSku.getEcCatalogueId() != 0) {
				esc.setEcCatalogueId(ecSku.getEcCatalogueId());
			}
			if(ecSku.getSuppliersIds()[0] != 0) {
				esc.setSuppliersIds(ecSku.getSuppliersIds());
			}
			if(ecSku.getSuppliersIds()[0] != 0 && StringUtils.isNotEmpty(ecSku.getFactoryNo()[0])) {
				esc.setFactoryNo(ecSku.getFactoryNo());
			}
			if(ecSku.getSuppliersIds()[0] != 0 && StringUtils.isNotEmpty(ecSku.getPurchasePrice()[0])) {
				esc.setPurchasePrice(ecSku.getPurchasePrice());
			}
			if(ecSku.getSuppliersIds()[0] != 0 && StringUtils.isNotEmpty(ecSku.getMinNum()[0])) {
				esc.setMinNum(ecSku.getMinNum());
			}
			if(ecSku.getPackageType() != -1) {
				esc.setPackageType(ecSku.getPackageType());
			}
			if(StringUtils.isNotEmpty(ecSku.getSalesStaff())) {
				esc.setSalesStaff(ecSku.getSalesStaff());
			}
			esc.setCompany(ecSku.getCompany());
			if(StringUtils.isNotEmpty(ecSku.getShippingWeight())) {
				esc.setShippingWeight(ecSku.getShippingWeight());
			}
			if(ecSku.getShippingWeightCompany() != -1) {
				esc.setShippingWeightCompany(ecSku.getShippingWeightCompany());
			}
			if(StringUtils.isNotEmpty(ecSku.getItemWeight())) {
				esc.setItemWeight(ecSku.getItemWeight());
			}
			if(ecSku.getItemWeightCompany() != -1) {
				esc.setItemWeightCompany(ecSku.getItemWeightCompany());
			}
			if(StringUtils.isNotEmpty(ecSku.getCostPrice())) {
				esc.setCostPrice(ecSku.getCostPrice());
			}
			num = ecSkuService.saveEcSku(esc);
		}
		if(num != 0) {
			skuMap.clear();
		}
		return num;
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
			String xmlPath = rootPath + "/file/excelXml/ecSku.xml";
			CommonsMultipartFile cf= (CommonsMultipartFile)myfile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File file = fi.getStoreLocation();
	        
	        List<SysDict> saDlist = (List<SysDict>) sysDictService.findAllMultimap().get("sku_attr");
	        List<String> salist = new ArrayList<String>();
			for(SysDict sd : saDlist) {
				salist.add(sd.getValue());
			}
			
			List<SysDict> ssDlist = (List<SysDict>) sysDictService.findAllMultimap().get("sku_status");
			List<String> sslist = new ArrayList<String>();
			for(SysDict sd : ssDlist) {
				sslist.add(sd.getValue());
			}
			// 尺寸单位
			List<SysDict> ccDlist = (List<SysDict>) sysDictService.findAllMultimap().get("commodity_company");
			List<String> cclist = new ArrayList<String>();
			for(SysDict cc : ccDlist) {
				cclist.add(cc.getValue());
			}
			// 包材类型
			List<SysDict> ptDlist = (List<SysDict>) sysDictService.findAllMultimap().get("package_type");
			List<String> ptlist = new ArrayList<String>();
			for(SysDict pt : ptDlist) {
				ptlist.add(pt.getValue());
			}
			// 单位 
			List<SysDict> suDlist = (List<SysDict>) sysDictService.findAllMultimap().get("spu_units");
			List<String> sulist = new ArrayList<String>();
			for(SysDict su : suDlist) {
				sulist.add(su.getValue());
			}
			// 重量单位
			List<SysDict> wcDlist = (List<SysDict>) sysDictService.findAllMultimap().get("weight_company");
			List<String> wclist = new ArrayList<String>();
			for(SysDict wc : wcDlist) {
				wclist.add(wc.getValue());
			}
			List<EcSku> list = (List<EcSku>) EasyXls.xls2List(xmlPath, file);
			Map<String, String> errorMap = new HashMap<String, String>();
			for (int i = 1; i <= list.size(); i++) {
				EcSku ecSku = list.get(i-1);
				if(StringUtils.isEmpty(ecSku.getSku()) || StringUtils.isEmpty(ecSku.getName())) {
					errorMap.put("第" + i + "行", "sku或者商品名称为空");
					failCount++;
					continue;
				} else {
					// 验证SKU是否重复
					JSONObject obj = ecSkuService.getSkuNoCountByName(ecSku.getSku(), null);
					if("n".equals(obj.get("status"))) {
						errorMap.put("第" + i + "行", "sku已经存在");
						failCount++;
						continue;
					}
				}
				if(StringUtils.isNotEmpty(ecSku.getSaleSku())) {
					// 验证销售SKU是否重复
					JSONObject obj = ecSkuService.getSaleSkuNoCountByName(ecSku.getSaleSku(), null);
					if("n".equals(obj.get("status"))) {
						errorMap.put("第" + i + "行", "销售sku已经存在");
						failCount++;
						continue;
					}
				}
				
				// 验证是否存在产品属性
				if(ecSku.getAttr() != null && !salist.contains(ecSku.getAttr() + "")) {
					errorMap.put("第" + i + "行", "产品属性ID不存在");
					failCount++;
					continue;
				}
				// 验证是否存在商品状态
				if(ecSku.getStatus() != null && !sslist.contains(ecSku.getStatus() + "")) {
					errorMap.put("第" + i + "行", "商品状态ID不存在");
					failCount++;
					continue;
				}
				// 验证关联SPU是否存在
				if(StringUtils.isNotEmpty(ecSku.getSpuNo()) && commoditySpuService.findCountByName(ecSku.getSpuNo()) == 0) {
					errorMap.put("第" + i + "行", "关联SPU不存在");
					failCount++;
					continue;
				}
				// 验证是否存在商品细类目录
				if(ecSku.getEcCatalogueId() != null) {
					if(ecCatalogueService.selectByPrimaryKey(ecSku.getEcCatalogueId()) == null) {
						errorMap.put("第" + i + "行", "商品细类目录ID不存在");
						failCount++;
						continue;
					}
				} else {
					errorMap.put("第" + i + "行", "商品细类目录为空");
					failCount++;
					continue;
				}
				// 验证尺寸单位是否存在
				if(StringUtils.isNotEmpty(ecSku.getCommodityCompany()) && !cclist.contains(ecSku.getCommodityCompany())) {
					errorMap.put("第" + i + "行", "商品尺寸单位ID不存在");
					failCount++;
					continue;
				}
				if(StringUtils.isNotEmpty(ecSku.getPackingCompany()) && !cclist.contains(ecSku.getPackingCompany())) {
					errorMap.put("第" + i + "行", "包装尺寸单位ID不存在");
					failCount++;
					continue;
				}
				// 验证包材类型是否存在
				if(ecSku.getPackageType() != null && !ptlist.contains(ecSku.getPackageType() + "")) {
					errorMap.put("第" + i + "行", "包材类型ID不存在");
					failCount++;
					continue;
				}
				// 验证单位是否存在
				if(StringUtils.isNotEmpty(ecSku.getCompany()) && !suDlist.contains(ecSku.getCompany())) {
					errorMap.put("第" + i + "行", "单位ID不存在");
					failCount++;
					continue;
				}
				// 验证重量单位是否存在
				if(ecSku.getShippingWeightCompany() != null && !cclist.contains(ecSku.getShippingWeightCompany() + "")) {
					errorMap.put("第" + i + "行", "商品毛重单位ID不存在");
					failCount++;
					continue;
				}
				if(ecSku.getItemWeightCompany() != null && !cclist.contains(ecSku.getItemWeightCompany() + "")) {
					errorMap.put("第" + i + "行", "商品净重单位ID不存在");
					failCount++;
					continue;
				}
				ecSkuService.insertSelective(ecSku);
				count++;
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("成功导入"+count + "条数据!失败了" + failCount + "条数据!失败原因:"+JSONObject.toJSONString(errorMap));
		} catch (Exception e) {
			response.getWriter().write("导入失败");
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "downloadModel")
	public void downloadModel( HttpServletRequest request,HttpServletResponse response){
		String rootPath = request.getSession().getServletContext().getRealPath("");
		String templatePath = rootPath + "/file/model/sku.xls";
		try {
			ExcelUtils.exportExcelTemplate(response, templatePath, "sku模板.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
