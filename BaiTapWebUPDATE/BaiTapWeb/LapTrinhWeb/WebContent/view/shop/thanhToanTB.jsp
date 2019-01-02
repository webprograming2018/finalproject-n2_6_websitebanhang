<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/LapTrinhWeb/static/css/style.css" rel="stylesheet" />
<script type="text/javascript" charset="utf8" src="/LapTrinhWeb/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/LapTrinhWeb/static/js/myJS.js"></script>
<title>Homeshop</title>
</head>
<body>
	<jsp:include page="menutop.jsp"></jsp:include>
	<div id="s-title">
		<p>
			<a href="/LapTrinhWeb/home-shop">HOME</a>/THANH TOÁN
		</p>
		<h1>Thanh toán</h1>
		<p>
			<a href="/LapTrinhWeb/smartphone">&larr; Back to Home</a>
		</p>
	</div>
	<div id="thanh-toan-that-bai">
		<p>Thanh toán thất bại.<a href="<c:url value = '/gio-hang'/>"> Click</a> để quay lại giỏ hàng.</p>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>