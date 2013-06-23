package com.vendenet.utilidades;

import java.net.URL;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
public class MySAXParserBean extends 
DefaultHandler implements java.io.Serializable {
   private String text;
   private Vector vector = new Vector();
   private MySAXElement current = null;

   public MySAXParserBean() {
   }

   public Vector parse(String filename) throws 
   Exception {
     SAXParserFactory spf = 
      SAXParserFactory.newInstance(); 
     spf.setValidating(false);
     SAXParser saxParser = spf.newSAXParser(); 
     // create an XML reader
     XMLReader reader = saxParser.getXMLReader();
     URL url = new URL(filename);
     
//     FileReader file = new FileReader(filename);
     // set handler
     reader.setContentHandler(this);
     // call parse on an input source
     reader.parse(new InputSource(url.openStream()));
     return vector;
   }
   
   // receive notification of the beginning of an element 
   public void startElement (String uri, String name,
    String qName, Attributes atts) {
      current = new MySAXElement(
      uri, name, qName, atts);
      vector.addElement(current);
      text = new String();

   }

   // receive notification of the end of an element
   public void endElement (String uri, String name, 
   String qName) {
     if(current != null && text != null) {
        current.setValue(text.trim());
     }
     current = null;
   }
   
   // receive notification of character data
   public void characters (char ch[], int start, 
   int length) {
     if(current != null && text != null) {
        String value = new String(
        ch, start, length);
        text += value;
     }
   }
}