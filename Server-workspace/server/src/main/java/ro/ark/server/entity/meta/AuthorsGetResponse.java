package ro.ark.server.entity.meta;

import java.util.List;

import ro.ark.server.entity.Author;

public class AuthorsGetResponse {
	public List<AuthorShort> authors;
	public int numberOfAuthors;
	
	public AuthorsGetResponse(){}
	public AuthorsGetResponse(List<AuthorShort> authors, int numberOfAuthors){
		this.authors = authors;
		this.numberOfAuthors = numberOfAuthors;
	}
	
	public List<AuthorShort> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorShort> authors) {
		this.authors = authors;
	}
	public int getNumberOfAuthors() {
		return numberOfAuthors;
	}
	public void setNumberOfAuthors(int numberOfAuthors) {
		this.numberOfAuthors = numberOfAuthors;
	}
}
