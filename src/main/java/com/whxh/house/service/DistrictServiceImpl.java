package com.whxh.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.District;
import com.whxh.house.entity.DistrictExample;
import com.whxh.house.mapper.DistrictMapper;
import com.whxh.house.mapper.StreetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
 @Autowired
 private DistrictMapper districtMapper;
 @Autowired
 private StreetMapper streetMapper;
 @Override
 public PageInfo<District> getDistrictByPage(Integer page, Integer pageSize) {
//  启动分页
  PageHelper.startPage(page,pageSize);
  //调用dao层查询：selectByExample  ，条件查询很有用
  //创建条件实体
  DistrictExample example=new DistrictExample();
/*  DistrictExample.Criteria criteria=example.createCriteria();
  //criteria添加条件
  criteria.andNameLike("%城%");*/
  List<District> list=districtMapper.selectByExample(example);
  return new PageInfo<>(list);
 }

 @Override
 public int addDistrict(District district) {
  return districtMapper.insertSelective(district);
 }

 @Override
 public int updateDistrict(District district) {
  return districtMapper.updateByPrimaryKeySelective(district);
 }

/* @Override
 public int deleteDistrict(Integer id) {
   //删除区域
     return districtMapper.deleteByPrimaryKey(id);
 }*/

 @Override
 @Transactional
 public int deleteDistrict(Integer id) {
  try {
   //  删除该id区域下的街道
   streetMapper.deleteStreetByDid(id);
   //删除区域
   districtMapper.deleteByPrimaryKey(id);
   return 1;
  }catch (Exception e){
   return 0;
  }
 }

 @Override
 public int deleteMoreDistrict(Integer[] ids) {
  return districtMapper.deleteMoreDistrict(ids);
 }
//发布页面查询展示区域
 @Override
 public List<District> getAllDistrict() {
  return districtMapper.selectByExample(new DistrictExample());
 }

}
