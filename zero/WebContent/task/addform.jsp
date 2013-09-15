<%@page import="net.bitacademy.java41.vo.Task"%>
<%@page import="net.bitacademy.java41.vo.MemberProject"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 등록</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
<h1>New Task</h1>
<form action="taskadd.do" method="post">
프로젝트 번호: <input type="text" name="pno" value="${param.pno}" readonly><br>
Title: <input type="text" name="title"><br>
UiprotoUrl:<input type="text" name="uiprotourl"><br>
Content:<br> 
<textarea name="content" rows="4" cols="50"></textarea><br>
시작일 : <input type="text" name="startdate" placeholder="예)2013-4-5"><br>
종료일 : <input type="text" name="enddate" placeholder="예)2013-6-5"><br>
상태: <input type="text" name="status" placeholder="예)태그1 태그2 태그3 (공백으로 구분)"><br>
<input type="submit" value="등록"><input type="reset" value="취소"><br>
</form>
<p><a href="../task/task.do?pno=${param.pno}">[목록]</a></p>
</div>

<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>
