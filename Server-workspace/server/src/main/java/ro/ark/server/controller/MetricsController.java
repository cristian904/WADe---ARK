package ro.ark.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.ark.server.entity.meta.IdNameValue;
import ro.ark.server.entity.meta.NameValue;
import ro.ark.server.entity.meta.StringIdNameValue;
import ro.ark.server.service.ArtworkService;
import ro.ark.server.service.AuthorService;
import ro.ark.server.service.MuseumService;

@Controller
public class MetricsController {
	
	@Autowired
	MuseumService museumService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ArtworkService artworkService;
	
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/museums/total-count")
	 public ResponseEntity<Integer> getMuseumsCount() {
		 Integer response = museumService.count();
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/authors/total-count")
	 public ResponseEntity<Integer> getAuthorsCount() {
		 Integer response = authorService.getNumberOfAuthors("");
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/artworks/total-count")
	 public ResponseEntity<Integer> getArtworksCount() {
		 Integer response = artworkService.getNumberOfArtworks("", "", "", "");
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/museum/{id}/group-by/object-of-work")
	 public ResponseEntity<List<NameValue>> getMuseumsGroupByObjOfWork(
			 @PathVariable String id	
			 ) {
		 List<NameValue> response = museumService.groupByObjectOfWork(id);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/museum/{id}/group-by/state")
	 public ResponseEntity<List<NameValue>> getAllMuseumsGroupByState(
			 @PathVariable String id	
			 ) {
		 List<NameValue> response = museumService.groupByState(id);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/museum/{id}/group-by/author")
	 public ResponseEntity<List<IdNameValue>> getAllMuseumsGroupByAuthor(
			 @PathVariable String id	
			 ) {
		 List<IdNameValue> response = museumService.groupByAuthor(id);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/author/{id}/group-by/museum")
	 public ResponseEntity<List<StringIdNameValue>> getAllAuthorsGroupByMuseum(
			 @PathVariable long id	
			 ) {
		 List<StringIdNameValue> response = authorService.groupByMuseum(id);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/author/{id}/group-by/object-of-work")
	 public ResponseEntity<List<NameValue>> getAuthorsGroupByObjOfWork(
			 @PathVariable long id	
			 ) {
		 List<NameValue> response = authorService.groupByObjectOfWork(id);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
}
