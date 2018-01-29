package ro.ark.server.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Museum;
import ro.ark.server.entity.meta.IdNameValue;
import ro.ark.server.entity.meta.NameValue;
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

	public Museum findById(long id) {
		return this.em.find(Museum.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<NameValue> groupByObjectOfWork(String id) {
		List<Object[]> results = this.em.createNativeQuery("select object_of_work, count(id) "
				+ "from artworks where repositoryid = ? "
				+ "group by object_of_work")
		.setParameter(1, id)
		.getResultList();
		
		List<NameValue> nameValues = new ArrayList<>();
		for(Object[] result : results){
			nameValues.add(new NameValue((String)result[0], ((BigInteger)result[1]).intValue()));
		}
		
		return nameValues;
	}

	@SuppressWarnings("unchecked")
	public List<NameValue> groupByState(String id) {
		List<Object[]> results = this.em.createNativeQuery("select display_state, count(id) "
				+ "from artworks where repositoryid = ? "
				+ "group by display_state")
		.setParameter(1, id)
		.getResultList();
		
		List<NameValue> nameValues = new ArrayList<>();
		for(Object[] result : results){
			nameValues.add(new NameValue((String)result[0], ((BigInteger)result[1]).intValue()));
		}
		
		return nameValues;
	}
	
	@SuppressWarnings("unchecked")
	public List<IdNameValue> groupByAuthor(String id){
		List<Object[]> results = this.em.createNativeQuery(
				"select authors.id, authors.name, count(author_id) from artworks, museums, authors " 
				+ "where (artworks.repositoryid = museums.repositoryid) " 
				+ "and artworks.author_id = authors.id "
				+ "and museums.repositoryid= ? "
				+ "group by authors.name, authors.id ")
			.setParameter(1, id)
			.getResultList();
		
		List<IdNameValue> idNameValues = new ArrayList<>();
		for(Object[] result : results){
			idNameValues.add(new IdNameValue((long)(int)result[0], (String)result[1], ((BigInteger)result[2]).intValue()));
		}
		return idNameValues;
	}

	public Integer count() {
		return (int)(long)em.createNamedQuery(Museum.GET_COUNT)
				.getSingleResult();
	}
}
