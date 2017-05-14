package cn.jxust.blog.service;

import java.util.Map;

import com.github.pagehelper.Page;

import cn.jxust.blog.po.Admin;

public interface AdminService{
	public void addAdmin(Admin admin,String txtGroupsSelect);
	
	public void updateAdmin(Admin admin,String txtGroupsSelect);
	
	public boolean isExist(String accountName);
	
	public Page<Admin> selectAdmin(Map<String,Object> params,Integer index,Integer pageSize);
	
	public void updateAdminStatus(String adminIDs,String lockFlag);
	
	Admin selectByPrimaryKey(Integer id);
	 
	Admin selectByAccountName(String accountName);
	
	/**
	 * 重置密码
	 * @param password	原始密码
	 * @param newPassword 新密码
	 * @param Admin 用户
	 */
	public void updatePassword(String password,String newPassword,Admin admin);
}
