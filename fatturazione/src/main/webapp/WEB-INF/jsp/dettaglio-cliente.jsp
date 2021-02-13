

  <%@ include file="inc/head.jsp" %>


   



<script>
     $(document).ready(function() {
    	 
    	 
    	 var codProv=$('#codProv').val(); 
    	 var codCom=$('#codCom').val(); 
    	 console.log('codCom='+codCom);
    	 if(codProv!=null&&codProv!=''&&codCom!=null&&codCom!=''){
    		 
    		 $.ajax({
                 type: "GET",
                 url: "get-comuni-by-provincia",
                 data:  "codProv=" +codProv,
                 success: function(data) {
                      
                      console.log(data); 
                      
                      var dropdown = $('#codCom');
                      dropdown.empty();
                      $.each(data, function (key, entry) {
                   	    dropdown.append($('<option></option>').attr('value', entry.codCom).text(entry.descrizione));
                   	   
                   	  })
                   	  
                   	 if(codCom!=null&&codCom!=''){
                   		 console.log('codCom='+codCom);
                   		$("#codCom").val(codCom);
                	 }

                 }
            });
    		 
    	 }
    	 
    	 
          $('#codProv').on('change', function() {
        	console.log("ciaooo");
           	var value = $(this).val(); 
            $.ajax({
                 type: "GET",
                 url: "get-comuni-by-provincia",
                 data:  "codProv=" +value,
                 success: function(data) {
                      
                      console.log(data); 
                      
                      var dropdown = $('#codCom');
                      dropdown.empty();
                      $.each(data, function (key, entry) {
                   	    dropdown.append($('<option></option>').attr('value', entry.codCom).text(entry.descrizione));
                   	  })

                 }
            });
          })
          
         $('#elimina').on('click', function(event) { 
        	 console.log("sono in elimina cliente");
        	 event.preventDefault();
        	 
        	 bootbox.confirm({
      		    title: "Eliminazione cliente",
      		    message: "Vuoi eliminare il cliente ?",
      		    className: "my-modal-dialog",
      		    buttons: {
      		        cancel: {
      		            label: '<i class="fa fa-times"></i> Annulla',
      		            className: 'btn btn-secondary'
      		        },
      		        confirm: {
      		            label: '<i class="fa fa-check"></i> Procedi',
      		            className: 'btn btn-danger'
      		        }
      		    },
      		    callback: function (result) {
      			    if(result){
      			    	 $("#cliente").attr("action","delete-cliente");
      		        	 $("#cliente").submit();	
      				}
      		    }
      		});
        			
         });
          
        //Controllo la validità  del codice fiscale
      	$("#salva").on("click", function(event){
      		event.preventDefault();
      		var isVuotoCF=jQuery.isEmptyObject($("#piva").val());
      		if(!isVuotoCF){
      			if(IsCodFiscale($("#piva").val()) || IsPartitaIva($("#piva").val()) ){
      				$('#cliente').submit();
      			}else{
      				var title="Nuovo cliente";
      				var msg="Attenzione la p.iva/codice fiscale inserito non &egrave; corretto !";
      				bootbox.alert({message: msg,
      			  			title: title});
      				return false;
      			}
      		}else{
      			var title="Nuovo cliente";
      			var msg="Attenzione la p.iva/codice fiscale &egrave; obbligatorio !";
      			bootbox.alert({message: msg,
      		  			title: title});
      			return false;
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
           <%@ include file="inc/messaggi.jsp" %>
          
          <!-- Approach -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">${titoloPagina}</h6>
                </div>
                <div class="card-body">
				
				<form:form method="POST" action="add-cliente" modelAttribute="cliente" id="cliente" name="cliente">
					
				<form:hidden path="idCliente" />
				
				<spring:bind path="denominazione">
				  <div class="form-group ${status.error ? 'has-error' : ''}">
				    <form:label path="denominazione">Denominazione</form:label>
				    <form:input   type="text" class="form-control" id="denominazione" name="denominazione" path="denominazione"  aria-describedby="denominazioneHelp" placeholder="Denominazione cliente" />
				    <form:errors path="denominazione" cssClass="error forceInline"/>
				  </div>
				 </spring:bind>
				  
				  <div class="form-group">
				    <form:label path="nickname">Nickname</form:label>
				    <form:input required="true"  type="text" class="form-control" id="nickname" name="nickname" path="nickname"  aria-describedby="nicknameHelp" placeholder="nickname cliente" />
				    <form:errors path="nickname" cssClass="error forceInline"/>
				  </div>
				  
				   <div class="form-group">
				    <form:label path="piva">P.IVA/Codice fiscale</form:label>
				    <form:input required="true" type="text" class="form-control" id="piva" name="piva" path="piva" aria-describedby="pivaHelp" placeholder="P.IVA/Codice Fiscale"/>
				    <form:errors path="piva" cssClass="error forceInline"/>
				  </div>
				  
				  <div class="form-group">
				  
				   <form:label path = "codProv">Provincia</form:label>
				  
				  	<form:select required="true" class="form-control" path="codProv" >
				  		<form:option value=""></form:option>
					    <form:options items="${listaProvincie}"  itemValue="codProv" itemLabel="descrizione" />
					</form:select>
					<form:errors path="codProv" cssClass="error forceInline"/>
				 </div>
				 
				 <div class="form-group">
				  
				   <form:label path = "codCom">Comune</form:label>
				  	<form:select required="true" class="form-control" path="codCom">
					    <form:options items="${listaComuni}"  itemValue="codCom" itemLabel="descrizione"/>
					</form:select>
					<form:errors path="codCom" cssClass="error forceInline"/>
				 </div>
				  
				  
				  <div class="form-group">
				    <form:label path="indirizzo">Indirizzo</form:label>
				    <form:input path="indirizzo" required="true" type="text" class="form-control" id="indirizzo" aria-describedby="indirizzoHelp" placeholder="Indirizzo"/>
				    <form:errors path="indirizzo" cssClass="error forceInline"/>
				  </div>
				  
				  <div class="form-group">
				    <form:label path="cap">CAP</form:label>
				    <form:input path="cap" required="true" type="text" maxlength="5" class="form-control" id="cap" aria-describedby="capHelp" placeholder="CAP"/>
				    <form:errors path="cap" cssClass="error forceInline"/>
				  </div>
				
					<div class="form-group">
				    <form:label path="telefono">Telefono</form:label>
				    <form:input path="telefono" type="text" class="form-control" id="telefono" aria-describedby="telefonoHelp" placeholder="Telefono"/>
				    <form:errors path="telefono" cssClass="error forceInline"/>
				  </div>
				
				   <div class="form-group">
				    <form:label path="email">Email</form:label>
				    <form:input path="email" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="e-mail"/>
				    <form:errors path="email" cssClass="error forceInline"/>
				  </div>
				  
				
				<div class="form-group" style="text-align: center;">
				  <button type="submit" id="salva" name="salva" class="btn btn-primary"><i class="fa fa-save" aria-hidden="true"></i>&nbsp;Salva</button>
				  
				  <c:if test="${cliente.idCliente!=null}">
				  	<button type="submit" id="elimina" name="elimina" class="btn btn-danger"><i class="fas fa-trash-alt"></i>&nbsp;Elimina</button>
				  </c:if>
				  
 					<a href="<c:url value='/lista-clienti' />" >
					  <button type="button" class="btn btn-secondary"><i class="far fa-arrow-alt-circle-left"></i>&nbsp;Lista clienti</button>
					</a> 
				</div> 

				  
				</form:form>


                </div>
              </div>
              
              <c:if test="${cliente.idCliente!=null}">
              	<%@ include file="inc/listaFattureCliente.jsp" %>
              	
              	<%@ include file="inc/listaPreventiviCliente.jsp" %>
              </c:if>

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


  
  
  
  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>
</body>

</html>
