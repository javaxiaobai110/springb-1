<%@page pageEncoding="UTF-8" %>
<style type="text/css">
    .lay1{ width:500px; height:100px;  float:left}
    .lay2{ width:500px; height:100px;  float:left}

</style>
<script type="text/javascript">
    var obj=$('#showOne').dialog('options');
    var queryParams = obj["queryParams"];
    var result =  queryParams["row"];
    $(function(){

            console.log(result.id);
            $("#sp0").html(result.title);
            $("#img1").prop("src",result.coverImg);
            $("#sp1").text(result.author);
            $("#sp2").text(result.broadcast);
            $("#sp3").text(result.count);
            $("#sp4").text(result.score);
            $("#sp5").text(result.pubDate);
            $("#sp6").text(result.brief);



    });

</script>

<div id="divfu">
    专辑名称：<span id="sp0"></span>
    <hr/>
    <div class="lay1">
        <img id="img1"/>
    </div>
    <div class="lay2">
        作者：<span id="sp1"></span>
        <hr/>
        播音：<span id="sp2"></span>
        <hr/>
        集数：<span id="sp3"></span>
        <hr/>
        评分：<span id="sp4"></span>
        <hr/>
        发布日期：<span id="sp5"></span>
        <hr/>
        简介：<span id="sp6"></span>
    </div>



</div>
