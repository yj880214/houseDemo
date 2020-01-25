package com.whxh.house.service;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.UserCondition;
import com.whxh.house.entity.Users;

public interface UserService {
 //查询所有用户
// PageInfo<Users> getUserByPage(Integer page,Integer rows);
 PageInfo<Users> getUserByPage(UserCondition userCondition);
 //注册时根据用户输入的用户名是否存在
 public int checkName(String username);
 //添加用户 ：  注册
 public int addUser(Users users);
//登入
 public Users login(String username,String password);
}
