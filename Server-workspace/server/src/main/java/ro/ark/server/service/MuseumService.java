package ro.ark.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ark.server.dao.ArtworkDao;
import ro.ark.server.dao.MuseumDao;
import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Museum;

@Service
public class MuseumService {

	@Autowired
	MuseumDao museumDao;
	
	
	public List<Museum> getAllMuseums(){
		return museumDao.getAll(null);
	}
}
