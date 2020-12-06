$(document).ready(function(){
	$('.btn').click(function(){
		var eno = $(this).attr('id');
//		print(eno);
		$('#empno').val(eno);
		$('#emp').submit();
	});
	
});

