package com.wzf.show;

import java.io.File;
import java.util.ArrayList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.wzf.model.FunXmlModel;
import com.wzf.model.FunXmlModelList;

/**
 * �ֶ����ɷ�������
 * @author WZF
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		FunXmlModel f=new FunXmlModel();
		f.setDescription("��ÿ����β��ӹ涨�ַ�");
		f.setName("ǰ��׺");
		f.setPath("com.wzf.Deal1");
		FunXmlModel f1=new FunXmlModel();
		f1.setDescription("��ָ����λ������ַ�");
		f1.setName("�������");
		f1.setPath("com.wzf.Deal2");
		
		FunXmlModelList fList=new FunXmlModelList();
		ArrayList<FunXmlModel> funXmlModelList=new ArrayList<FunXmlModel>();
		funXmlModelList.add(f);
		funXmlModelList.add(f1);
		fList.setFunXmlModelList(funXmlModelList);
		
		File file=new File("d://a.txt"); 
		Serializer serializer = new Persister();
		serializer.write(fList, file);
		
	}

}
