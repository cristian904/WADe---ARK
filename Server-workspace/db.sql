CREATE TABLE artworks (
	id SERIAL PRIMARY KEY,
	recID VARCHAR(255) UNIQUE,
	object_of_work VARCHAR(255),
	classiciations VARCHAR(1024),
	categories VARCHAR(1024),
	
	title VARCHAR(255),
	author_id VARCHAR(512),
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

ALTER TABLE authors 
	ADD COLUMN place_name VARCHAR(512),
	ADD COLUMN country VARCHAR(512),
	ADD COLUMN movement_name VARCHAR(2048),
	ADD COLUMN influencers VARCHAR(2048),
	ADD COLUMN trainers VARCHAR(2048),
	ADD COLUMN birthDate VARCHAR(255),
	ADD COLUMN deathDate VARCHAR(255),
	ADD COLUMN description VARCHAR(2048),
	ADD COLUMN image VARCHAR(512);
	