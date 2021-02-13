<script>
$(document).ready( function () {
	 
	
	$.ajax({
        type: "GET",
        url: "get-all-anno-fatture",
        success: function(data) {
             
             console.log(data); 
             
             var dropdown = $('#annoCliente');
             dropdown.empty();
             $.each(data, function (key, entry) {
          	    dropdown.append($('<option></option>').attr('value', entry.cod).text(entry.descrizione));
          	   })
             var elemento = $("#annoCliente").prop("selectedIndex", 0).val(); 
             console.log("elemento="+elemento);
             
             $.ajax({
     			type : 'GET',
     			contentType: "application/json",
     			data:  "anno=" +elemento,
     			url : '<c:url value='/grafico-fatture-cliente-anno' />',
     			dataType: 'json',
     			success : function(result) {
     				//emptyChart(myPieClienteChart);
     				console.log("result="+result);
     				$.each(result, function(i, item) {
     				    console.log("item.denominazioneCliente="+item.denominazioneCliente);
     				    console.log("item.fatturatoCliente="+item.fatturatoCliente);
     				    addDataPie(myPieClienteChart, item.denominazioneCliente, item.fatturatoCliente,random_rgba());
     				});
     								
     			}
     		});

        }
   });
	
	
	$('#annoCliente').on('change', function() {
		var value = $(this).val(); 
		 $.ajax({
  			type : 'GET',
  			contentType: "application/json",
  			data:  "anno=" +value,
  			url : '<c:url value='/grafico-fatture-cliente-anno' />',
  			dataType: 'json',
  			success : function(result) {
  				emptyChart(myPieClienteChart);
  				console.log("result="+result);
  				$.each(result, function(i, item) {
  				    console.log("item.denominazioneCliente="+item.denominazioneCliente);
  				    console.log("item.fatturatoCliente="+item.fatturatoCliente);
  				    addDataPie(myPieClienteChart, item.denominazioneCliente, item.fatturatoCliente,random_rgba());
  				    
  				});
  								
  			}
  		});
	});
	 
	 
	 
	 
	// Pie Chart Example
	 var ctxPieCliente = document.getElementById("myPieChartCliente");
	 var myPieClienteChart = new Chart(ctxPieCliente, {
	   type: 'doughnut',
	   data: {
	     labels: [],
	     datasets: [{
	       data: [],
	       backgroundColor: [],
	       hoverBackgroundColor: [],
	       hoverBorderColor: "rgba(234, 236, 244, 1)",
	     }],
	   },
	   options: {
	     maintainAspectRatio: false,
	     tooltips: {
	       backgroundColor: "rgb(255,255,255)",
	       bodyFontColor: "#858796",
	       borderColor: '#dddfeb',
	       borderWidth: 1,
	       xPadding: 15,
	       yPadding: 15,
	       displayColors: false,
	       caretPadding: 10,
	     },
	     legend: {
	       display: true
	     },
	     cutoutPercentage: 80,
	   },
	 });
	 
});


	function addDataPie(chart, label, data,backgroundColor) {
		//chart.data.labels=[];
		//chart.data.datasets=[];
	    chart.data.labels.push(label);
	    chart.data.datasets.forEach((dataset) => {
	        dataset.data.push(data);
	        dataset.backgroundColor.push(backgroundColor);
	    });
	    chart.update();
	}
	
	
	function emptyChart(chart){
		chart.data.labels.length = 0;
		chart.data.datasets.forEach((dataset) => {
	        dataset.data.length = 0;
	        dataset.backgroundColor.length = 0;
	    });
	}
	
	function random_rgba() {
	    var o = Math.round, r = Math.random, s = 250;
	    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')';
	}

</script>


<div class="card shadow mb-4">
	<div class="card-header py-3" style="background-color: #ed23ed;">
		<h6 class="m-0 font-weight-bold text-primary">Clienti</h6>
	</div>
	<div class="card-body">
		<div class="form-group" style="width: 100%">
			<label for="annoCliente">Annualit&agrave;</label>
			<select id="annoCliente" name="annoCliente" class="form-control" >
			</select>
		</div>
		<div class="form-group" style="width: 100%">
			<div class="chart-area">
              <canvas id="myPieChartCliente" class="chartjs-render-monitor"></canvas>
            </div>
		</div>
	</div>	
</div>