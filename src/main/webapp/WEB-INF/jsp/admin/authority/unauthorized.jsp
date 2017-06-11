<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<c:set var="CTP_ADMIN" value="${pageContext.request.contextPath}/resources/admin"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>权限不足</title>
<style type="text/css">

.no-auth-div{

width:800px;
margin:0 auto;
margin-top:40px;
}
.no-auth-div span{

 font-size:25px;
 font-weight: bold;
 margin-left:200px;
}
.bb{

  width:200px;
  margin:0 auto;
  
  margin-top:20px;
}
button {
    min-width: 80px;
    background-color: #009a61;
    text-align: center;
    color: #fff;
    cursor: pointer;
    height:35px;
    border:1px solid;
    border-radius:3px;
}
button:hover,
button:active {
    background-color: #008151;
}

.footer {
            margin: 40px auto;
            
            width:200px;
            color: #999;
            text-transform: uppercase;
            font-size: 13px;
}
.footer a {
    color: #999;
    text-decoration: none;
}
.footer a:hover{
    text-decoration: underline;
}
        
</style>
</head>
<body>


<div class="no-auth-div"><img src="${CTP_ADMIN}/image/no-auth.png"/><span>抱歉，认证未通过，或者权限不足!</span>
<div class="bb"><button type="button"      onclick="window.location.href='${pageContext.request.contextPath}/admin/index'">返回主页</button>  <button type="button"      onclick="self.location=document.referrer;">返回上一页</button></div>

</div>

 <div class="footer text-center">
         &copy; <a href="${pageContext.request.contextPath}/admin/index">Mingshan</a>
</div>
</body>
</html>