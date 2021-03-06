 <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Fatturazione</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="index.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Clienti
      </div>


      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/lista-clienti' />">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Lista Clienti</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/nuovo-cliente' />">
          <i class="fas fa-fw fa-table"></i>
          <span>Nuovo cliente</span></a>
      </li>
      
      
       <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Fatture
      </div>


      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/lista-fatture' />">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Lista Fatture</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/nuova-fattura?tipoFattura=FT001' />">
          <i class="fas fa-fw fa-table"></i>
          <span>Nuova Fattura</span></a>
      </li>
      
      
      
      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Prestazioni informali
      </div>


      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/lista-prestazioni' />">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Lista Prestazioni</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/nuova-fattura?tipoFattura=FT003' />">
          <i class="fas fa-fw fa-table"></i>
          <span>Nuova Prestazione</span></a>
      </li>
      
      
       <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Bozze
      </div>


      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/lista-bozze' />">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Lista Bozze</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/nuova-fattura?tipoFattura=FT002' />">
          <i class="fas fa-fw fa-table"></i>
          <span>Nuova Bozza</span></a>
      </li>
      

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->