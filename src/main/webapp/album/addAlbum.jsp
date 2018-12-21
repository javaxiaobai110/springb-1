<%--
  Created by IntelliJ IDEA.
  User: HENGEN
  Date: 2018/12/21
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<script type="text/javascript">
    $(function(){
        $("#albtit").textbox({
            required:true
        });

        $("#albaut").textbox({
            required:true
        });
        $("#albroad").textbox({
            required:true
        });
        $("#albrif").textbox({
            required:true
        });
        $("#albdat").datebox({
            required:true
        });
        $("#alba").linkbutton({
            iconCls:"icon-save",
            text:"保存",
            plain:true,
            onClick:function(){
                $("#forms1").form("submit", {
                    url:"${pageContext.request.contextPath }/album/add",
                    onSubmit:function(){
                        // 表单验证 -- 调form的validate方法
                        return $("#forms1").form("validate");
                    },
                    success:function(){
                        $("#dg").treegrid("reload")
                        $("#addOne").dialog("close");
                        $.messager.show({
                            title:"标题",
                            msg:"保存成功"
                        });
                    },
                    /*queryParams:{"aaa":"xxx","bbb":"ccc"}*/
                });
            }
        })


    });

</script>

<form id="forms1" method="post" enctype="multipart/form-data">
    Title: <input id="albtit" type="text" name="title"/><br/>
    CoverImg: <input id="albcmg" type="file" name="file"/><br/>
    Author: <input id="albaut" type="text" name="author"/><br/>
    Broadcast: <input id="albroad" type="text" name="broadcast"/><br/>
    Brief: <input id="albrif" type="text" name="brief"/><br/>
    PubDate: <input id="albdat" type="text" name="pubDate"/>
    <a id="alba"></a>
</form>
