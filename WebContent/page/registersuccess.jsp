<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册成功,跳转登陆界面</title>
<base href="<%=request.getContextPath() %>/">
<link rel="stylesheet" href="static/css/bootstrap.css" />

		<!--需要引入JQuery-->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script type="text/javascript" src="static/js/bootstrap.js"></script>

		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<style>
			.center-in-center{   
            position: absolute;   
            top: 45%;   
            left: 38%;   
        }   
		</style>
</head>
<body onload="pictures()" >

		<div class="text-center center-in-center" >
				注册成功! 正在跳转至登陆页面中(<span id="count" style="color:red">5</span>秒后自动跳转)......<a href="page/login.jsp">直接跳转</a>
		</div>
		<script type="text/javascript">
					var num = 5;
					function changeCount(){
						if(num>1){
							num= num-1;
							$("#count").html(num);
						}else{
							num = 5;
							location = "page/login.jsp";
						}
					}
					
					function pictures(){
						setInterval("changeCount()",1000);
		 			}
		</script> 
</body>
</html>