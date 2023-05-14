package es.cursojava.demo.dao;

import java.util.List;

import es.cursojava.demo.models.Usuario;

public interface UsuarioDao {
	
	List<Usuario> getUsuarios();
	
	void eliminar(long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

	
	
	
}
