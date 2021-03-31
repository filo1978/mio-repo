<script>
$(document).ready( function () {
	 
	 
	 
	 $.ajax({
			type : 'GET',
			contentType: "application/json",
			url : '<c:url value='/grafico-fatture-mese-anno' />',
			dataType: 'json',
			success : function(result) {
				//drawChart();
				console.log("inzio fatture mese anno!!!!");
				console.log("result="+result);
				var labelMesi= new Array();
				var datasets=new Array();
				$.each(result, function(k, dato) {
					console.log("valore k="+k)
					var valoriAnno= new Array();
					var anno=dato.anno;
					var labelAnno="Guadagno anno "+anno;
					setValoriAnno(valoriAnno,dato.lista);
					if(k==0){
						setLabelMesi(labelMesi,dato.lista);
					}
					
					var colore= random_rgb();
					
					var dataset={
						       label: labelAnno,
						       lineTension: 0.3,
						       backgroundColor: colore,
						       borderColor: colore,
						       pointRadius: 3,
						       pointBackgroundColor: "rgb(102, 102, 153)",
						       pointBorderColor: "rgb(102, 102, 153)",
						       pointHoverRadius: 3,
						       pointHoverBackgroundColor: "rgb(102, 0, 204)",
						       pointHoverBorderColor: "rgb(102, 0, 204)",
						       pointHitRadius: 10,
						       pointBorderWidth: 2,
						 	  data: valoriAnno,
						     };
					datasets.push(dataset);

				});
				
				 var myLineChart = new Chart(ctx1, {
					   type: 'line',
					   data: {
					     labels: labelMesi,
					     datasets: datasets,
					   },
					   options: {
					     maintainAspectRatio: false,
					     layout: {
					       padding: {
					         left: 10,
					         right: 25,
					         top: 25,
					         bottom: 0
					       }
					     },
					     scales: {
					       xAxes: [{
					         time: {
					           unit: 'date'
					         },
					         gridLines: {
					           display: false,
					           drawBorder: false
					         },
					         ticks: {
					           maxTicksLimit: 7
					         }
					       }],
					       yAxes: [{
					         ticks: {
					           maxTicksLimit: 5,
					           padding: 10,
					           // Include a dollar sign in the ticks
					           callback: function(value, index, values) {
					             return 'Euro ' + number_format(value);
					           }
					         },
					         gridLines: {
					           color: "rgb(234, 236, 244)",
					           zeroLineColor: "rgb(234, 236, 244)",
					           drawBorder: false,
					           borderDash: [2],
					           zeroLineBorderDash: [2]
					         }
					       }],
					     },
					     legend: {
					       display: true
					     },
					     tooltips: {
					       backgroundColor: "rgb(255,255,255)",
					       bodyFontColor: "#858796",
					       titleMarginBottom: 10,
					       titleFontColor: '#6e707e',
					       titleFontSize: 14,
					       borderColor: '#dddfeb',
					       borderWidth: 1,
					       xPadding: 15,
					       yPadding: 15,
					       displayColors: false,
					       intersect: false,
					       mode: 'index',
					       caretPadding: 10,
					       callbacks: {
					         label: function(tooltipItem, chart) {
					           var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
					           return datasetLabel + ': Euro ' + number_format(tooltipItem.yLabel);
					         }
					       }
					     }
					   }
					 });
								
			}
		});
	 
	 
	 
	//Area Chart Example
	 var ctx1 = $('#myEarningAreaChart');// document.getElementById("myEarningAreaChart");
	 console.log("myEarningAreaChart="+ctx1);
	
	 
});
	function setValoriAnno(valoriAnno,lista){
		$.each(lista, function(k, dato) {
			//console.log("setValoriAnno="+dato.importo);
			valoriAnno.push(dato.importo);
		});
	}
	
	function setLabelMesi(labelMesi,lista){
		$.each(lista, function(k, dato) {
			//console.log("setLabelMesi="+dato.mese);
			labelMesi.push(dato.mese);
		});
	}

	function addData(chart, label, data) {
	    chart.data.labels.push(label);
	    chart.data.datasets.forEach((dataset) => {
	        dataset.data.push(data);
	    });
	    chart.update();
	}

	
	function random_rgb() {
	    var o = Math.round, r = Math.random, s = 250;
	    return 'rgb(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')';
	}
	
</script>


<div class="card shadow mb-4">
	<div class="card-header py-3" style="background-color: #ed23ed;">
		<h6 class="m-0 font-weight-bold text-primary">Fatturato mensile netto</h6>
	</div>
	<div class="card-body">
		<div class="form-group" style="width: 100%">
			<div class="chart-area">
              <canvas id="myEarningAreaChart" class="chartjs-render-monitor"></canvas>
            </div>
		</div>
	</div>	
</div>