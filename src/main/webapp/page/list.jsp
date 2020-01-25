<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>安居客租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">

    <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
    <script language="JavaScript">
        $(function () {
            //加载类型
            $.post("getType",null,function (data) {
                for (var i=0;i<data.length;i++){
                    var option= $("<option value="+data[i].id+">"+data[i].name+"</option>");
                    $("#type").append(option);
                }
                //设置回显
                if (${condition.tid!=null}){
                    $("#type").val(${condition.tid})
                }
            },"json");

            //加载区域
            $.post("getDistrict",null,function (data) {
                for (var i=0;i<data.length;i++){
                    var option= $("<option value="+data[i].id+">"+data[i].name+"</option>");
                    $("#district").append(option);
                }
                //设置回显
                if (${condition.did!=null}){
                    $("#district").val(${condition.did})
                }
            },"json");
            //给区域添加改变事件，显示对应的街道
            $("#district").change(function () {
                // alert("我要显示街道");
                //获取区域id，到后台查询对应的街道
                var did=$(this).val();
                showStreet(did);

            });
        });

        function showStreet(did){
            //发送异步请求获取街道
            $.post("getStreetByDid2",{"did":did},function (data) {
                $("#street>option:gt(0)").remove();//清空选项   一直有"请选择"
                for (var i=0;i<data.length;i++){
                    var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                    $("#street").append(option);//追加节点
                }

                //设置回显
                if (${condition.sid!=null}){
                    $("#street").val(${condition.sid})
                }
            },"json");
        }
        //条件搜索后分页：传页码
        function goPage(num) {
            $("#showPage").val(num);
            //提交
            $("#form").submit();
        }

        //设置回显 面积

            $("#floorage").val(${condition.floorage})

    </script>

</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=form method=post action="goList">
      <input type="hidden" id="showPage" name="page" value="1">
  <UL id="ul1">
    <li>标题：<input type=text name=title value="${condition.title}"></li>
     <li>最低价格：<input type="text" name="startPrice" value="${condition.startPrice}">
         最高价格：<input type="text" name="endPrice" value="${condition.endPrice}">
     </li>

    <LI>区域：
        <SELECT id=district name=did>
        <OPTION selected value="">请选择</OPTION>
      </SELECT>
        街道：<SELECT id=street name=sid>
            <OPTION selected value="">请选择</SELECT>
    </LI>

    <LI>房型：
        <SELECT id="type" name=tid>
        <OPTION selected value="" >请选择</OPTION>
        </SELECT>
   </LI>
    <LI>面积：
        <SELECT name=floorage id="floorage">
        <OPTION value="">不限</OPTION>
        <OPTION value="0-40">40以下</OPTION>
        <OPTION value="41-80">41-80</OPTION>
        <OPTION value="81-500">81-500</OPTION>
    </SELECT>

    </LI>
      <LI>
        <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
      </LI>
    </UL>
  <input type="text" value="${condition.floorage}">
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span>
        <a href="details.jsp" target="_blank">
            <img src="http://localhost:81/${h.path}" width="100" height="75" alt="">
        </a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}区${h.sname}街,${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
  </TR>

  </c:forEach>
      </TBODY></TABLE>
<DIV class=pager>
<UL>
<%--  <LI class=current><A href="goList?num=1">首页</A></LI>
  <LI><A href="goList?num=${pageInfo.prePage==0?1:pageInfo.prePage}">上一页</A></LI>
  <LI><A href="goList?num=${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage}">下一页</A></LI>
  <LI><A href="goList?num=${pageInfo.pages}">末页</A></LI>--%>
    <LI class=current><A href="javascript:goPage(1);">首页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage});">上一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage});">下一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.pages});">末页</A></LI>
</UL>
    <SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN>
</DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>安居客租房 © 2018 安居客 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
