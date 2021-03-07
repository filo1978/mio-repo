

  <%@ include file="inc/head.jsp" %>


   



<script>
     $(document).ready(function() {
    	 initDate();
    	 initNumber();
    	 gestisciDatiFattura( $('#codTipo').val());
    	 
          
    	 $('#codTipo').on('change', function() {
          	 var codTipoSelezionato = $(this).val(); 
             console.log("codTipoSelezionato="+codTipoSelezionato);
             gestisciDatiFattura(codTipoSelezionato);
             //Perchè gli do sempre la possibilità di tornare in dietro in stato bozza
             $("#codTipoDiv").show();
     	 });
    	 
    	 $('#stampa-fattura').click(function(e) {
    		 e.preventDefault();
    		 var urlStampa =$(this).attr('href');
    		 $.ajax({
			    
		        type: 'GET',
		        contentType: "application/json",
		        url: "<c:url value='/get-totale-attivita'/>",
		       // data : { sezione : sezioneToolTip,codFase:fase},
		        success: function(data){
		        	console.log("data.toCheck="+data.toCheck);
		        	console.log("data.totaleAttivita="+data.totaleAttivita);
		        	if(data.toCheck){
		        		var importoNettoFattura=$("#importoNetto").autoNumeric("get");
		        		console.log("importoNettoFattura="+importoNettoFattura);
		        		if(data.totaleAttivita!=importoNettoFattura){
		        			
		        			bootbox.alert("Attenzione il totale imponibile delle attività ("+data.totaleAttivita+") è diverso dall'imponibile della fattura ("+importoNettoFattura+")");
		        			
		        		}else{
		        		
			    		    location.href = urlStampa;
		        			
		        		}
		        		
		        	}else{
		        	
		    		    location.href = urlStampa;
		        		
		        	}
		        	
		        },
		        error:function(){
			        
		        }
			});

    	 });
    	 
    	 $( "#fattura" ).on( "submit", function() { 
    		  	$("#codTipo").attr("disabled", false);
    		  	/*if($("#idBollo").val()!=null){
    		  		$("#idBollo").val($("#idBollo").autoNumeric("get"));	
    		  	}*/
    		 	$("#importoNetto").val($("#importoNetto").autoNumeric("get"));
    	 		$("#iva").val($("#iva").autoNumeric("get"));
    	 	});  
    	 
    	 $('#elimina').on('click', function(event) { 
        	 console.log("sono in elimina fattura");
        	 event.preventDefault();

        	 bootbox.confirm({
     		    title: "Eliminazione fattura",
     		    message: "Vuoi eliminare la fattura ?",
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
     			    	 $("#fattura").attr("action","delete-fattura");
     		        	 $("#fattura").submit();	
     				}
     		    }
     		});
        	 
        		
      	});
    	 
    	 
    	 
    	 $("#importoNetto").change(function(){
			
 			calcolaImportoLordo();
 	 	});
    	 
    	 $("#iva").change(function(){

  			calcolaImportoLordo();
  	 	});
     
    
     })
     
     
     function aggiornaImportiFattura(data){
		console.log("valore daAggiornareFattura="+data.daAggiornareFattura);
		if(data.daAggiornareFattura){
			console.log("valore importoFattura="+data.importoFattura);
			console.log("Number valore importoFattura="+Number(data.importoFattura));
			$("#importoNetto").autoNumeric('set', Number(data.importoFattura));
			calcolaImportoLordo();
		}
	}
     
     function calcolaImportoLordo(){
    	var importoNetto= $("#importoNetto").autoNumeric("get");
    	var iva= $("#iva").autoNumeric("get");
    	console.log("importoNetto="+importoNetto);
    	console.log("iva="+iva);
    	if(importoNetto!=null&&iva!=null){
    		var valoreIva=(importoNetto*(iva/100));
    		console.log("valoreIva="+valoreIva);
    		var importoLordo=Number(importoNetto)+Number(valoreIva);
    		console.log("importoLordo="+importoLordo);
    		$("#importoLordo").autoNumeric('set', importoLordo);
    	}
     }
     
    
     function listaFatture() {
		$("#form-to-dettaglio-cliente" ).submit();					
	}
     
     
     
     function gestisciDatiFattura(codTipo){
    	 console.log("codTipo="+codTipo);
    	 if(codTipo=='FT002'){
    		//Se bozza
    		$("#dtFatturaDiv").hide();
    		$("#dtFattura").prop('required',false);
    		
    		$("#flagPagatoDiv").hide();
    		
    		$("#imponibileDiv").show();
    		$("#importoNetto").prop('required',false);
    		$("#importoNetto").attr('readonly', 'readonly');
    		
    		$("#ivaDiv").hide();
    		$("#iva").prop('required',false);
    		
    		$("#importoLordoDiv").hide();
    		
    		$("#idBolloDiv").hide();
    		$("#idBollo").prop('required',false);
    		
    		$("#codTipoDiv").show();
         	
         	$("#meseDiv").show();
         }else if(codTipo=='FT003'){
     		//Se black
     		$("#dtFatturaDiv").show();
     		$("#dtFattura").prop('required',true);
     		
     		$("#flagPagatoDiv").show();
     		
     		$("#importoNetto").prop('required',true);
     		
     		$("#ivaDiv").hide();
     		
     		$("#importoLordoDiv").hide();
     		
     		$("#idBolloDiv").hide();
     		$("#idBollo").prop('required',false);
     		
     		$("#codTipoDiv").hide();
          	
          	$("#meseDiv").hide();
          }else{
        	 //Se fattura
        	 $("#dtFatturaDiv").show();
        	 $("#dtFattura").prop('required',true);
        	 
        	 $("#flagPagatoDiv").show();
        	 
        	 $("#imponibileDiv").show();
        	 $("#importoNetto").prop('required',true);
        	 $("#importoNetto").removeAttr('readonly');
        	 
        	 $("#ivaDiv").show();
        	 $("#iva").prop('required',true);
        	 
        	 $("#importoLordoDiv").show();
        	 
        	 $("#idBolloDiv").show();
        	 $("#idBollo").prop('required',true);
        	 
        	 $("#codTipoDiv").hide();
        	 
        	 $("#meseDiv").hide();
         }
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
         
          
         <form action="dettaglio-cliente" method="POST" id="form-to-dettaglio-cliente">
			<input type="hidden" id="idCliente" name="idCliente" value="${cliente.idCliente}">
		</form>
          
          <!-- Approach -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">${titoloPagina}</h6>
                </div>
                <div class="card-body">
				
				<form:form method="POST" action="add-fattura" modelAttribute="fattura" id="fattura" name="fattura">
					
				<form:hidden path="idFattura" />
				
				<c:if test="${isInsert || fattura.tipoFattura}">
					<form:hidden path="codTipo" />
				</c:if>
				
				 
				 
				 <div class="form-group">
				  
				   <form:label path = "codTipo">Cliente</form:label>
				  
				  	<form:select required="true" class="form-control" path="idCliente" >
				  		<form:option value=""></form:option>
				  		<form:options items="${listaClienti}"  itemValue="idCliente" itemLabel="denominazione" />
					</form:select>
					<form:errors path="idCliente" cssClass="error forceInline"/>
				 </div>
				
				<c:if test="${!isInsert}">
				 	<div class="form-group" id="codTipoDiv">
				 		<form:label path = "codTipo">Tipo</form:label>
					  	<form:select required="true" class="form-control" path="codTipo">
						    <form:options items="${listaTipoFattura}"  itemValue="cod" itemLabel="descrizione"/>
						</form:select>
				 	</div>
				 </c:if>
				
				 
				 <div class="form-group" id="dtFatturaDiv">
				    <form:label path="dtFattura">Data fattura</form:label>
				    <form:input path="dtFattura" type="text" class="form-control date" id="dtFattura" aria-describedby="dtFatturaHelp" placeholder="Data fattura" />
				    <form:errors path="dtFattura" cssClass="error forceInline"/>
				  </div>
				  
				  
				  <div class="form-group" id="meseDiv">
				  	<form:label path = "codMese">Mese</form:label>
					<form:select   class="form-control" path="codMese" >
				  		<form:options items="${listaMesi}"  itemValue="cod" itemLabel="descrizione" />
					</form:select>
				  </div>
				
				  
				   <c:if test="${!isInsert && fattura.tipoFattura}">
					   	<div class="form-group">
						   	<form:label path="numeroFattura">Numero Fattura</form:label>
						    <form:input path="numeroFattura" type="text" class="form-control" id="numeroFattura" aria-describedby="numeroFatturaHelp" disabled="true" />
					   	</div>
				   </c:if>
				  
				  	<div class="form-group"  id="flagPagatoDiv">
				     	<form:label path = "codStato">Stato</form:label>
					  
					  	<form:select   class="form-control" path="codStato" >
					  		<form:options items="${listaStatiFattura}"  itemValue="cod" itemLabel="descrizione" />
						</form:select>
				 	</div>
				  
				  <div class="form-group" id="imponibileDiv">
					  <div class="form-group">
					    <form:label path="importoNetto">Imponibile</form:label>
					    <form:input path="importoNetto" type="text" class="form-control euro" id="importoNetto" aria-describedby="importoNettoHelp" placeholder="Importo netto" required="true"/>
					    <form:errors path="importoNetto" cssClass="error forceInline"/>
					  </div>
					</div>
				  
				   <div class="form-group" id="ivaDiv">
				    <form:label path="iva">Iva</form:label>
				    <form:input path="iva" type="text" class="form-control percentuale" id="iva" aria-describedby="ivaHelp" placeholder="Iva" required="true"/>
				    <form:errors path="iva" cssClass="error forceInline"/>
				  </div>
				  
				  <div class="form-group" id="importoLordoDiv">
				    <form:label path="importoLordo">Importo lordo</form:label>
				    <form:input path="importoLordo" type="text" class="form-control euro" id="importoLordo" aria-describedby="importoLordoHelp" placeholder="" disabled="true"/>
				  </div>
				  
				  
				  <div class="form-group" id="idBolloDiv">
				    <form:label path="idBollo">Identificativo bollo</form:label>
				    <form:input path="idBollo" type="text" class="form-control" id="idBollo" aria-describedby="idBolloHelp" placeholder="Identificativo bollo" maxlength="14"/>
				    <form:errors path="idBollo" cssClass="error forceInline"/>
				  </div>
				
				  
				  <div class="form-group">
				    <form:label path="oggetto">Oggetto</form:label>
				    <form:textarea path="oggetto" type="text" class="form-control" id="oggetto" aria-describedby="oggettoHelp" placeholder="Oggetto" required="true"/>
				    <form:errors path="oggetto" cssClass="error forceInline"/>
				  </div>
				 
				  <div class="form-group" style="text-align: center;">
				  	
				  	<button type="submit" id="salva" name="salva" class="btn btn-primary"><i class="fa fa-save" aria-hidden="true"></i>&nbsp;Salva</button>&nbsp;
				  
				  	<c:if test="${!isInsert&&fattura.tipoFattura}">
				  		<a href="<c:url value='/duplica-fattura' />"  />
						  <button type="button" class="btn btn-primary"><i class="fas fa-clone"></i>&nbsp;Duplica</button>&nbsp;
						</a>
				  	</c:if>
				  
					<c:if test="${!isInsert}">
					  	<button type="submit" id="elimina" name="elimina" class="btn btn-danger"><i class="fas fa-trash-alt"></i>&nbsp;Elimina</button>&nbsp;
					</c:if>
				  
				  	  <c:if test="${!isInsert&&fattura.tipoFattura}">
				  		<a href="${pageContext.request.contextPath}/stampa-fattura" id="stampa-fattura" />
						  <button type="button" class="btn btn-secondary"><i class="fa fa-print"></i>&nbsp;Stampa</button>&nbsp;
						</a>
				  	  </c:if>
				  	  
				  	 <a href="<c:url value='${indietroLink}'/>"  />
					 	<button type="button" class="btn btn-secondary"><i class="far fa-arrow-alt-circle-left"></i>&nbsp;${indietroButton}</button>&nbsp;
					 </a>  
				</div>

				  
				</form:form>


                </div>
              </div>
              
              <c:if test="${!isInsert}">
              	<%@ include file="inc/lista-attivita.jsp" %>
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

	<%@include file="inc/attivitaModal.jsp"%> 
  
  
  
  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

</body>

</html>
