package com.whxh.house.service;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.District;

import java.util.List;

public interface DistrictService {
 //查询所有区域,带分页
   PageInfo<District> getDistrictByPage(Integer page,Integer pageSize);
   //添加区域
 public int addDistrict(District district);
 // 实现修改

 /**
  * 修改区域
  * @param district
  * @return
  */
 public int updateDistrict(District district);


 /**
  * 删除区域
  * @param 传id
  * @return
  */
 public int deleteDistrict(Integer id);
//批量删除
int deleteMoreDistrict(Integer[] ids);
//发布：查所有区域
 List<District> getAllDistrict();

}
