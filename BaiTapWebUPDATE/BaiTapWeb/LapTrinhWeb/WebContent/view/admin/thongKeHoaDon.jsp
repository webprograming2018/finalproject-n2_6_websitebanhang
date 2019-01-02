<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/LapTrinhWeb/static/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/LapTrinhWeb/static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="/LapTrinhWeb/static/css/custom.min.css" rel="stylesheet">
	<style type="text/css">
	th {
		text-align: center;
	}
	</style>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="/LapTrinhWeb/static/js/loader.js"></script>
    <script type='text/javascript'>
      google.charts.load('current', {'packages':['annotationchart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        var t1 = ${t1}; var t2 =${t2};var t3 = ${t3}; var t4=${t4};
        var t5 = ${t5}; var t6 =${t6};var t7 = ${t7}; var t8 =${t8};
        var t9 = ${t9}; var t10 =${t10};var t11 = ${t11}; var t12 =${t12};
        data.addColumn('date', 'Date');
        data.addColumn('number', 'Số hóa đơn');
        data.addRows([
          [new Date(2017, 12), 0],
          [new Date(2018, 1), t1],
          [new Date(2018, 2), t2],
          [new Date(2018, 3), t3],
          [new Date(2018, 4), t4],
          [new Date(2018, 5), t5],
          [new Date(2018, 6), t6],
          [new Date(2018, 7), t7],
          [new Date(2018, 8), t8],
          [new Date(2018, 9), t9],
          [new Date(2018, 10), t10],
          [new Date(2018, 11), t11],
          [new Date(2018, 12), t12]
        ]);

        var chart = new google.visualization.AnnotationChart(document.getElementById('chart_div'));

        var options = {
          displayAnnotations: true,
        };

        chart.draw(data, options);
      }
    </script>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<jsp:include page="menuAdmin.jsp"></jsp:include>
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Biểu đồ <small>Thống kê số lượng hóa đơn</small></h3>
							<div id='chart_div' style='width: 900px; height: 450px;'></div>
						</div>
					</div>
					<br />
					<!-- <div style="float: right;"> 
							<select class="selectpicker" style="width: 150px;">
							  <optgroup label="Thống kê trong năm">
							    <option>2018</option>
							  </optgroup>
							  <optgroup label="Thống kê trong quý">
							    <option>Quý một</option>
							    <option>Quý hai</option>
							    <option>Quý ba</option>
							    <option>Quý bôn</option>
							  </optgroup>
							</select>
					</div> -->
				</div>
			</div>
		</div>
	</div>

	<footer> </footer>
	<script src="/LapTrinhWeb/static/vendors/jquery/dist/jquery.min.js"></script>
	<script src="/LapTrinhWeb/static/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="/LapTrinhWeb/static/js/custom.js"></script>
</body>
</html>

