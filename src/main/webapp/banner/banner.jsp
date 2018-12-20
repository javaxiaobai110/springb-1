<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#addBannerDialog").dialog({
            title:'添加用户',
            iconCls:"icon-man",
            cache:false,
            resizable:true,
            width:400,
            height:200,
            toolbar:'#tb',
            closed:true,
            href:"${pageContext.request.contextPath}/banner/banneradd.jsp"
        });

        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#addBannerDialog").dialog("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("destroyRow", index);
                } else {
                    alert("请先选中行");
                    return ;
                }


            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")
            }
        }]

        $('#dg').edatagrid({
            method: "GET",
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            url: '${pageContext.request.contextPath}/banner/showAll',
            //destroyUrl: "${pageContext.request.contextPath}/banner/delete",
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required:true}
                    }
                },
                {field: 'pubDate', title: '时间', width: 100, align: 'right'}

            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            toolbar: toolbar,
            view: detailview,
            onDestroy:function(index,row){
                console.log(index);
                console.log(row);
                $.get("${pageContext.request.contextPath}/banner/delete","id="+row.id,function(){
                    $("#dg").edatagrid("load");
                });
            },
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

</script>

<table id="dg"></table>

<div id="addBannerDialog">这是自定义对话框</div>