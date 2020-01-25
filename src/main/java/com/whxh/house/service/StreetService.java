package com.whxh.house.service;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.Street;

import java.util.List;

public interface StreetService {
 //通过区域显示街道带分页
 PageInfo<Street> getStreetByDid(Integer page, Integer pageSize, Integer did);

 //添加街道（根据区域的id）
 void insertStreet(Street street);

 //发布页面显示区域下的街道
 List<Street> getStreetByDid(Integer did);
}