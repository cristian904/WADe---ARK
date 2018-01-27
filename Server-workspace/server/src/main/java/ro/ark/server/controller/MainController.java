package ro.ark.server.controller;

import java.util.List;

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
		 return new ResponseEntity<>(
				 artworkService.getSingleArtwork(id),
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/artworks")
	 public ResponseEntity<ArtworksGetResponse> getArtworks(
			 @RequestParam(value = "title", required = false) String title,
			 @RequestParam(value = "author", required = false) String author,
			 @RequestParam(value = "museum", required = false) String museum,
			 @RequestParam(value = "pageNumber", required = false) int pageNumber,
			 @RequestParam(value = "pageSize", required = false) int pageSize
			 ){
		 
		 ArtworksGetResponse response =new ArtworksGetResponse(
				 artworkService.getArtwork(title, author, museum, pageSize, pageNumber),
				 artworkService.getNumberOfArtworks(title, author, museum));
		 
		 return new ResponseEntity<>(
				 response,
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/authors")
	 public ResponseEntity<AuthorsGetResponse> getAuthors(
			 @RequestParam(value = "name", required = false) String name,
			 @RequestParam(value = "pageNumber", required = false) int pageNumber,
			 @RequestParam(value = "pageSize", required = false) int pageSize
			 ){
		 
		 AuthorsGetResponse response = new AuthorsGetResponse(
				 authorService.getAll(name, pageSize, pageNumber),
				 authorService.getNumberOfAuthors(name));
		 
		 return new ResponseEntity<>(
				 response,
				 HttpStatus.OK);
	 }

	 @CrossOrigin(origins = "*")
	 @RequestMapping("/author/{id}")
	 public ResponseEntity<?> getAuthor(@PathVariable Long id){
		 Author author = authorService.getById(id);
		 
		 author = dbPediaService.getAuthorInfo(author);
		 
		 return new ResponseEntity<>(author, HttpStatus.OK);
	 }
}
