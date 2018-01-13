package main;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import handlers.RomHeritageSAXHandler;

public class Main {

	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
		    InputStream xmlInput  = new FileInputStream("resources/actual.xml");
		    SAXParser saxParser = factory.newSAXParser();
		    DefaultHandler handler   = new RomHeritageSAXHandler();
		    saxParser.parse(xmlInput, handler);
		} catch (Throwable err) {
		    err.printStackTrace ();
		}
	}

}
