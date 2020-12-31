package com.flyimg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.AssertUtil;
import com.flyimg.comm.utils.CryptoUtils;
import com.flyimg.dao.CodeMapper;
import com.flyimg.dao.UserMapper;
import com.flyimg.exception.CodeException;
import com.flyimg.pojo.FileOSS;
import com.flyimg.pojo.User;
import com.flyimg.service.UserService;
import com.flyimg.comm.utils.Print;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CodeMapper codeMapper;

    @Override
    public Integer register(User user) {
        return userMapper.insert(user);
    }


    @Override
    public Integer login(String email, String password) {
        // TODO Auto-generated method stub
        User user = userMapper.selectUserByEmail(email);
        AssertUtil.isNotNullsEx(ResultCode.USER_USERNAME_NOT_EXIST, user);
        AssertUtil.isTrue(user.getPassword().equals(CryptoUtils.encodeMD5(password)), ResultCode.USER_PASS_ERROR);
        return user.getId();
    }

    @Override
    public User get(String email) {
        // TODO Auto-generated method stub
        return userMapper.selectUserByEmail(email);
    }

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
    public synchronized Long getMemAndIncr(Integer userid, Long addMemory) {
        User user = userMapper.selectUserByUserid(userid);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user, user.getMemory(), user.getMemoryUsed());
        return user.getMemory() - user.getMemoryUsed() - (addMemory==null ? 0L : addMemory);
    }


    @Override
    public Integer insertImg(FileOSS img) {
        // TODO Auto-generated method stub
        return userMapper.insertimg(img);
    }

    @Override
    public Integer change(User user) {
        // TODO Auto-generated method stub
        return userMapper.change(user);
    }

    @Override
    public Integer checkUsername(String username) {
        // TODO Auto-generated method stub
        return userMapper.checkUsername(username);
    }

    @Override
    public Integer getUserTotal() {
        // TODO Auto-generated method stub
        return userMapper.getUserTotal();
    }

    @Override
    public Integer deleUser(Integer id) {
        return userMapper.deleuser(id);
    }

    @Override
    public Integer countUsername(String username) {
        return userMapper.countusername(username);
    }

    @Override
    public Integer countMail(String email) {
        return userMapper.countmail(email);
    }

    @Override
    public List<User> getUserList(User user) {
        return userMapper.getuserlist(user);
    }

    @Override
    public Integer uidUser(String uid) {
        return userMapper.uiduser(uid);
    }

    @Override
    public User getUsersMail(String uid) {
        return userMapper.getUsersMail(uid);
    }

    @Override
    public Integer setIsok(User user) {
        return userMapper.setisok(user);
    }

    @Override
    public Integer setMemory(User user) {
        return userMapper.setmemory(user);
    }

    @Override
    public User getUsersid(Integer id) {
        return userMapper.getUsersid(id);
    }

    @Override
    public List<User> getUserListForGroupid(Integer groupid) {
        return userMapper.getuserlistforgroupid(groupid);
    }

    @Transactional//默认遇到throw new RuntimeException(“…”);会回滚
    public Integer usersetmemory(User user,String codestring) {
        Integer ret = userMapper.setmemory(user);
        if(ret<=0){
            Print.warning("用户空间没有设置成功。回滚");
            throw new CodeException("用户之没有设置成功。");
        }else{
            ret = codeMapper.deleteCode(codestring);
        }
        return ret;
    }
}
