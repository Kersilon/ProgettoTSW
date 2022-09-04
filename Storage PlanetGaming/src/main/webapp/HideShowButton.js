/*
vecchie funzioni usate in paginaOrdini sulle tabelle prima di usare le flexbox
function hide(){
	let table;
	let hideButton;
	let showButton;
	
	table = document.getElementsByClassName("hide");
	hideButton = document.getElementById("HideButton");
	showButton = document.getElementById("ShowButton");
	
	for(i = 0; i<table.length; i++){
		table[i].style.display ="none";
	}
	
	hideButton.style.display = "none";
	showButton.style.display = "inline";
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
}*/

function hide(idOrdine){
	flexBox = document.getElementsByClassName(idOrdine);
	
	for(i = 0; i<flexBox.length; i++){
		flexBox[i].style.display ="none";
	}
}
function show(idOrdine){
	flexBox = document.getElementsByClassName(idOrdine);
	
	for(i = 0; i<flexBox.length; i++){
		flexBox[i].style.display ="flex";
	}
}