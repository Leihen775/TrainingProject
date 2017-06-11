<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>后台管理</title>
    <c:set var="CTP" value="${pageContext.request.contextPath}"></c:set>
	<c:set var="CTP_ADMIN" value="${pageContext.request.contextPath}/resources/admin"></c:set>
    
    <link rel="stylesheet" href="${CTP}/resources/common/js/extends/layui/css/layui.css">
    <link rel="stylesheet" href="${CTP}/resources/common/js/extends/font-awesome/css/font-awesome.min.css">
    <style type="text/css">
    	
    	
.admin-side-toggle,.admin-side-full{
	position: absolute; 
	cursor: pointer;
    z-index: 19940201;
    left: 200px;
    color: white;
    text-align: center;
    width: 30px;
    height: 30px;
    background-color: #1AA094;
    line-height: 30px;
    top: 25%;
}
.admin-side-toggle:hover{
	background-color: #5FB878;
}
.admin-side-full{
	left: 240px;
}
    	
    </style>
</head>
<style>

</style>
<body>
    <!-- 布局容器 -->
    <div class="layui-layout layui-layout-admin">
        <!-- 头部 -->
        <div class="layui-header">
            <div class="layui-main">
                <!-- logo -->
              
              <div class="admin-login-box">
						 <a href="/" style="color: #c2c2c2; font-size: 18px; line-height: 60px;">后台管理系统</a>
						<div class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
						<div class="admin-side-full">
							<i class="fa fa-life-bouy" aria-hidden="true"></i>
						</div>
					</div>
           
                
       
                	
                <!-- 水平导航 -->
                <ul class="layui-nav" style="position: absolute; top: 0; right: 0; background: none;">
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                         	   进入前台
                        </a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                     	  <shiro:principal/>
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;">
                                                                                                      个人信息
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;">
                                                                                                      修改密码
                                </a>
                            </dd>
                            <dd>
                                <a href="${CTP}/admin/user/logout">
                                                                                                     退出登录
                                </a>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 侧边栏 -->
        <div class="layui-side layui-bg-black" id="admin-side">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree" lay-filter="left-nav" style="border-radius: 0;">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;" data-url="${CTP}/admin/welcome" data-id="1">
                                                                            后台管理
                        </a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                                                                                     权限管理
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-url="https://www.baidu.com/" data-id="2">
                               	   角色列表
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="http://cn.bing.com/" data-id="3">
                           	             角色分配
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="https://www.so.com/" data-id="4">
                               	  权限列表
                                </a>
                            </dd>
  
                        </dl>
                    </li>
                    
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                                                                                 内容管理
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-url="https://www.baidu.com/" data-id="5">
                                                                                                       内容列表
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="http://cn.bing.com/" data-id="6">
                             	       发布内容
                                </a>
                            </dd>
                            
                        </dl>
                    </li>
                    
                     <li class="layui-nav-item">
                        <a href="javascript:;">
      				             系统管理
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-url="https://www.baidu.com/" data-id="7">
                             	      友链管理
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="http://cn.bing.com/" data-id="8">
                           		         清除缓存
                                </a>
                            </dd>
                            
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 主体 -->
        <div class="layui-body" id="admin-body">
            <!-- 顶部切换卡 -->
            <div class="layui-tab layui-tab-brief" lay-filter="top-tab" lay-allowClose="true" style="margin: 0;">
                <ul class="layui-tab-title"></ul>
                <div class="layui-tab-content" style="padding: 0;"></div>
            </div>
        </div>

        <!-- 底部 -->
        <div class="layui-footer" id="admin-footer" style="text-align: center; line-height: 44px;">
            Copyright © 2017 <a href="#" target="_blank">Mingshan</a> 
        </div>
    </div>

    <script type="text/javascript" src="${CTP}/resources/common/js/extends/layui/layui.js"></script>
    <script type="text/javascript">
        layui.use(['layer','element'], function() {
            var element    = layui.element();
            var layer      = layui.layer;
            var $          = layui.jquery;

            var navfilter  = 'left-nav';
            var nav        = $('.layui-nav[lay-filter='+navfilter+']').eq(0);

            var tabfilter  = 'top-tab';
            var tab        = $('.layui-tab[lay-filter='+tabfilter+']').eq(0);
            var tabcontent = tab.children('.layui-tab-content').eq(0);
            var tabtitle   = tab.children('.layui-tab-title').eq(0);

            /**
             * iframe自适应
             */
            $(window).resize(function() {
                //设置顶部切换卡容器度
                tabcontent.height($(this).height() - 60 - 41 - 44); //头部高度 顶部切换卡高度 底部高度
                //设置顶部切换卡容器内每个iframe高度
                tabcontent.find('iframe').each(function () {
                    $(this).height(tabcontent.height());
                });
            }).resize();

            /**
             * 监听侧边栏导航点击事件
             */
            element.on('nav('+navfilter+')', function(elem) {
                var a         = elem.children('a');
                var title     = a.text();
                var src       = elem.children('a').attr('data-url');
                var id        = elem.children('a').attr('data-id');
                var iframe    = tabcontent.find('iframe[data-id='+id+']').eq(0);
                var tabindex  = tabtitle.children('li').length;

                if(src != undefined && src != null && id != undefined && id != null) {
                    if(iframe.length) { //存在 iframe
                        //获取iframe身上的tab index
                        tabindex = iframe.attr('data-tabindex');
                    }else{ //不存在 iframe
                        //显示加载层
                        layer.load(2);
                        setTimeout(function() {
                            layer.closeAll('loading');
                        }, 300);
                        //拼接iframe
                        var iframe = '<iframe';
                        iframe += ' src="'+src+'" data-id="'+id+'" data-tabindex="'+tabindex+'"';
                        iframe += ' style="width: 100%; height: '+tabcontent.height()+'px; border: 0px;"';
                        iframe += '></iframe>';
                        //顶部切换卡新增一个卡片
                        element.tabAdd(tabfilter, {title: title,content: iframe});
                    }

                    //切换到指定索引的卡片
                    element.tabChange(tabfilter, tabindex);
                }
            });

            /**
             * 初始化点击侧边栏第一个导航
             */
            nav.find('li').eq(0).click();
            
            
	         // 关闭/显示右侧导航  
		     $('.admin-side-toggle').on('click', function () {
		        var sideWidth = $('#admin-side').width();
		        if (sideWidth === 200) {
		            $('#admin-body').animate({
		                left: '0'
		            }); //admin-footer
		            $('#admin-footer').animate({
		                left: '0'
		            });
		            $('#admin-side').animate({
		                width: '0'
		            });
		        } else {
		            $('#admin-body').animate({
		                left: '200px'
		            });
		            $('#admin-footer').animate({
		                left: '200px'
		            });
		            $('#admin-side').animate({
		                width: '200px'
		            });
		        }
		    });
	        //全屏
		    $('.admin-side-full').on('click', function () {
		        var docElm = document.documentElement;
		        //W3C  
		        if (docElm.requestFullscreen) {
		            docElm.requestFullscreen();
		        }
		        //FireFox  
		        else if (docElm.mozRequestFullScreen) {
		            docElm.mozRequestFullScreen();
		        }
		        //Chrome等  
		        else if (docElm.webkitRequestFullScreen) {
		            docElm.webkitRequestFullScreen();
		        }
		        //IE11
		        else if (elem.msRequestFullscreen) {
		            elem.msRequestFullscreen();
		        }
		        layer.msg('按Esc即可退出全屏');
		    });
       });
    </script>
</body>
</html>