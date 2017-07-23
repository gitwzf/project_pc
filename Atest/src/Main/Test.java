package Main;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Test{
	public static void main(String[] args) throws ServiceException, AxisFault{
		String url = "http://localhost:8080/AxisWeb/services/TestWebService.jws";//           
		 Service service = new Service();
		 Call call;
		 try {
		 call = (Call) service.createCall();
		 call.setTargetEndpointAddress(new java.net.URL(url) );
		//  call.setOperationName("testWebsServiceNull" );// 这是要调用的方法
		//  String res = (String) call.invoke(new Object[] {null});
		 
		 call.setOperationName("testWebsServiceOne" );// 这是要调用的方法
		 String res = (String) call.invoke( new Object[] {"徐士宽"} );
		 
		//  call.setOperationName("testWebsServiceTwo" );// 这是要调用的方法
		//  String res = (String) call.invoke( new Object[] {17,"徐士宽"} );
		 
		 System.out.println(res);
		  }catch (MalformedURLException e) {
		 
		 } catch (RemoteException e) {
		  e.printStackTrace();
		  } catch (ServiceException e) {
		  e.printStackTrace();
		 }
		}
	




}
