package ro.ark.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ark.server.dao.ArtworkDao;
import ro.ark.server.dao.MuseumDao;
import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Museum;

@Service
public class ArtworkService {

	@Autowired
	ArtworkDao artworkDao;
	
	@Autowired
	MuseumDao museumDao;
	
	public List<Artwork> getArtwork(String title, String author, String museum, String repositoryId, int pageSize, int pageNumber) {
		List<Museum> museums = museumDao.getAll(museum);
		List<String> museumIds = museums.stream().map(m -> m.getRepositoryId()).collect(Collectors.toList());
		return artworkDao.get(title, author, museumIds, repositoryId, pageSize, pageNumber);
	}

	public int getNumberOfArtworks(String title, String author, String museum, String repositoryId) {
		List<Museum> museums = museumDao.getAll(museum);
		List<String> museumIds = museums.stream().map(m -> m.getRepositoryId()).collect(Collectors.toList());
		return artworkDao.getCount(title, author, museumIds, repositoryId);
	}

	public Artwork getSingleArtwork(Long id) {
		return artworkDao.getSingle(id);
	}

}
