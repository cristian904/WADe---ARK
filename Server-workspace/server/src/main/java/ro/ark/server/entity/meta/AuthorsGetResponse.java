package ro.ark.server.entity.meta;

import java.util.List;

import ro.ark.server.entity.Artwork;
import ro.ark.server.entity.Author;

public class AuthorsGetResponse {
	public List<Author> authors;
	public int numberOfAuthors;
	
	public AuthorsGetResponse(){}
	public AuthorsGetResponse(List<Author> authors, int numberOfAuthors){
		this.authors = authors;
		this.numberOfAuthors = numberOfAuthors;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public int getNumberOfAuthors() {
		return numberOfAuthors;
	}
	public void setNumberOfAuthors(int numberOfAuthors) {
		this.numberOfAuthors = numberOfAuthors;
	}
}
