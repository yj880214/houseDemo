﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%
  //判断当前用户有没有登入
  Object o=session.getAttribute("user");
  if (o==null){
    out.print("<script>alert('您还没有登入或者已登入超时！');location.href='login.jsp';</script>");
  }
%>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>安居客 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script type="text/javascript" src="/admin/js/jquery-1.8.3.js"></script>
  <script type="text/javascript">
     $(function () {
         //网页加载完区域和街道就显示
         showStreet($("#district").val());
        //给区域添加改变事件，显示对应的街道
        $("#district").change(function () {
          // alert("我也显示街道");
          //获取区域id，到后台查询对应的街道
            var did=$(this).val();
            showStreet(did); //点击事件后
        });
     });
     function showStreet(did){
         //发送异步请求获取街道
         $.post("getStreetByDid2",{"did":did},function (data) {
             $("#street>option").remove();//清空选项
             for (var i=0;i<data.length;i++){
                 var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                 $("#street").append(option);//追加节点
             }
                 $("#street").val(${house.streetId}); //设置街道
         },"json");
     }

  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV>
  <DIV class=search>[欢迎：${user.name}]<LABEL class="ui-green searchs"><a href="preFabu" title="">发布房屋信息</a></LABEL> <LABEL class="ui-green searchs"><a href="getUserHouse">管理房屋信息</a></LABEL>
    <LABEL class=ui-green><INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search></LABEL>
  </DIV></DIV>

<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息修改</DT>
  <DD class=past>更新房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=ss
      action="upHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <input type="hidden" name="id" value="${house.id}">
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD>
      <SELECT class=text name=typeId>
      <c:forEach items="${typeList}" var="t">
        <OPTION <c:if test="${t.id==house.typeId}">selected="selected"</c:if> value=${t.id}>${t.name}</OPTION>
      </c:forEach>
    </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT id="district" class=text name=district_id>
      <c:forEach items="${districtList}" var="d">
        <OPTION <c:if test="${house.districtid==d.id}">selected="selected"</c:if>
                value=${d.id}>${d.name}</OPTION>
      </c:forEach>
    </SELECT>
      街：<SELECT class=text id="street" name="streetId">
        <OPTION selected value=1001>请选择街道</OPTION>
      </SELECT>
    </TD></TR>

  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD><img src="http://localhost:81/${house.path}?a=<%=Math.random()%>" width="100" height="100">
        <input type="hidden" name="oldPic" value="${house.path}">
      <INPUT id=pic name="pfile" class=text type=file > </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description> ${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT  value=立即更新 type=submit name="xxx">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>安居客租房 © 2018 安居客 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
