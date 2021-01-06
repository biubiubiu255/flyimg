package com.flyimg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.AssertUtil;
import com.flyimg.comm.utils.CryptoUtils;
import com.flyimg.dao.UserMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.pojo.SysConfig;
import com.flyimg.pojo.User;
import com.flyimg.service.SysConfigService;
import com.flyimg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public Integer register(User user) {
        // 校验用户名是否已存在
        AssertUtil.isNullsEx(ResultCode.USER_USERNAME_EXIST ,get(user.getEmail()));

        // 写入user对象并入库
        SysConfig sysConfig = sysConfigService.get();
        user.setPassword(CryptoUtils.encodeMD5(user.getPassword()));
        user.setSecret(CryptoUtils.encodeMD5(UUID.randomUUID().toString()));
        user.setMemoryUsed(0);
        user.setMemory(sysConfig.getMaxMemoryUser().intValue());
        user.setStatus(1);
        user.setCreatedTime(System.currentTimeMillis());
        return userMapper.insert(user);
    }


    @Override
    public Integer login(String email, String password) {
        // TODO Auto-generated method stub
        User user = userMapper.selectUserByEmail(email);
        AssertUtil.isNotNullsEx(ResultCode.USER_USERNAME_NOT_EXIST, user);
        log.info("校对账号密码，{} {} {}", user.getPassword(), CryptoUtils.encodeMD5(password), password);
        AssertUtil.isTrue(user.getPassword().equals(CryptoUtils.encodeMD5(password)), ResultCode.USER_PASS_ERROR);
        return user.getId();
    }

    @Override
    public User get(String email) {
        // TODO Auto-generated method stub
        return userMapper.selectUserByEmail(email);
    }

    @Cacheable(value="comm", key="#root.targetClass+#root.methodName+#userid" )
    @Override
    public User get(Integer userid) {
        // TODO Auto-generated method stub
        return userMapper.selectUserByUserid(userid);
    }


    @Override
    public User getByKey(String key) {
        // TODO Auto-generated method stub
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("key",key);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public synchronized Long getMemAndUsedMemIncr(Integer userid, Long decreaseMem) {
        User user = userMapper.selectUserByUserid(userid);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user, user.getMemory(), user.getMemoryUsed());
        Long balanceMem = user.getMemory() - user.getMemoryUsed() - (decreaseMem ==null ? 0L : decreaseMem);
        if (balanceMem>=0 && decreaseMem!=null && decreaseMem!=0){
            userMapper.updateMemUsedAdd(userid, decreaseMem.intValue());
        }
        return balanceMem;
    }

    @Override
    public Integer updatePassword(Integer userid, String password, String newPassword) {
        AssertUtil.isNotNullsEx(ResultCode.PARAM_NOT_COMPLETE, password, newPassword);
        User user = get(userid);
        AssertUtil.isTrue(password.equals(CryptoUtils.encodeMD5(user.getPassword())), ResultCode.USER_PASS_ERROR);

        User u = new User();
        u.setId(userid);
        u.setPassword(CryptoUtils.encodeMD5(newPassword));
        return userMapper.updateById(u);
    }

}
