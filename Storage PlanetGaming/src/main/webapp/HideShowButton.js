function hide(){
	let table;
	let hideButton;
	let showButton;
	
	table = document.getElementsByClassName("hide");
	hideButton = document.getElementById("HideButton");
	showButton = document.getElementById("ShowButton");
	
	for(i = 0; i<table.length; i++){
		//table[i].style.display ="none";
		
	}
	
	hideButton.style.display = "none";
	showButton.style.display = "block";
}

function show(){
	let table;
	let hideButton;
	let showButton;
	
	table = document.getElementsByClassName("hide");
	hideButton = document.getElementById("HideButton");
	showButton = document.getElementById("ShowButton");
	
	for(i = 0; i<table.length; i++){
		table[i].style.display ="block";
	}
	
	hideButton.style.display = "block";
	showButton.style.display = "none";
}