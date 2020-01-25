package com.whxh.house.protal.controller;

import com.whxh.house.entity.District;
import com.whxh.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class DistrictController2 {
 @Autowired
 private DistrictService districtService;
 @RequestMapping("getDistrict")
 @ResponseBody
 public List<District> getDistrict(){
  //调用业务获取区域
  return districtService.getAllDistrict();
 }
}
