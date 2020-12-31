package com.flyimg.service.impl;

import com.flyimg.dao.UserGroupMapper;
import com.flyimg.pojo.UserGroup;
import com.flyimg.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/20 14:13
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {
    @Resource
    private UserGroupMapper userGroupMapper;

    @Override
    public UserGroup userIdGetUserGroup(Integer userid) {
        return userGroupMapper.useridGetUserGroup(userid);
    }

    @Override
    public UserGroup idGetUserGroup(Integer id) {
        return userGroupMapper.idGetUserGroup(id);
    }

    @Override
    public Integer addUserGroup(UserGroup userGroup) {
        return userGroupMapper.addUserGroup(userGroup);
    }

    @Override
    public Integer updateUserGroup(UserGroup userGroup) {
        return userGroupMapper.updateUserGroup(userGroup);
    }

    @Override
    public Integer updateUserGroupDefault(Integer groupid) {
        return userGroupMapper.updateUserGroupDefault(groupid);
    }

    @Override
    public Integer deleUserGroup(Integer userid) {
        return userGroupMapper.deleUserGroup(userid);
    }
}
