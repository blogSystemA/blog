package cn.jxust.blog.service;

import java.util.List;

import cn.jxust.blog.po.Role;

public interface RoleService {
	// 获取admin的角色集合
	public List<Role> seletAdminRole(Integer adminId);

}
