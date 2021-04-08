
$(document).ready(function() {
		$('table #delBtn').click(function() {
			event.preventDefault();
			var href = $(this).attr('href');
			$('#deleteModal #delRef').attr('href', href);
			$('#deleteModal').modal();
		});
	});
	
	$(document).ready(function () {

	    $("#pageSiz").on('change', function () {
	    	var value = $(this).val();
	    	window.location.replace(value);
	    });

	});
	
	$(document).ready(function() {
		$('.delBtn').click(function() {
			event.preventDefault();
			
			var href = $(this).attr('href');
		
			
			$('#deleteModalAuth #delRefAuth').attr('href', href);
			$('#deleteModalAuth').modal();
		});
	});
	
	$(document).ready(function() {
		$('#sub').click(function() {
			
			if($("#langCode option:selected").val() == 0){
				event.preventDefault();
				$('#alert').show();
			}
		});
	});
	

	
	
	
	
	
	
	
	

	
