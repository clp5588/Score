<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/html/js/resource/resource.js"></script>
    <link rel="stylesheet" type="text/css" href="/html/css/resource/resource.css">
</head>
<body>
<article id="res_article" class="module width_full">
<header>
    <h3 class="tabs_involved">资源列表</h3>
    <a class="btn search" href="#" onclick="find(this);"><i class="icon-plus-sign"></i>添加</a>
    <a id="sub" class="btn search" href="#" onclick="sub();" style="display: none;"><i class="icon-ok"></i>提交</a>
    <div id="res_search" class="input-append" style="float: right; margin: 5px;">
        <input class="span2" id="inputIcon" type="text" placeholder="请输入资源名字">
        <span class="add-on"><i class="icon-search" style="cursor: pointer;"></i></span>
    </div>
</header>
<div>
<form style="display: none;" id="res_form" type="post" action="/sys/resource/add" class="module_content">
<fieldset class="fleft">
    <label>资源名称</label>
    <input id="resName" name="resName" type="text">
</fieldset>
<fieldset class="fright">
    <label>资源URL</label>
    <input id="resUrl" name="resUrl" type="text">
</fieldset>
<fieldset>
    <label>资源挂载点</label>
    <select id="resParentId" name="resParentId">
        <option value="">请选择</option>
        <option value="0|0">根节点</option>
        <c:forEach var="res" items="${list}">
            <c:if test="${res.is_module!=0}">
                <option value="${res.id}|${res.is_module}">${res.name}</option>
            </c:if>
        </c:forEach>
    </select>
</fieldset>
<fieldset class="fleft">
    <label>资源权限</label>
    <select id="resAuth" name="resAuth">
        <option value="">请选择</option>
        <c:forEach var="oper" items="${operations}">
            <option value="${oper.auth}">${oper.name}</option>
        </c:forEach>
    </select>
</fieldset>
<fieldset class="fright">
    <label>资源类型</label>
    <select id="resType" name="resType" disabled="disabled">
        <option value="">请选择</option>
        <option value="-1">顶级模块</option>
        <option value="1">上层模块</option>
        <option value="0">子模块</option>
    </select>
</fieldset>
<fieldset id="ff_icon" style="display: none;">
    <label>资源图片(点击选择)</label>
    <input id="resIcon" name="resIcon" type="text" value="" disabled="disabled"/>
    <ul class="the-icons" id="iconDiv"><li><i class="icon-edit"></i> icon-edit</li></ul>
</fieldset>
<fieldset id="fset_icons" style="display: none;">
    <section id="icons-web-app" class="row">
        <div class="span2.5">
            <ul class="the-icons">
                <li><i class="icon-adjust"></i> icon-adjust</li>
                <li><i class="icon-asterisk"></i> icon-asterisk</li>
                <li><i class="icon-ban-circle"></i> icon-ban-circle</li>
                <li><i class="icon-bar-chart"></i> icon-bar-chart</li>
                <li><i class="icon-barcode"></i> icon-barcode</li>
                <li><i class="icon-beaker"></i> icon-beaker</li>
                <li><i class="icon-beer"></i> icon-beer</li>
                <li><i class="icon-bell"></i> icon-bell</li>
                <li><i class="icon-bell-alt"></i> icon-bell-alt</li>
                <li><i class="icon-bolt"></i> icon-bolt</li>
                <li><i class="icon-book"></i> icon-book</li>
                <li><i class="icon-bookmark"></i> icon-bookmark</li>
                <li><i class="icon-bookmark-empty"></i> icon-bookmark-empty</li>
                <li><i class="icon-briefcase"></i> icon-briefcase</li>
                <li><i class="icon-bullhorn"></i> icon-bullhorn</li>
                <li><i class="icon-calendar"></i> icon-calendar</li>
                <li><i class="icon-camera"></i> icon-camera</li>
                <li><i class="icon-camera-retro"></i> icon-camera-retro</li>
                <li><i class="icon-certificate"></i> icon-certificate</li>
                <li><i class="icon-check"></i> icon-check</li>
                <li><i class="icon-check-empty"></i> icon-check-empty</li>
                <li><i class="icon-circle"></i> icon-circle</li>
                <li><i class="icon-circle-blank"></i> icon-circle-blank</li>
                <li><i class="icon-cloud"></i> icon-cloud</li>
                <li><i class="icon-cloud-download"></i> icon-cloud-download</li>
                <li><i class="icon-cloud-upload"></i> icon-cloud-upload</li>
                <li><i class="icon-coffee"></i> icon-coffee</li>
                <li><i class="icon-cog"></i> icon-cog</li>
                <li><i class="icon-cogs"></i> icon-cogs</li>
                <li><i class="icon-comment"></i> icon-comment</li>
                <li><i class="icon-comment-alt"></i> icon-comment-alt</li>
                <li><i class="icon-comments"></i> icon-comments</li>
                <li><i class="icon-comments-alt"></i> icon-comments-alt</li>
                <li><i class="icon-credit-card"></i> icon-credit-card</li>
                <li><i class="icon-dashboard"></i> icon-dashboard</li>
                <li><i class="icon-desktop"></i> icon-desktop</li>
                <li><i class="icon-download"></i> icon-download</li>
                <li><i class="icon-download-alt"></i> icon-download-alt</li>
            </ul>
        </div>
        <div class="span2.5">
            <ul class="the-icons">
                <li><i class="icon-edit"></i> icon-edit</li>
                <li><i class="icon-envelope"></i> icon-envelope</li>
                <li><i class="icon-envelope-alt"></i> icon-envelope-alt</li>
                <li><i class="icon-exchange"></i> icon-exchange</li>
                <li><i class="icon-exclamation-sign"></i> icon-exclamation-sign</li>
                <li><i class="icon-external-link"></i> icon-external-link</li>
                <li><i class="icon-eye-close"></i> icon-eye-close</li>
                <li><i class="icon-eye-open"></i> icon-eye-open</li>
                <li><i class="icon-facetime-video"></i> icon-facetime-video</li>
                <li><i class="icon-fighter-jet"></i> icon-fighter-jet</li>
                <li><i class="icon-film"></i> icon-film</li>
                <li><i class="icon-filter"></i> icon-filter</li>
                <li><i class="icon-fire"></i> icon-fire</li>
                <li><i class="icon-flag"></i> icon-flag</li>
                <li><i class="icon-folder-close"></i> icon-folder-close</li>
                <li><i class="icon-folder-open"></i> icon-folder-open</li>
                <li><i class="icon-folder-close-alt"></i> icon-folder-close-alt</li>
                <li><i class="icon-folder-open-alt"></i> icon-folder-open-alt</li>
                <li><i class="icon-food"></i> icon-food</li>
                <li><i class="icon-gift"></i> icon-gift</li>
                <li><i class="icon-glass"></i> icon-glass</li>
                <li><i class="icon-globe"></i> icon-globe</li>
                <li><i class="icon-group"></i> icon-group</li>
                <li><i class="icon-hdd"></i> icon-hdd</li>
                <li><i class="icon-headphones"></i> icon-headphones</li>
                <li><i class="icon-heart"></i> icon-heart</li>
                <li><i class="icon-heart-empty"></i> icon-heart-empty</li>
                <li><i class="icon-home"></i> icon-home</li>
                <li><i class="icon-inbox"></i> icon-inbox</li>
                <li><i class="icon-info-sign"></i> icon-info-sign</li>
                <li><i class="icon-key"></i> icon-key</li>
                <li><i class="icon-leaf"></i> icon-leaf</li>
                <li><i class="icon-laptop"></i> icon-laptop</li>
                <li><i class="icon-legal"></i> icon-legal</li>
                <li><i class="icon-lemon"></i> icon-lemon</li>
                <li><i class="icon-lightbulb"></i> icon-lightbulb</li>
                <li><i class="icon-lock"></i> icon-lock</li>
                <li><i class="icon-unlock"></i> icon-unlock</li>
            </ul>
        </div>
        <div class="span2.5">
            <ul class="the-icons">
                <li><i class="icon-magic"></i> icon-magic</li>
                <li><i class="icon-magnet"></i> icon-magnet</li>
                <li><i class="icon-map-marker"></i> icon-map-marker</li>
                <li><i class="icon-minus"></i> icon-minus</li>
                <li><i class="icon-minus-sign"></i> icon-minus-sign</li>
                <li><i class="icon-mobile-phone"></i> icon-mobile-phone</li>
                <li><i class="icon-money"></i> icon-money</li>
                <li><i class="icon-move"></i> icon-move</li>
                <li><i class="icon-music"></i> icon-music</li>
                <li><i class="icon-off"></i> icon-off</li>
                <li><i class="icon-ok"></i> icon-ok</li>
                <li><i class="icon-ok-circle"></i> icon-ok-circle</li>
                <li><i class="icon-ok-sign"></i> icon-ok-sign</li>
                <li><i class="icon-pencil"></i> icon-pencil</li>
                <li><i class="icon-picture"></i> icon-picture</li>
                <li><i class="icon-plane"></i> icon-plane</li>
                <li><i class="icon-plus"></i> icon-plus</li>
                <li><i class="icon-plus-sign"></i> icon-plus-sign</li>
                <li><i class="icon-print"></i> icon-print</li>
                <li><i class="icon-pushpin"></i> icon-pushpin</li>
                <li><i class="icon-qrcode"></i> icon-qrcode</li>
                <li><i class="icon-question-sign"></i> icon-question-sign</li>
                <li><i class="icon-quote-left"></i> icon-quote-left</li>
                <li><i class="icon-quote-right"></i> icon-quote-right</li>
                <li><i class="icon-random"></i> icon-random</li>
                <li><i class="icon-refresh"></i> icon-refresh</li>
                <li><i class="icon-remove"></i> icon-remove</li>
                <li><i class="icon-remove-circle"></i> icon-remove-circle</li>
                <li><i class="icon-remove-sign"></i> icon-remove-sign</li>
                <li><i class="icon-reorder"></i> icon-reorder</li>
                <li><i class="icon-reply"></i> icon-reply</li>
                <li><i class="icon-resize-horizontal"></i> icon-resize-horizontal</li>
                <li><i class="icon-resize-vertical"></i> icon-resize-vertical</li>
                <li><i class="icon-retweet"></i> icon-retweet</li>
                <li><i class="icon-road"></i> icon-road</li>
                <li><i class="icon-rss"></i> icon-rss</li>
                <li><i class="icon-screenshot"></i> icon-screenshot</li>
                <li><i class="icon-search"></i> icon-search</li>
            </ul>
        </div>
        <div class="span2.5">
            <ul class="the-icons">
                <li><i class="icon-share"></i> icon-share</li>
                <li><i class="icon-share-alt"></i> icon-share-alt</li>
                <li><i class="icon-shopping-cart"></i> icon-shopping-cart</li>
                <li><i class="icon-signal"></i> icon-signal</li>
                <li><i class="icon-signin"></i> icon-signin</li>
                <li><i class="icon-signout"></i> icon-signout</li>
                <li><i class="icon-sitemap"></i> icon-sitemap</li>
                <li><i class="icon-sort"></i> icon-sort</li>
                <li><i class="icon-sort-down"></i> icon-sort-down</li>
                <li><i class="icon-sort-up"></i> icon-sort-up</li>
                <li><i class="icon-spinner"></i> icon-spinner</li>
                <li><i class="icon-star"></i> icon-star</li>
                <li><i class="icon-star-empty"></i> icon-star-empty</li>
                <li><i class="icon-star-half"></i> icon-star-half</li>
                <li><i class="icon-tablet"></i> icon-tablet</li>
                <li><i class="icon-tag"></i> icon-tag</li>
                <li><i class="icon-tags"></i> icon-tags</li>
                <li><i class="icon-tasks"></i> icon-tasks</li>
                <li><i class="icon-thumbs-down"></i> icon-thumbs-down</li>
                <li><i class="icon-thumbs-up"></i> icon-thumbs-up</li>
                <li><i class="icon-time"></i> icon-time</li>
                <li><i class="icon-tint"></i> icon-tint</li>
                <li><i class="icon-trash"></i> icon-trash</li>
                <li><i class="icon-trophy"></i> icon-trophy</li>
                <li><i class="icon-truck"></i> icon-truck</li>
                <li><i class="icon-umbrella"></i> icon-umbrella</li>
                <li><i class="icon-upload"></i> icon-upload</li>
                <li><i class="icon-upload-alt"></i> icon-upload-alt</li>
                <li><i class="icon-user"></i> icon-user</li>
                <li><i class="icon-user-md"></i> icon-user-md</li>
                <li><i class="icon-volume-off"></i> icon-volume-off</li>
                <li><i class="icon-volume-down"></i> icon-volume-down</li>
                <li><i class="icon-volume-up"></i> icon-volume-up</li>
                <li><i class="icon-warning-sign"></i> icon-warning-sign</li>
                <li><i class="icon-wrench"></i> icon-wrench</li>
                <li><i class="icon-zoom-in"></i> icon-zoom-in</li>
                <li><i class="icon-zoom-out"></i> icon-zoom-out</li>
            </ul>
        </div>
    </section>
</fieldset>

</form>
</div>
<table id="res_table" class="tablesorter" cellspacing="0">
    <thead>
    <tr>
        <th>资源名称</th>
        <th>资源URL</th>
        <th>资源图片</th>
        <th>资源类型</th>
        <th>资源权限</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="res">
        <tr>
            <td>${res.name}</td>
            <td>${res.url}</td>
            <td><i class="${res.icon} icon-large"></i></td>
            <td><c:if test="${res.is_module==0}">子模块</c:if>
                <c:if test="${res.is_module==-1}">顶级模块</c:if>
                <c:if test="${res.is_module==1}">上层模块</c:if></td>
            <td><c:if test="${res.auth==0}">无权限</c:if>
                <c:if test="${res.auth==1}">读权限</c:if>
                <c:if test="${res.auth==2}">写权限</c:if></td>
            <td><input type="image" src="/html/images/icn_edit.png" title="Edit">
                <input type="image" src="/html/images/icn_trash.png" title="Trash"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<footer>
</footer>
</article>
</body>
</html>