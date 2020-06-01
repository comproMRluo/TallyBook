<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta content="text/html; charset=utf-8">
<base href="<%=request.getContextPath() %>/">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>登录</title>
		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="static/css/bootstrap.css"/>

		<!--需要引入JQuery-->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script type="text/javascript" src="static/js/bootstrap.js"></script>

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
			a{
				font-size: large;
				color: #5149a9;
				
			}
			
			p{
				font-size: large;
				
			}
			
		</style>
		<script>
			$(function(){
				$("#rembpsw").click(function(){
					var $remb = $(this).parent().next();
					if($(this).is(":checked")){
						$remb.attr("value","1");
					  }else{
						  $remb.attr("value","0");
					  }
				})
				$('.carousel').carousel({
 					 interval: 3000
				})
				
				$('#myCarousel').on('slide.bs.carousel', function () {
  
				})
			})
		</script>
</head>
<body>

		<div class="container-fluid">
					<div class="row">
							<div class="col-md-4">
									<h1 class="text-info">登录我的记账本</h1>
								<div class="grid_30">
									<a href="#" class=""target="_blank"></a>
								</div>
								<div class=""></div>
							</div>
							<div class="col-md-2 col-md-offset-6">
								<br>
								
								<ul class="list-inline">
								  <li >
									<a href="UserServlet?method=logOut"><ins><strong>预览记账本</strong></ins></a> 
									
								  </li>
								  <li><a href="https://user.qzone.qq.com/1772023203" target="view_window"><strong>联系我们</strong></a></li>
								</ul>
							
							</div>

					</div>
					

		
					<div class="row bg-warning">
						<div class="col-md-8 ">	
							<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
							  <!-- Indicators -->
							  <ol class="carousel-indicators">
							    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
							    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
							  </ol>
							
							  <!-- Wrapper for slides -->
							  <div class="carousel-inner" role="listbox">
							    <div class="item active">
							      <img src="static/imges/1.jpeg" alt="loding...">
							      <div class="carousel-caption">
							        
							      </div>
							    </div>
							    <div class="item">
							      <img src="static/imges/2.jpeg" alt="loding...">
							      <div class="carousel-caption">
							       
							      </div>
							    </div>
							    <div class="item">
							      <img src="static/imges/3.jpeg" alt="loding...">
							      <div class="carousel-caption">
							      
							      </div>
							    </div>
							  </div>
							
							  <!-- Controls -->
							  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
							    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
							    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>
						</div>
						
						<div class="col-md-3 ">
							<div class="">
								<div class="">&nbsp;</div>
								<div class="">&nbsp;</div>
								<div class="">
									<div class="lead">
										
									
										<div>
											<h2>登录</h2>
											<span id="msg" class="text-danger">${requestScope.msg}</span>
											<form action="UserServlet?method=getUser" method="post">
											  <div class="form-group">
												<label for="exampleInputEmail1">accountManagement</label>
												<input type="text" class="form-control input-md btn-default" id="exampleInputEmail1" name="user" placeholder="账号" value="<c:if test="${not empty sessionScope.accountManagement}">${sessionScope.accountManagement}</c:if>">
											  </div>
											  <div class="form-group">
												<label for="exampleInputPassword1">accountPassword</label>
												<input type="password" class="form-control input-md btn-default" id="exampleInputPassword1" name="pw" placeholder="密码" value="<c:if test="${not empty sessionScope.password}">${sessionScope.password}</c:if>">
											  </div>
											 
											  <div class="checkbox">
												<label>
												  <input type="checkbox" id="rembpsw" <c:if test="${not empty sessionScope.password}">checked="checked"</c:if>> <p>记住密码</p>
												</label>
												
												<input type="hidden" name="rembpsw" value="<c:choose><c:when test="${not empty sessionScope.password}">1</c:when><c:otherwise>0</c:otherwise></c:choose>" >
											  </div>
											  <button type="submit" class="btn btn-default">登陆</button>
											</form>
										</div>
										
										<div class="">
											<ul class="list-inline">
												<li><a class="" href="#">找回账号</a></li>
												<li><a class="" href="#">找回密码</a></li>
												<li><a class="" href="page/register.jsp">注册</a></li>
												
											</ul>
											
											
											
										</div>
											
										<span class=""> </span> <a href="https://open.weixin.qq.com" class="">微信登录</a>
										
										<span class=""> </span> <a href="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm" class="">支付宝登录</a>
									</div>
								</div>
							</div>
						</div>
					</div>

				<div class="row">
			
					<div class="col-md-4 col-md-offset-4">
						<p >Copyright © 2020 <a href="https://user.qzone.qq.com/1216770825"target="_blank">WW</a> All Rights Reserved</p>
					</div>
					
				</div>
			</div>

</body>
</html>