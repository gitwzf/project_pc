package unit;

public class ReplaceUnit {
	public static void main(String[] args) {
		String[] params=new String[]{"orgNo","ym","consId"};
		String[] params1=getFields(params);
		
		String Level1="Long ${Level0_0}3441123\"${Level0_1}\"4234252";
		String Level1_0=load1(Level1,params,params1);
		
		String Level11="Array ${Level0_0}abc=${Level0_0}4234252";
		String Level1_1=load2(Level11,params,params1);
		//¿ò¼Ü?
		String Level2="¶¨Òå\n${Level1_0}\n¸³Öµ\n${level1_1}";
		String endStr=load3(Level2,Level1_0,Level1_1);
		System.out.println(endStr);
	}
	
	public static String load3(String level,String para,String para1){
		return level.replace("${Level1_0}", para).replace("${level1_1}", para1);
	}
	
	public static String load1(String level,String[] para,String[] para1){
		String reStr="";
		for(int i=0;i<para.length;i++){
			reStr+=level.replace("${Level0_0}", para[i]).replace("${Level0_1}", para1[i])+";\n";
		}
		return reStr;
	}
	
	public static String load2(String level,String[] para,String[] para1){
		String reStr="";
		for(int i=0;i<para.length;i++){
			reStr+=level.replace("${Level0_0}", para[i]).replace("${Level0_1}", para1[i])+";\n";
		}
		return reStr;
	}
	
	public static String[] getFields(String[] params){
		String[] reParams = null;
		if(params==null||params.length==0)
			return reParams;
		reParams=new String[params.length];
		for(int i=0;i<params.length;i++){
			reParams[i]=Field(params[i]);
		}
		return reParams;
	}
	
	public static String Field(String str){
		if(str==null||str.isEmpty())
			return str;
		String reStr="";
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)>64 && str.charAt(i)<91)
				reStr+="_"+str.charAt(i);
			else if(str.charAt(i)>96 && str.charAt(i)<123)
				reStr+=(char)(str.charAt(i)-32);
			else
				reStr+=str.charAt(i);
		}
		return reStr;
	}

}
