function readJSON(text) { 
	
		 /************************************************** 
		 * Lấy dữ liệu sản phẩm theo danh mục từ file JSON.*
		 * Sử dụng JQUERY.                                 *
		 * ************************************************/
	
		$.getJSON('/LapTrinhWeb/view/shop/myJSON.json', function(data){
            var productSwapperHtml = '', productHtml = '', keyHang='Tất cả', keyTien = 'Tất cả';
            var classD = 'product-detail';
            var classB = 'product-box';
            var productSwapper = [], product = [];
            $.each(data, function(index, element){
           	 if (element.danhMuc===text) {
           		 	productSwapperHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
           		 	productSwapper.push(element);
                }
            });
            $('.product-swapper').html(productSwapperHtml);
            $('.id-product').hide();
            console.log(productSwapper);
            
          	  /*********************************
          	  * Lọc dữ liệu theo giá sản phẩm **
          	  * *******************************/
            $('.a').on("click",function(){
           	 keyHang = $(this).html();
           	 var productHtml = '';
           	 var product = [];
           	 if(keyHang === 'Tất cả' && keyTien ==='Tất cả'){
           		 $('.product-swapper').html(productSwapperHtml);
                    console.log(productSwapper);
                    $('.id-product').hide();
                    $('.a').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 	$(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	 $('.add-to-cart').on('click',function(e){
                	 e.stopPropagation();
                	 $('#thong-bao-them').html('');
                	 $('#thong-bao-them').removeClass('show');
                	 var  id = $(this).siblings('.id-product').html();
                	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
                		 $.each(data, function(index, element){
                			 if (element.idPro==id) {
                				 $('#thong-bao-them').html(ketqua);
                				 $('#name-product-td').html('Tên: '+element.namePro);
                				 $('#gia-product-td').html('Giá: ' +element.gia);
                				 checkCookie(id);
                				 $('#so-luong-items').html(getSoLuong());
                				 return false;
                			 }
                		 });
                	 });
                	setTimeout(function(){
                		//$('#thong-bao-them').addClass('show');
                		$('#thong-bao-them').addClass('show');
                	}, 2000);
                 });
           	 }else if(keyHang !== 'Tất cả' && keyTien ==='Tất cả'){
           		 $.each(productSwapper, function(index, element){
                   	 if (element.hang===keyHang) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
           		 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	$('.id-product').hide();
               	 }
           		 $('.a').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
	               			 checkCookie(id);
	           				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
           	 }else if(keyHang === 'Tất cả' && keyTien !=='Tất cả'){
           		 if(keyTien==='Dưới 6 triệu'){
               		 var min = 0, max = 6000000;
               	 }else if(keyTien==='Từ 6 đến 10 triệu'){
               		 var min = 6000000, max = 10000000;
               	 }else if(keyTien==='Từ 10 đến 20 triệu'){
               		 var min = 10000000, max = 20000000;
               	 }else if(keyTien==='Trên 20 triệu'){
               		 var min = 20000000, max = 100000000000;
               	 }
           		 $.each(productSwapper, function(index, element){
               		 var gia = parseInt(element.giaPro);
               		 console.log(gia);
                   	 if (gia<max && gia>min) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
               	 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	$('.id-product').hide();
               	 }
               	 $('.a').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	 
               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
	               			 checkCookie(id);
	           				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
               	 
           	 }else if(keyHang !=='Tất cẩ' && keyTien !== 'Tất cả'){
           		 if(keyTien==='Dưới 6 triệu'){
               		 var min = 0, max = 6000000;
               	 }else if(keyTien==='Từ 6 đến 10 triệu'){
               		 var min = 6000000, max = 10000000;
               	 }else if(keyTien==='Từ 10 đến 20 triệu'){
               		 var min = 10000000, max = 20000000;
               	 }else if(keyTien==='Trên 20 triệu'){
               		 var min = 20000000, max = 100000000000;
               	 }
           		 $.each(productSwapper, function(index, element){
               		 var gia = parseInt(element.giaPro);
               		 console.log(gia);
                   	 if (gia<max && gia>min && element.hang===keyHang) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
               	 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	$('.id-product').hide();
               	 }
               	 $('.a').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	 
               	 
               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
               				 checkCookie(id);
            				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
               	 
           	 }
        	 });
            
              /*********************************
         	  * Lọc dữ liệu theo giá sản phẩm **
         	  * *******************************/
            
            $('.b').on("click",function(){
           	 keyTien = $(this).html();
           	 var productHtml = '';
           	 var product = [];
           	 if(keyHang === 'Tất cả' && keyTien ==='Tất cả'){
           		 $('.product-swapper').html(productSwapperHtml);
           		 	$('.id-product').hide();
                    console.log(productSwapper);
                    $('.b').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 	$(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
           	 }else if(keyHang !== 'Tất cả' && keyTien ==='Tất cả'){
           		 $.each(productSwapper, function(index, element){
                   	 if (element.hang===keyHang) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
           		 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	 $('.id-product').hide();
               	 }
           		 $('.b').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
               				 checkCookie(id);
               				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
           	 }else if(keyHang === 'Tất cả' && keyTien !=='Tất cả'){
           		 if(keyTien==='Dưới 6 triệu'){
               		 var min = 0, max = 6000000;
               	 }else if(keyTien==='Từ 6 đến 10 triệu'){
               		 var min = 6000000, max = 10000000;
               	 }else if(keyTien==='Từ 10 đến 20 triệu'){
               		 var min = 10000000, max = 20000000;
               	 }else if(keyTien==='Trên 20 triệu'){
               		 var min = 20000000, max = 100000000000;
               	 }
           		 $.each(productSwapper, function(index, element){
               		 var gia = parseInt(element.giaPro);
               		 console.log(gia);
                   	 if (gia<max && gia>min) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
               	 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	 $('.id-product').hide();
               	 }
               	 $('.b').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");
               	 
               	 
               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
	               			 checkCookie(id);
	           				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
               	 
           	 }else if(keyHang !=='Tất cẩ' && keyTien !== 'Tất cả'){
           		 if(keyTien==='Dưới 6 triệu'){
               		 var min = 0, max = 6000000;
               	 }else if(keyTien==='Từ 6 đến 10 triệu'){
               		 var min = 6000000, max = 10000000;
               	 }else if(keyTien==='Từ 10 đến 20 triệu'){
               		 var min = 10000000, max = 20000000;
               	 }else if(keyTien==='Trên 20 triệu'){
               		 var min = 20000000, max = 100000000000;
               	 }
           		 $.each(productSwapper, function(index, element){
               		 var gia = parseInt(element.giaPro);
               		 console.log(gia);
                   	 if (gia<max && gia>min && element.hang===keyHang) {
                   		 productHtml += '<div class="'+classB+'"><img src="/LapTrinhWeb/static/images/'+element.imgPro+'"/><div class="'+classD+'"><h4><a href = "/LapTrinhWeb/chi-tiet?id='+element.idPro+'">'+element.namePro+'</a></h4><p class="id-product">'+element.idPro+'</p><p class = "add-to-cart"><a>Add to Cart</a></p><h5>'+element.gia+'</h5></div></div>';
                   		 product.push(element);
                        }
                    });
               	 if(product.length==0){
               		 $('.product-swapper').html('</br></br>Không có sản phẩm nào');
               	 }else{
               	 	 $('.product-swapper').html(productHtml);
               	 	 $('.id-product').hide();
               	 }
               	 $('.b').css("list-style-image", "url(/LapTrinhWeb/static/images/checkbox.png)");
               	 $(this).css("list-style-image", "url(/LapTrinhWeb/static/images/checkboxCheck.png)");


               	$('.add-to-cart').on('click',function(e){
               	 e.stopPropagation();
               	 $('#thong-bao-them').html('');
               	 $('#thong-bao-them').removeClass('show');
               	 var  id = $(this).siblings('.id-product').html();
               	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
               		 $.each(data, function(index, element){
               			 if (element.idPro==id) {
               				 $('#thong-bao-them').html(ketqua);
               				 $('#name-product-td').html('Tên: '+element.namePro);
               				 $('#gia-product-td').html('Giá: ' +element.gia);
	               			 checkCookie(id);
	           				 $('#so-luong-items').html(getSoLuong());
               				 return false;
               			 }
               		 });
               	 });
               	setTimeout(function(){
               		//$('#thong-bao-them').addClass('show');
               		$('#thong-bao-them').addClass('show');
               	}, 2000);
                });
           	 }
        	 });
            //add to cart
            $('.add-to-cart').on('click',function(e){
            	 e.stopPropagation();
            	 $('#thong-bao-them').html('');
            	 $('#thong-bao-them').removeClass('show');
            	 var  id = $(this).siblings('.id-product').html();
            	 $.get('/LapTrinhWeb/view/shop/thongDiem.html', function(ketqua){
            		 $.each(data, function(index, element){
            			 if (element.idPro==id) {
            				 id = parseInt(id);
            				 $('#thong-bao-them').html(ketqua);
            				 $('#name-product-td').html('Tên: '+element.namePro);
            				 $('#gia-product-td').html('Giá: ' +element.gia);
            				 checkCookie(id);
            				 $('#so-luong-items').html(getSoLuong());
            				 return false;
            			 }
            		 });
            	 });
            	setTimeout(function(){
            		//$('#thong-bao-them').addClass('show');
            		$('#thong-bao-them').addClass('show');
            	}, 2000);
             });
            
            /************************************
             * Hiển thị danh sách sản phẩm theo *
             * 3 style khác nhau.				*
             * **********************************/
            
            $('#button-list').on('click',function(){
            	  //$('.product-swapper div').css({"height": "250px","width": "750px","text-align": "left","padding":"10px 30px","cursor":"default"});
            	  $('.product-swapper div').removeClass('product-box');
            	  $('.product-swapper div').removeClass('product-box-after-after');
            	  $('.product-swapper div').addClass('product-box-after');
            	  $('.product-swapper div div').removeClass('product-detail');
            	  $('.product-swapper div div').removeClass('product-detail-after-after');
            	  $('.product-swapper div div').removeClass('product-box-after');
            	  $('.product-swapper div div').addClass('product-detail-after');
            	  classD='product-detail-after';
            	  classB='product-box-after';
            });
            $('#button-grid').on('click',function(){
            	//$('.product-swapper div').css({"height": "auto","width": "250px","text-align": "center","padding":"0px","cursor":"pointer"});
            	$('.product-swapper div').removeClass('product-box-after');
            	$('.product-swapper div').removeClass('product-box-after-after');
          	  	$('.product-swapper div').addClass('product-box');
            	$('.product-swapper div div').removeClass('product-detail-after');
            	$('.product-swapper div div').removeClass('product-detail-after-after');
            	$('.product-swapper div div').removeClass('product-box');
            	$('.product-swapper div div').addClass('product-detail');
            	classD='product-detail';
            	classB='product-box';
            });
            $('#button-style').on('click', function(){
            $('.product-swapper div').removeClass('product-box');
            $('.product-swapper div').removeClass('product-box-after');
          	  $('.product-swapper div').addClass('product-box-after-after');
          	  $('.product-swapper div div').removeClass('product-detail');
          	  $('.product-swapper div div').removeClass('product-detail-after');
          	  $('.product-swapper div div').removeClass('product-box-after-after');
          	  $('.product-swapper div div').addClass('product-detail-after-after');
          	  classD='product-detail-after-after';
          	  classB='product-box-after-after';
            });
        });
		
}
function chiTietSP(idProduct) {
	$.getJSON('/LapTrinhWeb/view/shop/myJSON.json',function(data){
		$.each(data, function(index, value) {
			
			if(value.idPro==idProduct){
				$('.id-product').html(value.idPro);
				$('.id-product').hide();
				$('.danh-muc-product').html(value.danhMuc);
				$('.name-product').html(value.namePro);
				$('.gia-product').html(value.gia);
				$('#img-product img').attr('src','/LapTrinhWeb/static/images/'+value.imgPro);
				$('.link-danh-muc').attr('href','/LapTrinhWeb/'+value.danhMuc);
				return false;
			}
		});
	});
}
function checkCookie(id){
	if(document.cookie.length>0){
		var ktr = false;
		var cookie = document.cookie;
		var arrCookie = cookie.split(';');
		for(var i = 0; i<arrCookie.length; i++){
			var temp = arrCookie[i].split('=');
			var nameC = parseInt(temp[0]);
			console.log(nameC);
			var value = parseInt(temp[1]);
			if(id == nameC){
				addCookie(nameC,value+1);
				ktr = true;
				break
			}
		}
		if(ktr==false){
			addCookie(id, 1);
		}
	}else{
		addCookie(id, 1);
	}
}
function addCookie(name, value){
	document.cookie = name +'='+value; 
}
function getSoLuong(){
	var cookie = document.cookie;
	var arrCookie = cookie.split(';');
	var sum = 0;
	if(arrCookie.length==0){
		return 0;
	}else{
		for(var i = 0; i<arrCookie.length; i++){
			var temp = arrCookie[i].split('=');
			var value = parseInt(temp[1]);
			sum += value;
		}
		return sum;
	}
}
function showGioHang(){
	var cookie = document.cookie;
	var arrCookie = cookie.split(';');
	var productHtml = '<tr><th colspan="2">Sản phẩm</th><th>Số lượng</th><th>Xóa</th></tr>';
	var product = [];
	$.getJSON('/LapTrinhWeb/view/shop/myJSON.json', function(data){
		for(var i=0 ;i< arrCookie.length; i++){
			var temp= arrCookie[i].split('=');
			var idProduct = parseInt(temp[0]);
			var soLuong = parseInt(temp[1]);
			$.each(data, function(index, element){ 	
				var idPro = parseInt(element.idPro);
				if(element.idPro==idProduct){
					productHtml += '<tr><td><img class="img-pro-gio-hang" src="/LapTrinhWeb/static/images/'+element.imgPro+'"></td><td>'+element.namePro+'</td><td>'+soLuong+'</td><td><img class="xoa-pro" src="/LapTrinhWeb/static/images/bin.png"></td></tr>';
					product.push(element);
					return false;
				}
			});
		}
		$('#show-gio-hang').html(productHtml);
		console.log(product);
		$('.button-tt').on('click',function(){
			alert('Thanh toán thành công! Cảm ơn khách hàng.')
		});
	});
}