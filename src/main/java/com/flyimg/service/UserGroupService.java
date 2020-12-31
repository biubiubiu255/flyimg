package com.flyimg.service;

import com.flyimg.pojo.UserGroup;
import org.springframework.stereotype.Service;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/20 14:12
 */
@Service
public interface UserGroupService {
    UserGroup userIdGetUserGroup(Integer userid);
    UserGroup idGetUserGroup(Integer id);
    Integer addUserGroup(UserGroup userGroup);
    Integer updateUserGroup(UserGroup userGroup);
    Integer updateUserGroupDefault(Integer groupid);
    Integer deleUserGroup(Integer userid);
}
