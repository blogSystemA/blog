package cn.jxust.blog.shiro;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.jxust.blog.po.Admin;
import cn.jxust.blog.service.AdminService;


/**
 * 自定义Realm,进行数据源配置
 */
public class MyRealm extends AuthorizingRealm {
//	@Resource
//	private ResourcesService resourcesService;
	@SuppressWarnings("restriction")
	@Resource
	private AdminService adminService;
//	@Resource
//	private RoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
	    	 SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//未进行授权处理
	        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		    UsernamePasswordToken adminnamePasswordToke = (UsernamePasswordToken)token; 
	        String account = adminnamePasswordToke.getUsername();
	        String pwd = String.valueOf(adminnamePasswordToke.getPassword());
	        Admin admin = this.adminService.selectByAccountName(account);
	        if( admin == null ){
	            throw new UnknownAccountException();
	        }
	        if( !admin.getPassword().equals(pwd)){
	            throw new IncorrectCredentialsException();
	        }
			if(Boolean.TRUE.equals( admin.getLocked())){
				  throw new LockedAccountException(); //帐号锁定
			}
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	                account,pwd,this.getName()); //此处未进行密码加密处理
	        return authenticationInfo;
	}

}