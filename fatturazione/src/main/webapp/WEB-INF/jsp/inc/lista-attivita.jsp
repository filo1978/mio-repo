<script>

$(document).ready( function () {
	 var table = $('#listaAttivitaTable').DataTable({
			"sAjaxSource": "<c:url value='/lista-attivita' />",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
				 { "sTitle" : "Descrizione",
			        "mData" : "descrizione",
			        "autoWidth": true
			       },
			       { "sTitle" : "Imponibile",
				        "mData" : "importoNettoAttivita",
				        "autoWidth": true
				       },
			      
				{	"sTitle" : "Dettaglio",
				   	        "mData" : "idAttivita",
				   	     "autoWidth": true,
				   	     "className": "text-center",
				   	      	"orderable": false,
		   	        "mRender" : function(data, type, val) {
		   	        	return '<a href="javascript:void();" aria-label="visualizza dettaglio" title="visualizza dettaglio" onclick="javascript:dettaglioAttivita('+data+');"> <i class="fas fa-info-circle btn-sm"></i></a></td>'

		   	        }
		   	    }
			],
			"bFilter": true,
			"sPaginationType": "full_numbers",
			"aLengthMenu": [[5, 10, 25], [5, 10, 25]],
		    "oLanguage":{
		    	"sProcessing":   "Caricamento...",
		    	"sLengthMenu":   "Visualizza _MENU_ elementi",
		    	"sZeroRecords":  "La ricerca non ha portato alcun risultato.",
		    	"sInfo":         "Vista da _START_ a _END_ di _TOTAL_ elementi",
		    	"sInfoEmpty":    "Vista da 0 a 0 di 0 elementi",
		    	"sInfoFiltered": "(filtrati da _MAX_ elementi totali)",
		    	"sInfoPostFix":  "",
		    	"sSearch":       "Cerca:",
		    	"sUrl":          "",
		    	"oPaginate": {
		    		"sFirst":    "|&lt;&lt;",
		    		"sPrevious": "&lt;",
		    		"sNext":     "&gt;",
		    		"sLast":     "&gt;&gt;|"
		    	}
		    }
	 });  
	 
	 $("#salva-attivita").click(function(e) {
			
			e.preventDefault();
			
			resetMsg();
			setValoreNumber();
			var attivitaFormString = $("#attivitaForm").serializeObject();
			var formSerializzata=JSON.stringify(attivitaFormString);
			console.log("ecco qui="+ formSerializzata);

			$.ajax({
			    
		        type: 'POST',
		        contentType: "application/json",
		        url: "<c:url value='/salva-attivita'/>",
		        data : formSerializzata,
		        dataType: 'json',
		       
		        success: function(data){
		        	
		        	$("#attivitaModal").modal("show");
		        	
		        	if(jQuery.isEmptyObject(data.errors)){
		        		populateFormAttivita(data);
		        		changeImponibileAttivitaFattura();
		        		var table = $('#listaAttivitaTable').DataTable();
			        	table.ajax.reload();
			        	$("#msgOkDivAttivita").show();
		            	$("#msgOkAttivita").text(data.msgOk);
		            	console.log("mostrato messaggio ok");
		            	$("#attivitaModal").modal("hide");
		            	aggiornaImportiFattura(data);
		            	
		        	}else{
		        		
		        		console.log("ci sono errori...ora li mostro");
		            	$("#msgKoDivAttivita").show();
		            	$.each(data.errors, function( index, value ) {
		            		console.log("errore:"+value);
		            		 $('.error-list').append('<li>'+value+'</li>');
		            	});
		        		
		        	}
		        	
		        	
		        },
		        error:function(){
		          showGenericError();
		        }

		    });
			
		}); 
	 
	 
	 $("#elimina-attivita").click(function(e) {
			
			e.preventDefault();
			resetMsg();
			setValoreNumber();
			var attivitaFormString = $("#attivitaForm").serializeObject();
			var formSerializzata=JSON.stringify(attivitaFormString);
			console.log("ecco qui="+ formSerializzata);
			
			$.ajax({
			    
		        type: 'POST',
		        contentType: "application/json",
		        url: "<c:url value='/elimina-attivita'/>",
		        data : formSerializzata,
		        dataType: 'json',
		       
		        success: function(data){
		        	
		        	if(jQuery.isEmptyObject(data.errors)){
		        		//Se non ci sono errori devo ricaricare anche la tab correttivi in modo che ricalcoli il tutto
		        		changeImponibileAttivitaFattura();
		        		var table = $('#listaAttivitaTable').DataTable();
			        	table.ajax.reload();
		        		$("#attivitaModal").modal("hide");
		        		console.log('elimina e data.daAggiornareFattura='+data.daAggiornareFattura);
		        		aggiornaImportiFattura(data);
		            }else{
		            	$("#msgKoDivAttivita").show();
		            	$.each(data.errors, function( index, value ) {
		            		 $('.error-list').append('<li>'+value+'</li>');
		            	});
			        }
		        },
		        error:function(){
		          showGenericError();
		        }
		
		    });
			
		});

	 
	 
});



function resetMsg(){
	
	$("#msgOkDivAttivita").hide();
	$("#msgKoDivAttivita").hide();
	$('.error-list').empty();
}


function dettaglioCliente(item) {
	$('#idCliente').val(item);
	$("#form-to-dettaglio-cliente" ).submit();					
	
}

function changeImponibileAttivitaFattura(){
	var imponibileAttivita=$('#imponibileAttivita').val();
	var importoNettoAttivita=$('#importoNettoAttivita').val();
	console.log("imponibileAttivita)="+imponibileAttivita);
	console.log("importoNettoAttivita)="+importoNettoAttivita);
}

function emptyAttivita(){
		//console.log("sono qui in emptyEsito");
		$.ajax({
		    
	        type: 'GET',
	        url: "<c:url value='/empty-attivita'/>",
	        contentType: "application/json",
			dataType: 'json',
	        success: function(data){
	        	resetMsg();
	        	console.log(data);
	        	populateFormAttivita(data); 
	        	$("#attivitaModal").modal("show");
	        },
	        error:function(){
	          showGenericError();
	        }

	    });	
	}
	
function dettaglioAttivita(item) {
	console.log("item="+item);
	$.ajax({
	    
        type: 'GET',
        url: "<c:url value='/dettaglio-attivita'/>",
        data: {"idAttivita": item },
        dataType: 'json',
       
        success: function(data){
        	resetMsg();
        	console.log(data);
        	populateFormAttivita(data);
        	//$("#elimina-agriturismo").css("visibility", "visible");
           	$("#attivitaModal").modal("show");
	 
        },
        error:function(){
          showGenericError();
        }

    });			
	
}

function populateFormAttivita(data){
	
	
		$('#attivitaForm').populate(data);
		var idAttivita=Number(data.idAttivita);
		console.log("valore idAttivita="+idAttivita);
		if(idAttivita!=-1){
			console.log("mostro bottone elimina");
			$("#elimina-attivita").show();
			
		}else{
			console.log("nascondo bottone elimina");
			$("#elimina-attivita").hide();
		}
	    console.log("finito populate attivta");
}

function showGenericError(){
	$("#msgKoDivAttivita").show();
}

</script>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Lista Attività</h6>
	</div>
	<div class="card-body">
		<div class="form-group">
			<div class="form-group" style="width: 100%">
				<table id="listaAttivitaTable"
					class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer"
					style="width: 100%">
				</table>
			</div>
		</div>
		
		<div class="form-group" style="text-align: center;">
			<button  id="nuovaAttivitaButton"  name="nuovaAttivitaButton" type="button" class="btn btn-success navbar-btn"   
				 aria-label="Nuova attività"  title="Nuova attività"
				 onclick="javascript:emptyAttivita();">
					<i class="fas fa-pencil-alt"></i> &nbsp;Nuova attività
				</button>
		</div>
	</div>
</div>