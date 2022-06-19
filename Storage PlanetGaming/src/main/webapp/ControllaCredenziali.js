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

function checkPassword(){
	let password;
	let passwordError;
	let regex;
	
	regex = /(?=.*[0-9])/;	//deve esserci almeno un numero
	password = document.getElementById("password").value;
	passwordError = document.getElementById("passwordError");

	if(regex.test(password)){
		passwordError.innerHTML = "";
		return true;
	}else{
		passwordError.innerHTML = "Password not valid";
		return false;
	}
}

function checkBirthDate(){
	let birthDate;
	let birthDateError;
	let regex;
	
	regex = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;	//data nel formato dd-/mm-/aaaa va bene sia "-" o "/" per separare i caratteri
	birthDate = document.getElementById("dataNascita").value;
	birthDateError = document.getElementById("birthDateError");
	
	if(regex.test(birthDate)){
		birthDateError.innerHTML = "";
		return true;
	}else{
		birthDateError.innerHTML = "Birth Date not valid";
		return false;
	}
}

function checkPhone(){
	let phone;
	let phoneError;
	let regex;
	
	regex = /^(\+)?([ 0-9]){10,16}$/; //si possono inserire prefissi come "+39"
	phone = document.getElementById("telefono").value;
	phoneError = document.getElementById("phoneError");
	
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



function checkCredentialsLogin(){
	let counter;
	
	
	//non uso un and logico tra le varie funzioni di controllo perché altrimenti se il primo campo è sbagliato non mi controlla il secondo
	//essendo un and logico se il primo valore è falso a prescindere dal secondo l'output sarà falso
	counter = 0;
	
	if(checkEmail()){
		counter++;
	}
	if(checkPassword()){
		counter++;
	}
	

	if(counter == 2){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("LoginButton").setAttribute('type', 'submit');
	}
}

function checkCredentialsRegistration(){
		let counter;
		
		
		counter = 0;
		
		if(checkEmail()){
		counter++;
		}
		if(checkPassword()){
		counter++;
		}
		if(checkBirthDate()){
		counter++;
		}
		if(checkPhone()){
		counter++;
		}
		
		
	if(counter == 4){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("RegistrationButton").setAttribute('type', 'submit');
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
		if(checkExpirationDate()){
		counter++;
		}
		
		
	if(counter == 3){
		//faccio diventare il pulsamnte che controlla i campi un pulsante di submit
		document.getElementById("paymentMethodButton").setAttribute('type', 'submit');
	}
}