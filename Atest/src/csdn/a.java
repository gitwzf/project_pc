package csdn;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���򻥲��Ӵ�
 * 
 * @author wzf
 *@category
 
 **/
public class a {
	/*
	 * �ַ���ֻ������д��ĸ�����Ƕ���һ����ĸ����13λ�Ժ����ĸ��������A��N������B��O������C��P����������������Ҫ��һ���ַ������������ص����Ӵ�������һ��������һ���ķ�תȫ����
	 * ���⣬��Ϊ���ǵ�������������ѧ���������������������ַ�����һ������ɾ�������ַ���ע�⣺������һ���ַ���ɾ������λ�õ������ַ�������һ������ȫ��������
	 * �������ǵ�Ŀ��������ķ��򻥲��Ӵ��ĳ��ȡ� �����ʽ��
	 * �������ݣ�ÿ������һ�У���һ���ַ�����ÿ���ַ������Ȳ�����2000��ֻ�д�дӢ����ĸ��ɡ� �����ʽ��
	 * ÿ���������һ�а���һ����������ʾ��ķ��򻥲��Ӵ����ȡ� ��ս���� �������� ABCD ABCDQOPN ABON ��������� 0 2 2
	 * ���ͣ� ��һ��������û�л������Ӵ��� �ڶ���������AB��NO���������Է��򻥲��Ӵ���ʱ�����ǿ���ѡ��NPO��ɾ��һ��P���ɡ�
	 * ������������AB��ON���򻥲���
	 */
	public static void main(String[] args) {//��n�ݼ�1���ֲ���Կ���Ϊ��n!���   �Ϳ��Լ�2�����ࣨ��ʵ���ǲ�2�㣩
		//method1();
           method2();
	}
	public static void method2(){
		System.out.println("input:");
		String[] str=new String[3];
		for(int i=0;i<3;i++){
		Scanner scan = new Scanner(System.in);
		str[i]= scan.next();
		}
		int[] max=new int[str.length];
		int m=0;
		for(String str0:str){//����  �ַ���
			for(int i=1;i<str0.length()/2+1;i++){//����ÿ�ֳ���
		        //�ӿ�ʼλ�ô���ʼ����
				for(int j=0;j<=str0.length()-i;j++){
					String str01=str0.substring(j,j+i);
					//ƥ�䴮�жϺ���
					if(haveMatch(str0,str01,j))
						if(max[m]<str01.length())max[m]=str01.length();
						
				}
		    }
			m++;
		}
		
		for(int k:max)
			System.out.println(k);
	}
	/**
	 *@param  ��ƥ�䴮str01
	 *@return  �����Ƿ���ƥ��
	 * 
	 **/
	public static boolean haveMatch(String str0,String str01,int beg){
		//ƴ��ƥ�������ַ���
		String pattern=addSpecial(str01);
		Pattern pat=Pattern.compile(pattern);
		Matcher mat=pat.matcher(str0);
		
		while(mat.find()){
		int beg0=mat.start(),end0=mat.end()-1;
			String str02=mat.group();
			
			System.out.println(str01+"    "+pattern);
			System.out.println(beg0+" "+beg+" "+str01.length()+" "+end0);
			
			if(beg0>beg+str01.length()||end0<beg)
			if(str02.length()-2<=str01.length())
				return true;
		}
		 return false;
		
	}
	public static String addSpecial(String str){
		int len=str.length();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<len;i++){
			sb.append((char)(str.charAt(len-1-i)>77?(str.charAt(len-1-i)-13):(str.charAt(len-1-i)+13)));
			if(i!=len-1)sb.append("\\w{0,2}");
		}
		return sb.toString();
	}
	
	
	public static void method1(){
		System.out.println("input:");
		Scanner scan = new Scanner(System.in);
		// ArrayList array=new ArrayList<String>();
		String[] str = scan.nextLine().split(" ");
		int[] maxlen = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			String str0 = str[i];

			int max_len = str0.length() / 2;
			String s1, s2 = null;
			for (int j = 1; j <= max_len; j++) {
				for (int k = 0; k < str0.length() - j; k++) {
					s1 = str0.substring(k, k + j);
					// s2��ǰ���������
					for (int aa = 0; aa < 3; aa++)// ����
					{
						for (int l = 0; l < k; l++) {
							System.out.println("pre:" + l + "  " + k + " " + j);
							if (l + j + aa < k) {
								s2 = str0.substring(l, l + j + aa);
								int a = getLen(s1, s2);
								if (a > maxlen[i])
									maxlen[i] = a;
							}
						}
						for (int l = k + j; l < str0.length(); l++) {
							System.out.println("fix:" + l + "  " + k + " " + j);
							if (l + j + aa < str0.length()) {
								s2 = str0.substring(l, l + j + aa);
								int a = getLen(s1, s2);
								if (a > maxlen[i])
									maxlen[i] = a;
							}
						}
					}
				}
			}

		}
		for (int i = 0; i < str.length; i++)
			System.out.print(maxlen[i] + " ");
		
	}

	public static int getLen(String s1, String s2) {
		// System.out.println(s1+"  "+s2);
		int m = 2;
		boolean flag = false;
		int loc2 = s2.length() - 1;
		for (int i = 0; i < s1.length(); i++) {
			if (loc2 - i >= 0)
				if (s1.charAt(i) + 13 != s2.charAt(loc2 - i))
					if (m != 0) {
						m--;
						i--;
						loc2--;
					} else
						return 0;
			// System.out.println(m);
		}
		return s1.length();

	}

}
