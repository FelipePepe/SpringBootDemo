package es.cursojava.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import es.cursojava.demo.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuarios() {
		
		String query = "FROM Usuario";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void eliminar(long id) {
		
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.remove(usuario);
		
	}

	@Override
	public void registrar(Usuario usuario) {
		
		entityManager.merge(usuario);
		
	}

	@Override
	public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {

		boolean esValida = false;
		Usuario usuarioCompleto = null;
		
		String query = "FROM Usuario WHERE email = :mail" ;
		List<Usuario> lstUsuarios = entityManager.createQuery(query)
				.setParameter("mail", usuario.getEmail())
				.getResultList();
		
		if (lstUsuarios != null && !lstUsuarios.isEmpty())
		{
			Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
			esValida = argon2.verify(lstUsuarios.get(0).getPassword(), usuario.getPassword());
			if (esValida) {
				usuarioCompleto = lstUsuarios.get(0);
			}
		}
		
		
		
		return usuarioCompleto;
		
	}
}
