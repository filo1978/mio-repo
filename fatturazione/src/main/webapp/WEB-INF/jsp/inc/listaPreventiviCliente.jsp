<script>

$(document).ready( function () {
	 var table = $('#listaPreventiviTable').DataTable({
			"sAjaxSource": "<c:url value='/lista-all-bozze-cliente' />",
			"sAjaxDataProp": "",
			"order": [[ 0, "desc" ]],
			"aoColumns": [
				
			       { "sTitle" : "Mese",
				        "mData" : "descrMese",
				        "autoWidth": true
				    },
				    { "sTitle" : "Attività",
				        "mData" : "attivita",
				        "autoWidth": true
				    },   
				    { "sTitle" : "Importo",
				        "mData" : "importoAttvita",
				        "autoWidth": true
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
	 })
});


</script>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Lista Bozze</h6>
	</div>
	<div class="card-body">
		<div class="form-group">
			<table id="listaPreventiviTable"
				class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer"
				style="width: 100%">
			</table>
		</div>
		
	</div>
</div>