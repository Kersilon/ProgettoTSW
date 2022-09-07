function checkEmail(){
	let email;
	let emailError;
	let regex;
	
	regex = /\S+@\S+.\S+/; 
	//va fatto qui il .value perché altrimenti "rallenta" l'operazione
	//dato che accedere al dom "dovrebbe" essere dispendioso
	email = document.getElementById("email").value;
	emailError = document.getElementById("emailError");
	
	//se l'email inserita non è valida viene inserito contenuto nel paragrafo EmailError
	//uso regex.test(email) perché email.value.match(regex) "ovvero quello delle slide" non funziona per qualche motivo
	if(regex.test(email)){
		emailError.innerHTML = "";
		return true;
	}else{
		emailError.innerHTML = "Email not valid";
		return false;
	}
}

function checkPassword(form, indexError){
	let password;
	let passwordError;
	let regex;
	
	regex = /(?=.*[0-9])/;	//deve esserci almeno un numero
	password = document.forms[form]["password"].value;
	passwordError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);

	if(regex.test(password)){
		passwordError.innerHTML = "";
		return true;
	}else{
		passwordError.innerHTML = "Password not valid";
		return false;
	}
}


function checkDate(form, indexError){
	let birthDate;
	let birthDateError;
	let regex;
	
	regex = /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/;	//data nel formato aaaa-mm-dd va bene sia "-" o "/" per separare i caratteri

/*	birthDate = document.forms[form]["data"].value;*/
	birthDate = $(form + " input[name = data]").val();
	
/*	birthDateError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);*/
	birthDateError = $(form + " .ErrorParagraph")[indexError];
	
	if(regex.test(birthDate)){
		birthDateError.innerHTML = "";
		return true;
	}else{
		birthDateError.innerHTML = "Date not valid";
		return false;
	}
}

function checkPhone(form, indexError){
	let phone;
	let phoneError;
	let regex;
	
	regex = /^(\+)?([ 0-9]){10,16}$/; //si possono inserire prefissi come "+39"
	phone = document.forms[form]["telefono"].value;
	phoneError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);
	
	if(regex.test(phone)){
		phoneError.innerHTML = "";
		return true;
	}else{
		phoneError.innerHTML = "Phone not valid";
		return false;
	}
}

function checkFiscalCode(){
	let fiscalCode;
	let fiscalCodeError;
	let regex;
}


//campi indirizzo di consegna (form delivery address in paginaProtetta.jsp)
function checkCap(){
	let cap;
	let capError;
	let regex;
	
	regex = /^\d{5}$/;
	cap = document.getElementById("cap").value;
	capError = document.getElementById("capError");
	
	if(regex.test(cap)){
		capError.innerHTML = "";
		return true;
	}else{
		capError.innerHTML = "Cap not valid";
		return false;
	}
}

function checkProvince(){
	let province;
	let provinceError;
	let regex;
	
	regex = /^[A-Z]{2}$/;
	province = document.getElementById("province").value;
	provinceError = document.getElementById("provinceError");
	
	if(regex.test(province)){
		provinceError.innerHTML = "";
		return true;
	}else{
		provinceError.innerHTML = "province not valid";
		return false;
	}
}

function checkCity(){
	let city;
	let cityError;
	let regex;
	
	regex = /^[a-zA-Z',.\s-]{1,25}$/;
	///^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$/;
	city = document.getElementById("city").value;
	cityError = document.getElementById("cityError");
	
	if(regex.test(city)){
		cityError.innerHTML = "";
		return true;
	}else{
		cityError.innerHTML = "city not valid";
		return false;
	}
}



//campi metodo di pagamento (form payment method in paginaProtetta.jsp)
function checkCardNumber(){
	let cardNumber;
	let cardNumberError;
	let regex;
	
	regex = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6011[0-9]{12}|622((12[6-9]|1[3-9][0-9])|([2-8][0-9][0-9])|(9(([0-1][0-9])|(2[0-5]))))[0-9]{10}|64[4-9][0-9]{13}|65[0-9]{14}|3(?:0[0-5]|[68][0-9])[0-9]{11}|3[47][0-9]{13})*$/;
	//l'espressione regolare controlla se il prefisso è corretto quindi deve corrispondere ad un reale prefisso ad esempio Visa che sarebbe 4023
	cardNumber = document.getElementById("cardNumber").value;
	cardNumberError = document.getElementById("cardNumberError");
	
	if(regex.test(cardNumber)){
		cardNumberError.innerHTML = "";
		return true;
	}else{
		cardNumberError.innerHTML = "Card number not valid";
		return false;
	}
}

function checkCcv(){
	let ccv;
	let ccvError;
	let regex;
	
	regex = /^[0-9]{3}$/;
	ccv = document.getElementById("ccv").value;
	ccvError = document.getElementById("ccvError");
	
	if(regex.test(ccv)){
		ccvError.innerHTML = "";
		return true;
	}else{
		ccvError.innerHTML = "ccv not valid";
		return false;
	}
}
/*
function checkExpirationDate(){
	let expirationDate;
	let expirationDateError;
	let regex;
	
	regex = /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;
	expirationDate = document.getElementById("expirationDate").value;
	expirationDateError = document.getElementById("expirationDateError");
	
	if(regex.test(expirationDate)){
		expirationDateError.innerHTML = "";
		return true;
	}else{
		expirationDateError.innerHTML = "Expiration date not valid";
		return false;
	}
}
*/



//controlli inserimento prodotto in storageAdmin.jsp
function checkPrice(form, indexError){
	let regex;
	let price;
	let priceError;
	
	regex = /^\d{0,8}(\.\d{1,4})?$/;
	
/*	price = document.forms[form]["prezzo_vetrina"].value;*/
	price = $(form + " input[name=prezzo_vetrina]").val();
	
/*	priceError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);*/
	priceError = $(form + " .ErrorParagraph")[indexError];
	
	if(regex.test(price)){
		priceError.innerHTML = "";
		return true;
	}else{
		priceError.innerHTML = "price not valid";
		return false;
	}
}

/*
function checkDate(form, indexError){
	let date;
	let dateError;
	let regex;
	
	regex = /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/;
	date = document.forms[form]["data_uscita"].value;
	dateError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);
	
	if(regex.test(date)){
		dateError.innerHTML = "";
		return true;
	}else{
		dateError.innerHTML = "date not valid";
		return false;
	}
}
*/
function checkSale(form, indexError){
	let sale;
	let saleError;
	let regex;
	
	regex = /^100(\.0{0,2})? *%?$|^\d{1,2}(\.\d{1,2})? *%?$/; //matches 	100% 100.00 % 95.4% 1.2 % 12
	
/*	sale = document.forms[form]["sconto"].value;*/
	sale = $(form + " input[name=sconto]").val();
	
/*	saleError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);*/
	saleError = $(form + " .ErrorParagraph")[indexError];
	
	if(regex.test(sale)){
		saleError.innerHTML = "";
		return true;
	}else{
		saleError.innerHTML = "Sale not valid";
		return false;
	}
}

function checkCopy(form, indexError){
	let copy;
	let copyError;
	let regex;
	
	regex = /^\d+$/;
/*	copy = document.forms[form]["#copie"].value;*/
	copy = $(form + " input[name='#copie']").val();
		
/*	copyError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);*/
	copyError = $(form + " .ErrorParagraph")[indexError];


	
	if(regex.test(copy)){
		copyError.innerHTML = "";
		return true;
	}else{
		copyError.innerHTML = "copy not valid";
		return false;
	}
}

function checkId(form, indexError){
	let id;
	let idError;
	let regex;
	
	regex = /^\d+$/;
	id = document.forms[form]["codice_prodotto"].value;
	idError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);
	
	if(regex.test(id)){
		idError.innerHTML = "";
		return true;
	}else{
		idError.innerHTML = "id not valid";
		return false;
	}
}



function checkCredentialsLogin(form){
	let counter;
	
	
	//non uso un and logico tra le varie funzioni di controllo perché altrimenti se il primo campo è sbagliato non mi controlla il secondo
	//essendo un and logico se il primo valore è falso a prescindere dal secondo l'output sarà falso
	counter = 0;
	
	if(checkEmail()){
		counter++;
	}
	if(checkPassword(form, 1)){
		counter++;
	}
	

	if(counter == 2){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		//document.getElementById("LoginButton").setAttribute('type', 'submit');
		return true;
	}else{
		return false;
	}
}

function checkCredentialsRegistration(form){
		let counter;
		
		
		counter = 0;
		
		if(checkDate(form, 0)){
			counter++;
		}
		
		if(checkPassword(form, 1)){
			counter++;
		}
		
		if(checkEmail()){
			counter++;
		}
		
		if(checkPhone(form, 3)){
			counter++;
		}
		
		
	if(counter == 4){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		return true;
	}else{
		return false;
	}
}

function checkDeliveryAddress(){
		let counter;
		
		
		counter = 0;
		
		if(checkCap()){
			counter++;
		}
		if(checkProvince()){
			counter++;
		}
		if(checkCity()){
			counter++;
		}
		
		
	if(counter == 3){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("addressButton").setAttribute('type', 'submit');
	}
}

function checkPaymentMethod(){
		let counter;
		
		
		counter = 0;
		
		if(checkCardNumber()){
		counter++;
		}
		if(checkCcv()){
		counter++;
		}
		if(checkDate()){
		counter++;
		}
		
		
	if(counter == 3){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("paymentMethodButton").setAttribute('type', 'submit');
	}
}

function checkInsert(form){
		let counter;
		
		
		counter = 0;
		
		if(checkPrice(form, 0)){
			counter++;
		}
		if(checkDate(form, 1)){
			counter++;
		}
		if(checkSale(form, 2)){
			counter++;
		}
		if(checkCopy(form, 3)){
			counter++;
		}
		
		
	if(counter == 4){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
	document.getElementById("insertButton").setAttribute('type', 'submit');
	}
}

/*
function checkModify(form){
		let counter;
		
		counter = 0;
		
		if(checkId(form, 0)){
			counter++;
		}
		if(checkPrice(form, 1)){
			counter++;
		}
		if(checkDate(form, 2)){
			counter++;
		}
		if(checkSale(form, 3)){
			counter++;
		}
		if(checkCopy(form, 4)){
			counter++;
		}

		
		
	if(counter == 5){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("modifyButton").setAttribute('type', 'submit');
	}
}
*/

function checkModify(form, checkFunction){
	if(checkFunction(form, 0)){
		document.forms[form].getElementsByTagName("button")[0].setAttribute('type', 'submit');
	}
}

/*
function checkId(form, indexError){
	let id;
	let idError;
	let regex;
	
	regex = /^\d+$/;
	id = document.forms[form]["codice_prodotto"].value;
	idError = document.forms[form].getElementsByClassName("ErrorParagraph").item(indexError);
	
	if(regex.test(id)){
		idError.innerHTML = "";
		return true;
	}else{
		idError.innerHTML = "id not valid";
		return false;
	}
}
*/

function checkModifyUserData(form){
		let counter;
		
		counter = 0;
		
		if(checkPassword(form, 0)){
			counter++;
		}

		if(checkPhone(form, 1)){
			counter++;
		}

		
		
	if(counter == 2){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("modifyUserDataButton").setAttribute('type', 'submit');
	}
}

function checkIdToDelete(form){

	
	if(checkId(form, 0)){
			return true;
	}else{
		return false;
	}
}