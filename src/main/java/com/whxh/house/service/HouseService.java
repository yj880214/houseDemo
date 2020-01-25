package com.whxh.house.service;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.House;
import com.whxh.house.entity.HouseCondition;

public interface HouseService {
 //添加出租房
 public int addHouse(House house);
 //查询出租房
 public PageInfo<House> getUserHouseByPage(Integer page,Integer rows,Integer uid);
 //查询出租房（单条）
 public House getHouse(String id);
 //修改出租房
 public int updateHouse(House house);
 //删除出租房
 public int delHouse(String id);
 //查询审核出租房
 PageInfo<House> getHouseByState(Integer page,Integer rows,Integer ispass);
 //审核出租房
 public int passHouse(String id);

 //查询浏览的出租房
// public PageInfo<House> getHouseByBrowser(Integer page,Integer pageSize);
 public PageInfo<House> getHouseByBrowser(HouseCondition condition);

}
