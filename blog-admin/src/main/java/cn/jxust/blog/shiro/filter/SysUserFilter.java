package cn.jxust.blog.shiro.filter;


import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import cn.jxust.blog.service.AdminService;




public class SysUserFilter extends PathMatchingFilter {

	@Resource
	private AdminService adminService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String accountName = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", adminService.selectByAccountName(accountName));
        return true;
    }
}