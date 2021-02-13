


function fillPairDtoCombo(data,nomeCombo){
	//console.log('sono in fillDatiCombo');
	$('#'+nomeCombo+'').find('option').remove();
   	$('#'+nomeCombo+'').selectpicker('refresh'); 
   	$.when(
   	 $.each(data, function(key, value) {
   		 //console.log("value.descr="+value.descrizione);
   		 //console.log("value.codice="+value.codice);
         $('#'+nomeCombo+'').append('<option value="'+value.codice+'">'+value.descrizione+'</option>');
         $('#'+nomeCombo+'').selectpicker('refresh'); 
        })).done(function(){
            //part 2: select one of the values
            console.log("sono qui, ho finito");
       	  
        });
}