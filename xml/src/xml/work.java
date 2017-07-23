package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*дһ������3��ṹ��XML
1��Ҫ���ܹ���DOM�Ը�����xml�ĵ����н����������config.xml
        ����jdbc��dbconnection�����ݿ⡢�û����������ֵ��ʹ�����ͨ���ԡ�
2���ܹ���DOM����һ��xml�ĵ�ģ�ͣ�����д�뵽�ļ���ȥ��
3��ʹ�� DOM4j��xml�ļ����ж�д������*/

public class work {
	public static void main(String[] args) throws IOException, DocumentException, ClassNotFoundException, SQLException {
		String USER="",PASSWORD ="",URL="";
		boolean flag=false;
		
		Document document=DocumentHelper.createDocument();
		Element root=document.addElement("students");
		Element student=root.addElement("student");
		Element user=student.addElement("user");
		Element password=student.addElement("password");
		Element url=student.addElement("url");
		student.addAttribute("id", "1");
		user.addText("root");
		password.addText("123456");
		url.addText("jdbc:mysql://localhost:3306/w");
		
		
//		Element root2=document.addElement("students");
		Element student2=root.addElement("student");
		Element user2=student2.addElement("user");
		Element password2=student2.addElement("password");
		Element url2=student2.addElement("url");
		student2.addAttribute("id", "2");
		user2.addText("root");
		password2.addText("12345");
		url2.addText("jdbc:mysql://localhost:3306/w");
		
		File file=new File("d:/1/2/config.xml");
		FileWriter writer=new FileWriter(file);
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("gb18030");//?
		format.setIndent(true);//?�����õ�
		XMLWriter xmlWriter=new XMLWriter(writer,format);
		xmlWriter.write(document);
//		xmlWriter.write(document2);
		xmlWriter.flush();
		xmlWriter.close();
		
		File f=new File("d:/1/2/config.xml");
		SAXReader reader=new SAXReader();
		Document d=reader.read(f);//��ȡ��document�ĵ�
		
		Element r=d.getRootElement();
		List<Element>list=r.elements();
		
		for(Element book:list){
			
		List<Element>userlist=book.elements("user"); USER=userlist.get(0).getText();
		List<Element>passwordlist=book.elements("password"); PASSWORD=passwordlist.get(0).getText();
		List<Element>urlrlist=book.elements("url");URL=urlrlist.get(0).getText();
		
		ResultSet rs;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
		Connection conn=DriverManager.getConnection(URL,USER,PASSWORD);
		Statement stat=conn.createStatement();
		String sql="select *from student";
		rs=stat.executeQuery(sql);
		while(rs.next()){
			flag=true;
		}
		if(flag)System.out.println("���ӳɹ���");
		}catch(Exception e){
		 System.out.println("����ʧ�ܣ�");}
		}
		//���ʣ�xml��δ�����ı�����һ��ģ���ô�ֱ�ȡ��
		
		
		
//		for(Element book:list){
//			System.out.println("idΪ"+book.attributeValue("id")+"��ѧ����");
//			String sql="select name from student where id='"+book.attributeValue("id")+"'";
//			rs=stat.executeQuery(sql);
//			List<Element>namelist=book.elements("name");
//            System.out.println(namelist.get(0).getText());
//             while(rs.next()){
//			if(!(rs.getString("name").equals(namelist.get(0).getText()))){
//				String sq="update student set name='"+namelist.get(0).getText()+"'where id='"+book.attributeValue("id")+"'";
//				stat.executeUpdate(sq);System.out.println(book.attributeValue("id")+"��name�Ѹ�");
//				
//				}List<Element>passwordlist=book.elements("password");
////System.out.println(namelist.get(0).getText());
//				if(!(""+rs.getInt("password")).equals(passwordlist.get(0).getText())){
//					String s="update student set password='"+passwordlist.get(0).getText()+"'where id='"+book.attributeValue("id")+"'";
//					stat.executeUpdate(s);System.out.println(book.attributeValue("id")+"��password�Ѹ�");
//			
//		}//������document�е�����
//	}
//		}
//		try{
//			stat.close();
//			conn.close();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
}
}

