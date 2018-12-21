<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">

        var toolbar = [{
            iconCls: 'icon-add',
            text: "专辑详情",
            handler: function () {
                var row = $("#album").treegrid("getSelected");

                if(row == null){
                    alert("请选择专辑");
                    return;
                }else if(row.duration != null) {
                    alert("请选择专辑");
                    return;
                }else {
                    showOne(row);
                }


            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-edit',
            handler: function () {
                $("#addOne").dialog("open");
            }
        }, '-', {
            text: "添加音频",
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#album").treegrid("getSelected");
                if(row == null){
                    alert("请选择专辑");
                    return;
                }else if(row.duration != null) {
                    alert("请选择专辑");
                    return;
                }else {
                    addAudio(row);
                }
            }
        }, '-', {
            text: "音频下载",
            iconCls: 'icon-save',
            handler: function () {
                var row = $("#album").treegrid("getSelected");
                if(row == null){
                    alert("请选择文件");
                    return;
                }else if(row.author != null) {
                    alert("请选择文件");
                    return;
                }else {
                    //提交下载文件的表单
                }

            }
        }]
    $(function () {

        $("#addOne").dialog({
            title:'添加专辑',
            iconCls:"icon-man",
            cache:false,
            resizable:true,
            width:600,
            height:400,
            closed:true,
            href:"${pageContext.request.contextPath}/album/addAlbum.jsp",
        });



        $('#album').treegrid({
            url:'${pageContext.request.contextPath}/album/showAll',
            idField:'id',
            treeField:'title',
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            columns:[[
                {field:'title',title:'名字',width:60},
                {field:'duration',title:'时长',width:80},
                {field:'size',title:'大小',width:80}
            ]],
            fit:true,
            fitColumns:true,
            toolbar:toolbar,

        });
    })

    function showOne(row) {
        $("#showOne").dialog({
            title:'专辑详情',
            iconCls:"icon-man",
            cache:false,
            resizable:true,
            width:600,
            height:400,
            toolbar:'#tb',
            closed:false,
            href:"${pageContext.request.contextPath}/album/showOne.jsp",
            queryParams: {"row":row}
        });
    }

    function addAudio(row) {
        $("#addAudio").dialog({
            title:'添加音频',
            iconCls:"icon-man",
            cache:false,
            resizable:true,
            width:600,
            height:400,
            closed:false,
            href:"${pageContext.request.contextPath}/chapter/addChapter.jsp",
            queryParams: {"row":row}
        });
    }

</script>


<table id="album"></table>
<div id="showOne"></div>
<div id="addOne"></div>
<div id="addAudio"></div>
