<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" charset="utf8" src="/LapTrinhWeb/static/js/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.add-to-cart').click(function(){
			$.ajax({
				url:'http://localhost:8080/LapTrinhWeb/test',
	            data:{name:'abc'},
	            type:'GET',
	            success:function(data){
	               alert(data);
	            },
	            error:function(){
	              alert('error');
	            }
		});
	});
});
</script>
</head>
<body>
	<input type="button" class="add-to-cart"></a>
</body>
</html>