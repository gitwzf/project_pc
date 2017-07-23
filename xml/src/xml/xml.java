package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class xml {
		public static void main(String[] args) throws IOException {
			Document document=DocumentHelper.createDocument();
			Element root=document.addElement("books");
			Element book=root.addElement("book");
			Element name=book.addElement("name");
			Element author=name.addElement("author");
			Element price=author.addElement("price");
			
			book.addAttribute("id", "1");
			name.addText("ÍøÒ³Éè¼Æ¾ø¼¼");
			author.addText("W");
			price.addText("100$");
			
//			File file=new File("d:/1/2/w.xml");
//			FileWriter writer=new FileWriter(file);
//			document.write(writer);
//			writer.flush();
//			writer.close();
			
			File file=new File("d:/1/2/w.xml");
			FileWriter writer=new FileWriter(file);
			OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("gb18030");
			XMLWriter xmlWriter=new XMLWriter(writer,format);
			xmlWriter.write(document);
			xmlWriter.flush();
			xmlWriter.close();
		}
}
