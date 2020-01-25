package com.whxh.house.entity;

public class HouseCondition {
   //定义查询条件
  private String title;//标题
  private Integer startPrice;//最低价格
  private Integer endPrice;//最高价格
  private Integer did;// 区域id
  private Integer sid;//街道id
  private Integer tid;//类型id
  private String floorage;//面积  格式0-40  转移到startFloora、endFloora

  private Integer startFloora;//最小面积
  private Integer endFloora;//最大面积
  //设置分页的属性
  private Integer page;     //页码
  private Integer pageSize=2; //页大小

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public Integer getPageSize() {
  return pageSize;
 }

 public void setPageSize(Integer pageSize) {
  this.pageSize = pageSize;
 }

 public Integer getStartFloora() {
  return startFloora;
 }

 public void setStartFloora(Integer startFloora) {
  this.startFloora = startFloora;
 }

 public Integer getEndFloora() {
  return endFloora;
 }

 public void setEndFloora(Integer endFloora) {
  this.endFloora = endFloora;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
//  this.title = "%"+title+"%";
 }

 public Integer getStartPrice() {
  return startPrice;
 }

 public void setStartPrice(Integer startPrice) {
  this.startPrice = startPrice;
 }

 public Integer getEndPrice() {
  return endPrice;
 }

 public void setEndPrice(Integer endPrice) {
  this.endPrice = endPrice;
 }

 public Integer getDid() {
  return did;
 }

 public void setDid(Integer did) {
  this.did = did;
 }

 public Integer getSid() {
  return sid;
 }

 public void setSid(Integer sid) {
  this.sid = sid;
 }

 public Integer getTid() {
  return tid;
 }

 public void setTid(Integer tid) {
  this.tid = tid;
 }

 public String getFloorage() {
  return floorage;
 }

 public void setFloorage(String floorage) {
  this.floorage = floorage;
  if (floorage!=null&&!floorage.equals("")){
    String arr[]=floorage.split("-");
    this.setStartFloora(Integer.parseInt(arr[0]));
    this.setEndFloora(Integer.parseInt(arr[1]));
  }
 }
}
