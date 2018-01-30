package ro.ark.server.entity.meta;

import ro.ark.server.entity.Author;

public class AuthorShort {

	private long id;
	private String name;
	private String image;
	private int numberOfArtworks;
	
	public AuthorShort(){}
	public AuthorShort(Author author){
		this.id = author.getId();
		this.name = author.getName();
		this.image = author.getImage();
		this.numberOfArtworks = author.getNumberOfArtworks();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumberOfArtworks() {
		return numberOfArtworks;
	}
	public void setNumberOfArtworks(int numberOfArtworks) {
		this.numberOfArtworks = numberOfArtworks;
	}
	
}
