$(document).ready(function(){
	$('.btnBox').click(function(){
		var tid = $(this).attr('id')
		var target = '';
		
		switch(tid){
			case 'hbtn':
				target = '/cls/main.cls';
				break;
			case 'lbtn':
				// 로그인버튼
				target = '/cls/member/login.cls'
				break;
				// 회원가입
			case 'jbtn': 
				target = '/cls/member/join.cls'
				break;
				// 로그아웃버튼
			case 'obtn': 
				target = '/cls/member/logout.cls'
				break;
				// 리셋버튼
			case 'rbtn': 
				$('#body').val('');
				return;
				break;
			case 'wbtn':
				// 글 등록버튼
				$('#frm').attr('method','POST');
				$('#frm').attr('action','/cls/guestBoard/gBoardWrite.cls');
				// 데이터 읽어오고
				var txt = $('#body').val();
				if(!txt){
					$('#body').focus();
					return;
				}
				
				$('#frm').submit();
				return;
				break;				
		}
		$(location).attr('href', target);
	});
	
	$('.pagebtn').click(function(){
		var str = $(this).text();
		
		alert('str : ' + str);
		
		var sPage = '';
		if(str == 'pre'){
			sPage = $(this).attr('id');
		} else if(str == 'next'){
			sPage = $(this).attr('id');
		} else {
			sPage = str;
		}
		
		/*
		//1. GET 방식 전송
		$(location).attr('href', '/cls/guestBoard/gBoardList.cls?nowPage=' + sPage);
		*/
		
		
		//2 POST 방식 전송(form 태그로 전송)
		// 파라미터 셋팅 부터 하고
		$('#nowPage').val(sPage);
		// form 태그 전송하고
		$('#gfrm').submit();
	});
});

























