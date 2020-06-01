<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8">
<base href="<%=request.getContextPath() %>/">
<title>注册</title>

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="static/css/bootstrap.css" />

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
			
			.hugeScreen{
				
				
			}
		</style>
		
		<script>
			$(function(){
				
				$("#accountManagement").change(function(){
					var match = /^[A-Za-z0-9]+$/i; 
				
// 					1.取值
					var accountManagement = $("#accountManagement").val();
// 					2.Ajax异步请求校验
					if(accountManagement.length!=0){
						if(match.test(accountManagement)){
							$.post("UserServlet?method=checkName",{"accountManagement":accountManagement},function(msg){
								if($.trim(msg) == "true"){
									$("#account").text("账号已存在")
									$("#account").attr("class","label label-danger fail");
								}else{
									$("#account").text("账号可用")
									$("#account").attr("class","label label-success");
									return true;
								}
							});
						}else{
							$("#account").text("账号由数字和字母构成")
							$("#account").attr("class","label label-danger fail");
						}
			
					}else{
						$("#account").text("账号不能为空");
						$("#account").attr("class","label label-danger fail");
					}
			
				});
				
				
				$("#accountPassword").change(function(){
					var psw = $(this).val();
					if(psw.length == 0){
						$("#psw").text("请输入密码")
						$("#psw").attr("class","label label-danger fail");
					
					}else{
						$("#psw").text("");
						$("#psw").attr("class","fail");
					}
				});
				
				$("#confirmPassword").change(function(){
					var password = $("#accountPassword").val();
					var cfpsw = $(this).val();
					if(password == cfpsw && password.length!=0){
						$("#psw").text("√")
						$("#psw").attr("class","label label-success");
						$("#cmfpsw").text("√")
						$("#cmfpsw").attr("class","label label-success");
					}else{
						$("#cmfpsw").text("输入密码不一致")
						$("#cmfpsw").attr("class","label label-danger fail");
						
					}
					
				});
				
				$("#phone").change(function(){
					var phone = $(this).val();
					
					if((/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(phone))&&phone.length!=0){
						$("#phoneCheck").text("√")
						$("#phoneCheck").attr("class","label label-success");
					}else if(phone.length==0){
						$("#phoneCheck").text("手机号不能为空")
						$("#phoneCheck").attr("class","label label-danger fail");
					}else{
						$("#phoneCheck").text("手机号格式错误")
						$("#phoneCheck").attr("class","label label-danger fail");
	
					}
					
					
				});
				
				$("#codeIMG").click(function(){
					$(this).attr("src","code.jpg?random="+Math.random());
				});
				
				$("#code").change(function(){
					var code = $(this).val();
					$.post("UserServlet?method=checkCode",{"code":code},function(msg){
							if($.trim(msg) == "false"){
								alert("验证码输入错误")
								$("#codeCheck").attr("class","fail");
							}else{
								$("#codeCheck").attr("class","");
							}
						}	
					);
					
				});
				
				$("#handon").submit(function(){
					$(".bitian").trigger("change");
					if($(".fail").length!=0){
						alert("有"+$(".fail").length+"个必填项还没完善!");
						return false;
						
					}
					
				});
				
				$("#towardslogin").click(function(){
					location = "http://localhost:8080/TallyBook/page/login.jsp";
				});
				
			});
			
		</script>
</head>
<body>
		
		<div class="container-fluid">
					<div class="row">
							<div class="col-md-4">
									<h1 class="text-info">申请我的记账本</h1>
								<div class="grid_30">
									<a href="homepage.jsp" class=""target="_blank"></a>
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
						<div class="col-md-7">
							<div class="jumbotron hugeScreen">
							  <h1>欢迎注册我的记账本</h1>
							  <p>项目基于web技术，jsp,el,jstl,java,mysql技术为自己项目的实现提供了可能。</p>
							  <p>平台:本项目基于WW平台,对于面向广大的社会人群都适用。</p>
							  <p>技术优势:无技术优势,目前自会这些技术,没得挑。</p>
							  <p>开发团队成员:WW以及其他人员</p>
							  <p>产品logo:   <b class="glyphicon glyphicon-piggy-bank"></b></p>
							  <p>产品亮点:能让生活更麻烦。</p>
							  <p>产品框架:未用框架</p>
							  <p>致谢感谢那些为项目负责的团队、个人、平台提供者。</p>
							  <p><a class="btn btn-primary btn-lg" href="LearnMoreServlet?method=learnMore&LearnMore=1" role="button">了解更多</a></p>
							</div>
						</div>
									
						<div class="col-md-4 ">
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div class="row lead">			
								<div class="col-md-0"><h2>注册</h2></div>			

								<form action="UserServlet?method=insertUser" method="post" id="handon">
									<div class="row form-group">
									    <label  class="col-sm-4 control-label text-right"><b class="text-danger">*</b>account:</label>
									    <div class="col-sm-6">
									      <input type="text" class="form-control input-lg btn-default bitian" name="accountManagement" id="accountManagement" placeholder="账号" data-toggle="tooltip" data-placement="right" title="必填项!">
									    </div>
									    <div class="col-sm-2">
									    	<span id="account"></span>
									    </div>
									  </div>
									  <div class="row form-group">
									    <label  class="col-sm-4 control-label text-right"><b class="text-danger">*</b>Password:</label>
									    <div class="col-sm-6">
									      <input type="password" class="form-control input-lg bitian"  name="accountPassword" id="accountPassword" placeholder="密码" data-toggle="tooltip" data-placement="right" title="必填项!">
									    </div>
									    <div class="col-sm-2">
									     	<span id="psw"></span>
									    </div>
									  </div>
									  <div class="row form-group">
									    <label  class="col-sm-4 control-label text-right"><b class="text-danger">*</b>cfPassword:</label>
									    <div class="col-sm-6">
									      <input type="password" class="form-control input-lg bitian" id="confirmPassword" placeholder="确认密码" data-toggle="tooltip" data-placement="right" title="必填项!">
									    </div>
									     <div class="col-sm-2">
									     	<span id="cmfpsw"></span>
									    </div>
									  </div>
									  <div class="row form-group">
									    <label  class="col-sm-4 control-label text-right"><b class="text-danger">*</b>phone:</label>
									    <div class="col-sm-6">
									      <input type="text" class="form-control input-lg bitian" name="phone" id="phone" placeholder="phone" data-toggle="tooltip" data-placement="right" title="必填项!">
									    </div>
									     <div class="col-sm-2">
									     	<span id="phoneCheck"></span>
									    </div>
									  </div>
									  
									  <div class="row form-group">
									    <label  class="col-sm-4 control-label text-right">username:</label>
									    <div class="col-sm-6">
									      <input type="text" class="form-control input-lg" name="username" id="username" placeholder="username">
									    </div>
									     <div class="col-sm-2">
									     	<span id="usernameCheck"></span>
									    </div>
									  </div>
									  
									  <div class="row form-group">
									    <label  class="col-sm-4 control-label text-right">email:</label>
									    <div class="col-sm-6">
									      <input type="email" class="form-control input-lg" name="email" id="email" placeholder="email">
									    </div>
									     <div class="col-sm-2">
									     	<span id="emailCheck"></span>
									    </div>
									  </div>
									  
									  <div class="row input-group">
									  	<label  class="col-sm-4 control-label text-right"><b class="text-danger">*</b>code:</label>
									  	<div class="col-sm-3"> 
											<input type="text" class="form-control bitian " id="code" placeholder="code" aria-describedby="basic-addon1" data-toggle="tooltip" data-placement="right" title="必填项!">
								  			<input type="hidden" id="codeCheck">
								  		</div>
								  		<div class="col-sm-5">
								  			<img alt="loading" src="code.jpg" class="thumbnail img-responsive" id="codeIMG">
								  		</div>
									</div>
								 	
									<div class="row ">
										<div class="col-md-2 col-md-offset-3">
										  	<button type="submit" class="btn btn-default">开启我的记账本</button>
										</div>
										<div class="col-md-2 col-md-offset-1">
										  	<button type="reset" class="btn btn-default" id="towardslogin">已有账号?</button>
										</div>
									</div>
								  
								</form>
										
										
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