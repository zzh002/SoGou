<html >
<head>
    <meta charset="UTF-8">
    <title>登陆界面</title>

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
            <p class="p1">登录</p>
        </div>
        <!-- 输入框 -->
        <form method="post" action="/crawler/user/login">
            <div class="lgD">
                <img class="img1" src="/crawler/img/logName.png" /><input type="text" name="username" required="required"
                                                                 placeholder="输入用户名" />
            </div>
            <div class="lgD">
                <img class="img1" src="/crawler/img/logPwd.png" /><input type="password" name="password" required="required"
                                                                placeholder="输入用户密码" />
            </div>
            <div class="logC">
                <button type="submit" class="btn">登录</button>
            </div>
            <div class="logC">
                <a target="_top" href="/crawler/user/RegisterMain"><button type="button" class="btn" >注册</button>
                </a>
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