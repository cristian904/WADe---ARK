package ro.ark.server.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Author;
import ro.ark.server.entity.meta.IdNameValue;
import ro.ark.server.entity.meta.NameValue;
import ro.ark.server.entity.meta.StringIdNameValue;
import utils.Utils;

@Repository
public class AuthorDao {

	@PersistenceContext
	EntityManager em;
	
	public Author findById(long id){
		return em.find(Author.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Author> getAll(String name, int pageSize, int pageNumber) {
		name = Utils.prepareForLikeStatement(name);
		
		List<Object[]> results = em.createNativeQuery("select authors.id, authors.name, count(artworks.id) from authors, artworks "
				+ "where authors.id = artworks.author_id and upper(authors.name) like :name "
				+ "group by authors.name, authors.id "
				+ "order by count(artworks.id) desc")
		.setParameter("name", name)
		.setMaxResults(pageSize)
		.setFirstResult(pageSize*pageNumber)
		.getResultList();
		
		List<Author> authors = new ArrayList<>();
		for(Object[] result : results){
			Author a = new Author();
			a.setId((long)(int)result[0]);
			a.setName((String)result[1]);
			authors.add(a);
		}
		
		return authors;
		
	}

	public int getCount(String name) {
		name = Utils.prepareForLikeStatement(name);
		return (int)((BigInteger)em.createNativeQuery("select count(authors.id) from authors "
				+ "where upper(authors.name) like :name ")
		.setParameter("name", name)
		.getSingleResult()).intValue();
	}

	@Transactional
	public void update(Author author) {
		this.em.merge(author);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StringIdNameValue> groupByMuseum(long id){
		List<Object[]> results = this.em.createNativeQuery(
				"select museums.repositoryid, museums.repository_name, count(museums.repository_name) from artworks, museums, authors " 
				+ "where (artworks.repositoryid = museums.repositoryid) " 
				+ "and artworks.author_id = authors.id "
				+ "and authors.id = ? "
				+ "group by museums.repositoryid, museums.repository_name ")
			.setParameter(1, id)
			.getResultList();
		
		List<StringIdNameValue> idNameValues = new ArrayList<>();
		for(Object[] result : results){
			idNameValues.add(new StringIdNameValue((String)result[0], (String)result[1], ((BigInteger)result[2]).intValue()));
		}
		return idNameValues;
	}
	
	@SuppressWarnings("unchecked")
	public List<NameValue> groupByObjectOfWork(long id) {
		List<Object[]> results = this.em.createNativeQuery("select object_of_work, count(id) "
				+ "from artworks where author_id = ? "
				+ "group by object_of_work")
		.setParameter(1, id)
		.getResultList();
		
		List<NameValue> nameValues = new ArrayList<>();
		for(Object[] result : results){
			nameValues.add(new NameValue((String)result[0], ((BigInteger)result[1]).intValue()));
		}
		
		return nameValues;
	}
}
