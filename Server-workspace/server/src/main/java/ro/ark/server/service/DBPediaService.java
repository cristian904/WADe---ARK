package ro.ark.server.service;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import ro.ark.server.entity.Author;


@Service
public class DBPediaService {

	public Author getAuthorInfo(String authorName){
		String service = "http://dbpedia.org/sparql";
		String queryString =
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+		
				"PREFIX type: <http://dbpedia.org/class/yago/> " + 
				 "PREFIX dbpediaO: <http://dbpedia.org/ontology/> " + 
				 "SELECT DISTINCT ?x ?place_name ?desc ?country_name ?movement_name ?birthDate ?deathDate ?img " + 
				 "WHERE { " + 
				 "    ?x rdf:type type:Artist109812338. " + 
				 "    ?x foaf:name 'Nicolae Tonitza'@en. " + 
				 "    ?x dbpediaO:birthPlace ?place. " + 
				 "    ?x rdfs:comment ?desc. " + 
				 "    ?place dbpediaO:country ?country. " + 
				 "    ?country foaf:name ?country_name. " + 
				 "    ?place foaf:name ?place_name. " + 
				 "    ?x dbpediaO:movement ?movement. " + 
				 "    ?movement rdfs:label ?movement_name. " + 
				 "    ?x dbpediaO:birthDate ?birthDate. " + 
				 "    ?x dbpediaO:deathDate ?deathDate. " + 
				 "    ?x foaf:depiction ?img. " + 
				 "    filter langMatches( lang(?movement_name), 'EN' ) " + 
				 "    filter langMatches( lang(?desc), 'EN' ) " + 
				 "} " + 
				 "ORDER BY ?country";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
		ResultSet rs = qexec.execSelect();
		try{
			
			Author author = new Author();
			for (; rs.hasNext() ;){
				QuerySolution soln = rs.nextSolution();
				
				author.setName(authorName);
				if(soln.contains("birthDate"))
				author.setBirthDate(soln.get("birthDate").toString());
				if(soln.contains("deathDate"))
				author.setDeathDate(soln.get("deathDate").toString());
				if(soln.contains("place_name"))
				author.setPlaceName(soln.get("place_name").toString());
				if(soln.contains("country_name"))
				author.setCountry(soln.get("country_name").toString());
				if(soln.contains("desc"))
				author.setDesc(soln.get("desc").toString());
				if(soln.contains("img"))
				author.setImage(soln.get("img").toString());

				if(soln.contains("movement_name")){
					if(!author.getMovementName().contains(soln.get("movement_name").toString())){
						author.getMovementName().add(soln.get("movement_name").toString());
					}
				}
			}	
			return author;
		}finally{
			qexec.close();
		}
		
	}
}
