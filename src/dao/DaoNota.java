package dao;

import javax.persistence.Query;

import model.Nota;

public class DaoNota extends DaoEntity<Nota, Integer>{

	public Nota getNotaExist(Integer id, Integer uid){
		em.clear();
		try {
			Query q = em.createNativeQuery(" SELECT * FROM nota WHERE via_id = "+id+" "
					+ "AND usuario_id = "+uid+" ", Nota.class);
			
			return (Nota)q.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public DaoNota() {
		super(Nota.class);
	}

}
