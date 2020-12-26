package com.flyimg.service.impl;

import com.flyimg.dao.UserGroupMapper;
import com.flyimg.pojo.UserGroup;
import com.flyimg.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/20 14:13
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public UserGroup useridgetusergroup(Integer userid) {
        return userGroupMapper.useridGetUserGroup(userid);
    }

    @Override
    public UserGroup idgetusergroup(Integer id) {
        return userGroupMapper.idGetUserGroup(id);
    }

    @Override
    public Integer addusergroup(UserGroup userGroup) {
        return userGroupMapper.addUserGroup(userGroup);
    }

    @Override
    public Integer updateusergroup(UserGroup userGroup) {
        return userGroupMapper.updateUserGroup(userGroup);
    }

    @Override
    public Integer updateusergroupdefault(Integer groupid) {
        return userGroupMapper.updateUserGroupDefault(groupid);
    }

    @Override
    public Integer deleusergroup(Integer userid) {
        return userGroupMapper.deleUserGroup(userid);
    }
}
