<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script type="text/javascript">
    var goeasy = new GoEasy({
        appkey: 'BS-9c012ab0820644b6a17b1cf2e4ffeb3b'
    });
    console.log(goeasy);
    alert("heh");
    goeasy.subscribe({
        channel: 'FirstgoEasy',
        onMessage: function(message){
            alert("hhhhhhhh");
            alert(message.content);
        }
    });



</script>
<span>heheh</span>