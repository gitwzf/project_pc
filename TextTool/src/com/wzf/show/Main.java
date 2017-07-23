package com.wzf.show;

import java.io.File;
import java.util.ArrayList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.wzf.model.FunXmlModel;
import com.wzf.model.FunXmlModelList;

/**
 * 手动生成方法配置
 * @author WZF
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		FunXmlModel f=new FunXmlModel();
		f.setDescription("在每行首尾添加规定字符");
		f.setName("前后缀");
		f.setPath("com.wzf.Deal1");
		FunXmlModel f1=new FunXmlModel();
		f1.setDescription("在指定的位置添加字符");
		f1.setName("内容添加");
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
