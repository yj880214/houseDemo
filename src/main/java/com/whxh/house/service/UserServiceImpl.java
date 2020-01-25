package com.whxh.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.UserCondition;
import com.whxh.house.entity.Users;
import com.whxh.house.entity.UsersExample;
import com.whxh.house.mapper.UsersMapper;
import com.whxh.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
 @Autowired
 private UsersMapper usersMapper;
 @Override
 public PageInfo<Users> getUserByPage(UserCondition userCondition) {
  //查询所有
  PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
  UsersExample usersExample=new UsersExample();
  //添加条件
  UsersExample.Criteria criteria=usersExample.createCriteria();
  criteria.andIsadminEqualTo(1); //后台添加的用户
//添加查询条件
  //电话
  if (userCondition.getTelephone()!=null){
    criteria.andTelephoneLike("%"+userCondition.getTelephone()+"%");
  }
  //最小年龄
  if (userCondition.getStartAge()!=null){
   criteria.andAgeGreaterThan(userCondition.getStartAge());
  }
  //最大年龄
  if (userCondition.getEndAge()!=null) {
   criteria.andAgeLessThan(userCondition.getEndAge());
  }

  List<Users> usersList=usersMapper.selectByExample(usersExample);
  PageInfo<Users> pageInfo=new PageInfo<>(usersList);
  return pageInfo;
 }


//验证用户名：根据输入的名称查询
 @Override
 public int checkName(String username) {
  //两种方法，也可以写持久化操作
  UsersExample usersExample=new UsersExample();
  UsersExample.Criteria criteria=usersExample.createCriteria();
  criteria.andNameEqualTo(username);
  criteria.andIsadminEqualTo(1);
  List<Users> usersList=usersMapper.selectByExample(usersExample);
  return usersList.size()==0?0:1;
 }
//注册实现（添加）
 @Override
 public int addUser(Users users) {
  //设置普通用户1
  users.setIsadmin(1);
  //对用户的密码使用MD5加密
  users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
  return usersMapper.insertSelective(users);
 }

 //登入实现
 @Override
 public Users login(String username, String password) {
  UsersExample usersExample=new UsersExample();
  UsersExample.Criteria criteria=usersExample.createCriteria();
  criteria.andIsadminEqualTo(1);
  criteria.andNameEqualTo(username);
  //加密登录输入的密码
  criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
  List<Users> usersList=usersMapper.selectByExample(usersExample);
  if (usersList.size()==1){
    return usersList.get(0);
  }
  return null;
 }
}
