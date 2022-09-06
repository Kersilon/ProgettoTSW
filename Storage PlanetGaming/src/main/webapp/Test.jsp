<!DOCTYPE html>
<html lang="en">
    <head>
	    <link rel="stylesheet" href="css/Test.css">
	    <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Roboto" rel="stylesheet">
    </head>
    <body>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="javaScript/Test.js"></script>
    <script type="text/javascript" src="javaScript/ControllaCredenziali.js"></script>

		<div id="top">
			<p>Qui verranno scritti i link</p>
    	</div>
    	<div id="middle">
    		<p>Qui apparira il contenuto</p>
    	</div>
    	<div id="bottom">
    		<p>Pulsanti</p>
    		<button onclick="initializeArray()">Are you ready for this?</button>
    		<button onclick="clean()">Clean all</button>
    	</div>
    </body>
    <script>
		function initializeArray(){
			const xhttp = new XMLHttpRequest();
			
			xhttp.onload = function(){
				TakeLink();
			}
			
			xhttp.open("GET", "Test");
			xhttp.send();
		}
    	

		
		
    	function TakeLink(){	
    		const xhttp = new XMLHttpRequest();
    		
    		xhttp.onload = function(){
    			$("#top").html(this.responseText);
    			TakeContent();
    		}
    		
    		xhttp.open("GET", "Test3.jsp");
    		xhttp.send();
    	}
		
		
		
		
    	function TakeContent(){	
    		const xhttp = new XMLHttpRequest();
    		
    		xhttp.onload = function(){
    			$("#middle").html(this.responseText);

    		}
    		
    		xhttp.open("GET", "Test2.jsp");
    		xhttp.send();
    	}
    	
    	
    	

    	
    	
		function clean(){
			$("#top").html("");
			$("#middle").html("");
		}
    </script>
</html>