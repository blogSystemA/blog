package cn.jxust.blog.service.impl;

import java.util.Map;

import com.github.pagehelper.Page;

import cn.jxust.blog.po.Admin;
import cn.jxust.blog.service.AdminService;

public class AdminServiceimpl implements AdminService{

	public void addAdmin(Admin admin, String txtGroupsSelect) {
		// TODO Auto-generated method stub
		
	}

	public void updateAdmin(Admin admin, String txtGroupsSelect) {
		// TODO Auto-generated method stub
		
	}

	public boolean isExist(String accountName) {
		// TODO Auto-generated method stub
		return false;
	}

	public Page<Admin> selectAdmin(Map<String, Object> params, Integer index,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateAdminStatus(String adminIDs, String lockFlag) {
		// TODO Auto-generated method stub
		
	}

	public Admin selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin selectByAccountName(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePassword(String password, String newPassword, Admin admin) {
		// TODO Auto-generated method stub
		
	}
	
}
