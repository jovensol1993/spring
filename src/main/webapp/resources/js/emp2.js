$(document).ready(function(){
	$('#empbtn').click(function(){
		var empno = $(this).text(); //empbtn이 클릭이되는 값 :사원번호
		$.ajax({
			url:'/cls/emp/empProc.cls',
			type: 'POST',
			dataType: 'json',
			data: {
				eno : empno //키 , 밸류
			},
			success: function(data){
				var empno = data.empno;
				var ename = data.ename;
				var hiredate = data.hiredate;
				var mgr = data.mgr;
				var comm = data.comm;
				$('#empno').html(empno);
				$('#ename').html(ename);
				$('#hiredate').html(hiredate);
				$('#mgr').html(mgr);
				$('#comm').html(comm);
				$('#emsg').removeClass('w3-hide');
			},
			error: function(){
				alert('헤헤')
			}
			});
		});
	});

