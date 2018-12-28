<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">

    $(function(){
        $("#titl").textbox({
            required:true,
        });
        /*$("#fil1").textbox({
            required:true
        });*/
        $("#sta1").textbox({
           required:true
        });
        $("#pub1").datebox({
            editable:false,
            required:true
        });
        $("#des1").textbox({
            required:true
        })

       $("#a1").linkbutton({
           iconCls:"icon-save",
           plain:true,
           onClick:function(){
               $("#f1").form("submit", {
                   url:"${pageContext.request.contextPath }/banner/add",
                   onSubmit:function(){
                       // 表单验证 -- 调form的validate方法
                       return $("#addUserForm").form("validate");
                   },
                   success:function(result){
                       $("#dg").edatagrid("load")
                       $("#addBannerDialog").dialog("close");
                       $.messager.show({
                           title:"系统提示",
                           msg:result
                       });
                   },
                   /*queryParams:{"aaa":"xxx","bbb":"ccc"}*/
               });
           }

       }) ;
    });

</script>

<form id="f1" method="post" enctype="multipart/form-data">
    Title: <input id="titl" type="text" name="title"><br/>
    ImgPath: <input id="fil1" type="file" name="file"><br/>
    Status: <input id="sta1" type="text" name="status"><br/>
    pubDate: <input id="pub1" type="text" name="pubDate"><br/>
    Description: <input id="des1" type="text" name="description"><br/>
    <a id="a1">保存</a>
</form>