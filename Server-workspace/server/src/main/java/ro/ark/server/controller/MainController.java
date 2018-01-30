package ro.ark.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Author;
import ro.ark.server.entity.Museum;
import ro.ark.server.entity.meta.ArtworksGetResponse;
import ro.ark.server.entity.meta.AuthorShort;
import ro.ark.server.entity.meta.AuthorsGetResponse;
import ro.ark.server.service.ArtworkService;
import ro.ark.server.service.AuthorService;
import ro.ark.server.service.DBPediaService;
import ro.ark.server.service.MuseumService;

@Controller
public class MainController {
	@Autowired
	MuseumService museumService;
	@Autowired
	ArtworkService artworkService;
	@Autowired
	AuthorService authorService;
	
	@Autowired
	DBPediaService dbPediaService;

	@CrossOrigin(origins = "*")
	@RequestMapping("/helloworld")
	 public ResponseEntity<String> hello() {
	  return new ResponseEntity<>("Hello!", HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/museums")
	 public ResponseEntity<List<Museum>> getAllMuseums() {
		 return new ResponseEntity<>(museumService.getAllMuseums(), HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/artwork/{id}")
	 public ResponseEntity<Artwork> getArtwork(@PathVariable Long id){	 	
		 Artwork a = artworkService.getSingleArtwork(id);
		 if(a == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 
		 return new ResponseEntity<>(
				 a,
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/artworks")
	 public ResponseEntity<ArtworksGetResponse> getArtworks(
			 @RequestParam(value = "title", required = false) String title,
			 @RequestParam(value = "author", required = false) String author,
			 @RequestParam(value = "museum", required = false) String museum,
			 @RequestParam(value = "objectOfWork", required = false) String objectOfWork,
			 @RequestParam(value = "repositoryId", required = false) String repositoryId,
			 @RequestParam(value = "pageNumber") int pageNumber,
			 @RequestParam(value = "pageSize") int pageSize
			 ){
		 pageNumber-- ;
		 
		 List<Artwork> artworks = artworkService.getArtwork(title, author, museum, repositoryId, objectOfWork, pageSize, pageNumber);
		 
		 if(artworks == null || artworks.isEmpty()){
			 return new ResponseEntity<>(null, HttpStatus.OK);
		 }
		 
		 ArtworksGetResponse response =new ArtworksGetResponse(
				 artworks,
				 artworkService.getNumberOfArtworks(title, author, museum, repositoryId, objectOfWork));
		 
		 
		 return new ResponseEntity<>(
				 response,
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/authors")
	 public ResponseEntity<AuthorsGetResponse> getAuthors(
			 @RequestParam(value = "name", required = false) String name,
			 @RequestParam(value = "pageNumber") int pageNumber,
			 @RequestParam(value = "pageSize") int pageSize
			 ){

		 pageNumber-- ;
		 List<AuthorShort> authors = 
				 authorService.getAll(name, pageSize, pageNumber)
				 .stream()
				 .map(a -> new AuthorShort(a))
				 .collect(Collectors.toList());
		 
		 AuthorsGetResponse response = new AuthorsGetResponse(
				 authors,
				 authorService.getNumberOfAuthors(name));
		 
		 return new ResponseEntity<>(
				 response,
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/author/{id}")
	 public ResponseEntity<?> getAuthor(@PathVariable Long id){
		 Author author = authorService.getById(id);
		 if(author == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 author = dbPediaService.getAuthorInfo(author);
		 authorService.updateAuthor(author);
		 
		 return new ResponseEntity<>(author, HttpStatus.OK);
	 }
}
