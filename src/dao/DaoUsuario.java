package dao;

import javax.persistence.Query;

import model.Usuario;

public class DaoUsuario extends DaoEntity<Usuario, Integer>{

	public DaoUsuario() {
		super(Usuario.class);
	}

	public Usuario checkLogin(String email, String senha){
		em.clear();
		try {
			Query q = em.createNativeQuery(" SELECT * FROM usuario WHERE email = '"+email+"' "
					+ "AND ativo = true "
					+ "AND senha = '"+senha+"'", Usuario.class);
			
			return (Usuario) q.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}
	
}
