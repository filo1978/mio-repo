<script>

$(document).ready( function () {
	 var table = $('#riassuntoFattureTable').DataTable({
			"sAjaxSource": "<c:url value='/riassunto-fatture' />",
			"sAjaxDataProp": "",
			"order": [[ 0, "desc" ]],
			"aoColumns": [
				{ "sTitle" : "Annualit&agrave;",
			        "mData" : "annoFattura",
			        "sWidth": "10%"
			       },
			       { "sTitle" : "Importo Lordo Fatturato",
				        "mData" : "importoLordoFatturato",
				        "sWidth": "15%"
				       },
				 { "sTitle" : "Importo Lordo Incassato",
			        "mData" : "importoLordoPagato",
			        "sWidth": "15%"
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
	 
	 
	 var table1 = $('#riassuntoFattureNonPagateTable').DataTable({
			"sAjaxSource": "<c:url value='/riassunto-fatture' />",
			"sAjaxDataProp": "",
			"order": [[ 0, "desc" ]],
			"aoColumns": [
				{ "sTitle" : "Annualit&agrave;",
			        "mData" : "annoFattura",
			        "sWidth": "10%"
			       },
			       { "sTitle" : "Importo Lordo Non Pagato",
				        "mData" : "importoLordoNonPagato",
				        "sWidth": "15%"
				       },
		       	{ "sTitle" : "Importo Netto Non Pagato",
			        "mData" : "importoNettoNonPagato",
			        "sWidth": "15%"
			       },
			       { "sTitle" : "Importo Lordo Annullato",
				        "mData" : "importoLordoAnnullato",
				        "sWidth": "15%"
				       },
		       	{ "sTitle" : "Importo Netto Annullato",
			        "mData" : "importoNettoAnnullato",
			        "sWidth": "15%"
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
	 
	 
	 
});


</script>
<div class="card shadow mb-4">
	<div class="card-header py-3" style="background-color: #ed23ed;">
		<h6 class="m-0 font-weight-bold text-primary">Fatturato</h6>
	</div>
	<div class="card-body">
		<div class="form-group" style="width: 100%">
			<table id="riassuntoFattureTable" class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer" style="width:100%">
			</table>
		</div>
	</div>
</div>
<div class="card shadow mb-4">
	<div class="card-header py-3" style="background-color: #a36fa3;">
		<h6 class="m-0 font-weight-bold text-primary">Crediti</h6>
	</div>
	<div class="card-body">
		<div class="form-group" style="width: 100%">
			<table id="riassuntoFattureNonPagateTable" class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer" style="width:100%">
			</table>
		</div>
	</div>	
</div>