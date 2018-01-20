CREATE TABLE artworks (
	id SERIAL PRIMARY KEY,
	recID VARCHAR(255) UNIQUE,
	object_of_work VARCHAR(255),
	classiciations VARCHAR(1024),
	categories VARCHAR(1024),
	
	title VARCHAR(255),
	author VARCHAR(512),
	description VARCHAR(255),
	measurements VARCHAR(255),
	repositoryID VARCHAR(255) REFERENCES museums(repositoryID),
	image_url TEXT,
 
	display_state VARCHAR(255)
);

CREATE TABLE museums(
	id SERIAL PRIMARY KEY,
	
	repositoryID VARCHAR(255) UNIQUE,
	repository_name VARCHAR(255)
);