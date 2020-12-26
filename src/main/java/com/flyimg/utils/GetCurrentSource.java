package com.flyimg.utils;

import com.flyimg.pojo.Group;
import com.flyimg.pojo.User;
import com.flyimg.service.impl.GroupServiceImpl;
import com.flyimg.service.impl.UserGroupServiceImpl;
import com.flyimg.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/20 14:10
 */
@Component
public class GetCurrentSource {
    @Autowired
    private GroupServiceImpl groupServiceImpl;
    @Autowired
    private UserGroupServiceImpl userGroupServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    private static GroupServiceImpl groupService;
    private static UserGroupServiceImpl userGroupService;
    private static UserServiceImpl userService;

    @PostConstruct
    public void init() {
        groupService =groupServiceImpl;
        userGroupService = userGroupServiceImpl;
        userService = userServiceImpl;
    }


    public static Integer GetSource(Integer userid){
        Integer ret = 0;
        if(userid==null){
            Group group = groupService.idgrouplist(1);
            ret = group.getKeyid();
        }else{
            User user = userService.getUsersid(userid);
            Group group = groupService.idgrouplist(user.getGroupid());
            ret = group.getKeyid();
        }
        return ret;
    }
}