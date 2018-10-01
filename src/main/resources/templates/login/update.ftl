<html >
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>

    <link rel="stylesheet" type="text/css" href="/crawler/css/public.css" />
    <link rel="stylesheet" type="text/css" href="/crawler/css/page.css" />
    <script type="text/javascript" src="/crawler/js/jquery.min.js"></script>
    <script type="text/javascript" src="/crawler/js/public.js"></script>
</head>
<body>

<!-- 登录body -->
<div class="logDiv">
    <img class="logBanner" src="/crawler/img/logBanner.png" />
    <div class="logGet">
        <!-- 头部提示信息 -->
        <div class="logD logDtip">
            <p class="p1">修改密码</p>
        </div>
        <!-- 输入框 -->
        <form method="post" action="/crawler/user/update">
            <div class="lgD">
                <img class="img1" src="/crawler/img/logPwd.png" /><input type="password" name="passwordOld" required="required"
                                                                          placeholder="输入旧密码" />
            </div>
            <div class="lgD">
                <img class="img1" src="/crawler/img/logPwd.png" /><input type="password" name="passwordNew1" required="required"
                                                                         placeholder="输入新密码" />
            </div>
            <div class="lgD">
                <img class="img1" src="/crawler/img/logPwd.png" /><input type="password" name="passwordNew2" required="required"
                                                                         placeholder="确认新密码" />
            </div>
            <div class="logC">
                <button type="submit" class="btn">修改</button>
            </div>
        </form>
    </div>
</div>
<!-- 登录body  end -->

<!-- 登录页面底部 -->
<div class="logFoot">
    <p class="p1">版权所有：XXX公司</p>
    <p class="p2">电话号码：123456789</p>
</div>
<!-- 登录页面底部end -->

</body>
</html>