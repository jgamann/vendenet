package com.vendenet.utilidades;

import org.xml.sax.Attributes;

public class MySAXElement implements 
java.io.Serializable {
   String uri;
   String localName;
   String qName;
   String value=null;
   Attributes attributes;

   public MySAXElement(String uri, String localName,
    String qName, Attributes attributes) {
      this.uri = uri;
      this.localName = localName;
      this.qName = qName;
      this.attributes = attributes;
   }

   public String getUri() {
      return uri;
   }

   public String getLocalName() {
      return localName;
   }

   public String getQname() {
      return qName;
   }

   public Attributes getAttributes() {
      return attributes;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}