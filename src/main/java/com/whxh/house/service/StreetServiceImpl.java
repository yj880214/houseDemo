package com.whxh.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Street;
import com.whxh.house.entity.StreetExample;
import com.whxh.house.mapper.StreetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
 @Autowired
 private StreetMapper streetMapper;
 @Override
 public PageInfo<Street> getStreetByDid(Integer page, Integer pageSize, Integer did) {
  PageHelper.startPage(page,pageSize);
  StreetExample streetExample=new StreetExample();
  StreetExample.Criteria criteria=streetExample.createCriteria();
  //传条件
  criteria.andDistrictIdEqualTo(did);
  List<Street> streetList=streetMapper.selectByExample(streetExample);
  return new PageInfo<>(streetList);
 }
//添加街道
 @Override
 public void insertStreet(Street street) {
   streetMapper.insertSelective(street);
 }
//发布页面根据区域获取区域下的街道
 @Override
 public List<Street> getStreetByDid(Integer did) {
  StreetExample streetExample=new StreetExample();
  StreetExample.Criteria criteria=streetExample.createCriteria();
  //传条件
  criteria.andDistrictIdEqualTo(did);
  List<Street> streetList=streetMapper.selectByExample(streetExample);
  return streetList;
 }
}
