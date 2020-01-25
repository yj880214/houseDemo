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
    <title>房屋管理页面</title>
    <%--<link href="Css/default.css" rel="stylesheet" type="text/css" />--%>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

    <script language="JavaScript">
        $(function () {
            //使用datagrid绑定数据展示
            $("#dg").datagrid({
                title:"审核出租房管理",
                url:"/admin/getHouseByNoPass",
                fitColumns:true,
                pagination:true,
                pageList:[2,4,6,10],
                toolbar:"#ToolBar",  //工具栏
                pageSize:4,
                // singleSelect:true ,//设置单选
                columns:[[
                    {field:'check',checkbox:true}, //设置复选框
                    {field:'id',title:'房屋编号',width:50,align:"center"},
                    {field:'title',title:'标题',width:50,align:"center"},
                    {field:'price',title:'价格',width:50,align:"center"},
                    {field:'floorage',title:'面积',width:50,align:"center"},
                    {field:'dname',title:'区域',width:50,align:"center"},
                    {field:'sname',title:'街道',width:50,align:"center"},
                    {field:'tname',title:'类型',width:50,align:"center"},
                    {field:'ispass',title:'状态',width:50,align:"center",
                        formatter:function (value,row,index) {
                            return "未审核";  //每行显示的值为"未审核"
                        }
                    },
                    {field:'opt',title:'操作',width:50,align:"center",
                        formatter: function(value,row,index){
                            return "<a href='javascript:checkPass("+row.id+")'>点击审核</a>";
                        }
                    }
                ]]
            });
        });

       /* //条件搜索
        function searchUser() {
            // $("#dg").datagrid("load",跟查询条件的参数);
            var telephone=$("#tel").val();
            var startAge=$("#start").val();
            var endAge=$("#end").val();
            $("#dg").datagrid("load",{"telephone":telephone,"startAge":startAge,"endAge":endAge});
        }*/

       //审核房屋
        function checkPass(id) {
            //异步请求
            alert("测试");
            $.post("passHouse",{"id":id},function (data) {
                alert(id);
                 if (data.result>0){
                     $.messager.alert("提示框","审核通过！");
                     $("#dg").datagrid("reload");
                 } else {
                     $.messager.alert("提示框","审核未通过！");
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
    <%--条件搜索--%>
    <div>
        区域：<select name=""></select>
        街道：<select name=""></select>
        类型：<select name=""></select>
        最小价格：<input type="text" name="startAge" id="start"/>
        最大价格：<input type="text" name="endAge" id="end"/>
        <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
</div>


  <table id="dg"></table>


</body>
</html>
