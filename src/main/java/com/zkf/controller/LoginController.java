package com.zkf.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.common.utils.Constants;
import com.common.utils.MD5;
import com.zkf.model.SysUserRe;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.SysUser;
import com.zkf.mysql.repository.RoleRepo;
import com.zkf.mysql.repository.SystemUserRepo;
import com.zkf.pojo.SysUserReq;
import com.zkf.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "sysUserServiceImpl")
	private SysUserService sysUserService;

	@Resource(name = "systemUserRepo")
	private SystemUserRepo systemUserRepo;

	@Resource(name = "roleRepo")
	private RoleRepo roleRepo;

	@GetMapping("/login")
	public String getSysParameter(HttpServletRequest request, HttpServletResponse response, Model model, String message,
								  String info, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotBlank(message)){
			model.addAttribute("error", message);
		}
		return "login";
	}

	@PostMapping("/sysUser")
	@ResponseBody
	public VResult<?> getSysUser(@RequestBody SysUserReq sysUserReq) {
		return new VResult<>(true, sysUserService.getSysUser(sysUserReq.getPage(), sysUserReq.getSize()));
	}


	@PostMapping(value = "/login")
	public String login(HttpServletRequest request, @Valid SysUser user, BindingResult bindingResult,
						RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		String username = user.getUserId();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(),
				MD5.computeToHexStr(user.getUserPwd()));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addAttribute("message", "用户不存在");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addAttribute("message", "用户名或密码错误次数过多");
		} catch (ExpiredCredentialsException ece) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已过期");
			redirectAttributes.addAttribute("message", "账户已过期");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			SysUser sysUser = systemUserRepo.findByUserId(user.getUserId());
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_USER_ID, sysUser);
			session.setAttribute("userName", sysUser.getUserName());
			session.setAttribute("userId", sysUser.getUserId());
			session.setAttribute("roleId", sysUser.getRoleId());
			return "redirect:/mainPage";
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

	@GetMapping("/common")
	public String getCommon(HttpSession session, Model model) {
//		SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER_ID);
//		model.addAttribute("userName", user.getUserName());
		return "common";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	@RequestMapping("/updatePwd/{id}")
	public String updatePwdPage(@PathVariable("id") String id, Model model) {
		model.addAttribute("userId", id);
		return "updatePwd";
	}


	@PostMapping("/updatePwd")
	@ResponseBody
	public VResult<?> updatePwd(@RequestBody SysUserRe sysUserRe) {
		systemUserRepo.changeUserPwd(MD5.computeToHexStr(sysUserRe.getUserPwd()), sysUserRe.getUserId());
		return new VResult<>(true);
	}


	@PostMapping("/deleteUser/{id}")
	@ResponseBody
	public VResult<?> deleteUser(@PathVariable("id") String userId) {
		SysUser sysUser = systemUserRepo.findByUserId(userId);
		systemUserRepo.delete(sysUser);
		return new VResult<>(true);
	}

	@RequestMapping("/addUser")
	public String addUser() {
		return "userManage/addUser";
	}


	@RequestMapping("/updateUser/{id}")
	public String updateHp(@PathVariable("id") String hpId, Model model) {
		model.addAttribute("id", hpId);
		return "userManage/updateUserManage";
	}

	@RequestMapping("/updateUserManage")
	@ResponseBody
	public VResult<?> updateUserManage(@RequestBody SysUser sysUser) {
		systemUserRepo.changeForSystemManage(sysUser.getUserName(), sysUser.getModules(), sysUser.getRoleId(), sysUser.getUserId(), sysUser.getStoresId());
		return new VResult<>(true);
	}

	@PostMapping("/findOne/{id}")
	@ResponseBody
	public VResult<?> findOne(@PathVariable("id") String userId) {
		SysUser sysUser = systemUserRepo.findByUserId(userId);
		return new VResult<>(true, sysUser);
	}

	@RequestMapping("/userManege")
	public String getHp() {
		
		return "userManage/userManage";
	}

}
