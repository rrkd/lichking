<%@include file="../globe.jsp" %>
<html>
<head>
    <meta name="description" content="Payment Success">
    <title>Payment Success</title>
</head>
<body>
<center>
    <div style='background:#fff;width:75px;padding:10px;margin-top:120px;'><img src='/assets/img/ldng.gif'
                                                                                style='color:#000;margin:0 0 0 0;'/><br/><font
            face='verdana' style='color:#000' size='1'>LOADING</font></div>
</center>
</body>
<script type="text/javascript">
    jQuery(function ($) {
        var payKey = "${payKey}";
        var transactionId ="${transactionId}";
        var callback ="${callback}";
        $.ajax({
            type:"GET",
            url:"/ws/paymentDetails?payKey=" + payKey + "&transactionId=" + transactionId,
            data:"",
            success:function (data) {
                if (data.status == 'OK') {
                    alert('Email has been Send.');
                }
                else {
                    window.location.href = callback;
                }
            }
        });
    });
</script>
</html>