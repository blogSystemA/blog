package cn.jxust.blog.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.jxust.blog.service.AdminService;
import cn.jxust.blog.service.ResourceService;
import cn.jxust.blog.service.RoleService;

@Controller
@RequestMapping("")
public class LoginController {
    // 处理用户业务类  
    @Autowired  
    private AdminService adminService;  
    
    @Autowired
	private RoleService roleService;
    @Autowired
	private ResourceService resourcesService;
	
	private static final String ERROR_STR = "error";
	
	private static final String LOGIN_STR = "/login";
	
	private static final String POST = "POST";
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		System.out.println("1111111111111111111111111");
		request.removeAttribute(ERROR_STR);
		return LOGIN_STR;
	}
  
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String password, HttpServletRequest request) {
		try {
			System.out.println("1111111111111111111111111");
			if (!POST.equals(request.getMethod())) {
				request.setAttribute(ERROR_STR, "支持POST方法提交！");
			}
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				request.setAttribute(ERROR_STR, "用户名或密码不能为空！");
				return LOGIN_STR;
			}
			// 想要得到 SecurityUtils.getSubject()　的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针
			Subject user = SecurityUtils.getSubject();
			// 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
			// 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
			// 当以上认证成功后会向下执行,认证失败会抛出异常
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			String result = login(request,user,token,username);
			if(result != null){
				return result;
			}
			request.removeAttribute(ERROR_STR);
		} catch (Exception e) {
			request.setAttribute(ERROR_STR, "登录异常,请联系管理员!");
			return LOGIN_STR;
		}
		return "home";
	}
	
	private String login(HttpServletRequest request,Subject user,UsernamePasswordToken token,String username){
		String result = null;
		try {
			user.login(token);
		} catch (LockedAccountException lae) {
			token.clear();
			request.setAttribute(ERROR_STR, "用户已经被锁定不能登录,请与管理员联系!");
			result = LOGIN_STR;
		} catch (ExcessiveAttemptsException e) {
			token.clear();
			request.setAttribute(ERROR_STR, "账号：" + username + " 登录失败次数过多,锁定10分钟!");
			result = LOGIN_STR;
		} catch (AuthenticationException e) {
			token.clear();
			request.setAttribute("error", "用户名或密码不正确!");
			result = LOGIN_STR;
		}
		return result;
	}

	@RequestMapping("home")
	public String index(Model model,HttpServletRequest request){
		model.addAttribute("msg", "登陆成功");
		return "/home";
	}
	


}
