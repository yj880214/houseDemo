package com.whxh.house.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Street;
import com.whxh.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
 @Autowired
 private StreetService streetService;
//后台管理（框架）：根据区域id查询街道
 @RequestMapping("getStreetByDid")
 @ResponseBody
 public Map<String,Object> getStreetByDid(Integer page,Integer rows,Integer did){
  //调用业务
  PageInfo<Street> pageInfo=streetService.getStreetByDid(page,rows,did);
  Map<String,Object> map=new HashMap<>();
  map.put("total",pageInfo.getTotal());
  map.put("rows",pageInfo.getList());
  return map;
 }
//添加街道
  @RequestMapping(value = "insertStreet",produces ="application/json;charset=utf-8" )
  @ResponseBody
  public String insertStreet(Street street){
    streetService.insertStreet(street);
    return "街道添加成功";
  }



}
