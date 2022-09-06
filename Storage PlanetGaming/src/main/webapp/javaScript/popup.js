			function popup(idPopup){
				$(idPopup).fadeTo(0100, 1);
				$(idPopup).fadeOut(3000);
			}
			
			function activatePopup(){
				alert("entrato");
				$(".addToCartForm").on('submit', function(e) {
		
			    e.preventDefault(); // avoid to execute the actual submit of the form.
					alert("prevenuto");
			    var form = $(this);
			    var actionUrl = form.attr('action');	    

	    		$.ajax({
			        type: "POST",
			        url: actionUrl,
			        data: form.serialize(), // serializes the form's elements.
			        success: function(data) // success means if the servlet has generated an output
			        {
				        
				        		popup("#popup");
				    }
			    	
			    });
	    		
	    	
		    
	    
	});
}