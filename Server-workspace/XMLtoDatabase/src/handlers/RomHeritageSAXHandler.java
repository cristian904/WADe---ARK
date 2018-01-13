package handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entity.Artwork;

public class RomHeritageSAXHandler extends DefaultHandler{

	List<Artwork> artworks;
	Artwork currentArtwork;
	
	String currentElement;
	
    public void startDocument() throws SAXException {
		artworks = new ArrayList<>();
    }

    public void endDocument() throws SAXException {
    	System.out.println("End : ");
    	System.out.println(this.artworks.size());
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
	    		artworks.add(currentArtwork);
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
        	currentArtwork.setRepositoryId(element);
        	break;
        }
        case "legalBodyName":{
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
        }
        }
        
    }

    public void ignorableWhitespace(char ch[], int start, int length)
    throws SAXException {
    }	
	
}
