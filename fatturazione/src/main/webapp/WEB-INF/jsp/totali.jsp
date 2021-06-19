

  <%@ include file="inc/head.jsp" %>


   



<script>
</script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->

<script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <!-- <script src="js/demo/chart-pie-demo.js"></script> -->

<script>

function getSum(numberOfColumn, api){

	var intVal = function ( i ) {
        return typeof i === 'string' ?
            i.replace(/[\$,]/g, '')*1 :
            typeof i === 'number' ?
                i : 0;
    };
	
	return	api
	    .column(numberOfColumn)
	    .data()
	    .reduce( function (a, b) {
	    	return intVal(a) + intVal(b);
		}, 0 );
}

$(document).ready( function () {
	 var table = $('#riassuntoFattureTable1').DataTable({
		 
		 "footerCallback": function ( row, data, start, end, display ) {
	         var api = this.api(), data;
			
	 		console.log("sono qui dentro");
	 		 
	 		let totale = getSum(4, api);
	 		console.log("totale="+totale);
	 		//$( api.column( 4 ).footer() ).html(totale);
		 
		 },
		 
			"sAjaxSource": "<c:url value='/lista-totali' />",
			"sAjaxDataProp": "",
			"order": [[ 0, "desc" ]],
			"aoColumns": [
				{ "sTitle" : "Id Fattura",
			        "mData" : "idFattura",
			        "visible": false
			       },
				{ "sTitle" : "Tipo",
			        "mData" : "tipo",
			        "autoWidth": true
			       },
			       { "sTitle" : "Cliente",
				        "mData" : "cliente",
				        "sWidth": "15%"
				       },
				 { "sTitle" : "Data",
			        "mData" : "data",
			        "autoWidth": true
			       },
					 { "sTitle" : "Importo",
				        "mData" : "importo",
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
	 });
})


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
          
          
          <!-- Approach -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                 <div class="card shadow mb-4">
						<div class="card-header py-3" style="background-color: #ed23ed;">
							<h6 class="m-0 font-weight-bold text-primary">Totali</h6>
						</div>
						<div class="card-body">
							<div class="form-group" style="width: 100%">
								<table id="riassuntoFattureTable1" class="table table-condensed table-bordered table-striped volumes center-all hover dataTable no-footer" style="width:100%">
								<!--<tfoot>
						            <tr>
						                <th colspan="4" style="text-align:right">Totale:</th>
						                <th></th>
						            </tr>
						        </tfoot>-->
								</table>
							</div>
							<div class="form-group" style="width: 100%;text-align: center;">
								<a href="<c:url value='/scarica-totali-excel' />" >
								  <button type="button" class="btn btn-success navbar-btn"><i class="fas fa-download"></i> Scarica totali</button>
								</a>
							</div>
						</div>
					</div>
                </div>
              </div>
        	
          </div>  
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

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


  
  
  

</body>

</html>
