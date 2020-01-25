package com.whxh.house.mapper;

import com.whxh.house.entity.Street;
import com.whxh.house.entity.StreetExample;
import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);
//添加街道
    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
    //通过区域删除街道
    int deleteStreetByDid(Integer id);
}