package ro.ark.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ark.server.dao.AuthorDao;
import ro.ark.server.entity.Author;

@Service
public class AuthorService {
	@Autowired
	AuthorDao authorDao;

	public Author getById(long id){
		return authorDao.findById(id);
	}
}
