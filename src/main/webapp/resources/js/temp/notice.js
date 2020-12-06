$(document).ready(function() {
	$('.title').click(function() {
		var nno = $(this).attr('id').substring(1); // nno
		$('#nno').val(nno);
		$('#frm').attr('action', '/cls/notice/noticeDetail.cls');
		$('#frm').submit();
	});

//	삭제
	$('.delbtn').click(function(){
		var sno = $(this).attr('id').substring(1);
		$('#nno').val(sno);
		$('#frm').attr('action', '/cls/notice/noticeDel.cls');
		$('#frm').submit();
	});

// 편집버튼
	$('.editbtn').click(function(){
		var sno = $(this).attr('id').substring(1);
//		var contentDiv = $(this).siblings().find('#' + sno).children().eq(1);
//		var scontents = contentDiv.text();
		$('#nno').val(sno);
//		$('')
		$('#frm').attr('action', '/cls/notice/noticeEdit.cls');
		$('#frm').submit();
	});
	
	$('#ubtn').click(function(){
	$('#frm').attr('action', '/cls/notice/noticeEditProc.cls');
	$('#frm').submit();
	})
});