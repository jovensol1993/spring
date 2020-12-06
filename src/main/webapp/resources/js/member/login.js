$(document).ready(function(){
	$('#btn1').click(function(){
		$(location).attr('href','/cls/main.cls');
	});
	$('#btn2').click(function(){
		// To do
		// 1. 입력한 데이터 읽고
		var sid = $('#id').val(); //input tag >> val로 읽는다.
		var spw = $('#pw').val(); //input tag >> val로 읽는다.
		
		if(!(sid && spw)){
			return;
		}
		
		$('#frm').submit();
		
	});
});