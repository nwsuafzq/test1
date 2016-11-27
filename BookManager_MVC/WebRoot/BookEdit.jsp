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
    
    <title>My JSP 'BookEdit.jsp' starting page</title>
    
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
    <h2 align="center">添加/编辑图书信息</h2>
    <form action="ctrl?action=save&id=${book.id}" method="post">
    <table width="300" align="center" border="1">
    <tr>
    <td>书籍ID：</td>
    <td><input type="text" name="bid" value="${book.bid}">
    </td>
    <tr>
    <td>书籍名:</td>
    <td><input type="text" name="name" value="${book.name}">
    </td>
    <tr>
    <td>作者:</td>
    <td><input type="text" name="authors" value="${book.authors}">
    </td>
    </tr>
    <tr>
    <td>出版社:</td>
    <td><input type="text" name="publisher" value="${book.publisher}">
    </td>
    </tr>
    <tr>
    <td>出版日期:</td>
    <td><input type="text" name="publishdate" value="${book.publishdate}">
    </td>
    </tr>
    <tr>
    <td>价格:</td>
    <td><input type="text" name="price" value="${book.price}">
    </td>
    </tr>
    <tr>
    <td colspan="2" align="center"><input type="submit" value="确定"/></td>
    </tr>
    </table>
    
    </form>
  </body>
</html>
