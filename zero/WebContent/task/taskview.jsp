<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="net.bitacademy.java41.vo.Task"%>
<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task 정보</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
<div id="submenu">
<p>
<a href="../project/view.do?no=${task.pno}">[기본정보]</a>
<a href="../task/task.do?pno=${task.pno}">[작업들]</a> 
<a href="../feed/list.do?projectNo=${project.no}">[게시판]</a>
</p>
</div>
<h1>테스크 정보</h1>
번호: ${task.tno}<br>
프로젝트명: ${task.title}<br>
UIPROTOURL: ${task.uiProtoUrl}<br>
내용:<br> 
${task.content }<br>
시작일: ${task.startDate}<br>
종료일: ${task.endDate}<br>
상태: ${task.status}<br>
<p>

<a href="updatetask.do?tno=${task.tno}">[변경]</a>
<a href="delete.do?tno=${task.tno}&pno=${task.pno}">[삭제]</a>
</p>
</div>

<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>









