<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8">


<title>记账本</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<style>
	a{
		font-size: large;
		
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#select1").change(function(){
			var a = $(this).val();
			$("#select2").empty();
			if(a=="0"||a=="1"){
				$.getJSON('RecordServlet?method=typeSelect',{"select1":a}, function(data) {
					var opt = document.createElement("option");
					var loc = document.createTextNode("-请选择-");
					opt.appendChild(loc);
					$("#select2").append(opt);
					  $.each(data, function(key, val) {
						  	var opt = document.createElement("option");
							var loc = document.createTextNode(val.typename);
							opt.appendChild(loc);
							$("#select2").append(opt);
					  });
					
				});
			}
		});
		
		$("#select2").change(function(){
			if(select1==0){
				$.post("RecordServlet?method=getAccountAmount",{"amount":amount},function(msg){
					if($.trim(msg) == "false"){
						alert("您当前账户的剩余金额不足以支持您记录下这笔支出,请您去详细信息模块核实自己近期的消费情况,适当进行修改或联系管理员qq:1216770825!");
						return false;
					}
				})
			}
		});
		
		$("#handon").submit(function(){
			var select1 = $("#select1").val();
			var select2 = $("#select2").val();
			var amount=  $("#amount").val();
			var result = true;
			if(!(/^(?:[1-9]\d*|0)(?:\.\d+)?$/.test(amount))){
				alert("请输入正实数范围内的金额!");
				return false;
			}
			if(select2==null){
				alert("必须选择类型!");
				return false;
			}
			if(select1==0){
				$.ajax({
		             async: false,    //设置为同步
		             type: "post",
		             url: "RecordServlet?method=getAccountAmount",
		             data: {"amount":amount},
		             success: function (msg) {
		            	 if($.trim(msg) == "false"){
								alert("您当前账户的剩余金额不足以支持您记录下这笔支出,请您去详细信息模块核实自己近期的消费情况,适当进行修改!");
								result = false;
						 }                                  
		             }
		          });
		          return result; 
		      }			
		});
		
	});
	
</script>
</head>
<body>
	<div class="container-fluid">
		<%@include file="/WEB-INF/include/head.jsp"%>
		<div class="row">
			<div class="collist-group col-md-2">
			  <a href="#" class="list-group-item active"></a>
			  <a href="RecordServlet?method=showConsumption" class="list-group-item active"><b class="text-danger">消费一览</b></a>
<!-- 			  <a href="#" class="list-group-item active">记一笔</a> -->
			  <button type="button" id="add" class="btn btn-primary btn-lg list-group-item active" data-toggle="modal" data-target="#myModal">记一笔</button>
			  <a href="RecordServlet?method=getRecordByPage&pageNo=1" class="list-group-item active">详细信息</a>
			  <c:if test="${sessionScope.User.permission==2||sessionScope.User.permission==null}">
			  <a class="list-group-item active">带扩展项</a>
			  </c:if>
			  <c:if test="${sessionScope.User.permission==0||sessionScope.User.permission==1}">
		  		<div class="btn-group">
				  <button type="button" class="btn btn-default dropdown-toggle list-group-item active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    管理员菜单 <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
				    <li class="list-group-item active"><a href="SuperPowerServlet?method=getTypesAndPaytypeId&pageNo=1">消费分类</a></li>
				    <li class="list-group-item active"><a href="SuperPowerServlet?method=checkAllUsers&pageNo=1">管理用户</a></li>
				    <li class="list-group-item active"><a href="RecordServlet?method=getAllRecordsByPage&pageNo=1">查看所有的用户的消费记录</a></li>
				    <li role="separator" class="divider"></li>
				    <li class="list-group-item active"><a>待扩展</a></li>
				  </ul>
				</div>
			  </c:if>
			  
			  <a class="list-group-item active"></a>
			  <a class="list-group-item active"></a>
			  <a class="list-group-item active"></a>
			  <a class="list-group-item active"></a>
			  <a class="list-group-item active"></a>
			</div>
			
			<div class="col-md-10">
			  	<div class="jumbotron">
				  <h1>消费总览</h1>
				  <p>总支出:    <b>${ requestScope.recordsBean.totalout}</b><b class="glyphicon glyphicon-jpy"></b></p>
				  <p>总收入:    <b>${ requestScope.recordsBean.totalin}</b><b class="glyphicon glyphicon-jpy"></b></p>
				  <p>目前财产余额:    <b>${requestScope.recordsBean.accountRest}</b><b class="glyphicon glyphicon-jpy"></b></p>
				  <p>支出收入比:    <b>${ requestScope.recordsBean.ioratio}</b><b class="">%</b></p>
				  <p>带扩展项.....  <b></b><b></b></p>
				  <p><a class="btn btn-primary btn-lg" href="RecordServlet?method=getRecordByPage&pageNo=1" role="button">查看明细</a></p>
				</div>
			</div>
			
			
		</div>
				
			
		
		<div class="row bg-primary text-center">
					<p >Copyright © 2020 <a href="https://user.qzone.qq.com/1216770825#"target="_blank">WW</a> All Rights Reserved</p>

		</div>
	
	
	</div>
	<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
					<form class="form-inline" id="handon" action="RecordServlet?method=addRecord" method="post">
						<div class="modal-header">
						  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						  <h3 class="modal-title" id="myModalLabel">记一笔</h3>
						</div>
						<div class="modal-body">
						  <div class="input-group">
						    <span class="input-group-addon" id="basic-addon1">金额</span>
						    <input type="text" class="form-control" name="amount"id="amount" placeholder="amount" aria-describedby="basic-addon1">
						  </div>
						  <div class="input-group">
						    <span class="input-group-addon" id="basic-addon1"><b class="text-danger">*</b>类型</span>
							<select class="form-control" id="select1"  aria-describedby="basic-addon1">
							  	<option value="-1">-请选择--</option>
							  	<option value="0">支出</option>
							  	<option value="1">收入</option>
							</select>
							<select class="form-control" name="select2" id="select2" aria-describedby="basic-addon1">
							  
							</select>
						  </div>
						<div class="input-group">
							  <span class="input-group-addon" id="basic-addon1">备注</span>
							  <input type="text" class="form-control" name="remark" id="remark" placeholder="remark" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						  <input type="submit"   class="btn btn-primary" value="完成"/>
						</div>
						
					</form>
			      
			    </div>
			  </div>
			</div>
</body>
