package com.flyimg.dao;

import com.flyimg.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/20 13:45
 */
@Mapper
public interface UserGroupMapper {
    UserGroup useridGetUserGroup(@Param("userid") Integer userid);
    UserGroup idGetUserGroup(@Param("id") Integer id);
    Integer addUserGroup(UserGroup userGroup);
    Integer updateUserGroup(UserGroup userGroup);
    Integer updateUserGroupDefault(@Param("groupid") Integer groupid);
    Integer deleUserGroup(@Param("userid") Integer userid);
}
