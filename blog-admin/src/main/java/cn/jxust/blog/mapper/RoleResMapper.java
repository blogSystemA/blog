package cn.jxust.blog.mapper;

import cn.jxust.blog.po.RoleResKey;


public interface RoleResMapper {
    int deleteByPrimaryKey(RoleResKey key);

    int insert(RoleResKey record);

    int insertSelective(RoleResKey record);
}