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
			<a href="/LapTrinhWeb/home-shop">HOME</a>/HÓA ĐƠN
		</p>
		<h1>Hóa đơn</h1>
		<p>
			<a href="/LapTrinhWeb/smartphone">&larr; Back to Home</a>
		</p>
	</div>
	<div id="main-gio-hang">
		<div id="thong-tin-khach-hang">
			<p>Thông tin khách hàng</p>
			<p>Tên khách hàng : ${order.member.hoTen}</p>
			<p>Gmail :${order.member.gmail}</p>
			<p>Số điện thoại : ${order.member.phone}</p>
			<p>Danh sách sản phẩm : </p>
		</div>
		<table id="show-gio-hang">
			<thead>
			<tr>
				<th>STT</th>
				<th colspan="2">Sản phẩm</th>
				<th>Đơn giá</th>
				<th>Số lượng</th>
				<th>Tổng giá</th>
			</tr>
			</thead>
			<c:forEach items="${order.listCartItem}" var="item" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td><img class="img-pro-gio-hang" src="<c:url value = '/static/images/${item.product.img }'/>"></td>
				<td>${item.product.tenP }</td>
				<td>${item.product.chiTietSP.gia }</td>
				<td>${item.quantity }</td>
				<td>${item.product.chiTietSP.gia*item.quantity  }</td>
			</tr>
			</c:forEach>
			<tfoot><tr>
				<th colspan="4">Tổng : </th>
				<th colspan="2">${tong }</th>
			</tr></tfoot>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>