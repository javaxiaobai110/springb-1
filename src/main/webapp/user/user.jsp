<%--
  Created by IntelliJ IDEA.
  User: HENGEN
  Date: 2018/12/25
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">

    var toolbar = [{
        iconCls: 'icon-add',
        text: "修改",
        handler: function () {
            //获取选中行
            var row = $("#user").edatagrid("getSelected");
            if (row != null) {
                //编辑指定行
                var index = $("#user").edatagrid("getRowIndex", row);
                $("#user").edatagrid("editRow", index);
            } else {
                alert("请先选中行")
            }

        }
    }, '-', {
            text: "保存",
            iconCls: 'icon-edit',
            handler: function () {
                $("#user").edatagrid("saveRow")
            }
    }, '-', {
        text: "导出",
        iconCls: 'icon-redo',
        handler: function () {
            window.location.href = "${pageContext.request.contextPath}/user/exportAllUser"
        }
    }, '-', {
        text: "导入",
        iconCls: 'icon-save',
        handler: function () {
            //window.location.href = "${pageContext.request.contextPath}/user/importUsers"
            $("#importDialog").dialog("open");
        }
    }]
    $(function () {





        $('#user').edatagrid({
            url:'${pageContext.request.contextPath}/user/byPage',
            //idField:'id',
            //treeField:'title',
            autoSave: true,
            updateUrl: "${pageContext.request.contextPath}/user/updateUser",
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            columns:[[
                {field:'phone',title:'用户名',width:5},
                {field:'password',title:'密码',width:5},
                {field:'salt',title:'盐',width:5},
                {field:'dharma',title:'上师',width:5},
                {field:'sign',title:'签名',width:5},
                {field:'sex',title:'性别',width:5},
                {field:'name',title:'名字',width:5},
                {field:'headPic',title:'头像',width:5,
                    formatter: function(value,row,index){
                        if(value == null){
                            return "";
                        }
                        return "<img src='${pageContext.request.contextPath}/img/"+value+"'>";
                    }
                },
                {field:'province',title:'省',width:5},
                {field:'city',title:'城市',width:5},
                {field:'status',title:'状态',width:5,editor: {
                        type: "text",
                        options: {required:true}
                    }},
                {field:'regTime',title:'注册时间',width:5},
            ]],
            fit:true,
            fitColumns:true,
            toolbar:toolbar,

        });
    })

    function addAudio(row) {

    }
    $(function () {
        $("#importDialog").dialog({
            title:'导入文件',
            iconCls:"icon-man",
            cache:false,
            resizable:true,
            width:600,
            height:400,
            closed:true,
            href:"${pageContext.request.contextPath}/user/ImportUser.jsp",
        });
    })
</script>

<table id="user">

</table>
<div id="importDialog"></div>
