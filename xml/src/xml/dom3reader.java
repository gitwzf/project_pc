package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class dom3reader {
		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
			File file=new File("d:/1/2/w.xml");
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document=builder.parse(file);
			Element root=document.getDocumentElement();
			NodeList list=root.getChildNodes();
			System.out.println(list.getLength());
			for(int i=0;i<list.getLength();i++){
				System.out.println(list.item(i));
				if(list.item(i) instanceof Element){
					Element element=(Element) list.item(i);
					System.out.println(element.getAttribute("id"));
					NodeList bookList=element.getChildNodes();
					for(int j=0;j<bookList.getLength();j++){
						if(bookList.item(j) instanceof Element){
							Element bookChild=(Element) bookList.item(j);
							System.out.println(bookChild.getTagName()+" "+bookChild.getTextContent());
						}
					}
				}
			}
					NodeList nameList=document.getElementsByTagName("name");
					for(int i=0;i<nameList.getLength();i++){
						System.out.println(nameList.item(i));
						Element e=(Element) nameList.item(i);
						boolean flag=e.hasChildNodes();
						System.out.println(e.getTextContent());
						System.out.println(e.getChildNodes().item(0).getNodeValue());
					}
					
		}
		
}

