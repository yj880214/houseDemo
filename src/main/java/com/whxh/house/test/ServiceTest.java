/*
package com.whxh.house.test;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.District;
import com.whxh.house.service.DistrictService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {
 public static void main(String[] args) {
  ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
  DistrictService districtService=(DistrictService)act.getBean("districtServiceImpl");
  PageInfo<District> pageInfo=districtService.getDistrictByPage(1,3);
  for (District d:pageInfo.getList()) {
   System.out.println(d.getName());
  }
 }
}
*/
