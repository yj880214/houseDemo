package com.whxh.house.mapper;

import com.whxh.house.entity.House;
import com.whxh.house.entity.HouseCondition;
import com.whxh.house.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户发布的出租房信息
    List<House> getHouseByUserId(Integer uid);

    //根据房屋id查询该出租房信息(带区域)
    House getHouseAndDid(String id);
//查询审核的房屋信息
    List<House> getHouseByState(Integer state);

    //查询浏览的出租房
// List<House> getHouseByBrowser();
    List<House> getHouseByBrowser(HouseCondition condition);

}
