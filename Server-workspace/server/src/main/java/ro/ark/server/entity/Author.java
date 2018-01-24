package ro.ark.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;

	
	//Extra info from DBPedia
	@Transient
	private String placeName;
	@Transient
	private String country;
	@Transient
	private List<String> movementName;
	@Transient
	private String birthDate;
	@Transient
	private String deathDate;
	@Transient
	private String desc;
	@Transient
	private String image;
	
	public Author(){
		movementName = new ArrayList<>();
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
		return movementName;
	}

	public void setMovementName(List<String> movementName) {
		this.movementName = movementName;
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

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", placeName=" + placeName + ", country=" + country
				+ ", movementName=" + movementName + ", birthDate=" + birthDate + ", deathDate=" + deathDate + ", desc="
				+ desc + ", image=" + image + "]";
	}
	
	
}
