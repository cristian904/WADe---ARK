package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entity.Artwork;

public class ArtworkDAO {
	PostgresConnection pgConn;
	
	public ArtworkDAO(){
		pgConn = new PostgresConnection();
	}
	
	public boolean checkMuseumExists(String repoID){
		String SQL = "SELECT count(*) FROM museums"
				+ " WHERE repositoryid = ?";
		
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, repoID);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs.getInt(1) > 0) return true;
			else return false;
		}catch(SQLException e){
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	
	public void addMuseum(String repoID, String repoName){
		if(checkMuseumExists(repoID)) return;
		
		String SQL = "INSERT INTO museums (repositoryid, repository_name, city)"
				+ " VALUES (?, ?, ?)";
		
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, repoID);
			
			String[] s = repoName.split("- ");
			repoName = s[0];
			String city = "";
			if(s.length > 1) city = s[1];
			stmt.setString(2, repoName);
			stmt.setString(3, city);
			System.out.println("in " + city);
			stmt.executeUpdate();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		
	}
	
	public int checkAuthorExists(String authorName){

		String SQL = "SELECT id FROM authors"
				+ " WHERE name = ?";
		
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, authorName);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}else{
				return -1;
			}
		}catch(SQLException e){
			System.err.println(e.getMessage());
			return -1;
		}
	}
	
	public long addAuthor(String authorName){
		long id = checkAuthorExists(authorName);
		
		if(id != -1) return id;

		String SQL = "INSERT INTO authors (name)"
				+ " VALUES (?)";
		
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				){
			stmt.setString(1, authorName);
			
			System.out.println("Added author " + authorName);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
			
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return -1;
		}
		
	}
	
	public void addToDatabase(Artwork a){
		addMuseum(a.getRepositoryId(), a.getRepositoryName());
		long authorId = addAuthor(a.getAuthor());
		
		String SQL = "INSERT INTO artworks (recID, object_of_work, classifications, categories"
				+ ", title, description, measurements, repositoryID, display_state, author_id, image_url)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, a.getId());
			stmt.setString(2, a.getObjectOfWork());
			stmt.setString(3, String.join(",", a.getClassification()));
			stmt.setString(4, String.join(",", a.getCategories()));
			stmt.setString(5, a.getTitle());
			stmt.setString(6, a.getDescription());
			stmt.setString(7, a.getMeasurements());
			stmt.setString(8, a.getRepositoryId());
			stmt.setString(9, a.getDisplayState());
			stmt.setLong(10,  authorId);
			stmt.setString(11,  a.getImageUrl());
			stmt.executeUpdate();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			System.out.println(a.toString());
		}		
	}
	
	public void addDateToDatabase(Artwork a){
		String SQL = "UPDATE artworks SET display_year = ? WHERE recID = ?";
		try(Connection conn = pgConn.connect();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			if(a.getDisplayYear() == null){
				stmt.setInt(1, 0);				
			}else{
				int year = 0;
				try{
					year = Integer.parseInt(a.getDisplayYear());
				}catch(NumberFormatException e){
					year = 0;
				}
				
				stmt.setInt(1, year);
			}
			stmt.setString(2, a.getId());
//			System.out.println("Updating " + a.getId());
			stmt.executeUpdate();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			System.out.println(a.toString());
		}	
	}
}
