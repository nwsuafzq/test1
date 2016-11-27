<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>bookmanagelist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2 align="center">图书信息管理 </h2>
    <p align="center"><a href="BookEdit.jsp">添加图书信息</a>
    <table align="center" width="600" border="1">
    <tr>
    <th>书籍id</th>
    <th>书名</th>
    <th>作者</th>
    <th>出版社</th>
    <th>出版日期</th>
    <th>价格</th>
    <th>操作</th>
    </tr>
    <c:forEach items="${bookList}" var="book">
    <tr>
    <td>${book.bid}</td>  
    <td>${book.name}</td>
    <td>${book.authors}</td> 
    <td>${book.publisher}</td>
    <td>${book.publishdate}</td> 
     <td>${book.price}</td>
     <td>
     <a href="ctrl?action=edit&id=${book.id}">编辑</a>
      <a href="ctrl?action=delete&id=${book.id}">删除</a>
    </tr>
    </c:forEach>
    </table> 
  </body>
</html>
