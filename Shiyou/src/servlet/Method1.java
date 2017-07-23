package servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
				
public class Method1 implements com.wabacus.system.serveraction.IServerAction {

	@Override
	public String executeServerAction(HttpServletRequest arg0,
			HttpServletResponse arg1, List<Map<String, String>> arg2) {
		
		System.out.println("111");
		return "111";
	}

	@Override
	public String executeSeverAction(ReportRequest arg0,
			IComponentConfigBean arg1, List<Map<String, String>> arg2,
			Map<String, String> arg3) {
		
		
		System.out.println("222");
		System.out.println(arg1);
		System.out.println(arg2.get(0));
		System.out.println(arg3);
		return "222";
	}

}
