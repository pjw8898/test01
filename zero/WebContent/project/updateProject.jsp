<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 정보변경</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
<h1>프로젝트 변경</h1>
<form action="postupdateProjectInfo.do" method="post">
*번호: <input type="text" name="no" value="${projectInfo.no}" readonly><br>
*프로젝트명: <input type="text" name="title" value="${projectInfo.content}">
*내용:<br> 
<textarea name="content" rows="4" cols="50"></textarea><br>
*시작일: <input type="text" name="startDate" value="${projectInfo.startDate}"><br>
종료일: <input type="text" name="endDate" value="${projectInfo.endDate}"><br>
*태그: <input type="text" name="tag" value="${projectInfo.tag}"><br>
<input type="submit" value="변경"><input type="reset" value="취소"><br>

 <c:choose>
<c:when test="${memberInfo.level == 0}">일반회원</c:when>
<c:when test="${memberInfo.level == 1}">관리자</c:when>
<c:when test="${memberInfo.level == 2}">PM</c:when>
<c:when test="${memberInfo.level == 3}">PL</c:when>
<c:when test="${memberInfo.level == 9}">손님</c:when>
</c:choose><br>

<p>
<a href="list.do">[목록]</a>
<c:if test='${memberInfo.level == 3}'>
<a href="updateProjectInfo.do?no=${projectInfo.no}">[변경]</a>
<a href="delete.do?email=${memberInfo.email}">[삭제]</a>
</c:if>
</p>
</form>

</div>

<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>









