package entity;

import java.util.ArrayList;
import java.util.List;

public class Artwork {

	String id;
	//Object classification
	String objectOfWork;
	String author;
	List<String> classification;
	List<String> categories;
	
	//Object identification
	String title;
	String repositoryId;
	String repositoryName;
	String description;
	String measurements;
	String imageUrl;
	
	String displayState;
	
	public Artwork(){
		classification = new ArrayList<>();
		categories = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjectOfWork() {
		return objectOfWork;
	}
	public void setObjectOfWork(String objectOfWork) {
		this.objectOfWork = objectOfWork;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getClassification() {
		return classification;
	}
	public void setClassification(List<String> classification) {
		this.classification = classification;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMeasurements() {
		return measurements;
	}
	public void setMeasurements(String measurements) {
		this.measurements = measurements;
	}
	public String getDisplayState() {
		return displayState;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setDisplayState(String displayState) {
		this.displayState = displayState;
	}
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Artwork [id=" + id + ", objectOfWork=" + objectOfWork + ", classification=" + classification
				+ ", categories=" + categories + ", title=" + title + ", repositoryId=" + repositoryId
				+ ", repositoryName=" + repositoryName + ", description=" + description + ", measurements="
				+ measurements + ", displayState=" + displayState + "]";
	}

	
}
