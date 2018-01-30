package ro.ark.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.ark.server.entity.Author;
import ro.ark.server.entity.meta.AuthorsByMovement;
import ro.ark.server.service.AuthorService;

@Controller
public class RecommendationController {

	@Autowired
	AuthorService authorService;
	
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/author/{id}/recommendation/movements")
	 public ResponseEntity<List<AuthorsByMovement>> getArtworksCount(
			 @PathVariable(value = "id", required = false) long id
			 ) {
		 Author author = authorService.getById(id);
		 if(author == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 if(author.getMovementName() == null || author.getMovementName().isEmpty())
			 return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		 
		 List<AuthorsByMovement> list = authorService.getSameMovementsRecommendations(author.getId(), author.getMovementName());
		 
		 return new ResponseEntity<>(list, HttpStatus.OK);
	 }
	
}
