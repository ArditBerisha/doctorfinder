$(document).ready(function(){
	  
	  /* 1. Visualizing things on Hover - See next part for action on click */
	  $('#stars li').on('mouseover', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
	   
	    // Now highlight all the stars that's not after the current hovered star
	    $(this).parent().children('li.star').each(function(e){
	      if (e < onStar) {
	        $(this).addClass('hover');
	      }
	      else {
	        $(this).removeClass('hover');
	      }
	    });
	    
	  }).on('mouseout', function(){
	    $(this).parent().children('li.star').each(function(e){
	      $(this).removeClass('hover');
	    });
	  });
	  
	  
	  /* 2. Action to perform on click */
	  $('#stars li').on('click', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently selected
	    var stars = $(this).parent().children('li.star');
	    var docId = $('input#docId').val();
	    
	    for (i = 0; i < stars.length; i++) {
	      $(stars[i]).removeClass('selected');
	    }
	    
	    for (i = 0; i < onStar; i++) {
	      $(stars[i]).addClass('selected');
	    }
	    
	    // JUST RESPONSE (Not needed)
	    var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
	    var msg = "";
	    if (ratingValue > 1) {
	        msg = "You rated this " + ratingValue + " stars.";
	    }
	    else {
	        msg = "We will improve ourselves. You rated this " + ratingValue + " stars.";
	    }
	    responseMessage(msg);
	    
	    var obj = {
	    		id: docId,
	    		rate: ratingValue
	    };
	    
	    var url = window.location;
	    
	    $(document).ready(function() {
			$('#reviewButton').click(function() {
				event.preventDefault();
				
				$.ajax({
	                type: 'POST',
	                //contentType : "application/json",
	                url: "/rating/save",
	                data: JSON.stringify(obj),
	                dataType : 'json',
	                success: function (response) {
	                    console.log(response);
	                },
	                error: function (response) {
	                    console.log("error");
	                    console.log("error");
	                    console.log(response);
	                }
	            }); 
				
				
			});
		});
	    
	    
	    
	    
	  });
	});


	function responseMessage(msg) {
	  $('#reviewModal').modal();
	  $('.success-box').fadeIn(200);  
	  $('.success-box div.text-message').html("<span>" + msg + "</span>");
	}