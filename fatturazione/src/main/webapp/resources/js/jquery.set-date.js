$(function() {

	initDate();
	
});


function initDate(){
	
	$('.date').each(function() {
		console.log('sto settando gli intero');
		$(this).datepicker({
			format: "dd/mm/yyyy",
		    language: "it",
		    todayBtn: "linked",
		    clearBtn: true,
		    multidate: false,
		    calendarWeeks: true,
		    autoclose: true,
		    todayHighlight: true

		});
	});
	
	
}


