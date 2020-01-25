package com.whxh.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.House;
import com.whxh.house.entity.HouseCondition;
import com.whxh.house.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousServiceImpl implements HouseService {
 @Autowired
 private HouseMapper houseMapper;
 @Override
 public int addHouse(House house) {
  return houseMapper.insertSelective(house);
 }

 @Override
 public PageInfo<House> getUserHouseByPage(Integer page, Integer rows, Integer uid) {
  PageHelper.startPage(page,rows);
  List<House> houseList=houseMapper.getHouseByUserId(uid);
  return new PageInfo<>(houseList);
 }

 @Override
 public House getHouse(String id) {
//  return houseMapper.selectByPrimaryKey(id);
  return houseMapper.getHouseAndDid(id);
 }
//修改执行
 @Override
 public int updateHouse(House house) {
  return houseMapper.updateByPrimaryKeySelective(house);
 }

 @Override
 public int delHouse(String id) {
  House house=new House();
  //设置主键
  house.setId(id);
  //设置出租房状态
  house.setIsdel(1);
  return houseMapper.updateByPrimaryKeySelective(house);
 }

 @Override
 public PageInfo<House> getHouseByState(Integer page,Integer rows,Integer ispass) {
  PageHelper.startPage(page,rows);
  List<House> houseList=houseMapper.getHouseByState(ispass);
  return new PageInfo<>(houseList);
 }

 @Override
 public int passHouse(String id) {
  House house=new House();
  house.setId(id);
  house.setIspass(1);//通过审核
  return houseMapper.updateByPrimaryKeySelective(house);
 }

/* @Override
 public PageInfo<House> getHouseByBrowser(Integer page, Integer pageSize) {
  PageHelper.startPage(page,pageSize);
  //调用业务  Example 实现单表条件搜索查询
  List<House> list = houseMapper.getHouseByBrowser();
  PageInfo<House> pageInfo=new PageInfo<>(list);
  return pageInfo;
 }*/
public PageInfo<House> getHouseByBrowser(HouseCondition condition) {
 PageHelper.startPage(condition.getPage(),condition.getPageSize());
 //调用业务  Example 实现单表条件搜索查询
 if (condition.getTitle()!=null){
  condition.setTitle("%"+condition.getTitle()+"%");
 }
 List<House> list = houseMapper.getHouseByBrowser(condition);
 PageInfo<House> pageInfo=new PageInfo<>(list);
 return pageInfo;
}

}
