<html>
<head>
    <title>详情界面</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/crawler/css/ul.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/bootstrap.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav  id="n" class="navbar navbar-default navbar-static-top" role="navigation" >
                <div class="navbar-header" >
                    <button  type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="/crawler/Main/list">搜狗词库主页</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li >
                            <a href="#" style="display:none">关键词一</a>
                        </li>
                        <li>
                            <a href="#" style="display:none">关键词二</a>
                        </li>
                        <li>
                            <a href="#" style="display:none">关键词三</a>
                        </li>

                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/crawler/Main/detail">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="输入关键词" name="keyword"/>
                        </div> <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <#if user.username == " ">
                            <li>
                                <a href="/crawler/user/main" target="_blank">登录</a>
                            </li>
                            <li>
                                <p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;">|</p>
                            </li>
                            <li>
                                <a href="/crawler/user/RegisterMain" target="_blank">注册</a>
                            </li>
                        <#else>
                            <li>
                                <p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;">欢迎用户：</p>
                            </li>
                            <li>
                                <a href="/crawler/user/UpdateMain" >${user.username}</a>
                            </li>
                            <li>
                                <a href="/crawler/user/logout" >登出</a>
                            </li>
                        </#if>
                    </ul>
                </div>

            </nav>
            <!-- dict_nav_list start -->
            <div id="dict_nav_list">
                <ul class="ul1">
                    <li id="nav_1" class="nav_list"   >
                        <a href="/crawler/Main/detail?keyword=城市信息大全" class="a1">城市信息大全</a>
                    </li>
                    <li id="nav_2" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=游戏" class="a1">游戏</a>
                    </li>
                    <li id="nav_3" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=自然" class="a1">自然</a>
                    </li>
                    <li id="nav_4" class="nav_list">
                        <a href="/crawler/Main/detail?keyword=人文" class="a1" >人文</a>
                    </li>
                    <li id="nav_5" class="nav_list">
                        <a href="/crawler/Main/detail?keyword=科学" class="a1">科学</a>
                    </li>
                    <li id="nav_6" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=医学"class="a1" >医学</a>
                    </li>
                    <li id="nav_7" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=运动" class="a1">运动</a>
                    </li>
                    <li id="nav_8" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=生活" class="a1">生活</a>
                    </li>
                    <li id="nav_9" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=娱乐" class="a1">娱乐</a>
                    </li>
                    <li id="nav_10" class="nav_list" >
                        <a href="/crawler/Main/detail?keyword=艺术" class="a1">艺术</a>
                    </li>

                </ul>
            </div>    <!-- dict_detail_list start -->
            <br><br>

            <div class="page-header">
                <h1>
                    搜索详情 <small></small>
                </h1>
            </div>
            <ul>

                    <li>
                        <a href="/crawler/Main/data?categoryId=${lexiconDTO.id}" style="width: 100%;height: 45px;display: block;line-height: 45px;font-size:20px;">${lexiconDTO.categoryName}</a>
                    <#list lexiconDTO.lexiconInfoList as lexiconInfo>
                        ${lexiconInfo.keyword}&nbsp;&nbsp;&nbsp;
                    </#list>
                    </li>

            </ul>

        </div>
    </div>
</div>
<div class="address" style="text-align:center;
	padding:1% 0;
	margin:0 auto;
	line-height:2em;
	font-size: 14px;
	width:90%;
    ">
    版权所有：XXX公司
    <br>
    电话号码：123456789
</div>
<div class="clear"></div>
</body>
</html>