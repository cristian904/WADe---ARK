package ro.ark.server.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import ro.ark.server.entity.Author;
import ro.ark.server.entity.Museum;


@Service
public class DBPediaService {

	public Author getAuthorInfo(Author author){
		String service = "http://dbpedia.org/sparql";
		List<String> authorNames = Arrays.asList(author.getName().split(","));
		authorNames = authorNames.stream().map(n -> n.trim()).collect(Collectors.toList());
		
		String queryString =
				" PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"+
				" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
				" PREFIX foaf: <http://xmlns.com/foaf/0.1/> \t\t\n"+
				" PREFIX type: <http://dbpedia.org/class/yago/> \n"+
				" PREFIX dbpediaO: <http://dbpedia.org/ontology/> \n"+
				" SELECT DISTINCT ?x ?place_name ?desc ?country_name ?movement_name ?birthDate ?deathDate ?img ?influencer_name ?trainer_name \n"+
				" WHERE { \n"+
				" {?x rdf:type dbpediaO:Artist} UNION {?x rdf:type type:Artist109812338} . \n"+
				" ?x foaf:name ?name . \n"+
				" OPTIONAL{\n"+
				"\t ?x dbpediaO:birthDate ?birthDate. \n"+
				"\t ?x dbpediaO:deathDate ?deathDate. \n"+
				" }\n"+
				" OPTIONAL{\n"+
				"\t ?x dbpediaO:birthPlace ?place. \n"+
				"\t ?place dbpediaO:country ?country. \n"+
				"\t ?country foaf:name ?country_name. \n"+
				"\t ?place foaf:name ?place_name. \n"+
				" }\n"+
				" OPTIONAL{\n"+
				"\t ?x rdfs:comment ?desc. \n"+
				"\t filter langMatches( lang(?desc), \'EN\' ) \n"+
				" }\t\n"+
				" OPTIONAL{ \n"+
				"\t ?x dbpediaO:movement ?movement. \n"+
				"\t ?movement rdfs:label ?movement_name. \n"+
				"\t filter langMatches( lang(?movement_name), \'EN\' ) \n"+
				" }\t\n"+
				" OPTIONAL{ \n"+
				"\t \t ?x dbpediaO:influencedBy ?influencer. \n"+
				"\t \t ?influencer rdfs:label ?influencer_name. \n"+
				"\t filter langMatches( lang(?influencer_name), \'EN\' ) \n"+
				" \t }\n"+
				" \t OPTIONAL{\n"+
				"\t\t?x dbpediaO:training ?trainer. \n"+
				"\t ?trainer rdfs:label ?trainer_name. \n"+
				"\t filter langMatches( lang(?trainer_name), \'EN\' ) \n"+
				" } \n"+
				" OPTIONAL{\n"+
				"\t ?x foaf:depiction ?img. \n"+
				" }\n"+
				 " 	  filter contains(?name, \"" + authorNames.get(0) + "\") " + 
				 (authorNames.size() > 1 ? "filter contains(?name, \"" + authorNames.get(1) + "\")" : "") + 
				 "} " + 
				 "ORDER BY ?country";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
		ResultSet rs = qexec.execSelect();
		try{
			
			for (; rs.hasNext() ;){
				QuerySolution soln = rs.nextSolution();
				
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
						List<String> movements = author.getMovementName();
						movements.add(soln.get("movement_name").toString());
						author.setMovementName(movements);
					}
				}
				
				if(soln.contains("influencer_name")){
					if(!author.getInfluencers().contains(soln.get("influencer_name").toString())){
						List<String> influencers = author.getInfluencers();
						influencers.add(soln.get("influencer_name").toString());
						author.setInfluencers(influencers);
					}
				}

				if(soln.contains("trainer_name")){
					if(!author.getTrainers().contains(soln.get("trainer_name").toString())){
						List<String> trainers = author.getTrainers();
						trainers.add(soln.get("trainer_name").toString());
						author.setTrainers(trainers);
					}
				}
			}	
			return author;
		}finally{
			qexec.close();
		}
	}
	
	
	public Museum getMuseumInfo(Museum museum){
		String service = "http://dbpedia.org/sparql";
		List<String> museumNameWords =Arrays.asList(museum.getRepositoryName().split(" "));
		
		String filters = "";
		for(String word : museumNameWords){
			filters += " filter contains(?name, '" + word + "') ";
		}
		
		String queryString = 
				"PREFIX yago: <http://dbpedia.org/class/yago/> "+
				"PREFIX dbo: <http://dbpedia.org/ontology/> " +
				"PREFIX dbc: <http://dbpedia.org/resource/Category:> " + 
				"PREFIX dct:  <http://purl.org/dc/terms/> " + 
				"SELECT ?x ?name ?image ?desc WHERE { " + 
				"{{?x rdf:type yago:Museum103800563} UNION {?x rdf:type dbo:Museum}. " + 
				"{?x dbo:location :Romania} } UNION {?x dct:subject dbc:Art_museums_and_galleries_in_Romania} UNION {?x dct:subject dbc:Historic_house_museums_in_Romania} . " +
				"?x foaf:name ?name . " + 
				"OPTIONAL{ " +
				"?x rdfs:comment ?desc " +
				"filter langMatches( lang(?desc), 'EN' ) " + 
				"} " + 
				"OPTIONAL{ " +
				"?x dbo:thumbnail ?image " +
	 			"} " +
	 			filters +
				" } ";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
		ResultSet rs = qexec.execSelect();
		
		return null;
	}
}
