package xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class xmlreader {
	public static void main(String[] args) throws DocumentException {
		File file=new File("d:/1/2/w.xml");
		SAXReader reader=new SAXReader();
		Document document=reader.read(file);//��ȡ��document�ĵ�
		
		Element root=document.getRootElement();
		List<Element>list=root.elements();
		for(Element book:list){
			System.out.println(book.attributeValue("id"));
			List<Element>namelist=book.elements("name");
			System.out.println(namelist.get(0).getText());
		}//������document�е�����
		
		Iterator it=root.elementIterator();
		while(it.hasNext()){
			Element e=(Element) it.next();
			System.out.println(e.attributeValue("id"));
		}
		System.out.println(root.nodeCount());//�ӽڵ�ĸ���
	}

}
