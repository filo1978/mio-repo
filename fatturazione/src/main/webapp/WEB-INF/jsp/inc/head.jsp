<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Fatturazione</title>

<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script> -->





<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">



    <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  
  
	
	
	
	
	  <!-- Custom styles for this page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
  
  
  
  <script type="text/javascript"  src="js/plugins/autoNumeric/autoNumeric.js"></script>
  
 <script src="js/FormCheck.js"></script>
 <script src="js/plugins/bootbox/bootbox.min.js"></script>
 
 
  <script src="js/jquery.populate.js"></script>
  <script src="js/jquery.serializeObject.js"></script>
  <script src="js/jquery.serializeObject.min.js"></script>
  
  <script src="js/jquery.set-date.js"></script>
  <script src="js/jquery.set-number.js"></script>
  
  <script type="text/javascript" src="js/plugins/datePicker/bootstrap-datepicker.js"></script>
  <link rel="stylesheet" type="text/css"	href="js/plugins/datePicker/bootstrap-datepicker.standalone.min.css" >
  
  
  
  
  
  
  
  <style type = "text/css">
  
  .iframe-container {    
	   /*  padding-bottom: 60%; */
	    padding-bottom: 110%;
	    padding-top: 30px; height: 0; overflow: hidden;
		}
	 
	.iframe-container iframe,
	.iframe-container object,
	.iframe-container embed {
	    position: absolute;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	}
  </style>
  
  <script type="text/javascript">
var waitingDialog = waitingDialog || (function ($) {
    'use strict';

	// Creating modal dialog's DOM
	var $dialog = $(
		'<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
		'<div class="modal-dialog modal-m">' +
		'<div class="modal-content">' +
			'<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
			'<div class="modal-body">' +
				'<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
			'</div>' +
		'</div></div></div>');

	return {
		/**
		 * Opens our dialog
		 * @param message Custom message
		 * @param options Custom options:
		 * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
		 * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
		 */
		show: function (message, options) {
			// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = 'Loading';
			}
			var settings = $.extend({
				dialogSize: 'm',
				progressType: '',
				onHide: null // This callback runs after the dialog was hidden
			}, options);

			// Configuring dialog
			$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
			$dialog.find('.progress-bar').attr('class', 'progress-bar');
			if (settings.progressType) {
				$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
			}
			$dialog.find('h3').text(message);
			// Adding callbacks
			if (typeof settings.onHide === 'function') {
				$dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
					settings.onHide.call($dialog);
				});
			}
			// Opening dialog
			$dialog.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			$dialog.modal('hide');
		}
	};

})(jQuery);
function showActivityIndicator(){

	//$.blockUI({ message: ' <strong> Attendere Prego ! </strong>' });
	// $.blockUI({ message: $('#loadingMessage') });
 	waitingDialog.show('Attendere prego', {dialogSize: 'sm', progressType: 'warning'}); 

}

</script>


<script type="text/javascript">

/*
* This is the plugin
*/
(function(a){
	a.createModal=function(b){
		defaults={title:"",message:"",closeButton:true,scrollable:false};
		var b=a.extend({},defaults,b);
		var c=(b.scrollable===true)?'style="max-height:420px;  overflow-y: auto;"':"";  
		html='<div class="modal fade" id="myModal">';
		html+='<div class="modal-dialog">';
		html+='<div class="modal-content">';
		html+='<div class="modal-header">';
	//	html+='<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>';
		if(b.title.length>0){
			html+='<h4 class="modal-title">'+b.title+"</h4>"
		}
		html+="</div>";
		html+='<div class="modal-body" '+c+">";
		html+=b.message;
		html+="</div>";html+='<div class="modal-footer">';
		if(b.closeButton===true){
			html+='<button type="button" class="btn btn-primary" data-dismiss="modal">Chiudi</button>'
		}
		html+="</div>";
		html+="</div>";
		html+="</div>";
		html+="</div>";
		a("body").prepend(html);
		a("#myModal").modal().on("hidden.bs.modal",function(){a(this).remove()})
		}
	}
)(jQuery);

/*
* Here is how you use it
*/
$(function(){    
    $('.view-pdf').on('click',function(){
        var pdf_link = $(this).attr('href');
        var iframe = '<div class="iframe-container" ><iframe  src="'+pdf_link+'"></iframe></div>'
        $.createModal({
        title:'Programma di fatturazione',
        message: iframe,
        closeButton:true,
        scrollable:false
        }) ;
        return false;        
    });    
})


</script>
  
</head>
  