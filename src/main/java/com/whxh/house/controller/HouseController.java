package com.whxh.house.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.House;
import com.whxh.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {
  @Autowired
  private HouseService houseService;

  //查询未审核
 @RequestMapping("getHouseByNoPass")
 @ResponseBody
 public Map<String,Object> getHouseByNoPass(Integer page,Integer rows){
  PageInfo<House> pageInfo = houseService.getHouseByState(page, rows, 0);
  Map<String,Object> map=new HashMap<String, Object>();
  map.put("total",pageInfo.getTotal());
  map.put("rows",pageInfo.getList());
  return map;
 }
 //查询已审核
 @RequestMapping("getHouseByYesPass")
 @ResponseBody
 public Map<String,Object> getHouseByYesPass(Integer page,Integer rows){
  PageInfo<House> pageInfo = houseService.getHouseByState(page, rows, 1);
  Map<String,Object> map=new HashMap<String, Object>();
  map.put("total",pageInfo.getTotal());
  map.put("rows",pageInfo.getList());
  return map;
 }
 //通过审核
 @RequestMapping("passHouse")
 @ResponseBody
 public Map<String,Object> passHouse(String id){
  System.out.println("id = " + id);
  int temp = houseService.passHouse(id);
  Map<String,Object> map=new HashMap<String, Object>();
  map.put("result",temp);
  return map;
 }
 //所有房屋
}
