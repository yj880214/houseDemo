package com.whxh.house.service;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Type;

import java.util.List;

public interface TypeService {
 //查询所有区域,带分页
   PageInfo<Type> getTypeByPage(Integer page, Integer pageSize);
   //添加区域
 public int addType(Type type);
 // 实现修改

 /**
  * 修改区域
  * @param type
  * @return
  */
 public int updateType(Type type);


 /**
  * 删除区域
  * @param 传id
  * @return
  */
 public int deleteType(Integer id);
//批量删除
int deleteMoreType(Integer[] ids);
//发布页面：查询所有的类型
 List<Type> getAllType();

}
