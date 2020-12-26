package com.flyimg.service;

import com.flyimg.pojo.Group;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/19 16:29
 */
@Service
public interface GroupService {
    List<Group> grouplist();
    Group idgrouplist(Integer id);
    Integer addgroup(Group group);
    Integer delegroup(Integer id);
    Integer setgroup(Group group);
}
