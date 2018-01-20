package ro.ark.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Museum;
import ro.ark.server.service.ArtworkService;
import ro.ark.server.service.MuseumService;

@Controller
public class MainController {
	@Autowired
	MuseumService museumService;
	@Autowired
	ArtworkService artworkService;
	
	@RequestMapping("/helloworld")
	 public ResponseEntity<String> hello() {
	  return new ResponseEntity<>("Hello!", HttpStatus.OK);
	 }
	 

	 @RequestMapping("/museums")
	 public ResponseEntity<List<Museum>> getAllMuseums() {
		 return new ResponseEntity<>(museumService.getAllMuseums(), HttpStatus.OK);
	 }
	 
	 @RequestMapping("/artworks")
	 public ResponseEntity<List<Artwork>> getArtwork(
			 @RequestParam(value = "title", required = false) String title,
			 @RequestParam(value = "author", required = false) String author,
			 @RequestParam(value = "museum", required = false) String museum){
		 
		 
		 return new ResponseEntity<>(artworkService.getArtwork(title, author, museum), HttpStatus.OK);
	 }
}
