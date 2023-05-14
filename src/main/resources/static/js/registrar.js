// Call the dataUsuarios jQuery plugin
$(document).ready(function() {
	// on ready
});

registrarUsuarios = async function() {
	
	let datos = {};
	datos.nombre = document.getElementById('txtNombre').value;
	datos.apellidos = document.getElementById('txtApellidos').value;
	datos.telefono = document.getElementById('txtTelefono').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
	
	let repetirPassword = document.getElementById('txtRepetirPassword').value;
	
	if (datos.password != repetirPassword)
	{
		alert('La contraseña no es correcta');
		return;
	}
	
	await fetch('api/usuarios', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(datos)
	});
	
	alert('La cuenta fue creada con éxito');
	
	window.location.href = 'login.html';
		
		
}
