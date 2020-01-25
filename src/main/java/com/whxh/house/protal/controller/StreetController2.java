package com.whxh.house.protal.controller;

import com.whxh.house.entity.Street;
import com.whxh.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class StreetController2 {
 @Autowired
 private StreetService streetService;
 //发布页面：根据区域id查询街道
 @RequestMapping("getStreetByDid2")
 @ResponseBody
 public List<Street> getStreetByDid2(Integer did){
  //调用业务
  List<Street> streetList=streetService.getStreetByDid(did);
  return streetList;
 }
}
