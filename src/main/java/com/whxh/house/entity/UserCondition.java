package com.whxh.house.entity;

public class UserCondition {
  //分页
 private Integer page;//页码或者当前页
 private Integer rows; //页大小
 //条件属性
 private String telephone;//电话
 private Integer startAge;//最小年龄
 private Integer endAge;//最大年龄

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public Integer getRows() {
  return rows;
 }

 public void setRows(Integer rows) {
  this.rows = rows;
 }

 public String getTelephone() {
  return telephone;
 }

 public void setTelephone(String telephone) {
  this.telephone = telephone;
 }

 public Integer getStartAge() {
  return startAge;
 }

 public void setStartAge(Integer startAge) {
  this.startAge = startAge;
 }

 public Integer getEndAge() {
  return endAge;
 }

 public void setEndAge(Integer endAge) {
  this.endAge = endAge;
 }


}
