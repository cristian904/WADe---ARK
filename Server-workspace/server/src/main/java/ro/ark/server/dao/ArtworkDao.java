package ro.ark.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Author;
import utils.Utils;

@Repository
public class ArtworkDao {
	
	@PersistenceContext
	EntityManager em;

	public List<Artwork> get(String title, String author, List<String> museumIds, String repositoryId, int pageSize, int pageNumber) {
		title = Utils.prepareForLikeStatement(title);
		author = Utils.prepareForLikeStatement(author);
		if(repositoryId == null || repositoryId.isEmpty()){
			repositoryId = "%%";
		}
		return em.createNamedQuery(Artwork.GET_ARTWORK, Artwork.class)
			.setParameter("title", title)
			.setParameter("author", author)
			.setParameter("museums", museumIds)
			.setParameter("repositoryId", repositoryId)
			.setMaxResults(pageSize)
			.setFirstResult(pageSize*pageNumber)
			.getResultList();
	}

	public int getCount(String title, String author, List<String> museumIds, String repositoryId) {
		title = Utils.prepareForLikeStatement(title);
		author = Utils.prepareForLikeStatement(author);
		if(repositoryId == null || repositoryId.isEmpty()){
			repositoryId = "%%";
		}
		
		return (int)(long)em.createNamedQuery(Artwork.GET_ARTWORK_COUNT)
			.setParameter("title", title)
			.setParameter("author", author)
			.setParameter("museums", museumIds)
			.setParameter("repositoryId", repositoryId)
			.getSingleResult();
	}

	public Artwork getSingle(Long id) {
		return em.find(Artwork.class, id);
		
	}

}
