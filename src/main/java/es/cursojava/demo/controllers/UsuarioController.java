package es.cursojava.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import es.cursojava.demo.dao.UsuarioDao;
import es.cursojava.demo.models.Usuario;
import es.cursojava.demo.utils.JWTUtil;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private boolean validarToken(String token)
	{
		String usuarioId = jwtUtil.getKey(token);
		return usuarioId != null;
	}

	@GetMapping("api/usuarios")
	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
		
		if (validarToken(token))
		{
			return usuarioDao.getUsuarios();
		} else {
			return null;
		}
	}
	
	@PostMapping("api/usuarios")
	public void registrarUsuario(@RequestBody Usuario usuario) {
		
		Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
		
		String hashPassword = argon2.hash(1, 1024, 1, usuario.getPassword().toCharArray());
		
		usuario.setPassword(hashPassword);
		
		usuarioDao.registrar(usuario);
	}
	
	@PutMapping("api/usuarios/{id}")
	public Usuario editar(@RequestHeader(value="Authorization") String token, @PathVariable long id) {
		Usuario usuario = null;
		return usuario;
	}
	
	@DeleteMapping("api/usuarios/{id}")
	public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable long id) {
		if (validarToken(token)) {
			usuarioDao.eliminar(id);
		}
		
	}
	
	@GetMapping("api/usuarios/{id}")
	public Usuario buscar(@RequestHeader(value="Authorization") String token, @PathVariable long id) {
		if (validarToken(token)) {
		Usuario usuario = new Usuario();
		return usuario;
		} else {
			return null;
		}
	}
	
}
