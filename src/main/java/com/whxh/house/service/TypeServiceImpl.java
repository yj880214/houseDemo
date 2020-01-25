package com.whxh.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Type;
import com.whxh.house.entity.TypeExample;
import com.whxh.house.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
 @Autowired
 private TypeMapper typeMapper;
 @Override
 public PageInfo<Type> getTypeByPage(Integer page, Integer pageSize) {
  //  启动分页
  PageHelper.startPage(page,pageSize);
  //调用dao层查询：selectByExample  ，条件查询很有用
  //创建条件实体
  TypeExample example=new TypeExample();

  List<Type> list=typeMapper.selectByExample(example);
  return new PageInfo<>(list);
 }

 @Override
 public int addType(Type type) {
  return typeMapper.insertSelective(type);
 }

 @Override
 public int updateType(Type type) {
  return typeMapper.updateByPrimaryKeySelective(type);
 }

 @Override
 public int deleteType(Integer id) {
  return typeMapper.deleteByPrimaryKey(id);
 }

 @Override
 public int deleteMoreType(Integer[] ids) {
  return typeMapper.deleteMoreType(ids);
 }

 //发布页面查询展示类型
 @Override
 public List<Type> getAllType() {
  return typeMapper.selectByExample(new TypeExample());
 }
}
