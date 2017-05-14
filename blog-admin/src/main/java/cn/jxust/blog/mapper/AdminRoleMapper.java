package cn.jxust.blog.mapper;

import cn.jxust.blog.po.AdminRoleKey;


public interface AdminRoleMapper {
    int deleteByPrimaryKey(AdminRoleKey key);

    int insert(AdminRoleKey record);

    int insertSelective(AdminRoleKey record);
}