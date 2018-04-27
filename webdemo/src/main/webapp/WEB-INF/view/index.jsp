<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<style type="text/css">
body, table {
	font-size: 12px;
}

table {
	table-layout: fixed;
	empty-cells: show;
	border-collapse: collapse;
	margin: 0 auto;
}

td {
	height: 30px;
}

h1, h2, h3 {
	font-size: 12px;
	margin: 0;
	padding: 0;
}

.table {
	border: 1px solid #cad9ea;
	color: #666;
}

.table th {
	background-repeat: repeat-x;
	height: 30px;
}

.table td, .table th {
	border: 1px solid #cad9ea;
	padding: 0 1em 0;
}

.table tr.alter {
	background-color: #f5fafe;
}
</style>
</head>
<body>
This is The Index Page, Click <a href='<c:url value="/mode?username=aaaa&password=bbbb"/>'>UserInfo</a> to Userinfo Page<br/><br/>

######################################Host Informations######################################<br/>
ip:${ip}</br>
hostname:${hostname}</br>
</br>
######################################System Properties######################################<br/>
<table class="table">
<c:forEach var="item" items="${systemProperties}" varStatus="status">
			<tr>
				<td>${status.count}</td><td>${item.key}</td><td>${item.value}</td>
			</tr>
</c:forEach>
</table>

</body>
</html>