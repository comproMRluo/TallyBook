<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<div class="row bg-success">
			<div class="col-md-4">
				<div class="page-header">
				  <h1>欢迎<c:if test="${sessionScope.User.permission==0}"><small class="text-danger">超级管理员:</small></c:if><c:if test="${sessionScope.User.permission==1}"><small class="text-warning">管理员:</small></c:if> <small class="text-success">${sessionScope.User.username}</small>登陆<small>记账本</small></h1>
				</div>
			</div>
			
			<div class="col-md-2 col-md-offset-6">
				<br />
				<br />
				<ul class="list-inline text-right">
					<li>
						<c:choose>
								<c:when test="${not empty sessionScope.User}"><a href="UserServlet?method=logOut" class="text-danger">退出登陆</a></c:when>
								<c:otherwise><a href="page/login.jsp" class="text-danger">去登陆</a></c:otherwise>
						</c:choose>
						
					</li>
					
				</ul>
			</div>
		</div>
</body>
</html>