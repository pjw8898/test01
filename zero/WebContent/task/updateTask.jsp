<%@page import="net.bitacademy.java41.vo.MemberProject"%>
<%@page import="net.bitacademy.java41.vo.Task"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 정보 변경</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
<h1>프로젝트 변경</h1>
<form action="taskupdatepost.do" method="post">
Project 번호: <input type="text" name="pno" value="${taskInfo.pno}" readonly><br>
Task 번호: <input type="text" name="tno" value="${taskInfo.tno}" readonly><br>
Title: <input type="text" name="title" value="${taskInfo.title}"><br>
UiprotoUrl:<input type="text" name="uiProtoUrl" value="${taskInfo.uiProtoUrl}"><br>
Content:<br> 
<textarea name="content" rows="4" cols="50" value="${taskInfo.content}"></textarea><br>
시작일 : <input type="text" name="startDate" placeholder="예)2013-4-5" value="${taskInfo.startDate}"><br>
종료일 : <input type="text" name="endDate" placeholder="예)2013-6-5" value="${taskInfo.endDate}"><br>
상태: <input type="text" name="status" placeholder="예)태그1 태그2 태그3 (공백으로 구분)" value="${taskInfo.status}"><br>
<input type="submit" value="변경"><input type="reset" value="취소"><br>
</form>

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









