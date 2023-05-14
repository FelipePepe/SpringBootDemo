package es.cursojava.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.cursojava.demo.dao.UsuarioDao;
import es.cursojava.demo.models.Usuario;
import es.cursojava.demo.utils.JWTUtil;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("api/login")
	public String login(@RequestBody Usuario usuario) {
		
		Usuario usuarioCompleto = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
		
		if (usuarioCompleto != null) {
			
			String tokenJWT = jwtUtil.create(String.valueOf(usuarioCompleto.getId()), usuarioCompleto.getPassword());
			
			return tokenJWT;
		}
		else  {
			return "KO";
		}
			
	}
	
}
