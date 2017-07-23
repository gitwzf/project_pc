package com.wzf.compara;
import java.util.Comparator;

import com.wzf.model.UserInfo;

public class UserCom implements Comparator<UserInfo>{

	public int compare(UserInfo o1, UserInfo o2) {
		if(o1==null && o2!=null)
			return -1;
		if(o1!=null && o2==null)
			return 1;
		if(o1!=null && o2!=null){
		if(o1.getId()>o2.getId())
			return 1;
		if(o1.getId()<o2.getId())
			return -1;
		if(o1.getLoginname()==null&& o2.getLoginname()!=null)
			return -1;
		if(o1.getLoginname()!=null && o2.getLoginname()==null)
			return 1;
		if(o1.getLoginname()!=null && o2.getLoginname()!=null){
		    return o1.getLoginname().compareTo(o2.getLoginname());
		}
		}
		
		return 0;
		
	}

}
