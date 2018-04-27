<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfo</title>
</head>
<body>
This is The Index Page, Click <a href='<c:url value="/"/>'>Context Root</a> to Return Context Root Page<br/><br/>
######################################User Informations######################################<br/>
<table width="80%" border="1px">
      <tbody>
             <tr><td>username</td><td><input type="text" value="${username }" name="Username" /></td></tr>
             <tr><td>username</td><td><input type="text" value="${password}" name="Password" /></td></tr>
      </tbody>
</table>
</body>
</html>