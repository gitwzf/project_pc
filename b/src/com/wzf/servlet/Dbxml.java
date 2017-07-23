package com.wzf.servlet;
import org.simpleframework.xml.*; 
import org.simpleframework.xml.core.Persister; 

import com.wzf.model.Xmlelement;

import java.io.File; 
import java.util.List; 
import java.util.ArrayList; 
@Root
public class Dbxml {
	 @Element 
     private String text; 

     @Attribute 
     private int index; 

     @Element() 
     private boolean flag; 

     @Element(required = false) 
     private Integer num; 

     @ElementList(required = false) 
     private List<Xmlelement> slist = new ArrayList<Xmlelement>(); 

     public Dbxml() { 
             super(); 
     } 

     public Dbxml(String text, int index) { 
             this.text = text; 
             this.index = index; 
            slist.add(new Xmlelement("1","wang",17)); 
     } 

     public String getMessage() { 
             return text; 
     } 

     public int getId() { 
             return index; 
     } 


     public static void main(String[] args) throws Exception { 
             Serializer serializer = new Persister(); 
            Dbxml example = new Dbxml("Example message", 123); 
             File result = new File("d://example.xml"); 
             //–¥»Î
             serializer.write(example, result); 
             //∂¡»°
             Dbxml _obj = serializer.read(Dbxml.class, result); 
             System.out.println(_obj.getMessage()); 


     } 
}