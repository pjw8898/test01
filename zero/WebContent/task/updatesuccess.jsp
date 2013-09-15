<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Refresh" content="1;url=../task/taskview.do?tno=${param.tno}">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Update Success</title>
<link rel="stylesheet" href="${rootPath}/css/base.css">
</head>
<body>
<h3>Task 업데이트가 성공했습니다.</h3>
<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="/sidebar.jsp"></jsp:include>

<div id="content">
</div>

<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>









