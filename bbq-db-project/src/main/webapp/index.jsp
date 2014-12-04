<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Lego</title>
<meta charset="utf-8">
<meta content="" name="keywords">
<meta content="" name="description">
<link media="all" type="text/css" href="http://static.lego.iqiyi.com/css/ui.css?v=2013061323025" rel="stylesheet">
<link media="all" type="text/css" href="http://static.lego.iqiyi.com/css/lego.css?v=2013061323025" rel="stylesheet">
<script charset="utf-8" type="text/javascript" src="http://static.lego.iqiyi.com/js/core.js?v=2013061323025"></script>
<script charset="utf-8" type="text/javascript" src="http://static.lego.iqiyi.com/js/ui.js?v=2013061323025"></script>
<script charset="utf-8" type="text/javascript" src="http://static.lego.iqiyi.com/js/lego.js?v=2013061323025"></script>
<script type="text/javascript" charset="utf-8">
var GLOBAL = GLOBAL || {};
GLOBAL['user'] = {"createTime":1367132120000,"id":9,"status":1,"updateTime":1367132120000,"userId":"1001528531","version":0};
GLOBAL['currentDate'] = null;
GLOBAL['host'] = null;
GLOBAL['accessDomain'] = null;
GLOBAL['role'] = null;
</script>
</head>
<body>
<header>
 <div class="navbar">
  <div class="navbar-inner">
    <ul class="nav">
        <li class="index"><a href="#">首页</a></li>
        <li class="collect">
            <a href="#" class="title">视频采编</a>
            <ul class="dropdown-menu title" role="menu" aria-labelledby="dLabel">
                <li><a href="/upload/home">手动上传</a></li>
                <li><a href="/ftp/collect/home">FTP扫描</a></li>
                <li><a href="/ftp/server/home">FTP管理</a>
                <li><a href="/material/home">素材库</a></li>
            </ul>
        </li>
        <li class="produce"><a href="#">内容编目</a>
            <ul class="dropdown-menu title" role="menu" aria-labelledby="dLabel">
                <li><a href="/video/home">节目列表</a>
                <li class="dropdown-submenu">
                    <a href="">节目编目</a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="/video/edit/dsj">电视剧</a></li>
                        <li><a tabindex="-1" href="/video/edit/dy">电影</a></li>
                        <li><a tabindex="-1" href="/video/edit/zy">综艺</a></li>
                        <li><a tabindex="-1" href="/video/edit/ly">旅游</a></li>
                        <li><a tabindex="-1" href="/video/edit/dm">动漫</a></li>
                        <li><a tabindex="-1" href="/video/edit/ss">时尚</a></li>
                        <li><a tabindex="-1" href="/video/edit/jlp">纪录片</a></li>
                        <li><a tabindex="-1" href="/video/edit/yl">娱乐</a></li>
                        <li><a tabindex="-1" href="/video/edit/ty">体育</a></li>
                        <li><a tabindex="-1" href="/video/edit/jy">教育</a></li>
                        <li><a tabindex="-1" href="/video/edit/cj">财经</a></li>
                        <li><a tabindex="-1" href="/video/edit/sh">生活</a></li>
                        <li><a tabindex="-1" href="/video/edit/wdy">微电影</a></li>
                        <li><a tabindex="-1" href="/video/edit/gx">搞笑</a></li>
                        <li><a tabindex="-1" href="/video/edit/gkk">公开课</a></li>
                        <li><a tabindex="-1" href="/video/edit/gg">广告</a></li>
                        <li><a tabindex="-1" href="/video/edit/ph">片花</a></li>
                        <li><a tabindex="-1" href="/video/edit/yy">音乐</a></li>
                    </ul>
                </li>
                <li><a href="/album/home">专辑列表</a>
                <li class="dropdown-submenu">
                    <a href="">专辑编目</a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="/album/edit/dsj">电视剧</a></li>
                        <li><a tabindex="-1" href="/album/edit/zy">综艺</a></li>
                        <li><a tabindex="-1" href="/album/edit/ly">旅游</a></li>
                        <li><a tabindex="-1" href="/album/edit/dm">动漫</a></li>
                        <li><a tabindex="-1" href="/album/edit/ss">时尚</a></li>
                        <li><a tabindex="-1" href="/album/edit/jlp">纪录片</a></li>
                        <li><a tabindex="-1" href="/album/edit/yl">娱乐</a></li>
                        <li><a tabindex="-1" href="/album/edit/ty">体育</a></li>
                        <li><a tabindex="-1" href="/album/edit/jy">教育</a></li>
                        <li><a tabindex="-1" href="/album/edit/cj">财经</a></li>
                        <li><a tabindex="-1" href="/album/edit/sh">生活</a></li>
                        <li><a tabindex="-1" href="/album/edit/wdy">微电影</a></li>
                        <li><a tabindex="-1" href="/album/edit/gx">搞笑</a></li>
                        <li><a tabindex="-1" href="/album/edit/gkk">公开课</a></li>
                        <li><a tabindex="-1" href="/album/edit/gg">广告</a></li>
                    </ul>
                </li>
                <li class="dropdown-submenu">
                    <a href="">Collection</a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="/collection/subalbum/add">新建子专辑</a></li>
                        <li><a tabindex="-1" href="/collection/entity/home-search">添加节目专辑</a></li>
                        <li><a tabindex="-1" href="/collection/subalbum/home-video?id=510">子专辑下节目列表</a></li>
                        <li><a tabindex="-1" href="/collection/entity/home?id=510">关联内容列表</a></li>
                        <li><a tabindex="-1" href="/collection/musicalbum/add">新建音乐专辑</a></li>
                        <li><a tabindex="-1" href="/collection/superalbum/add">新建关联关系</a></li>
                    </ul>
                </li>
                <li><a href="/brand/home">品牌库</a>
                <li><a href="/people/home">人物库</a>
                <li><a href="/sport/schedule/home">赛程管理</a>
                <li><a href="/sport/event/home">赛事管理</a>
                <li><a href="/sport/live/home">直播管理</a>
                <li><a href="/video/point?videoId=5&pointType=kd">看点(视频打点)</a></li>
                <li><a href="/video/status">视频播放控制</a></li>
            </ul>
        </li>
        <li class="audit"><a href="#">审核质检</a>
            <ul class="dropdown-menu title" role="menu" aria-labelledby="dLabel">
                <li><a href="/audit/abnormal/home">不良类型</a>
                <li><a href="/audit/abnormal/edit">不良类型编辑</a>
                <li><a href="/audit/abnormal/common-edit">常用不良排序</a>
                <li><a href="/audit/timeset/qc">审核时间设置</a>
                <li><a href="/audit/qc/add?videoId=344">视频审核</a>
                <li><a href="/audit/qa/home-wait">待质检列表</a>
                <li><a href="/audit/qa/home-one">一检明细</a>
                <li><a href="/audit/qa/home-two">二检明细</a>
                <li><a href="/audit/stat/home-base">质检统计</a>
                <li><a href="/audit/stat/home-abnormal">不良明细统计</a>
                <li><a href="/audit/timeset/qa">质检时间设置</a>
            </ul>
        </li>
        <li class="data"><a href="#">任务消息</a>
            <ul class="dropdown-menu title" role="menu" aria-labelledby="dLabel">
                <li><a href="/task/my">个人任务</a>
                <li><a href="/task/group">群组任务</a>
                <li><a href="/message-box/home">消息通知</a>
            </ul>
        </li>
        <li class="more"><a href="#">其他</a>
            <ul class="dropdown-menu title" role="menu" aria-labelledby="dLabel">
                <li><a href="/attention/home">我的关注</a>
                <li><a href="/watermark/home">水印列表</a>
                <li><a href="/watermark/addPre">新增水印</a>
            </ul>

        </li>
    </ul>
  </div>
</div>
<style>
header .nav li:hover ul.title {
    display: block;
}
header .nav li .dropdown-menu.title {
    left:auto;
    margin-top: -2px;
}
</style>
</header>
<h1>
欢迎您测试Lego系统
</h1>

</body>
</html>

