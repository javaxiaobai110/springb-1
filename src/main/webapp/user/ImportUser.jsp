<%--
  Created by IntelliJ IDEA.
  User: HENGEN
  Date: 2018/12/25
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form id="importUserForm1"  method="post" enctype="multipart/form-data">
    <input id="userImportfile1" name="file" />
    <a id="userImporta1"></a>
</form>
<script type="text/javascript">
    $("#userImportfile1").filebox({
        required:true
    });
    $("#userImporta1").linkbutton({
        text:"提交",
        iconCls:"icon-save",
        onClick:function () {
            $("#importUserForm1").form("submit",{

                url:"${pageContext.request.contextPath }/user/importUsers",
                onSubmit:function(){
                    // 表单验证 -- 调form的validate方法
                    return $("#importUserForm1").form("validate");
                },
                success:function(result){
                    if(result != null && result != ""){
                        $("#importDialog").dialog("close");
                        $.messager.show({
                            title:"系统提示",
                            msg:result
                        });
                        return ;
                    }
                    $("#user").edatagrid("reload");
                    $("#importDialog").dialog("close");
                    $.messager.show({
                        title:"系统提示",
                        msg:"保存成功"
                    });
                },
                /*queryParams:{"aaa":"xxx","bbb":"ccc"}*/
            })
        }
    });

</script>
