<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.ProjectTask"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="java.util.List"%>

<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="list" type="java.util.List<net.bitacademy.java41.vo.ProjectTask>" 
		scope="session"></jsp:useBean>  
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 목록</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div class="content">
        
        <div class="header">
            
            <h1>작업 관리 </h1>
        </div>
        <p align="right" style="pitch: medium;"><a href="<%=application.getContextPath()%>/task/taskaddForm.do" >작업 추가</a></p>


    <table class="table" >
      <thead >
        <tr>
          <th>Task No </th>
          <th></th>
          <th>제목 </th>
          <th></th>
          <th>파일 </th>
          <th></th>
          <th>시작일 </th>
          <th></th>
          <th>종료일 </th>
          <th></th>
          <th>진행상태</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      
      <tbody>
<% int i = 1; %>
       <% for(ProjectTask p : list){%>
       <tr>
          <td><%= i++ %></td>
          <td><a href="../task/taskview.do?tno=<%=p.getTno()%>"><%=p.getTitle()%></a></td>
          <td><%=p.getUiProtoUrl()%></td>
          <td><%=p.getStartDate()%></td>
          <td><%=p.getEndDate()%></td>
          <td><%=p.getStatus()%></td>
<%--           <td><%=p.getTno()%> </td> --%>

        	
        </tr>
       <%} %>
       </tbody>
    </table>
</div>
<p><a href="${rootPath}/project/addTaskForm.do">[신규 프로젝트]</a></p>


<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>









