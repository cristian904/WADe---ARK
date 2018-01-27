package ro.ark.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "artworks")
@NamedQueries({
	@NamedQuery(name = Artwork.GET_ARTWORK, 
			query = "SELECT a FROM Artwork a WHERE UPPER(a.title) LIKE :title AND UPPER(a.author.name) LIKE :author AND a.repositoryId IN :museums"),
	@NamedQuery(name = Artwork.GET_ARTWORK_COUNT, 
	query = "SELECT count(a.id) FROM Artwork a WHERE UPPER(a.title) LIKE :title AND UPPER(a.author.name) LIKE :author AND a.repositoryId IN :museums"),
})
public class Artwork {
	public static final String GET_ARTWORK = "getArtwork";
	public static final String GET_ARTWORK_COUNT = "getArtworkCount";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="recid")
	private String recId;
	
	@Column(name="object_of_work")
	private String objectOfWork;
	
	@Column
	private String classifications;
	
	@Column
	private String categories;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String measurements;
	
	@Column(name="repositoryid")
	private String repositoryId;
	
	@Column(name="display_state")
	private String displayState;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="author_id")
	private Author author;
	
	@Column(name="image_url")
	private String imageUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getObjectOfWork() {
		return objectOfWork;
	}

	public void setObjectOfWork(String objectOfWork) {
		this.objectOfWork = objectOfWork;
	}

	public String getClassifications() {
		return classifications;
	}

	public void setClassifications(String classifications) {
		this.classifications = classifications;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getDisplayState() {
		return displayState;
	}

	public void setDisplayState(String displayState) {
		this.displayState = displayState;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
