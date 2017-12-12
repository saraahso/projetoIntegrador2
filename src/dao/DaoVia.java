package dao;

import javax.persistence.Query;

import model.Via;

public class DaoVia extends DaoEntity<Via, Integer>{

	public String getNotaDB(Integer id){
		em.clear();
		try {
			Query q = em.createNativeQuery(" SELECT AVG(nota) FROM nota WHERE via_id = "+id+" ");
			
			return q.getSingleResult().toString();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public DaoVia() {
		super(Via.class);
	}

}
