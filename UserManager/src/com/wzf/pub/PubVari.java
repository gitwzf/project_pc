package com.wzf.pub;

import java.util.ArrayList;

import com.wzf.model.UserInfo;

public class PubVari {
	public static ArrayList<UserInfo> userList=new ArrayList<UserInfo>();
	static{
		userList.add(new UserInfo(1,"andy","Andy"));
		userList.add(new UserInfo(2,"carl","Carl"));
		userList.add(new UserInfo(3,"bruce","Bruce"));
		userList.add(new UserInfo(4,"dolly","Dolly"));
		
	}

}
