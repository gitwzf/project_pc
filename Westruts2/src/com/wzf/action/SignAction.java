package com.wzf.action;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.marker.weixin.MySecurity;

import com.opensymphony.xwork2.ModelDriven;
import com.wzf.form.SignForm;
import com.wzf.method.Sendmessage;

public class SignAction implements ModelDriven<SignForm> {
	
	private SignForm signform=new SignForm();
	private String token="D8yuc";
	
	public String weixin(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		if(request.getMethod().equals("GET"))
			sign(response);
		else
			Sendmessage.Sendwxmessage(request, response);
		return null;
	}
	public void sign(HttpServletResponse response){
		// ��дtotring�������õ�����������ƴ���ַ���
		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 2621444383666420433L;
			public String toString() {
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(token);
		list.add(signform.getTimestamp());
		list.add(signform.getNonce());
		Collections.sort(list);// ����
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);// SHA-1����
		Writer out;
		try {
			out = response.getWriter();
		if (signform.getSignature().equals(tmpStr)) {
			System.out.println(signform.getEchostr());
			out.write(signform.getEchostr());// ������֤�ɹ������������
		} else {
			out.write("");
		}
		out.flush();
		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public SignForm getModel() {
		// TODO Auto-generated method stub
		return signform;
	}
	

}
