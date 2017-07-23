package csdn;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 反向互补子串
 * 
 * @author wzf
 *@category
 
 **/
public class a {
	/*
	 * 字符串只包含大写字母。我们定义一个字母和它13位以后的字母互补。即A与N互补，B与O互补，C与P互补……，我们需要找一个字符串的两个不重叠的子串，其中一个和另外一个的翻转全互补
	 * 另外，因为我们的问题来自生物学，所以我们允许这两个字符串的一个可以删掉两个字符（注意：仅允许一个字符串删掉任意位置的两个字符，另外一个必须全部互补上
	 * ）。我们的目的是求最长的反向互补子串的长度。 输入格式：
	 * 多组数据，每组数据一行，有一个字符串。每个字符串长度不超过2000，只有大写英文字母组成。 输出格式：
	 * 每组数据输出一行包含一个整数，表示最长的反向互补子串长度。 挑战规则： 输入样例 ABCD ABCDQOPN ABON 输出样例： 0 2 2
	 * 解释： 第一个样例，没有互补的子串。 第二个样例，AB和NO互补，所以反向互补子串的时候我们可以选择NPO，删掉一个P即可。
	 * 第三个样例，AB与ON反向互补。
	 */
	public static void main(String[] args) {//由n递减1个分层可以考虑为是n!情况   就可以简化2个多余（其实就是差2层）
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
		for(String str0:str){//遍历  字符串
			for(int i=1;i<str0.length()/2+1;i++){//遍历每种长度
		        //从开始位置处开始查找
				for(int j=0;j<=str0.length()-i;j++){
					String str01=str0.substring(j,j+i);
					//匹配串判断函数
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
	 *@param  被匹配串str01
	 *@return  返回是否有匹配
	 * 
	 **/
	public static boolean haveMatch(String str0,String str01,int beg){
		//拼接匹配正则字符串
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
					// s2分前后两种情况
					for (int aa = 0; aa < 3; aa++)// 余数
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
