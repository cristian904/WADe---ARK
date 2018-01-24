package ro.ark.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Author;

@Repository
public class AuthorDao {

	@PersistenceContext
	EntityManager em;
	
	public Author findById(long id){
		return em.find(Author.class, id);
	}
}
