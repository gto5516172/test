package com.gyf.ec.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.FileType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gohuinuo.common.base.BaseController;
import com.gohuinuo.common.base.UploadFile;
import com.gohuinuo.common.utils.FileUtils;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.service.SysRoleService;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcFile;
import com.gyf.ec.model.EcFileClassification;
import com.gyf.ec.model.EcFileLog;
import com.gyf.ec.model.EcFileUrl;
import com.gyf.ec.service.EcFileClassificationService;
import com.gyf.ec.service.EcFileLogService;
import com.gyf.ec.service.EcFileService;
import com.gyf.ec.service.EcFileUrlService;

/**
 * 文件管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ecFile")
public class EcFileController extends BaseController {
	
	@Resource
	private EcFileClassificationService ecFileClassificationService;
	
	@Resource
	private EcFileService ecFileService;
	
	@Resource
	private EcFileUrlService ecFileUrlService;
	
	@Resource
	private EcFileLogService ecFileLogService;
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toEcFileController(Model model){
		model.addAttribute("treeList", JSON.toJSONString(ecFileClassificationService.findAllFileClassification()));
		return "ec/file/file";
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
		List<EcFile> list = ecFileService.findFileList(params);
		model.addAttribute("page", new PageInfo<EcFile>(list)).addAttribute("myId", userId);
		return "ec/file/file-list";
	}
	
	/**
	 * 弹窗显示
	* @param params {"mode":"1.add 2.edit 3.detail}
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable String mode, Model model){
		EcFile ecFile = null;
		EcFileClassification ecFileClassification = null;
		List<EcFileUrl>  ecFileUrlList = new ArrayList<EcFileUrl>();
		if(StringUtils.equals("edit", mode) || StringUtils.equals("detail", mode)){
			ecFile = ecFileService.selectByPrimaryKey(id);
			ecFileClassification = ecFileClassificationService.selectByPrimaryKey(ecFile.getEcFileClassificationId());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ecFileId", ecFile.getId());
			ecFileUrlList = ecFileUrlService.findFileUrlList(params);
		}
		model.addAttribute("ecFile", ecFile).addAttribute("ecFileClassification", ecFileClassification).addAttribute("ecFileUrlList", ecFileUrlList);
		String str = "";
		if(StringUtils.equals("edit", mode)) {
			str = "ec/file/file-edit";
		} else if(StringUtils.equals("add", mode)) {
			str = "ec/file/file-save";
		} else if(StringUtils.equals("detail", mode)) {
			long userId = SysUserUtils.getCacheLoginUser().getId();
			
			String createId = ecFile.getCreateBy().split(",")[0];
			if(createId.equals("" + userId)){
				model.addAttribute("flag", true);
			} else {
				boolean flag = ecFileService.authentication(userId, ecFile.getId());
				model.addAttribute("flag", flag);
			}
			str = "ec/file/file-detail";
		}
		return str;
	}
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute EcFile ecFile){
		int num = 0;
		long userId = SysUserUtils.getCacheLoginUser().getId();
		ecFile.setUserId(userId);
		long ecFileId = ecFileService.saveEcFile(ecFile);
		if(ecFileId > 0) {
			num = 1;
			String flist = ecFile.getFileList();
			if(ecFile != null && flist != null && !"".equals(flist)) {
				JSONArray jarr = JSON.parseArray("[" + flist + "]");
				String fileName = "";
				for (int i = 0; i < jarr.size(); i++) {
					JSONObject jo = jarr.getJSONObject(i);
					EcFileUrl ecu = new EcFileUrl();
					ecu.setFileName(jo.getString("fileName"));
					ecu.setEcFileId(ecFileId);
					ecu.setSaveDirectory(jo.getString("saveDirectory").replaceAll("/", "\\\\"));
					ecu.setOriginalFileName(jo.getString("originalFileName"));
					ecu.setRelativeUrl(jo.getString("relativeUrl"));
					ecu.setContentType(jo.getString("contentType"));
					ecu.setSuffix(jo.getString("suffix"));
					ecu.setUserId(userId);
					num = ecFileUrlService.saveEcFileUrl(ecu);
					fileName = fileName + "<br>" + jo.getString("originalFileName");
				}
				if(jarr.size() > 0) {
					EcFileLog efl = new EcFileLog();
					efl.setEcFileId(ecFileId);
					efl.setRemarks("上传:" + fileName);
					efl.setType(0);
					ecFileLogService.saveEcFileLog(efl);
				}
			}
		}
		return num;
	}
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return ecFileService.deleteEcFile(id);
	}
	
	/**
	 * 更新状态
	 * @param 
	 * @return
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	public @ResponseBody Integer updateStatus(Long id, Integer status) {
		EcFile ecFile = null;
		ecFile = ecFileService.selectByPrimaryKey(id);
		ecFile.setStatus(status);
		return ecFileService.updateStatus(ecFile);
	}
	
	/**
	 * 文件上传
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody  
    @RequestMapping(value = "/uploadFile",produces="application/json;charset=UTF-8")
	public String uploadFile(@RequestParam("myfile") MultipartFile myfile,
	                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
	    if(!myfile.isEmpty()){
	    	
	    	String originalFilename = myfile.getOriginalFilename();
	    	String suffix = FileType.getSuffixByFilename(originalFilename);
	    	
	    	String rootPath = request.getSession().getServletContext().getRealPath("");
	    	File descDir = new File(rootPath + "/file/ec/file");
			if (!descDir.exists()) {
				FileUtils.createDirectory(rootPath + "/file/ec/file");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String dateTime = sdf.format(date);
			File descDirDate = new File(rootPath + "/file/ec/file/" + dateTime);
			if (!descDirDate.exists()) {
				FileUtils.createDirectory(rootPath + "/file/ec/file/" + dateTime);
			}
			String dateFile = new Date().getTime() + "";
			UploadFile uploadFile = FileUtils.singleUploadFile(rootPath + "/file/ec/file/" + dateTime + "/" + dateFile + suffix, dateFile + suffix, request);
			uploadFile.setRelativeUrl("file/ec/file/" + dateTime + "/" + dateFile + suffix);
			uploadFile.setSuffix(suffix);
	    	return JSON.toJSONString(uploadFile);
	    } else {
	    	return "";
	    }
	}
	
	/**
	 * 文件下载
	 * @param myfile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody  
    @RequestMapping(value = "/downloadFile",produces="application/json;charset=UTF-8")
	public void downloadFile(@RequestParam("downLoadFileId") String downLoadFileId,
	                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		EcFileUrl ecFileUrl = ecFileUrlService.selectByPrimaryKey(Long.parseLong(downLoadFileId));
		FileUtils.downloadFile(response, ecFileUrl.getSaveDirectory().replaceAll("\\\\", "/"), ecFileUrl.getOriginalFileName());
	}
	
	/**
	 * 绑定用户界面
	* @return
	 */
	@RequestMapping(value="binduser",method=RequestMethod.POST)
	public String toBindUser(Long id,Model model){
		List<SysUser> users = ecFileService.findUserByFileId(id);
		model.addAttribute("users", users).addAttribute("fileId", id);
		return "ec/file/file-user";
	}
	
	/**
	 * 保存文件绑定的用户
	* @return
	 */
	@RequestMapping(value="saveuser",method=RequestMethod.POST)
	public @ResponseBody Integer saveUserFile(@ModelAttribute EcFile ecFile){
		return ecFileService.saveUserFile(ecFile);
	}
	
	/**
	 * 文件上传
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody  
    @RequestMapping(value = "/uploadFile2",produces="application/json;charset=UTF-8")
	public String uploadFile2(@RequestParam("myfile") MultipartFile myfile,
	                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
	    if(!myfile.isEmpty()){
	    	
	    	String originalFilename = myfile.getOriginalFilename();
	    	String suffix = FileType.getSuffixByFilename(originalFilename);
	    	
	    	String rootPath = request.getSession().getServletContext().getRealPath("");
	    	File descDir = new File(rootPath + "/file/ueditor/image");
			if (!descDir.exists()) {
				FileUtils.createDirectory(rootPath + "/file/ueditor/image");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String dateTime = sdf.format(date);
			File descDirDate = new File(rootPath + "/file/ueditor/image/" + dateTime);
			if (!descDirDate.exists()) {
				FileUtils.createDirectory(rootPath + "/file/ueditor/image/" + dateTime);
			}
			String dateFile = new Date().getTime() + "";
			UploadFile uploadFile = FileUtils.singleUploadFile(rootPath + "/file/ueditor/image/" + dateTime + "/" + dateFile + suffix, dateFile + suffix, request);
			uploadFile.setRelativeUrl("/file/ueditor/image/" + dateTime + "/" + dateFile + suffix);
			uploadFile.setSuffix(suffix);
	    	return JSON.toJSONString(uploadFile);
	    } else {
	    	return "";
	    }
	}
}
