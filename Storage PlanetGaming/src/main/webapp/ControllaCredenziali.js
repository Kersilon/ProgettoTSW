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

function checkAddress(){
	let address;
	let addressError;
	let regex;
}

function cardNumberCode(){
	let cardNumber;
	let cardNumberError;
	let regex;
}

function expirationDateCode(){
	let expirationDate;
	let expirationDateError;
	let regex;
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