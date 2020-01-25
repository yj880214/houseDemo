package com.whxh.house.mapper;

import com.whxh.house.entity.District;
import com.whxh.house.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    //实现批量删除  传list集合或者数组
    int deleteMoreDistrict(Integer[] ids);


}