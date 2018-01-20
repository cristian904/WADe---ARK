package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public void addToDatabase(Artwork a){
		addMuseum(a.getRepositoryId(), a.getRepositoryName());
		
		String SQL = "INSERT INTO artworks (recID, object_of_work, classifications, categories"
				+ ", title, description, measurements, repositoryID, display_state, author, image_url)"
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
			stmt.setString(10,  a.getAuthor());
			stmt.setString(11,  a.getImageUrl());
			stmt.executeUpdate();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			System.out.println(a.toString());
		}
		
		
	}
}
