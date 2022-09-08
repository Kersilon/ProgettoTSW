function takeConfirmModify(){
	const xhttp = new XMLHttpRequest();
	
	xhttp.onload = function(){
		divContainer = $(".areYouSure");
		
		divContainer.html(this.responseText);
	}
	
	xhttp.open("GET", "confirmModify.html");
	xhttp.send();
}















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
	    	let check = $("#deleteForm input[name=confirmModify]").serialize();
		    if(check != null && check[check.length - 1] == 'y'){
			
	    		$.ajax({
			        type: "POST",
			        url: actionUrl,
			        data: form.serialize(), // serializes the form's elements.
			        success: function(data) // success means if the servlet has generated an output
			        {
				        //console.log(idProduct);			for debugging
				        if(data === "successful deletion"){
				          	
				        	const tableRow = document.getElementById(idProduct);
				        	
				        	if(tableRow != null){
				        		tableRow.remove();
				        		//document.getElementById("popup").style.visibility = "visible";
				        		$("#popup").fadeTo(0100, 1);
				        		$("#popup").fadeOut(3000);
				        	}
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
/*		    	let bar = confirm('Are you sure you want to modify this parameter?');
		    	
		    	if(bar){*/
		    	let check = $(formId + " input[name=confirmModify]").serialize();
		    	if(check != null && check[check.length - 1] == 'y'){
			
					//per le immagini
				 	var data = new FormData($(formId)[0]);
					//
							
		    		$.ajax({
				        type: "POST",
				        url: actionUrl,
				        //data: form.serialize(), // serializes the form's elements.
				        
				        //per le immagini
				        enctype: 'multipart/form-data',
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
									// it just doesn't work $(tableRow.fotoCell).find("img") pertanto ho dato un id al tag image e ho usato la riga di codice quì sotto;
									$("#fotoName"+idProduct).attr("src", "./immagini Videogiochi/" + videogioco.foto);
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



function insertOnSubmit(formId, checkFunction){
	
	$(formId).on('submit', function(e) {
		
	    e.preventDefault(); // avoid to execute the actual submit of the form.
	
	    var form = $(this);
	    var actionUrl = form.attr('action');
	    //TODO la prendiamo dal bean passato tramite json 		var idProduct
	    
	    if(checkFunction(form.attr("id"))){
		    	
		    	
	
					//per le immagini
				 	var data = new FormData($(formId)[0]);
					//
							
		    		$.ajax({
				        type: "POST",
				        url: actionUrl,
				        
				        //per le immagini
				        enctype: 'multipart/form-data',
				        data: data,
					    cache: false,
					    contentType: false,
					    processData: false,
				        //
				        
				        success: function(data) // success means if the servlet has generated an output
				        {
					        //console.log(idProduct);			for debugging
					        
								videogioco = JSON.parse(data);
								
								//id dell'ultimo elemento inserito
								let idProduct = $("table tr").last().find(".idCell").html();
								if(idProduct == null){
									idProduct = 1;
								}
								
								//id del nuovo elemento
								idProduct++;
								videogioco.codice_prodotto = idProduct;
								
								$("table tr").last().after(
								"<tr id="+ videogioco.codice_prodotto +">" +
								"<td class="+"idCell"+"						>"+ videogioco.codice_prodotto +"																					</td>"+
								"<td class="+"nameCell"+"					>"+ videogioco.nome +"																								</td>"+
								"<td class="+"editionCell"+"				>"+ videogioco.edizione +"																							</td>"+
								"<td class="+"descriptionCell"+"			>"+ videogioco.descrizione +"																						</td>"+
								"<td class="+"priceCell"+"					>"+ videogioco.prezzo_vetrina +"																					</td>"+
								"<td class="+"dateCell"+"					>"+ videogioco.data_uscita +"																						</td>"+
								"<td class="+"platformCell"+"				>"+ videogioco.piattaforma +"																						</td>"+
								"<td class="+"consoleCell"+"				>"+ videogioco.console +"																							</td>"+
								"<td class="+"scontoCell"+"					>"+ videogioco.sconto +"																							</td>"+
								"<td class="+"copiesCell"+"					>"+ videogioco.copie +"																								</td>"+
								"<td class="+"developerCell"+"				>"+ videogioco.sviluppatore +"																						</td>"+
								"<td class="+"publisherCell"+"				>"+ videogioco.pubblisher +"																						</td>"+
								"<td class="+"fotoCell"+"					>"+ '<img id="fotoName' + videogioco.codice_prodotto + '"' + 'src="./immagini Videogiochi/'+ videogioco.foto +'">	</td>'+
								"</tr>"
								
								)
								/*		          	
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
									// it just doesn't work $(tableRow.fotoCell).find("img") pertanto ho dato un id al tag image e ho usato la riga di codice quì sotto;
									$("#fotoName"+idProduct).attr("src", "./immagini Videogiochi/" + videogioco.foto);
					        	}
					        	*/
					        	$("#popupInsert").fadeTo(0100, 1);
					        	$("#popupInsert").fadeOut(3000);	        	
				    	}
				    });
		    		
			    
		}
	});
}