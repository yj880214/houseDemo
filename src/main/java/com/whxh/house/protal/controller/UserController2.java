package com.whxh.house.protal.controller;

import com.whxh.house.entity.Users;
import com.whxh.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController2 {
 @Autowired
 private UserService userService;
 @RequestMapping("checkUsername")
 @ResponseBody
 public String checkUsername(String username){
    int temp=userService.checkName(username);
    return "{\"result\":"+temp+"}";

 }
//注册
 @RequestMapping("regUser")
 public String regUser(Users users){
  int temp=userService.addUser(users);
  if (temp>0){
   return "login";
  }else {
   return "error";
  }
 }
 //登录
 @RequestMapping("login")
 public String login(String username, String password, Model model, HttpServletRequest request,HttpSession session) {
  Users users = userService.login(username, password);

   if (users == null) {
    model.addAttribute("info", "用户名或密码错误");
    return "login";  //如果用户名密码错误查找为空,登录失败则继续登录
   } else {
    session.setAttribute("user", users);
//    session.setMaxInactiveInterval(600);
//    return "guanli"; //进入管理页面
    return "redirect:getUserHouse";
   }
 }
}
