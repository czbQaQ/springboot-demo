package com.asura.demo.dao;

import com.asura.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    public Integer userIsExix(String userName);
    public Integer insertUser(User user);
    public User selectUserByUsername(String userName);
}
