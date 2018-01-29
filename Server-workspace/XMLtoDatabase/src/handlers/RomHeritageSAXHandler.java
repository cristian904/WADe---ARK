package handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dao.ArtworkDAO;
import entity.Artwork;

public class RomHeritageSAXHandler extends DefaultHandler{

	ArtworkDAO artworkDao;
	
	
	Artwork currentArtwork;
	String currentElement;
	int count;
	
    public void startDocument() throws SAXException {
		artworkDao = new ArtworkDAO();
    }

    public void endDocument() throws SAXException {
    	System.out.println("End : " + count);
    }

    public void startElement(String uri, String localName,
            String qName, Attributes attributes)
    throws SAXException {
    	
    	//Strip namespace
    	qName = qName.split(":")[1];
    	
    	switch(qName){
	    	case "lido":{
	    		currentArtwork = new Artwork();
	    		break;
	    	}
	    	case "appellationValue":
	    	case "conceptID":
	    	case "term":{
	    		break;
	    	}
	    	default:{
	    		currentElement = qName;
	    	}
    	}

    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
    	
    	//Strip namespace
    	qName = qName.split(":")[1];
    	
    	switch(qName){
	    	case "lido":{
	    		count++;
//	    		System.out.println("Added " + currentArtwork.getTitle());
//	    		artworkDao.addToDatabase(currentArtwork);
	    		System.out.println("Setting date " + currentArtwork.getDisplayYear() + " to " + currentArtwork.getTitle());
	    		artworkDao.addDateToDatabase(currentArtwork);
	    		break;
	    	}
    	}
    }

    public void characters(char ch[], int start, int length)
    throws SAXException {
        String element = new String(ch, start, length);
        if(element == null || element.isEmpty() || element.contains("\n")) return;

        switch(currentElement){
        case "lidoRecID":{
        	currentArtwork.setId(element);
        	break;
        }
        case "objectWorkType": {
        	currentArtwork.setObjectOfWork(element);
        	break;
        }
        case "classification": {
        	currentArtwork.getClassification().add(element);
        	break;
        }
        case "titleSet":{
        	currentArtwork.setTitle(element);
        	break;
        }
        case "legalBodyID":{
        	if(currentArtwork.getRepositoryId() != null) break;
        	currentArtwork.setRepositoryId(element);
        	break;
        }
        case "legalBodyName":{
        	if(currentArtwork.getRepositoryName() != null) break;
        	currentArtwork.setRepositoryName(element);
        	break;
        }
        case "displayState":{
        	currentArtwork.setDisplayState(element);
        	break;
        }
        case "descriptiveNoteValue":{
        	currentArtwork.setDescription(element);
        	break;
        }
        case "displayObjectMeasurements":{
        	currentArtwork.setMeasurements(element);
        	break;
        }
        case "category":{
        	currentArtwork.getCategories().add(element);
        	break;
        }
        case "nameActorSet":{
        	currentArtwork.setAuthor(element);
        	break;
        }
        case "linkResource":{
        	currentArtwork.setImageUrl(element);
        	break;
        }
        case "displayDate":{
        	currentArtwork.setDisplayYear(element);
        	break;
        }
        }
        
    }

    public void ignorableWhitespace(char ch[], int start, int length)
    throws SAXException {
    }	
	
}
