package com.wzf.pubvari;

import java.util.HashMap;
import java.util.Map;

import com.wzf.pubvari.Variable;

public class Variable {
	//����������
	public String URL="http://ecs.touclick.com";
	
	//����      ����_�ֶ�
	public static final int POSITION_TYPE_HUNTER=0; //��ͷ
	public static final int POSITION_TYPE_COMPANY=1; //��˾
	
	public static final String HUNTID_USERID="huntid";// Ͷ����ͷ
	public static final String POSITIONID_USERID="positionid";//Ͷ�ݹ�˾
	
	// ΢�Ź����˺�
	public String WX_NAME = "Ǯ����";
	public String SIMPLE_WX_NAME = "qjwb";

	//����Ŀÿ�μ��ظ���
	public int p_size=10;
	
	//ͼƬ����Ƶ��ַ
		//wabacus
public String wa_pic_path=URL+"/image/"+SIMPLE_WX_NAME+"/";

	// ��·��
//	public String project = Variable.class.getClassLoader().getResource("")
//			.getPath().split("webapps/")[1].split("/WEB-INF")[0];
//	public String project_path = "D:/Tomcat 6.0/webapps/" + project;
     public String project="Qjwbqtwy";
     //��������������
     public String email_name="qtwy@8531.cn";
     public String email_pass="qwertyuiop";
     
	// mysql���ݿ�
    public String IP="127.0.0.1";
	public String database = "qjwb_qtwy";
	public String port = "3306";
	public String username = "root";
	public String password = "12345678";
	
	//map
	//��������
	public static Map<String,String> map_years=new HashMap<String, String>(),
	//ѧ��
	 map_degree=new HashMap<String, String>(),
	//��н
	 map_salary=new HashMap<String, String>(),
	//��˾��ģ
	  map_scale=new HashMap<String, String>(),
	//��˾����
	map_properties=new HashMap<String, String>(),
	//��ҵ���
	//map_typeoneid0=new HashMap<String, String>(),
	//��н����
	//map_msalary=new HashMap<String, String>(),
	//��������Ҫ��
	map_workexperi=new HashMap<String, String>(),
	//�齱��Ʒ��Ÿ�ͼƬ��Ӧ
	map_ranktype=new HashMap<String, String>();
	static{
		map_years.put("0", "�ڶ�ѧ��");
		map_years.put("1", "Ӧ���ҵ��");
		map_years.put("2", "һ������");
		map_years.put("3", "��������");
		map_years.put("4", "��������");
		map_years.put("5", "��������");
		map_years.put("6", "��������");
		map_years.put("7", "ʮ������");
		
		map_degree.put("0", "��ʿ");
		map_degree.put("1", "˶ʿ");
		map_degree.put("2", "��ѧ����");
		map_degree.put("3", "��ר");
		map_degree.put("4", "����");
		map_degree.put("5", "ְ��");
		map_degree.put("6", "����");
		map_degree.put("7", "�м�");
		map_degree.put("8", "��ר");
		
		map_salary.put("0", "4w����");
		map_salary.put("1", "4-7w");
		map_salary.put("2", "7-10w");
		map_salary.put("3", "10w����");
		
		map_scale.put("0", "20��");
		map_scale.put("1", "100��");
		map_scale.put("2", "500��");
		map_scale.put("3", "1000��");
		map_scale.put("4", "1000������");
		
		map_properties.put("0", "�����������ι�˾");
		map_properties.put("1", "����Ͷ����ҵ");
		map_properties.put("2", "����Ͷ�ʺϻ���ҵ");
		map_properties.put("3", "�ɷ����޹�˾");
		map_properties.put("4", "�ǹ�˾����ҵ");
		map_properties.put("5", "���幤�̻�");
		map_properties.put("6", "�ϻ���ҵ");
		map_properties.put("7", "��ѧ����ҵ");
		
		map_workexperi.put("0", "��");
		map_workexperi.put("1", "1������");
		map_workexperi.put("2", "2������");
		map_workexperi.put("3", "3������");
		map_workexperi.put("4", "4������");
		map_workexperi.put("5", "5������");
		
		map_ranktype.put("1", "0");//Ӧ�ð�������״̬����Ȼ�ᱻ��Ϊ�����ڶ����ص�
		map_ranktype.put("2", "1");
		map_ranktype.put("3", "2");
		map_ranktype.put("0", "3");
		map_ranktype.put("-2", "-1");
		map_ranktype.put("-1", "4");
	}
	
}
