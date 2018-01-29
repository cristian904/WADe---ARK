package ro.ark.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ark.server.dao.MuseumDao;
import ro.ark.server.entity.Museum;
import ro.ark.server.entity.meta.IdNameValue;
import ro.ark.server.entity.meta.NameValue;

@Service
public class MuseumService {

	@Autowired
	MuseumDao museumDao;
	
	public Museum findById(long id){
		return museumDao.findById(id);
	}
	
	public List<Museum> getAllMuseums(){
		return museumDao.getAll(null);
	}

	public List<NameValue> groupByObjectOfWork(String id) {
		return museumDao.groupByObjectOfWork(id);
	}

	public List<NameValue> groupByState(String id) {
		return museumDao.groupByState(id);
	}
	
	public List<IdNameValue> groupByAuthor(String id){
		return museumDao.groupByAuthor(id);
	}

	public Integer count() {
		return museumDao.count();
	}
}
