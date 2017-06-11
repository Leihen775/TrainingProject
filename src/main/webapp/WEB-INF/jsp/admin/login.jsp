<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<c:set var="CTP" value="${pageContext.request.contextPath}"></c:set>
<c:set var="CTP_ADMIN" value="${pageContext.request.contextPath}/resources/admin"></c:set>

<link rel="stylesheet" href="${CTP_ADMIN}/css/login.css" />
<script>
var CTPPATH="${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="menu-loginreg">
						
    <button class="login-btn">登录</button>
    <button class="reg-btn">注册</button>

</div>

		 <!--登录-->
<div class="modalDialog login-modalDialog" style="display: block;">
	<div>
		<a href="javascript:void(0);" title="关闭" class="close login-close" >X</a>
		<span class="openModal-title">登录界面</span>
        <div class="openModal-login">
            <form>
            <div class="login-content login-form">
            
                 <label>
				<input type="text" name="username" class="l_username" required placeholder="username" autocomplete="off">
				</label>
				<label>
					<input type="password" name="password" class="l_password" required placeholder="Password" autocomplete="off">
				</label>
					<input type="submit" value="Login" >
			  <span> <a href="">忘记密码？</a>  <a href="javascript:void(0);" class="link-reg">点击注册</a></span>
            </div> 
            
           </form>
        </div>       
		
	</div>
</div>

<!--注册-->

<div class="modalDialog reg-modalDialog" >
	
	<div>
		<a href="javascript:void(0);" title="关闭" class="close reg-close" >X</a>
		<span class="openModal-title">注册界面</span>
        <div class="openModal-login">
         <form>
            <div class="login-content login-form">
                <label>
				     <input type="text" name="username" class="g_username" autocomplete="off" required placeholder="username">
				</label>
                <label>
				     <input type="text" name="email" class="g_email" autocomplete="off" required placeholder="Email">
				</label>
				<label>
					<input type="password" name="password" class="g_password"  autocomplete="off" required placeholder="Password">
				</label>
				<label>
					<input type="password" name="re-password" class="g_re_password" autocomplete="off" required placeholder="re-password">
				</label>
					<input type="submit" value="register" >
			  <span>已有账号？ &nbsp;  <a href="javascript:void(0);" class="link-login">登录</a></span>
            </div> 
       </form>       
           
        </div>       
		
	</div>
	
	
</div>
</body>
</html>
<script type="text/javascript" src="${CTP}/resources/common/js/lib/jquery-1.10.2.min.js" ></script>
<script type="text/javascript" src="${CTP}/resources/common/js/extends/layer-2.4/layer.js"></script>
<script type="text/javascript" src="${CTP_ADMIN }/js/module/common.js"></script>

<script type="text/javascript">
	
	$(function(){
		var ok_username=false;
		var ok_email=false;
		var ok_password=false;
	    var ok_re_password=false;
		
		$(".login-btn").bind("click",function(){
			
			$('.login-modalDialog').slideDown();
		    $('html').addClass('overHiden');
		    $(".menu-loginreg").fadeOut();
		});
		
		$('.login-close').bind('click',function(){
			$('.login-modalDialog').slideUp();
			$('html').removeClass('overHiden');
			$(".menu-loginreg").fadeIn();
	    });
	    
	    
	    $(".reg-btn").bind("click",function(){
	    	
				    	
	    	$('.reg-modalDialog').slideDown();
		    $('html').addClass('overHiden');
		    $(".menu-loginreg").fadeOut();
	    });
	    
	    $('.reg-close').bind('click',function(){
			$('.reg-modalDialog').slideUp();
			$('html').removeClass('overHiden');
			$(".menu-loginreg").fadeIn();
	    });
	    
	    $(".link-login").bind("click",function(){
	    	$('.reg-modalDialog').slideUp();
	    	$('.login-modalDialog').slideDown();
		  
	    });
	    $(".link-reg").bind("click",function(){
	    	$('.reg-modalDialog').slideDown();
	    	$('.login-modalDialog').slideUp();
	    });
	    
	    function tog(v){return v?'addClass':'removeClass';}   
		  
		$(document).on('input', '.searchinput', function() {  
		    $(this)[tog(this.value)]('x');  
		}).on('mousemove', '.x', function(e) {  
		    $(this)[tog(this.offsetWidth-24 < e.clientX-this.getBoundingClientRect().left)]('onX');     
		}).on('click', '.onX', function(){  
		   // $(this).removeClass('x onX').val('').change(); 
		   
		}); 	  
		
		
		/**
		 * 登录校验
		 */
		
		//校检用户名
		//检验不能为空和数字
	 function checkUserName(className,tips){
	 	    var filter=/^[a-zA-Z\u4e00-\u9fa5]{1}[a-zA-Z0-9_\u4e00-\u9fa5]{1,8}$/;
		    var str = $(className).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
	     	if( str ==""|| str==null ){
	   		    layer.tips("*"+tips+"不能为空", $(className));
			    $(className).focus();
				return false;
			}else{
				if(filter.test(str) && str.length <9){
					return true;
				}else {
					layer.tips("*"+tips+"不能为数字",$(className));
					return false;
					
				}
			}
	   }
	 
	 //检查邮箱
	 	
	function checkEmail(className){
			var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    	var str = $(className).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( $(className).val() == "" || $(className).val==null ){
				layer.tips('邮箱不能为空', $(className));
	    		$(this).focus();
	    		ok_email=false;
			}else{
				
				if(filter.test( str )){
					ok_email=true;
				}else{
					layer.tips('邮箱格式不正确！', $(className));
					ok_email=false;
					$(className).focus();
				}
				
			}
	 }
		
	 //检查密码
	 function checkPassword(className){
		 var filter=/^[a-zA-Z0-9_]{5,18}$/;
	    	var str = $(className).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( $(className).val() == "" || $(className).val==null ){
				layer.tips('*密码不能为空', $(className));
	    		$(className).focus();
	    		ok_password=false;
			}else{
				if(filter.test( str )){
					ok_password=true;
				}else{
					layer.tips('密码5-18位', $(className));
					$(className).focus();
					ok_password=false;
					
				}
			}
	 }
	 
	 
	 
	 $(".l_username").bind("blur",function(){
		var flag= checkUserName($(this),"用户名");
		
		if(flag==true){
			ok_username=true;
		}else{
			ok_username=false;
		}
		
	 });
	 $(".l_password").bind("blur",function(){
		 checkPassword($(this));
	 });
	 
	 
	 $('form').eq(0).bind('submit',function(e){
	    	
	    	//阻止自动提交
	    	e.preventDefault();
	    	var username=$(".l_username").val();
	    	var password=$(".l_password").val();
	    
	    	if(ok_username == true && ok_password == true){
	    		
	    		//注册
	    		$.ajax({
					type:'post',
					dataType:'text',
					url:CTPPATH+"/admin/user/dealLogin",
					data:{"userName":username,"password":password},
				
					beforeSend:function(){
						//显示正在加载
						layer.msg('正在登录',{offset: 0,time:2000});
					},
					success:function(data){

						//关闭正在加载
						setTimeout(function(){
							  layer.closeAll('loading');
						}, 1000);
						
						//代表用户名不可用
						 if(data==0){
							
							layer.msg('*抱歉，登录失败，用户名或者密码错误！',{time:4000});
							
						}else{
							//代表用户名可用
							layer.msg('恭喜！登录成功',{time:4000});
							window.location.href=CTPPATH+"/admin/index";
						}
					},
					error:function(){

						//关闭正在加载
						setTimeout(function(){
							  layer.closeAll('loading');
						}, 1000);
						layer.msg("出错了", {icon: 2,time:2000});
					}
				});
	    		
	    	}else{
	    		layer.msg("信息填写不正确！");
	    	}
	    	
	 });
	 
	 
	 
	 /**
	  * 注册用户校验
	  */
	 
	 //检测用户名
	 
	    /**
	     * 对用户名校验
	     */
	    $(".g_username").bind("blur",function(){
	    	
	    	var mythis=$(this);
	    	var filter=/^[a-zA-Z\u4e00-\u9fa5]{1}[a-zA-Z0-9_\u4e00-\u9fa5]{1,8}$/;
	    	var str = mythis.val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			
	    	if( str ==""|| str==null ){
	    		layer.tips('*用户名不能为空', mythis);
	    		mythis.focus();
	    		ok_username=false;
			}else{
				
				if(filter.test( mythis.val() )){
					//调用ajax验证用户是否已被注册
					
					//start
					$.ajax({
						type:'post',
						dataType:'json',
						url:CTPPATH+"/admin/user/checkUser",
						data:{"userName":str},
					
						beforeSend:function(){
							//显示正在加载
							layer.load(2,{offset: 10,time:2000});
						},
						success:function(data){
		
							//关闭正在加载
							setTimeout(function(){
								  layer.closeAll('loading');
							}, 1000);
							
							//代表用户名可用
							if(data==1){
								layer.tips('恭喜！用户名可用', mythis);
								ok_username=true;
							}else if(data==0){
								//代表用户名不可用
								layer.tips('*抱歉，用户名已被使用', mythis);
								
								ok_username=false;
							}
						},
						error:function(){
		
							//关闭正在加载
							setTimeout(function(){
								  layer.closeAll('loading');
							}, 1000);
							layer.msg("出错了", {icon: 2,time:2000});
						}
					});
					
					//end
				}else{
					layer.tips('*用户名为2-9字符,首字不为数字,无特殊字符 !', mythis);
					mythis.focus();
					ok_username=false;
				}
			}
	    });
	    
	    /**
	     * 对邮箱校验
	     */
	    $(".g_email").bind("blur",function(){
	    	var mythis=$(this);
	    	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    	var str = mythis.val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( mythis.val() == "" || mythis.val==null ){
				layer.tips('邮箱不能为空', $(this));
				mythis.focus();
	    		ok_email=false;
			}else{
				if(filter.test( mythis.val() )){
					//ajax验证邮箱是否被使用
					//start
					$.ajax({
						type:'post',
						dataType:'json',
						url:CTPPATH+"/admin/user/checkEmail",
						data:{"email":str},
					
						beforeSend:function(){
							//显示正在加载
							layer.load(2,{offset: 10,time:2000});
						},
						success:function(data){
		
							//关闭正在加载
							setTimeout(function(){
								  layer.closeAll('loading');
							}, 1000);
							
							//代表用户名可用
							if(data==1){
								layer.tips('恭喜！邮箱可用', mythis);
								ok_email=true;
							}else if(data==0){
								//代表用户名不可用
								layer.tips('*抱歉，邮箱已被使用', mythis);
							
								ok_email=false;
							}
						},
						error:function(){
		
							//关闭正在加载
							setTimeout(function(){
								  layer.closeAll('loading');
							}, 1000);
							layer.msg("出错了", {icon: 2,time:2000});
						}
					});
					
					//end
				}else{
					layer.tips('*邮箱格式不正确', mythis);
					mythis.focus();
					ok_email=false;
				}
			}
	    });
	    
	    /**
	     * 对密码进行校验
	     */
	    
	    $(".g_password").bind("blur",function(){
	    	var filter=/^[a-zA-Z0-9_]{5,18}$/;
	    	var str = $(this).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( $(this).val() == "" || $(this).val==null ){
				layer.tips('*密码不能为空', $(this));
	    		$(this).focus();
	    		ok_password=false;
			}else{
				if(filter.test( $(this).val() )){
					layer.tips('密码格式正确', $(this));
					ok_password=true;
				}else{
					layer.tips('*密码5-18字符 ,不能有特殊字符!', $(this));
					$(this).focus();
					ok_password=false;
				}
			}
	    });
	    
	    /**
	     * 对重复密码校验
	     */
	    $(".g_re_password").bind("blur",function(){
	    	var filter=/^[a-zA-Z0-9_]{5,18}$/;
	    	var str = $(this).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( $(this).val() == "" || $(this).val==null ){
				layer.tips('*重复密码不能为空', $(this));
	    		$(this).focus();
	    		ok_re_password=false;
			}else{
				if(filter.test( $(this).val() )){
					 
					if($(this).val()!=$(".g_password").val()){
						layer.tips('*两次填写的密码不一致!', $(this));
						$(this).focus();
					}else{
						layer.tips('重复密码正确!', $(this));
						ok_re_password=true;
					}
				 
				}else{
					layer.tips('*重复密码5-18字符 ,不能有特殊字符!', $(this));
					$(this).focus();
					ok_re_password=false;
				}
			}
	    	
	    });
	 

	    //执行注册
	    
	    $("form").eq(1).bind("submit",function(e){
	    	
	    	//阻止自动提交
	    	e.preventDefault();
	    	
	    	//xalert(ok_username +" - "+ok_email+" - "+ok_password+" - "+ok_re_password);
	    	var userName=$(".g_username").val();
	    	var email=$(".g_email").val();
	    	var password=$(".g_password").val();
	    	var date=showLocale()+" "+hms();
	    	if(ok_username==true && ok_email ==true && ok_password ==true && ok_re_password ==true){
	    		
	    		
	    		 //注册
	    		$.ajax({
					type:'post',
					dataType:'text',
					url:CTPPATH+"/admin/user/save",
					data:{"userName":userName,"email":email,"password":password,"addTime":date},
				
					beforeSend:function(){
						//显示正在加载
						layer.msg('正在注册',{offset: 0,time:2000});
					},
					success:function(data){

						//关闭正在加载
						setTimeout(function(){
							  layer.closeAll('loading');
						}, 1000);
						
						//代表用户名可用
						if(data==1){
							layer.msg('恭喜！用户注册成功',{time:4000});
							
						}else if(data==0){
							//代表用户名不可用
							layer.msg('*抱歉，用户注册失败，请仔细核对信息！',{time:4000});
							
						}
					},
					error:function(){

						//关闭正在加载
						setTimeout(function(){
							  layer.closeAll('loading');
						}, 1000);
						layer.msg("出错了", {icon: 2,time:2000});
					}
				});
	    		
	    		
	    	}else{
	    		layer.msg("用户信息验证失败，请将信息填写正确后再提交！");
	    	}
	    	
	    });
	    
});
</script>

