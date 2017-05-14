package cn.jxust.blog.mapper;

import java.util.List;

import cn.jxust.blog.po.Admin;


public interface AdminMapper {

    Admin selectByAccountName(String accountName);
   
    /**
     * 根据用户删除已经分配给该用户的角色
     * @param userId
     * @return
     */
    public int deleteUserRoleByUserID(Integer userId);
    /**
     * 锁定用户
     * @param list
     * @return
     */
    public int lockUser(List<String> list);
    /**
     * 解锁用户
     * @param list
     * @return
     */
    public int unLockUser(List<String> list);
}