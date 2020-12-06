$(document).ready(function(){
	// 처음 가져온 본문글...
	var body = $('#body').val();
	
	$('#wrbtn').click(function(){
		// body글 읽어오고(버튼이 클릭됬을 때...)
		var tbody = $('#body').val();
		// 원글과 비고해서 처리
		if(body == tbody){
			// 이 경우는 글 내용이 수정이 안된 경우임으로 실행을 끝낸다.
			return;
		} else{
			$('#frm').submit();
		}
//		if(body == obody)
		// 폼태그 전송하고
//		$('#frm').submit();
		
	});
	
	$('#rbtn').click(function(){
		$('#body').val(body);
	});
});





















