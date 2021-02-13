

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
                 <%@ include file="inc/riassunto-fatture.jsp" %>
                </div>
              </div>
             
             <div class="row">
             	
             	<div class="col-xl-8 col-lg-7">
             		
             		<div class="card shadow mb-4">
		                <div class="card-header py-3">
		                 <%@ include file="inc/grafici-fatture.jsp" %>
		                </div>
	              	</div>
             		
             	</div>
             	
             	<div class="col-xl-4 col-lg-5">
             	
             		<div class="card shadow mb-4">
		                <div class="card-header py-3">
		                 <%@ include file="inc/grafici-fatture-cliente.jsp" %>
		                </div>
		            </div>
             	
             	</div>
              
              </div>
             
             <div class="card shadow mb-4">
                <div class="card-header py-3">
                 <%@ include file="inc/grafici-annualita.jsp" %>
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
