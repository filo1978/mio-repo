$(function() {

	initNumber();
	
});


function initNumber(){
	
	$('.intero').each(function() {
		console.log('sto settando gli intero');
		$(this).autoNumeric('init', {
			mDec: "0",
			aSep: ''
		});
	});
	
	$('.numero4Decimali').each(function() {
		console.log('sto settando i numero4Decimali');
		$(this).autoNumeric('init', {
			aSep: '.', 
			aDec: ',', 
			mDec: "4"
		});
	});
	
	$('.numero').each(function() {
		console.log('sto settando i numeri');
		$(this).autoNumeric('init', {
			aSep : '.',
			aDec : ',',
			mDec : "2",
			vMin : '-999999999'
		});
	});

	$('.euro').each(function() {
		console.log('sto settando gli euri');
		$(this).autoNumeric('init', {
			aSign : ' \u20AC',
			pSign : 's',
			aSep : '.',
			aDec : ',',
			vMin : '0'
		});

	});
	
	$('.euroNegativo').each(function() {
		console.log('sto settando gli euri');
		$(this).autoNumeric('init', {
			aSign : ' \u20AC',
			pSign : 's',
			aSep : '.',
			aDec : ',',
			aNeg:'-', 
			vMin: '-99999999'
		});

	});

	$('.percentuale').each(function() {
		console.log('sto settando le percentuali');
		$(this).autoNumeric('init', {
			aSign : ' %',
			pSign : 's',
			aSep : '.',
			aDec : ',',
			mDec : "2",
			vMin : '0.00',
			vMax : '100.00'
		});

	});
}

function setValoreNumber() {
	$('.intero').each(function() {
		$(this).val($(this).autoNumeric("get"));
	});
	$('.numero').each(function() {
		$(this).val($(this).autoNumeric("get"));
	});
	$('.euro').each(function() {
		$(this).val($(this).autoNumeric("get"));
	});
	$('.euroNegativo').each(function() {
		$(this).val($(this).autoNumeric("get"));
	});
	$('.percentuale').each(function() {
		$(this).val($(this).autoNumeric("get"));
	});
}

function setNumber(){
	 $('.intero').each(function(){
			
			if($(this).val().length != 0){
				console.log("setto valore="+$(this).val());
				$(this).autoNumeric('set', $(this).val());
			}
	  });
	  $('.numero').each(function(){
			
			if($(this).val().length != 0){
				console.log("setto valore="+$(this).val());
				$(this).autoNumeric('set', $(this).val());
			}
	  });
	  $('.euro').each(function(){
			
			if($(this).val().length != 0){
				console.log("setto euro="+$(this).val());
				$(this).autoNumeric('set', $(this).val());
			}
	  });
	  $('.euroNegativo').each(function(){
			
			if($(this).val().length != 0){
				console.log("setto euro negativo="+$(this).val());
				$(this).autoNumeric('set', $(this).val());
			}
	  });
	  $('.percentuale').each(function(){
			
			if($(this).val().length != 0){
				console.log("setto percentuale="+$(this).val());
				$(this).autoNumeric('set', $(this).val());
			}
	});
}
