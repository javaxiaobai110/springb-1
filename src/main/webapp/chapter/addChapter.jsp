<%--
  Created by IntelliJ IDEA.
  User: HENGEN
  Date: 2018/12/21
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<script type="text/javascript">
    var obj=$('#addAudio').dialog('options');
    var queryParams = obj["queryParams"];
    var result =  queryParams["row"];
    $(function () {


        $("#albId").val(result.id);
        $("#chapalbm").val(result.title);

        $("#chaptit").textbox({
            required:true
        });

        $("#chapsize").textbox({
            required:true
        });
        $("#chapdura").textbox({
            required:true
        });

        $("#chapuplda").datebox({
            required:true
        });
        $("#chapalbm").textbox({
            editable:false
        });
        $("#chapadd").linkbutton({
            iconCls:"icon-save",
            text:"保存",
            plain:true,
            onClick:function(){
                $("#forms2").form("submit", {
                    url:"${pageContext.request.contextPath }/chapter/add",
                    onSubmit:function(){
                        // 表单验证 -- 调form的validate方法
                        return $("#forms2").form("validate");
                    },
                    success:function(){
                        $("#dg").treegrid("load")
                        $("#addAudio").dialog("close");
                        $.messager.show({
                            title:"标题",
                            msg:"保存成功"
                        });
                    },
                    /*queryParams:{"aaa":"xxx","bbb":"ccc"}*/
                });
            }
        });

        $("#reset1").linkbutton({
            iconCls:"icon-redo",
            text:"重置",
            onClick:function () {
                $("#forms2").form("reset");
            }
        });

    });
</script>
<form id="forms2" method="post" enctype="multipart/form-data">
    Title: <input id="chaptit" type="text" name="title"/><br/>
    CoverImg: <input id="chapsize" type="text" name="size"/><br/>
    Author: <input id="chapdura" type="text" name="duration"/><br/>
    Broadcast: <input id="chapurl" type="file" name="file" required="true"/><br/>
    Brief: <input id="chapuplda" type="text" name="uploadDate"/><br/>
    Album: <input id="chapalbm" type="text" value=""/>
    <input id="albId" type="hidden" name="albId" value="">
    <a id="chapadd"></a>
    <a id="reset1"></a>
</form>

