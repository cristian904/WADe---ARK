package ro.ark.server.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Author;
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
}
