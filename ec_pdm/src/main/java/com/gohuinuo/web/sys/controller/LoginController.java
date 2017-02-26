package com.gohuinuo.web.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bety.web.model.CustomFaq;
import com.bety.web.service.CustomFaqService;
import com.gohuinuo.common.constant.Constant;
import com.gohuinuo.common.utils.IPUtils;
import com.gohuinuo.web.sys.model.SysMain;
import com.gohuinuo.web.sys.model.SysUser;
import com.gohuinuo.web.sys.service.SysResourceService;
import com.gohuinuo.web.sys.service.SysUserService;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.ec.model.EcFileUrl;
import com.gyf.ec.model.EcMainTaskStatus;
import com.gyf.ec.model.EcSku;
import com.gyf.ec.model.EcStatisticsBySupp;
import com.gyf.ec.service.EcFileUrlService;
import com.gyf.ec.service.EcSkuService;
import com.gyf.ec.service.EcStatisticsService;
import com.gyf.ec.service.EcTaskService;

@Controller
public class LoginController {

	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private EcSkuService ecSkuService; 
	@Resource
	private EcFileUrlService ecFileUrlService;
	@Resource
	private EcTaskService ecTaskService;
	@Resource
	private EcStatisticsService ecStatisticsService;
	@Resource
	private CustomFaqService customFaqService;
	
	/**
	 * 管理主页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/")
	public String toIndex(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("code"); // 清除code
		if( SysUserUtils.getSessionLoginUser() == null || 
				SysUserUtils.getCacheLoginUser() ==null ){
			return "redirect:/login";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		long userId = SysUserUtils.getCacheLoginUser().getId();
		map.put("userId", userId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(new Date());
		map.put("start", time + " 00:00:00");
		map.put("end", time + " 23:59:59");
		SysMain sysMain = sysUserService.findMain(map);
		List<EcSku> mainSkuList = ecSkuService.findMainSku();
		List<EcFileUrl> mainFileUrlList = ecFileUrlService.findFileUrlMain();
		List<EcMainTaskStatus> emtsList = ecTaskService.findMainTaskStatus(map);
		EcMainTaskStatus emts = new EcMainTaskStatus();
		emts.setTaskStatus0(0);
		emts.setTaskStatus1(0);
		emts.setTaskStatus2(0);
		emts.setTaskStatus3(0);
		emts.setTaskStatus4(0);
		emts.setTaskStatus5(0);
		emts.setTaskStatus6(0);
		emts.setTaskStatus7(0);
		emts.setTaskStatus8(0);
		for(int i = 0; i < emtsList.size(); i++) {
			EcMainTaskStatus emts2 = emtsList.get(i);
			if(emts2.getStatusType() == 0) {
				emts.setTaskStatus0(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 1) {
				emts.setTaskStatus1(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 2) {
				emts.setTaskStatus2(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 3) {
				emts.setTaskStatus3(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 4) {
				emts.setTaskStatus4(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 5) {
				emts.setTaskStatus5(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 6) {
				emts.setTaskStatus6(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 7) {
				emts.setTaskStatus7(emts2.getStatusNum());
			} else if(emts2.getStatusType() == 8) {
				emts.setTaskStatus8(emts2.getStatusNum());
			}
		} 
		List<EcStatisticsBySupp> ecStatisticsBySuppList = ecStatisticsService.findMainPrice(map);
		List<CustomFaq> customFaqList = customFaqService.findMainFaq(map);
		model.addAttribute("menuList", SysUserUtils.getUserMenus()).addAttribute("sysMain", sysMain).addAttribute("mainSkuList", mainSkuList)
		.addAttribute("mainFileUrlList", mainFileUrlList).addAttribute("ecMainTaskStatus", emts).addAttribute("ecStatisticsBySuppList", ecStatisticsBySuppList)
		.addAttribute("customFaqList", customFaqList);

		return "index";
	}
	
	/**
	 * 跳转到登录页�
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String toLogin() {
		if( SysUserUtils.getSessionLoginUser() != null && SysUserUtils.getCacheLoginUser() !=null ){
			return "redirect:/";
		}
		return "login";
	}
	
	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkLogin(@ModelAttribute String username,
			@ModelAttribute String password, @ModelAttribute String code, HttpServletRequest request) {

		Map<String, Object> msg = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		code = StringUtils.trim(request.getParameter("code"));
		username = StringUtils.trim(request.getParameter("username"));
		password = StringUtils.trim(request.getParameter("password"));
		Object scode = session.getAttribute("code");
		String sessionCode = null;
		if (scode != null)
			sessionCode = scode.toString();
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
			msg.put("error", "验证码错误");
			msg.put("code", "FAIL");
			return msg;
		}
		SysUser user = sysUserService.checkUser(username, password);
		if (null != user) {
			
			session.setAttribute(Constant.SESSION_LOGIN_USER, user);
			
			//缓存用户
			SysUserUtils.cacheLoginUser(user);
			
			//设置并缓存用户认证
			SysUserUtils.setUserAuth();
			
			//暂时，后续移动到日志�
			//更新用户最后登录ip和date
			SysUser newUser = new SysUser();
			newUser.setLoginDate(new Date());
			newUser.setLoginIp(IPUtils.getClientAddress(request));
			newUser.setId(user.getId());
			sysUserService.updateByPrimaryKeySelective(newUser);
			msg.put("code", "SUCCESS");
		} else {
			msg.put("error", "用户名或密码错误");
			msg.put("code", "FAIL");
		}
		return msg;
	}

	/**
	 * 用户退�
	 * 
	 * @return 跳转到登录页�
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		SysUserUtils.clearCacheUser(SysUserUtils.getSessionLoginUser().getId());
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("notauth")
	public String notAuth(){
		return "notauth";
	}
	
	@RequestMapping("notlogin")
	public String notLogin(){
		return "notlogin";
	}

}
