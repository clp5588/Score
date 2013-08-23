<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/include.jsp"%>
<%@ include file="../include/main.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/html/css/index-desktop.css">
<script type="text/javascript" src="/html/js/index/index-desktop.js"></script>
</head>
<body>
	<div id="desktop" oncontextmenu="event.returnValue=false" onselectstart="event.returnValue=false" ondragstart="window.event.returnValue=false" onsource="event.returnValue=false" style="margin-top:30px;">
		<ul id="dockul"></ul>
	</div>
	
	<div id="extra_top_menu" style="position: fixed; z-index: 990; left: 0px; top: -2px;display: block; width:100%;"
	oncontextmenu="event.returnValue=false" onselectstart="event.returnValue=false" ondragstart="window.event.returnValue=false" onsource="event.returnValue=false" topmargin="0">
	    <table class="fixmenu">
	        <tr>
	          <td width="194">
	            <div class="left">
	            <ul>
	              <li><div class="inner"><i class="icon-th-large"></i>&nbsp;admin</div><div class="devider"></div></li>
	            </ul>
	          </div></td>
	          <td ></td>
	          <td width="194">
	            <td width="194">
	            <div class="right">
	            <ul>
	              <li><div class="inner"><i class="icon-cog"></i></div><div class="devider"></div></li>
	              <li><div class="inner"><i class="icon-comment"></i>&nbsp;admin</div><div class="devider"></div></li>
	            </ul>
	          </div>
	          </td>
	        </tr>
	    </table>
	</div>
</body>
</html>