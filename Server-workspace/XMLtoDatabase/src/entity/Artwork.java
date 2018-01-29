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
	
	String displayYear;
	
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
	
	public String getDisplayYear() {
		return displayYear;
	}

	public void setDisplayYear(String displayYear) {
		try{
			if("NEDATAT".equals(displayYear.toUpperCase())){
				displayYear = "0";
			}else if(displayYear.contains("19")){
				displayYear = displayYear.substring(displayYear.indexOf("19"), displayYear.indexOf("19") + 4);
			}else if(displayYear.contains("18")){
				displayYear = displayYear.substring(displayYear.indexOf("18"), displayYear.indexOf("18") + 4);
			}else if(displayYear.contains("17")){
				displayYear = displayYear.substring(displayYear.indexOf("17"), displayYear.indexOf("17") + 4);
			}else if(displayYear.contains("16")){
				displayYear = displayYear.substring(displayYear.indexOf("16"), displayYear.indexOf("16") + 4);
			}else if(displayYear.contains("15")){
				displayYear = displayYear.substring(displayYear.indexOf("15"), displayYear.indexOf("15") + 4);
			}else if(displayYear.contains("14")){
				displayYear = displayYear.substring(displayYear.indexOf("14"), displayYear.indexOf("14") + 4);
			}else if(displayYear.contains("13")){
				displayYear = displayYear.substring(displayYear.indexOf("13"), displayYear.indexOf("13") + 4);
			}else if(displayYear.contains("XXI")){
				displayYear = "2000";
			}else if(displayYear.contains("XX")){
				displayYear = "1900";
			}else if(displayYear.contains("XIX")){
				displayYear = "1800";
			}else if(displayYear.contains("XVIII")){
				displayYear = "1700";
			}else if(displayYear.contains("XVII")){
				displayYear = "1600";
			}else if(displayYear.contains("XVI")){
				displayYear = "1500";
			}else {
				displayYear = "0";
			}
			
			this.displayYear = displayYear;
		}catch(StringIndexOutOfBoundsException e){
			System.err.println("Error for " + this.getId() + " , " + this.getTitle());
		}
		
	}

	@Override
	public String toString() {
		return "Artwork [id=" + id + ", objectOfWork=" + objectOfWork + ", author=" + author + ", classification="
				+ classification + ", categories=" + categories + ", title=" + title + ", repositoryId=" + repositoryId
				+ ", repositoryName=" + repositoryName + ", description=" + description + ", measurements="
				+ measurements + ", imageUrl=" + imageUrl + ", displayYear=" + displayYear + ", displayState="
				+ displayState + "]";
	}


	
}
