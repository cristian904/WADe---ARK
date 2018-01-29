package ro.ark.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "museums")
@NamedQueries({
	@NamedQuery(name = Museum.GET_ALL, query = "SELECT m FROM Museum m WHERE UPPER(m.repositoryName) LIKE :name "),
	@NamedQuery(name = Museum.GET_COUNT, query = "SELECT count(m.id) FROM Museum m")
})
public class Museum {
	public static final String GET_ALL = "getAllMuseums";
	public static final String GET_COUNT = "getCountMuseum";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="repositoryid")
	private String repositoryId;
	
	@Column(name="repository_name")
	private String repositoryName;
	
	@Column
	private String city;

	@Column(name="long")
	private Float longitude;
	
	@Column(name="lat")
	private Float latitude;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
}
