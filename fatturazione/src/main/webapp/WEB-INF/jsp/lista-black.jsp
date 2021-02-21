

  <%@ include file="inc/head.jsp" %>


   



<script>

$(document).ready( function () {
	 var table = $('#listaFattureTable').DataTable({
		 	"sAjaxSource": "<c:url value='/lista-all-fatture-black' />",
			"sAjaxDataProp": "",
			"order": [[0, "desc"]],
			"aoColumns": [
				{ "sTitle" : "Id Fattura",
			        "mData" : "idFattura",
			        "visible": false
			       },
				 { "sTitle" : "Cliente",
			        "mData" : "denominazioneCliente",
			        "autoWidth": true
			       },
			       { "sTitle" : "Data prestazione",
				        "mData" : "dtFattura",
				        "autoWidth": true
				       },
			       { "sTitle" : "Importo",
				        "mData" : "importoNetto",
				        "autoWidth": true
				       },
				       { "sTitle" : "Stato",
					        "mData" : "descrStato",
					        "autoWidth": true
					       },     
				{	"sTitle" : "Dettaglio",
				   	        "mData" : "idFattura",
				   	     	"autoWidth": true,
				   	      	"orderable": false,
				   	     "className": "text-center",
		   	        "mRender" : function(data, type, val) {
		   	        	return '<a href="javascript:void();" onclick="javascript:dettaglioFattura('+data+');"> <i class="fas fa-info-circle btn-sm"></i></a></td>'
		   	        }
		   	    }
			],
			
			"bFilter": true,
			"stateSave": true,
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


function dettaglioFattura(item) {
	$('#idFattura').val(item);
	$("#form-to-dettaglio-fattura" ).submit();					
	
}

</script>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <%@ include file="inc/leftMenu.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
        
         <%@ include file="inc/topbar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
         <!--  <h1 class="h3 mb-4 text-gray-800">Blank Page</h1> -->
          <%@ include file="inc/messaggi.jsp" %>
          
          <!-- Approach -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Lista prestazioni</h6>
                </div>
                <div class="card-body">
					<div class="form-group" style="width: 100%">
						<table id="listaFattureTable" class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer" style="width:100%">
					    </table>
						
					</div>
					<div class="form-group" style="width: 100%;text-align: center;">
							
							<a href="<c:url value='/nuova-fattura?tipoFattura=FT003' />" >
							  <button type="button" class="btn btn-success navbar-btn"><i class="fas fa-pencil-alt"></i> Nuova prestazione</button>
							</a>
							&nbsp;
							<a href="<c:url value='/scarica-lista-prestazioni-excel' />" >
							  <button type="button" class="btn btn-success navbar-btn"><i class="fas fa-download"></i> Scarica lista prestazioni</button>
							</a>
						
					</div>

                </div>
              </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
      
     	 <c:url var="url" value="/dettaglio-fattura" />
		<form action="${url}" method="POST" id="form-to-dettaglio-fattura">
			<input type="hidden" id="idFattura" name="idFattura" value="">
		</form>

     <%@ include file="inc/footer.jsp" %>

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  
 	<%@ include file="inc/logOutModal.jsp" %>


  
  
  
  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>
</body>

</html>
