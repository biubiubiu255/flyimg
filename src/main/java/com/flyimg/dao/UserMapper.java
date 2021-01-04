package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select password from user where email = #{username} limit 1")
    String selectPassword(String email);

    @Select("select * from user where email = #{email} limit 1")
    User selectUserByEmail(String email);

    @Select("select * from user where id = #{userid} limit 1")
    User selectUserByUserid(Integer userid);

    @Select("update user set memory_used = memory_used + #{addMem} where id = #{userid} limit 1")
    Boolean updateMemUsedAdd(Integer userid, Integer addMem);

    //插入图片
    Integer insertimg(FileOss img);

    //修改资料
    Integer change(User user);

    //检查用户名是否重复
    Integer checkUsername(@Param("username") String username);

    Integer getUserTotal();

    List<User> getuserlist(User user);

    //刪除用戶
    Integer deleuser(@Param("id") Integer id);

    //查询用户名或者邮箱是否存在
    Integer countusername(@Param("username") String username);

    Integer countmail(@Param("email") String email);

    Integer uiduser(@Param("uid") String uid);

    User getUsersMail(@Param("uid") String uid);
    Integer setisok (User user);

    Integer setmemory(User user);
    User getUsersid(@Param("id") Integer id);

    List<User> getuserlistforgroupid(@Param("groupid") Integer groupid);

}
