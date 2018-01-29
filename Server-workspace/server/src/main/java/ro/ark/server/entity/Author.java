package ro.ark.server.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;

	//Info from DBPedia
	@Column(name="place_name")
	private String placeName;
	@Column
	private String country;
	@Column(name="movement_name")
	private String movementName;
	@Column
	private String influencers;
	@Column
	private String trainers;
	@Column(name="birthDate")
	private String birthDate;
	@Column(name="deathDate")
	private String deathDate;
	@Column(name="description")
	private String desc;
	@Column
	private String image;
	
	public Author(){
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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getMovementName() {
		if(movementName == null || movementName.isEmpty()) return new ArrayList<String>();
		
		return new ArrayList<>(Arrays.asList(movementName.split(",")));
	}

	public void setMovementName(List<String> movementName) {
		this.movementName = String.join(",", movementName);
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getInfluencers() {
		if(influencers == null || influencers.isEmpty()) return new ArrayList<String>();
		return new ArrayList<>(Arrays.asList(influencers.split(",")));
	}

	public void setInfluencers(List<String> influencers) {
		this.influencers = String.join(",", influencers);
	}

	public List<String> getTrainers() {
		if(trainers == null || trainers.isEmpty()) return new ArrayList<String>();
		return new ArrayList<>(Arrays.asList(trainers.split(",")));
	}

	public void setTrainers(List<String> trainers) {
		this.trainers = String.join(",", trainers);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", placeName=" + placeName + ", country=" + country
				+ ", movementName=" + movementName + ", influencers=" + influencers + ", trainers=" + trainers
				+ ", birthDate=" + birthDate + ", deathDate=" + deathDate + ", desc=" + desc + ", image=" + image + "]";
	}
	
}
