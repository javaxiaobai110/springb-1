<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
           $.get("${pageContext.request.contextPath}/menu/showAllFu",function (result) {

                for(var i = 0; i < result.length; i++){

                    if(i == 0) { //显示第一个一级菜单下的二级菜单
                        $('#aa').accordion('add', {
                            title: result[i].text,
                            iconCls:result[i].iconCls,
                            content: '<div style="padding:10px 0px"><ul id="tree' + result[i].id + '"></ul></div>',
                            selected: false
                        });
                    } else {
                        $('#aa').accordion('add', {
                            title: result[i].text,
                            iconCls:result[i].iconCls,
                            content: '<div style="padding:10px 0px"><ul id="tree' + result[i].id + '"></ul></div>',
                            selected: false
                        });
                    }



                    $.ajax({
                        type: 'get',
                        async: false,
                        dataType: "json",
                        url: '${pageContext.request.contextPath}/menu/showAllZi/'+result[i].id,
                        success: function(data) {

                            $("#tree" + result[i].id).tree({
                                data: data,
                                animate: true,
                                onClick: function(node) { // 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs
                                    //if(node.url){//判断url是否存在，存在则创建tabs
                                    if(node) {
                                        addTab(node);
                                    }
                                }

                            });
                            /*$(".tree-icon,.tree-file").removeClass("tree-icon tree-file");
                            $(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed");*/
                        }

                    });
                }
           });

        });

        function addTab(node) {
            //var t=$.trim(t);
            var tabExitOrNot = $('#tt').tabs('exists', node.text);//判断此选项卡是否已存在
            if(tabExitOrNot == true) {
                $('#tt').tabs('select', node.text);
                return;
            }
            //添加选项卡
            $('#tt').tabs('add', {
                title: node.text,
                closable: true,
                href:"${pageContext.request.contextPath}/"+node.url,
                tools:[{
                    iconCls:node.iconCls,
                    handler:function(){
                        alert('refresh');
                    }
                }]

            });
        }
    </script>

</head>
<body class="easyui-layout">
<%--<c:if test="${sessionScope.admin == null}">
    <% response.sendRedirect(request.getContextPath()+"/login.jsp");%>
</c:if>--%>


<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>

    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        <shiro:authenticated>
            欢迎您:<shiro:principal></shiro:principal>
            <sh
            &nbsp;
        </shiro:authenticated>
        <shiro:notAuthenticated>
            你好，请<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
            &nbsp;
        </shiro:notAuthenticated>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/logout"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <%--<div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
            <h3 style="color:#0099FF;">Accordion for jQuery</h3>
            <p>Accordion is a part of easyui framework for jQuery.
                It lets you define your accordion component on web page more easily.</p>
        </div>
        <div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
            content2
        </div>
        <div title="Title3">
            content3
        </div>--%>

    </div>
</div>

<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood'" style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>