package com.whxh.house.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.UserCondition;
import com.whxh.house.entity.Users;
import com.whxh.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UserController {
 @Autowired
 private UserService userService;

 @RequestMapping("getUsers")
 @ResponseBody
 public Map<String,Object> getUsers(UserCondition userCondition){
  //调用业务
  PageInfo<Users> pageInfo= userService.getUserByPage(userCondition);
  Map<String,Object> map=new HashMap<>();
  map.put("total",pageInfo.getTotal());
  map.put("rows",pageInfo.getList());
  return map;
 }


}
