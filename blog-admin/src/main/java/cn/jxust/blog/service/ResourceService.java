package cn.jxust.blog.service;

import java.util.List;

import cn.jxust.blog.po.Resource;

public interface ResourceService {
	// 获取角色的权限集合
	List<Resource> findRoleResource(Integer id);

}
