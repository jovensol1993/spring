$(document).ready(function(){
	$('.pbtn').click(function(){
		// 어떤 버튼이 클릭되었는지 알아낸다.
		var str = $(this).text(); 
		/*var= &raquo */
		
		/* id value 가 없는 태그일 경우*/
		var sno = $(this).attr('id');
		
		if(!sno){
			sno = str;
		}
		$('#pfrm').attr('method', 'POST');
		$('#pfrm').attr('action', '/cls/reBoard/reBoardList.cls');
		$('#pfrm').prepend('<input type="hidden" name="nowPage" value="' + sno + '">' );
		$('#pfrm').submit();
	});
	
		
		$('.pagebtn').click(function(){
		// 어떤 버튼이 클릭되었는지 알아낸다.
		var str = $(this).text();
		 
		var sno = $(this).attr('id');
		
		if(!sno){
			// sno가 null 또는 undefined 인 경우는 페이지 번호 버튼이다.
			sno = $(this).text();
		}
		
		$('#nowPage').val(sno);
		$('#pfrm').submit();
	});
		

	$('.btnBox').click(function(){
		var tid= $(this).attr('id');
		switch(tid){
		case 'hbtn':
			url = '/cls/main.cls';
			break;
		case 'lbtn':
			url = '/cls/member/login.cls';
			break;
		case 'jbtn':
			url = '/cls/member/join.cls';
			break;
		case 'obtn':
			url = '/cls/member/logout.cls';
			break;
		}
		
		$(location).attr('href', url);
	});
	
	$('#mcbtn').click(function(){
		$('#wmodal').css('display', 'none');
	});
	
	// 글쓰기 버튼 이벤트 처리
	$('#wbtn').click(function(){
		// 버튼 내용 변경
		$('#body').val('');
		$('#wrbtn').html('글등록');
		$('#wmodal').css('display', 'block');
	});
	
	// 글삭제버튼 이벤트 처리
	$('.dbtn').click(function(){
		var str = $(this).attr('id');
		
		var tno = str.substring(1);
		
		$('#dbno').val(tno); // dbno에 data 채우기 작업
		$('#frm1').submit();
		
		
	});
	
	// 게시글 수정 이벤트 처리
	$('.ebtn').click(function(){
		/*
		// 모달창 사용해서 처리하는 방법
		// 버튼 내용 변경
		$('#wrbtn').html('edit');
		
		
		var tno = $(this).attr('id').substring(1); // 앞에 d or e 빼고 가져오려고
		
		// 수정할 글번호 기억시켜놓고
		$('tno').val(tno);
		
		// 수정할 내용 읽어오고
		var tbody = $(this).parent().siblings().eq(0).html().replace('<br>', '\r\n'); // 형제태그중 0번째 인덱스 태그 선택
	
		// 내용 입력태그에 입력하고
		$('#body').val(tbody);
		$('#wmodal').css('display', 'block');
		*/
		
		// 새로운 뷰를요청해서 처리하는 방법
		// 데이터 입력태그에 셋팅하고
		// 글번호 셋팅 
		var tno = $(this).attr('id').substring(1); // 앞에 d or e 빼고 가져오려고
		$('#tno').val(tno);
		// 글내용 셋팅
		var tbody = $(this).parent().siblings().eq(0).html().replace('<br>', '\n'); // 형제태그중 0번째 인덱스 태그 선택
		$('#body').val(tbody);
		
//		$(location).attr('href' , '/cls/reBoard/reBoardEditView.cls');
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardEditView.cls');
		$('#frm').submit();
	});
	
	$('#rbtn').click(function(){
		$('#body').val('');
	});
	
	$('#wrbtn').click(function(){
		// 수정인지 등록인지 구분
		var str = $(this).text();
		
		var txt = $('#body').val();
		var url = '/cls/reBoard/reBoardWriteProc.cls';
		
		if(str != 'edit'){
			if(!txt){
				alert('# 메세지를 입력하세요!');
				return;
			}
		} else {
			url = '/cls/reBoard/reBoardEditProc.cls';
		}
		
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', url);
		$('#frm').submit();
	});
	
	$('.rebtn').click(function(){
		 var tno = $(this).attr('id'); 
		$('#tno').val(tno);
		
		//form 태그 속성 세팅
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardComment.cls');
		$('#frm').submit();
		
	});
});





















