package cn.jxust.blog.shiro;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;


import org.apache.shiro.util.ByteSource;


import cn.jxust.blog.constant.AdminStatus;
import cn.jxust.blog.po.Admin;
import cn.jxust.blog.po.Role;
import cn.jxust.blog.po.Resource;
import cn.jxust.blog.service.AdminService;
import cn.jxust.blog.service.ResourceService;
import cn.jxust.blog.service.RoleService;

/**
 * 自定义Realm,进行数据源配置
 */
public class MyRealm extends AuthorizingRealm {
	@SuppressWarnings("restriction")
	@javax.annotation.Resource
	private ResourceService ResourceService;
	@SuppressWarnings("restriction")
	@javax.annotation.Resource
	private AdminService adminService;
	@SuppressWarnings("restriction")
	@javax.annotation.Resource
	private RoleService roleService;
	/**
	 * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		if (loginName != null) {
			String userId = SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString();
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 获取用户的角色集合
			List<Role> roles = roleService.seletAdminRole(Integer.parseInt(userId));
			HashSet hs = new LinkedHashSet();
			for (Role role : roles) {
				hs.add(role.getId());
				// 角色对应相应的权限
				List<Resource> rs = ResourceService.findRoleResource(role.getId());
				for (Resource Resource : rs) {
					info.addStringPermission(Resource.getResKey());
				}
			}
			info.setRoles(hs);
			return info;
		}
		return null;
	}

	/**
	 * 认证回调函数,登录时调用
	 * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
	 * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
	 * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
	 * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
	 * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
	 * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），
	 * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String accountName = (String) token.getPrincipal();
		Admin user = adminService.selectByAccountName(accountName);
		if (user != null) {
			if (AdminStatus.LOCK.getCode().equals(user.getLocked())) {
				throw new LockedAccountException(); // 帐号锁定
			}

			/**
			 * 	从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
			 *  当用户执行登录时,在方法处理上要实现login方法;
			 *  然后会自动进入这个类进行认证
			 *  交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
			 */
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accountName, // 用户名
					user.getPassword(), // 密码
					ByteSource.Util.bytes(accountName + "" + user.getCredentialsSalt()),// salt=username+salt
					getName() // realm name
			);
			// 当验证都通过后，把用户信息放在session里
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("userSession",user);
			session.setAttribute("userSessionId", user.getId());
			return authenticationInfo;
		} else {
			throw new UnknownAccountException();// 没找到帐号
		}

	}

}