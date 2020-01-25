<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/12/3
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类型展示页面</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

    <script language="JavaScript">
        $(function () {
            //使用datagrid绑定数据展示
            $("#dg").datagrid({
                title:"类型管理",
                url:"/admin/getType",
                fitColumns:true,
                pagination:true,
                pageList:[2,4,6,10],
                toolbar:"#ToolBar",  //工具栏
                pageSize:2,
                // singleSelect:true ,//设置单选
                columns:[[
                    {field:'check',checkbox:true}, //设置复选框
                    {field:'id',title:'编号',width:50,align:"center"},
                    {field:'name',title:'名称',width:50,align:"center"},
                    {field:'opt',title:'操作',width:50,align:"center",
                        formatter: function(value,row,index){
                            // return "<a href='deleteDistrict?id="+row.id+"'>删除</a>";
                            return "<a href='javascript:delSingle("+row.id+")'>删除</a>";
                        }
                    }
                ]]
            });
        });

        //打开窗口
        function Add() {
            // alert("ok");
            $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
        }

        //关闭添加的窗口
        function CloseDialog() {
            $("#AddDialog").dialog("close");
        }

        //    保存添加信息
        function SaveDialog() {
            //跳转到后台实现保存
            //传统：取值--通过$.ajax发送异步请求实现添加
            $('#addDialogForm').form('submit', {
                url:"/admin/addType",
                success:function(data){ //回调函数  接收服务器返回的值
                    // data接收的是json字符串，不是json对象  ;需要转换
                    var obj=$.parseJSON(data);
                    if (obj.result>0){
                        // alert("添加成功！")
                        //关闭添加窗口
                        $("#AddDialog").dialog("close");
                        $("#dg").datagrid("reload");//刷新
                        //弹出消息窗口
                        $.messager.alert('提示','添加成功!');

                    } else {
                        $.messager.alert('提示','系统维护中...');
                    }
                }
            });
        }
        //    弹出修改框
        function ModifyBySelect() {
            //判断有没有选择要修改的记录
            //获取选中行，没有选中就返回空
            var SelectRows=$("#dg").datagrid("getSelections")
            if (SelectRows.length!=1){
                $.messager.alert('提示','您还没有选中行或者选择了多行。');
                return;
            }
            //往下执行，表明选择了一行
            //显示数据
            var SelectRow=SelectRows[0]; //获取当前行的数据
            $("#upDialog").dialog("open").dialog('setTitle',"修改类型");
            //获得行对象的数据，加载到表单中显示
            $("#upDialogForm").form("load",SelectRow);
        }
        //    关闭修改窗口
        function CloseDialog2() {
            $("#upDialog").dialog("close");
        }
        //    实现修改
        function upDialog() {
            //    提交表单
            //跳转到后台实现保存
            //传统：取值--通过$.ajax发送异步请求实现添加
            $('#upDialogForm').form('submit', {
                url:"upType",
                success:function(data){ //回调函数  接收服务器返回的值
                    // data接收的是json字符串，不是json对象  ;需要转换
                    var obj=$.parseJSON(data);
                    if (obj.result>0){
                        // alert("添加成功！")
                        //关闭添加窗口
                        $("#upDialog").dialog("close");
                        //刷新页面
                        $("#dg").datagrid("reload");
                        //弹出消息窗口
                        $.messager.alert('提示','修改成功!');

                    } else {
                        $.messager.alert('提示','系统维护中...');
                    }
                }
            });

        }

        //    删除单条
        function delSingle(id) {
            $.messager.confirm("确认提示","确认要删除吗？",function (result) {
                if (result){
                    //实现删除,传参的格式{“参数名称”：值，“参数名称”：值}
                    $.post("deleteType",{"id":id},function (data) {
                        if (data.result>0){
                            $("#dg").datagrid("reload");//刷新
                            // $.messager.alert("提示框","删除成功！")
                        } else {
                            $.messager.alert("提示框","系统维护中")
                        }
                    },"json");
                }
            });

        }
        //批量删除
        function DeleteMore() {
            //判断有无选中行
            var SelectRows=$("#dg").datagrid("getSelections") //获取选中行
            if (SelectRows.length==0){
                $.messager.alert('提示','您还没有选中行。');
                return;
            }
            //获取选中项的值 id  拼成1,2,3
            var delvalue="";
            for (var i=0;i<SelectRows.length;i++){
                delvalue=delvalue+SelectRows[i].id+",";
            }
            delvalue=delvalue.substring(0,delvalue.length-1);
            alert(delvalue);
            //发送异步请求到服务器
            $.post("deleteMoreType",{"ids":delvalue},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload");//刷新
                    $.messager.alert("提示框","成功删除"+data.result+"行！")
                } else {
                    $.messager.alert("提示框","系统维护中")
                }
            },"json");
        }
    </script>
</head>
<body>

  <%--定义工具栏--%>
  <div id="ToolBar">
      <div style="height: 40px;">
          <a href="javascript:Add()" class="easyui-linkbutton"
             iconCls="icon-add" plain="true">添加</a>
          <a
              href="javascript:ModifyBySelect()" class="easyui-linkbutton"
              iconCls="icon-edit" plain="true">修改</a>
          <a
              href="javascript:DeleteMore()" class="easyui-linkbutton"
              iconCls="icon-remove" plain="true">删除选中项</a>
      </div>
  </div>

<%--添加信息的对话框显示--%>
  <div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
       style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
      <form id="addDialogForm" method="post">
          <table>
            <tr>
                  <td>类型:</td>
                  <td><input type="text" name="name" id="author" /></td>
              </tr>
          </table>
      </form>
  </div>

  <%--为“添加保存”的对话框添加按钮--%>
  <div id="AddDialogButtons">
      <a href="javascript:SaveDialog()" class="easyui-linkbutton"
         iconCls="icon-ok">保存</a>
      <a href="javascript:CloseDialog()" class="easyui-linkbutton"
         iconCls="icon-cancel">取消</a>
  </div>

  <%--修改信息的对话框显示--%>
  <div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
       style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
      <form id="upDialogForm" method="post">
          <table>
              <tr>
                  <td>编号:</td>
                  <td><input type="text" name="id" readonly id="dd" /></td>
              </tr>
              <tr>
                  <td>类型:</td>
                  <td><input type="text" name="name" id="author" /></td>
              </tr>
          </table>
      </form>
  </div>

  <%--为“修改”的对话框添加修改按钮--%>
  <div id="upDialogButtons">
      <a href="javascript:upDialog()" class="easyui-linkbutton"
         iconCls="icon-ok">修改</a>
      <a href="javascript:CloseDialog2()" class="easyui-linkbutton"
         iconCls="icon-cancel">取消</a>
  </div>
  <table id="dg"></table>


</body>
</html>
