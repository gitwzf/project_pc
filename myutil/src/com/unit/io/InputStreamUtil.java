package com.unit.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.unit.model.ReMethodObj;
import com.unit.pubvari.Variable;

public class InputStreamUtil {
	
	public static ReMethodObj getToStr(InputStream input,String enCode){
		if(!Variable.codeTypeMap.containsKey(enCode)){
			return new ReMethodObj(-1, "no this kind of encode");
		}
		StringBuffer buffer = new StringBuffer();  
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(input,enCode);
		} catch (UnsupportedEncodingException e1) {
			//no this kind of encode
		}
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
        String str = null;  
        try {
			while ((str = bufferedReader.readLine()) != null) {  
			    buffer.append(str);  
			}
			bufferedReader.close();
			inputStreamReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return new ReMethodObj(-1, "IOException:"+e.getMessage());
		}  
		return new ReMethodObj(1, "", buffer.toString());
	}

}
