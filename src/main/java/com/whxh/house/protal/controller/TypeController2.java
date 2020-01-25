package com.whxh.house.protal.controller;

import com.whxh.house.entity.Type;
import com.whxh.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class TypeController2 {
  @Autowired
 private TypeService typeService;
  @RequestMapping("getType")
  @ResponseBody
 public List<Type> getType(){
   //调用业务获取类型
   return typeService.getAllType();
  }

}
