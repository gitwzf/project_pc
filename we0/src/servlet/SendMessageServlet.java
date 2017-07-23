package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.WxService;
import model.Fans;

import dbconnection.Dbconn;

public class SendMessageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendMessageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		WxService wxS = new WxService();
		String content=request.getParameter("content");
	//	String fackId = request.getParameter("fackId");
		Dbconn db=new Dbconn();
		String pub=(String) request.getSession().getAttribute("pub");
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		wxS.login(s[0], s[1]);
		ArrayList<Fans>fans=(ArrayList<Fans>) request.getSession().getAttribute("fansarray");
		System.out.println(fans.get(0).getFakeid());
		boolean flag = wxS.sendMessageAll(content, fans);
		PrintWriter out = response.getWriter();
		if(flag){
			out.println("1");
		}else{
			out.println("0");
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String fackId = request.getParameter("fackId");
		String content = request.getParameter("content");
		WxService wxS = new WxService();
		Dbconn db=new Dbconn();
		String pub=(String) request.getSession().getAttribute("pub");
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		wxS.login(s[0], s[1]);
		boolean flag = wxS.sendMessageById(content, fackId);
		String[] a = null;
	//	boolean flag = wxS.sendMessageAll(content, a,request);
		PrintWriter out = response.getWriter();
		if(flag){
			out.println("1");
		}else{
			out.println("0");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
