<!-- Attivita Modal -->
<div class="modal fade" id="attivitaModal"  tabindex="-1" role="dialog"
	aria-labelledby="attivitaModalLabel" aria-hidden="true">
	
	
	<form id="attivitaForm" action="salva-attivita"
		class="form-horizontal ng-pristine ng-valid" method="POST">

			<input class="form-control" type="hidden" name="idAttivita"
				id="idAttivita" value="-1"/>
		
		


		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
				
					<h6 class="m-0 font-weight-bold text-primary" class="modal-title" id="attivitaModalLabel">Dettaglio Attivit&agrave;</h6>
				
				</div>
				
				<div class="modal-body">
					
					<div class="form-group" id="msgOkDivAttivita" >
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><p id="msgOkAttivita"></p></strong>
						</div>
					</div>

					<div class="form-group" id="msgKoDivAttivita"  >
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							ATTENZIONE:
							<ul class="error-list">

							</ul>
						</div>
					</div>


					 <div class="form-group">
							<label class="control-label" for="codCorrettivo">Descrizione:</label>
							<input type="text" name="descrizione" type="text" class="form-control" id="descrizione" aria-describedby="descrizioneHelp" placeholder="Descrizione" required/>
							
							<label class="control-label" for="importoNettoAttivita">Imponibile:</label>
							<input type="text" name="importoNettoAttivita" type="text" class="form-control euro" id="importoNettoAttivita" aria-describedby="importoNettoAttivitaHelp" placeholder="Importo netto" />
					</div>
				</div>
				<div class="modal-footer">
					<div class="form-group" style="width: 100%; text-align: center;">
						<button id="elimina-attivita" name="elimina-attivita" type="button"
							class="btn btn-danger" >
							<i class="fas fa-trash-alt"></i>&nbsp;Elimina
						</button>
						&nbsp;
						<button id="salva-attivita" name="salva-attivita" type="submit" class="btn btn-primary">
							<i class="fa fa-save" aria-hidden="true"></i>&nbsp;Salva
						</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<i class="fa fa-times"></i>&nbsp;Chiudi
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>

</div>
<!-- End Attivita Modal -->