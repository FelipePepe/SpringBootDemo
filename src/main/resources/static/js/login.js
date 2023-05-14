// Call the dataUsuarios jQuery plugin
$(document).ready(function() {
	// on ready
});

iniciarSesion = async function() {
	
	let datos = {};
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
	
	const request = await fetch('api/login', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(datos)
	});

	const response = await request.text();
	
	if (response != 'KO'){
		localStorage.token = response;
		localStorage.email = datos.email;
		window.location.href='usuarios.html'
	} else {
		alert ('Vuelva a intentarlo');
	}
	
	console.log(response);	
		
}
