package ro.ark.server.entity.meta;

import java.util.List;

public class AuthorsByMovement {

	private String movementName;
	private List<AuthorShort> authors;
	
	public String getMovementName() {
		return movementName;
	}
	public void setMovementName(String movementName) {
		this.movementName = movementName;
	}
	public List<AuthorShort> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorShort> authors) {
		this.authors = authors;
	}
	
	
	
}
