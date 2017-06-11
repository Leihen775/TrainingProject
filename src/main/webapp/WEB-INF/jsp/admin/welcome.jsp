<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎界面</title>
<c:set var="CTP" value="${pageContext.request.contextPath}"></c:set>
<c:set var="CTP_ADMIN" value="${pageContext.request.contextPath}/resources/admin"></c:set>
    
<link rel="stylesheet" href="${CTP}/resources/common/js/extends/layui/css/layui.css">
</head>
<body>
 <blockquote class="layui-elem-quote">
        下载： <a href="https://github.com/mstao/TrainingProject/archive/master.zip" target="_blank">点击下载</a>
        <hr>
        说明：
        <br>
        这是一个简单的houtai 
       
    </blockquote>
    <script type="text/javascript" src="${CTP}/resources/common/js/extends/layui/layui.js"></script>
    <script type="text/javascript">
        /**
         * 对layui进行全局配置
         */
         layui.use(['layer','element'], function() {
        	 var element    = layui.element();
             var layer      = layui.layer;
             var $          = layui.jquery;  
         });
    </script>
</body>
</html>