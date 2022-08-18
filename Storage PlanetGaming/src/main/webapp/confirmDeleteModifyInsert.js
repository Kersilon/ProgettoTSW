//rimuove un videogioco e aggiorna in tempo reale la tabella di videogiochi andando a rimuovere la riga che mostrava tale videogiocho
//per capire quale righa della tabella deve eliminare queste hanno come id, l'id del videogioco che mostrano
//quindi tramite quest'ultimo otteniamo la riga del videogioco eliminato

function deleteOnSubmit(){
	$("#deleteForm").on('submit', function(e) {
		
	    e.preventDefault(); // avoid to execute the actual submit of the form.
	
	    var form = $(this);
	    var actionUrl = form.attr('action');
	    var idProduct = $("#" + form.attr("id") + " " + "input[name=codice_prodotto]").val();
	    
	    if(checkIdToDelete(form.attr("id"))){
	    	let bar = confirm('Are you sure you want to delete this videogame?');
	    	
	    	if(bar){
	    		$.ajax({
			        type: "POST",
			        url: actionUrl,
			        data: form.serialize(), // serializes the form's elements.
			        success: function(data) // success means if the servlet has generated an output
			        {
				        //console.log(idProduct);			for debugging
				        if(data === "successful deletion"){
				       		//alert(data);
				          	
				        	const tableRow = document.getElementById(idProduct);
				        	
				        	if(tableRow != null){
				        		tableRow.remove();
				        		//document.getElementById("popup").style.visibility = "visible";
				        		$("#popup").fadeTo(0100, 1);
				        		$("#popup").fadeOut(3000);
				        	}
				    	}else{
			        		alert("Error");
			        		alert(data);
			        	}
			    	}
			    });
	    		
	    	}
		    
	    }
	});
}

function modifyOnSubmit(formId, checkFunction, indexError){
	
	$(formId).on('submit', function(e) {
		
	    e.preventDefault(); // avoid to execute the actual submit of the form.
	
	    var form = $(this);
	    var actionUrl = form.attr('action');
	    var idProduct = $("#" + form.attr("id") + " " + "input[name=codice_prodotto]").val();
	    //var idProduct = $("#modifyPriceId input[name=codice_prodotto]").val();
	    
	    if(checkIdToDelete(form.attr("id"))){
		    if(checkFunction == null || checkFunction(form.attr("id"), indexError)){
		    	let bar = confirm('Are you sure you want to modify this parameter?');
		    	
		    	if(bar){
			
					//test
				 	var data = new FormData();
					jQuery.each(jQuery('#file')[0].files, function(i, file) {
						data.append('file-'+i, file);
						});
					//
							
		    		$.ajax({
				        type: "POST",
				        url: actionUrl,
				        //data: form.serialize(), // serializes the form's elements.
				        
				        //test
				        data: data,
					    cache: false,
					    contentType: false,
					    processData: false,
				        //
				        
				        success: function(data) // success means if the servlet has generated an output
				        {
					        //console.log(idProduct);			for debugging
								videogioco = JSON.parse(data);			          	
					        	const tableRow = document.getElementById(idProduct);
					        	
					        	if(tableRow != null){
									console.log(videogioco.nome);
									$(tableRow).find(".idCell").html(videogioco.codice_prodotto);
									$(tableRow).find(".nameCell").html(videogioco.nome);
									$(tableRow).find(".editionCell").html(videogioco.edizione);
									$(tableRow).find(".descriptionCell").html(videogioco.descrizione);
									$(tableRow).find(".priceCell").html(videogioco.prezzo_vetrina);
									$(tableRow).find(".dateCell").html(videogioco.data_uscita);
									$(tableRow).find(".platformCell").html(videogioco.piattaforma);
									$(tableRow).find(".consoleCell").html(videogioco.console);
									$(tableRow).find(".scontoCell").html(videogioco.sconto);
									$(tableRow).find(".copiesCell").html(videogioco.copie);
									$(tableRow).find(".developerCell").html(videogioco.sviluppatore);
									$(tableRow).find(".publisherCell").html(videogioco.pubblisher);
									$(tableRow).find(".fotoCell").html(videogioco.foto);
					        	}
					        	
					        	$("#popupModify").fadeTo(0100, 1);
					        	$("#popupModify").fadeOut(3000);	        	
				    	}
				    });
		    		
		    	}
		    }
			    
		}
	});
}