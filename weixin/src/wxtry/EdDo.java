package wxtry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EdDo extends HttpServlet{
	Dbcon db=new Dbcon();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String delId=req.getParameter("delId");
		String id=req.getParameter("id");
		String title=req.getParameter("title");
		String main=req.getParameter("main");
		String picurl=req.getParameter("picurl");
		String link=req.getParameter("link");
		if(delId!=null)
			try {
				System.out.println(delId);
				db.delId(Integer.parseInt(delId));
				//resp.sendRedirect("Edit.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			else
		if(title!=null&&main!=null){
			try {
				db.addPicurl(Integer.parseInt(id),title, main,picurl,link);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
		try {
			req.getSession().setAttribute("repArr", db.repArr());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("Edit.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
