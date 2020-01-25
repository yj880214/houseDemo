package com.whxh.house.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Type;
import com.whxh.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller //@RestController=@Controller+@ResponseBody
@RequestMapping("/admin/")
public class TypeController {
 @Autowired
 private TypeService typeService;
  @RequestMapping("getType")
  @ResponseBody
 public Map<String,Object> getDistrict(Integer page,Integer rows){
    //调用业务
    PageInfo<Type> pageInfo= typeService.getTypeByPage(page,rows);
    Map<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
  }
  //添加区域
 @RequestMapping("addType")
 @ResponseBody
 public String addType(Type type){
  //调用业务
  int temp=typeService.addType(type);
  return "{\"result\":"+temp+"}";
 }
 //修改区域
 @RequestMapping("upType")
 @ResponseBody
 public String upType(Type type){
  //调用业务
  int temp=typeService.updateType(type);
  return "{\"result\":"+temp+"}";
 }
 //删除区域
 @RequestMapping("deleteType")
 @ResponseBody
 public String deleteType(Integer id){
  //调用业务
  int temp=typeService.deleteType(id);
  return "{\"result\":"+temp+"}";
 }

 //批量删除区域
 @RequestMapping("deleteMoreType")
 @ResponseBody
 public String deleteMoreType(String ids){ //ids=1,2,3
   //将字符串转换为整形数组
  String[] arrays=ids.split(",");
  Integer[] id=new Integer[arrays.length];
  for (int i=0;i<arrays.length;i++){
    id[i]=Integer.parseInt(arrays[i]);
  }
  //调用业务
  int temp=typeService.deleteMoreType(id);
  return "{\"result\":"+temp+"}";
 }

}
