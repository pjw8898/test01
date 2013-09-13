<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 정보</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
<h1>프로젝트 정보</h1>
번호: ${project.no}<br>
프로젝트명: ${project.title }<br>
내용:<br> 
${project.content }<br>
시작일: ${project.startDate }<br>
종료일: ${project.endDate }<br>
태그: ${project.tag }<br>

<h1>참여멤버 목록</h1>
<table>
<tr>
	<th>이메일</th>
	<th>이름</th>
	<th>전화번호</th>
	<th>블로그</th>
	<th>관리자</th>
</tr>
<c:forEach var="member" items="${list}">
<tr>
	<td>${member.email}</td>
	<td>${member.name}</td>
	<td>${member.tel}</td>
	<td>${member.blog}</td>
	<td><c:if test="${member.level==1}">[PL]</c:if></td>
	
</tr>	
</c:forEach>
</table>
<p>
<a href="list.do">[목록]</a>
<c:if test="${ member.level == 1 }"><a href="updateProjectInfo.do?no=${project.no}">[변경]</a></c:if>
<c:if test="${ member.level == 1 }"><a href="delete.do?no=${project.no}">[삭제]</a></c:if>
</p>
</div>

<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>









