function initializeVideogame(){
	const xhttp = new XMLHttpRequest();
			
	xhttp.onload = function(){
		takeVideogame();
	}
			
	xhttp.open("GET", "StorageControl?action=insert");
	xhttp.send();
	}

function takeVideogame(){
	const xhttp = new XMLHttpRequest();
    		
    xhttp.onload = function(){
    	$(".nextVideogame").html(this.responseText);

    	}
    		
    	xhttp.open("GET", "VideogiocoAdmin.jsp");
    	xhttp.send();
};