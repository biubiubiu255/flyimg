package com.flyimg.service;

import com.flyimg.pojo.FileOSS;
import com.flyimg.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 新用户注册
     */
    Integer register(User user);

    /**
     * 用户登录，成功返回userid，失败抛出异常
     */
    Integer login(String email, String password);

    /**
     * 获取用户 - 根据邮箱
     */
    User get(String email);

    /**
     * 获取用户 - 根据userid
     */
    User get(Integer userid);

    /**
     * 获取用户 - 根据私匙
     */
    User getByKey(String key);

    /**
     * 获取可用内存，支持自增，返回最新的内存剩余，填null则只获取，不自增 - 单位k
     */
    Long getMemAndIncr(Integer userid, Long addMemory);

    //插入图片
    Integer insertImg(FileOSS img);

    //修改资料
    Integer change(User user);

    //检查用户名是否重复
    Integer checkUsername(String username);

    Integer getUserTotal();

    List<User> getUserList(User user);

    Integer deleUser(Integer id);

    //查询用户名或者邮箱是否存在
    Integer countUsername(String username);

    Integer countMail(String email);

    Integer uidUser(String uid);

    User getUsersMail(String uid);
    Integer setIsok(User user);
    Integer setMemory(User user);
    User getUsersid(Integer id);
    List<User> getUserListForGroupid(Integer groupid);
}
