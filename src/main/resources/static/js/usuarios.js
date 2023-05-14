// Call the dataUsuarios jQuery plugin
$(document).ready(function() {
	cargarUsuarios();
  $('#usuarios').DataTable();
});

cargarUsuarios = async function() {
		const rawResponse = await fetch('api/usuarios', {
			method: 'GET',
			headers: getHeaders()
		});
		
		const usuarios = await rawResponse.json();
	
		console.log(usuarios);
		
		let usuariosHTML = '';
		
		for(let usuario of usuarios){
			
			let botonEliminar = '<a href="#" class="btn btn-danger btn-circle btn-sm" onclick="eliminarUsuario(' + usuario.id + ')"><i class="fas fa-trash"></i></a>';
			
			let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellidos + '</td><td>' + usuario.email + '</td><td>' + usuario.telefono + '</td><td>' + botonEliminar + '</td></tr>';
			
			usuariosHTML += usuarioHtml;
		}
		
		document.querySelector('#usuarios tbody').outerHTML = '<tbody>' + usuariosHTML + '</tbody>';
		
}

getHeaders = function() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization' : localStorage.token
	};
}

eliminarUsuario= async function(id){
	
	if (confirm('¿desea eliminar este usuario?'))
	{
		await fetch('api/usuarios/' + id , {
				method: 'delete',
				headers: getHeaders()
		});
		
		cargarUsuarios();
	}
}
