<%@include file="../globe.jsp" %>
<html>
<head>
    <title>test</title>
    <script type="text/javascript" src ="https://www.paypalobjects.com/js/external/dg.js"></script>
</head>
<body>
    <form enctype='application/json' action="/app/pay" method="POST">
        <input name='userId' value='1000'>
        <input name='payEmail' value='zumaexhaust-buyer1@gmail.com'>
        <input name='planId' value=''>
        <input name='money' value='1.0'>
        <input name='description' value='This is the test process'>
        <input name='callback' value='/app/paymentTestCB'>
        <input name='applicationId' value='ahJkdWxjZXQtZ2xhemluZy03MzdyGAsSC0FwcGxpY2F0aW9uGICAgICAgIALDA'>
        <input name='applicationToken' value='testtoken'>

        <input id="std_pay_button" type="submit" value="Submit">
    </form>
</body>
<script type="text/javascript" charset="utf-8">
    var dgFlow = new PAYPAL.apps.DGFlow({trigger: 'ep_authz_button'});
    function get_full_url(url_path) {
        var loc = window.location;
        var url = "" + loc.protocol + "//" + loc.host + url_path;
        return url;
    }

    function MyDGFlow(dgFlow) {
        this.mainObj = dgFlow;
        this.dgSuccess = function (payKey) {
            this.mainObj.closeFlow();
            window.location.href = get_full_url("/payDetails?payKey=" + payKey);
        };
        this.dgCancel = function () {
            this.mainObj.closeFlow();
            alert("You've canceled your payment!");
        };

    }
    var myDgFlow = new MyDGFlow(dgFlow);
</script>
</html>