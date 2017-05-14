package cn.jxust.blog.uitl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.jxust.blog.po.Admin;


public class SessionHelper {
	private SessionHelper(){
		
	}
	
	public static Integer getUserSessionId() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Object obj = request.getSession().getAttribute("userSessionId");
		if (obj != null) {
			return (Integer) obj;
		}
		return 0;
	}
	
	public static Admin getUserSession(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Object obj = request.getSession().getAttribute("userSession");
		if (obj != null) {
			return (Admin) obj;
		}
		return null;
	}
}
