package com.whxh.house.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.District;
import com.whxh.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller //@RestController=@Controller+@ResponseBody
@RequestMapping("/admin/")
public class DistrictController {
 @Autowired
 private DistrictService districtService;
  @RequestMapping("getDistrict")
  @ResponseBody
 public Map<String,Object> getDistrict(Integer page,Integer rows){
    //调用业务
    PageInfo<District> pageInfo= districtService.getDistrictByPage(page,rows);
    Map<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
  }
  //添加区域
 @RequestMapping("addDistrict")
 @ResponseBody
 public String addDistrict(District district){
  //调用业务
  int temp=districtService.addDistrict(district);
  return "{\"result\":"+temp+"}";
 }
 //修改区域
 @RequestMapping("upDistrict")
 @ResponseBody
 public String upDistrict(District district){
  //调用业务
  int temp=districtService.updateDistrict(district);
  return "{\"result\":"+temp+"}";
 }
 //删除区域
 @RequestMapping("deleteDistrict")
 @ResponseBody
 public String deleteDistrict(Integer id){
  //调用业务
  int temp=districtService.deleteDistrict(id);
  return "{\"result\":"+temp+"}";
 }

 //批量删除区域
 @RequestMapping("deleteMoreDistrict")
 @ResponseBody
 public String deleteMoreDistrict(String ids){ //ids=1,2,3
   //将字符串转换为整形数组
String[] arrays=ids.split(",");
Integer[] id=new Integer[arrays.length];
  for (int i=0;i<arrays.length;i++){
    id[i]=Integer.parseInt(arrays[i]);
  }
  //调用业务
  int temp=districtService.deleteMoreDistrict(id);
  return "{\"result\":"+temp+"}";
 }

}
