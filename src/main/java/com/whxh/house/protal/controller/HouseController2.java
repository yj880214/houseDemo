package com.whxh.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.whxh.house.entity.*;
import com.whxh.house.service.DistrictService;
import com.whxh.house.service.HouseService;
import com.whxh.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController2 {
 @Autowired
 private TypeService typeService;
 @Autowired
 private DistrictService districtService;
 @Autowired
 private HouseService houseService;

   @RequestMapping("preFabu")
   public String fabu(Model model){
    //查询所有的类型
    List<Type> typeList=typeService.getAllType();
    //查询所有的区域
    List<District> districtList =districtService.getAllDistrict();
    //使用model将数据传递到页面
    model.addAttribute("typeList",typeList);
    model.addAttribute("districtList",districtList);
    return "fabu";
   }
   //发布
 @RequestMapping("addHouse")
 public String addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {
  //将文件保存在服务器中  放入D:\\images
  File file = null;
  String fname = pfile.getOriginalFilename();
  if (fname.lastIndexOf(".")>0){
   String exName = fname.substring(fname.lastIndexOf("."));
   String saveName = System.currentTimeMillis() +exName;//保存文件名
    file = new File("D:\\images\\" + saveName);
   //  FileCopyUtils.copy(pfile.getBytes(),file);//保存
   pfile.transferTo(file);//保存方法2
   house.setPath(saveName);
  }

  //保存房屋信息，已有部分数据
  //设置编号

  house.setId(System.currentTimeMillis() +"");

  //用户编号
  Users users = (Users) session.getAttribute("user");
  house.setUserId(users.getId());
  house.setIspass(0);
  //设置是否删除
  house.setIsdel(0);
//  houseService.addHouse(house);

  if (houseService.addHouse(house)> 0) {
   return "redirect:preFabu";
  } else {
    file.delete(); //如果未添加信息成功，上传的图片删除
   return "list";
  }
 }
 //获取用户的出租房
 @RequestMapping("getUserHouse")
 public String getUserHouse(Integer page,HttpSession session,Model model){ //这里不用传用户id 直接在session中可以拿到
    Users users=(Users)session.getAttribute("user");
    PageInfo<House> pageInfo=houseService.getUserHouseByPage(page==null?1:page,10,users.getId());
    model.addAttribute("pageInfo",pageInfo);
    return "guanli";  //进入管理页面
 }

 //修改显示租房信息
 @RequestMapping("getHouse")
 public String getUserHouse(String id,Model model){
  //查询类型
  List<Type> typeList=typeService.getAllType();
  //查询区域
  List<District> districtList =districtService.getAllDistrict();
  //查询出租房信息
  House house=houseService.getHouse(id);
  //将数据设置到域中
  model.addAttribute("typeList",typeList);
  model.addAttribute("districtList",districtList);
  model.addAttribute("house",house);
  return "upfabu";
 }

 //修改租房信息
 @RequestMapping("upHouse")
 public String upHouse(String oldPic,House house,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile,HttpSession session) throws IOException {
   //修改时判断用户有没有修改图
//  System.out.println("dd:"+pfile.getOriginalFilename());
  File file=null;
  if (pfile.getOriginalFilename().equals("")||pfile.getOriginalFilename()==null){
//     System.out.println("不修改图片");
   //不修改图片，house实体path不需要path设置属性. 不操作
  }else {
   System.out.println("修改图片");
   //上传新的图片，删除旧的图片(或者覆盖图片名称)，设置path为上传新的图片名称
   //通过隐藏域传来的图片名称，而且用原来的图片名称
   file = new File("D:\\images\\" + oldPic);
   pfile.transferTo(file);
   house.setPath(oldPic);
  }
//房屋id  从表单隐藏域获取
  //用户编号  无需修改，不设置
  if (houseService.updateHouse(house) <=0) {
    if (file != null) file.delete(); //如果未添加信息成功，上传的图片删除
   }
   return "redirect:getUserHouse";
   }
   //删除房屋
 @RequestMapping("delHouse")
 @ResponseBody
 public String delHouse(String id){
    //调用业务
   int temp=houseService.delHouse(id);
  System.out.println("temp = " + temp);
//   return "redirect:getUserHouse"; //跳转到查询出租房页面
  return "{\"result\":"+temp+"}";  //异步 注意添加responsebody
 }
 //查询所有浏览出租房信息
/* @RequestMapping("goList")
 public String goList(Integer num,Model model){ //传页码
   PageInfo<House> pageInfo= houseService.getHouseByBrowser(num==null?1:num,3);
   //将分页信息放到作用域中
   model.addAttribute("pageInfo",pageInfo);
   return "list";
 }*/
 @RequestMapping("goList")
 public String goList(HouseCondition condition, Model model){ //传页码
  //首次访问，condition中没有页码，初始值设置1，即首页
  if (condition.getPage()==null){
    condition.setPage(1);
  }
  PageInfo<House> pageInfo= houseService.getHouseByBrowser(condition);
  //将分页信息放到作用域中
  model.addAttribute("pageInfo",pageInfo);
  //解决模糊查询回显取消%
  if (condition.getTitle()!=null){
   condition.setTitle(condition.getTitle().replaceAll("%",""));//回显时去掉%
  }
  //条件回显准备
  model.addAttribute("condition",condition);
  return "list";
 }

}