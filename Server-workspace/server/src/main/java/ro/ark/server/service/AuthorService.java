package ro.ark.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ark.server.dao.AuthorDao;
import ro.ark.server.entity.Author;
import ro.ark.server.entity.meta.AuthorShort;
import ro.ark.server.entity.meta.AuthorsByMovement;
import ro.ark.server.entity.meta.NameValue;
import ro.ark.server.entity.meta.StringIdNameValue;

@Service
public class AuthorService {
	@Autowired
	AuthorDao authorDao;

	public Author getById(long id){
		return authorDao.findById(id);
	}

	public List<Author> getAll(String name, int pageSize, int pageNumber) {
		return authorDao.getAll(name, pageSize, pageNumber);
	}

	public int getNumberOfAuthors(String name) {
		return authorDao.getCount(name);
	}

	public void updateAuthor(Author author) {
		authorDao.update(author);
	}
	
	public List<NameValue> groupByObjectOfWork(long id){
		return authorDao.groupByObjectOfWork(id);
	}
	public List<StringIdNameValue> groupByMuseum(long id){
		return authorDao.groupByMuseum(id);
	}

	public List<AuthorsByMovement> getSameMovementsRecommendations(long id, List<String> movementName) {
		List<AuthorsByMovement> list = new ArrayList<>();
		for (String movement : movementName){
			AuthorsByMovement authorsByMovement = new AuthorsByMovement();
			authorsByMovement.setAuthors(
					authorDao.getByMovement(movement)
						.stream().map(a -> new AuthorShort(a))
						.collect(Collectors.toList())
					);
			
			authorsByMovement.setAuthors(
					authorsByMovement.getAuthors().stream().filter(a -> a.getId() != id).collect(Collectors.toList())
					);
			
			authorsByMovement.setMovementName(movement);	
			list.add(authorsByMovement);
		}
		list = list.stream().filter(a -> a.getAuthors().size() > 0).collect(Collectors.toList());
		return list;
		
	}
}
