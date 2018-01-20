package ro.ark.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Museum;
import utils.Utils;

@Repository
public class MuseumDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Museum> getAll(String name){
		name = Utils.prepareForLikeStatement(name);
		
		return this.em.createNamedQuery(Museum.GET_ALL, Museum.class)
				.setParameter("name", name)
				.getResultList();
	}
}
