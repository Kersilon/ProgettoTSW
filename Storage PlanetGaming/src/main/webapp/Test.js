function LoginFail(idButton, errorTextContainer) {
	/*
	// Create an XMLHttpRequest object
	const xhttp = new XMLHttpRequest();
	
	// Define a callback function
	xhttp.onload = function() {
	  alert("it works");
	}
	
	// Send a request
	xhttp.open("GET", "http://localhost:8080/Storage_PlanetGaming/Test.jsp");
	xhttp.send();
	*/

    $(document).on("click", idButton, function() { 		// When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    	$.post("LoginStorage", function(responseText) {   		// Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
   		$(errorTextContainer).text(responseText);       // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
    	//alert(responseText);
    	});
    });

}

function test(){
	 ${pageContext.request.contextPath};
}