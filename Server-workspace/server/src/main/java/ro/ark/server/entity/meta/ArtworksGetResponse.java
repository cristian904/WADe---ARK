package ro.ark.server.entity.meta;

import java.util.List;

import ro.ark.server.entity.Artwork;

public class ArtworksGetResponse {
	public List<Artwork> artworks;
	public int numberOfArtworks;
	
	public ArtworksGetResponse(){}
	public ArtworksGetResponse(List<Artwork> artworks, int numberOfArtworks){
		this.artworks = artworks;
		this.numberOfArtworks = numberOfArtworks;
	}
	
	public List<Artwork> getArtworks() {
		return artworks;
	}
	public void setArtworks(List<Artwork> artworks) {
		this.artworks = artworks;
	}
	public int getNumberOfArtworks() {
		return numberOfArtworks;
	}
	public void setNumberOfArtworks(int numberOfArtworks) {
		this.numberOfArtworks = numberOfArtworks;
	}
}
