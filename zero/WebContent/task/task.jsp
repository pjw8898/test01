<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Task"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="java.util.List"%>

<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="list" type="java.util.List<net.bitacademy.java41.vo.Task>" 
		scope="session"></jsp:useBean>  
<link rel="stylesheet" href="${rootPath}/css/base.css">
 <link id="bs-css" href="${rootPath}/css/bootstrap-cerulean.css" rel="stylesheet"> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 목록</title>

</head>

<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>
            
<h1>작업 관리 </h1>
       


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
<div class="row-fluid sortable">	
	<div class="box span12">
					
		<div class="box-content">
			<table class="table table-bordered table-striped table-condensed">
				<thead>
					<tr>
					 <th>Task No</th>
					 <th>Title</th>
					 <th>UI File</th>
					 <th>Start Date</th>
					 <th>End Date</th>
					 <th>Status</th>
				 </tr>
			 </thead>   
		 <tbody>
<%
	int i = 1;
%>
       <%
       	for(Task p : list){
       %>
       <tr>
          <td><%= i++ %></td>
          <td><a href="../task/taskview.do?tno=<%=p.getTno()%>"><%=p.getTitle()%></a></td>
          <td><%=p.getUiProtoUrl()%></td>
          <td><%=p.getStartDate()%></td>
          <td><%=p.getEndDate()%></td>
          <td class="center">
			 <span class="label label-important"><%=p.getStatus()%></span>
		  </td> 
         
        </tr>
       <%} %>
			</tbody>
						 </table>  
						 <div class="pagination pagination-centered">
						  <ul>
							<li><a href="#">Prev</a></li>
							<li class="active">
							  <a href="#">1</a>
							</li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">Next</a></li>
						  </ul>
						</div>     
					</div>
				</div><!--/span-->
			</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>		
<p align="left"><a href="${rootPath}/task/addform.do?pno=${ param.pno }"><font size="5">[New Task]</fonr></a></p>
<jsp:include page="/tail.jsp"></jsp:include>
</body>
</html>