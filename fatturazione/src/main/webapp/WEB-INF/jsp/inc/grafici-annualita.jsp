<script>
$(document).ready( function () {
	 
	 
	 
	 $.ajax({
			type : 'GET',
			contentType: "application/json",
			url : '<c:url value='/grafico-fatture-anno' />',
			dataType: 'json',
			success : function(result) {
				//drawChart();
				console.log("inzio fatture mese anno!!!!");
				console.log("result="+result);
				
				var labelz= new Array();
				var dataz= new Array();
				var massimoImporto=result.importoMassimo;
				
				setValoriGuadagniAnno(result,dataz,labelz);
				
				 var myBarChart = new Chart(myAnnualitaChart, {
					  type: 'bar',
					  data: {
					    labels: labelz,
					    datasets: [{
					      label: "Guadagno",
					      backgroundColor: "#ff00ff",
					      hoverBackgroundColor: "#ff0066",
					      borderColor: "#4e73df",
					      data: dataz,
					    }],
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
					          unit: 'anno'
					        },
					        gridLines: {
					          display: false,
					          drawBorder: false
					        },
					        ticks: {
					          maxTicksLimit: 6
					        },
					        maxBarThickness: 25,
					      }],
					      yAxes: [{
					        ticks: {
					          min: 0,
					          max: massimoImporto,
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
					      display: false
					    },
					    tooltips: {
					      titleMarginBottom: 10,
					      titleFontColor: '#6e707e',
					      titleFontSize: 14,
					      backgroundColor: "rgb(255,255,255)",
					      bodyFontColor: "#858796",
					      borderColor: '#dddfeb',
					      borderWidth: 1,
					      xPadding: 15,
					      yPadding: 15,
					      displayColors: false,
					      caretPadding: 10,
					      callbacks: {
					        label: function(tooltipItem, chart) {
					          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
					          return datasetLabel + ': Euro ' + number_format(tooltipItem.yLabel);
					        }
					      }
					    },
					  }
					});
								
			}
		});
	 
	 
	 
	//Area Chart Example
	 var myAnnualitaChart = $('#myAnnualitaChart');// document.getElementById("myEarningAreaChart");
	 //console.log("myAnnualitaChart="+myAnnualitaChart);
	
	 
});
	function setValoriGuadagniAnno(result,dataz,labelz){
		$.each(result.lista, function(k, dato) {
			dataz.push(dato.importo);
			labelz.push(dato.anno);
			
		});
	}
	
	


</script>


<div class="card shadow mb-4">
	<div class="card-header py-3" style="background-color: #a36fa3;">
		<h6 class="m-0 font-weight-bold text-primary">Gaudagni annuali</h6>
	</div>
	<div class="card-body">
		<div class="form-group" style="width: 100%">
			<div class="chart-area">
              <canvas id="myAnnualitaChart" class="chartjs-render-monitor"></canvas>
            </div>
		</div>
	</div>	
</div>