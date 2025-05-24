

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Jdbc;


@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String tell=request.getParameter("userTell");
		String name=request.getParameter("userName");
		String password=request.getParameter("password");
		Jdbc jdbc=new Jdbc();
		try {
			jdbc.DBconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			jdbc.add(tell, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("webdemo.html?phone="+URLEncoder.encode(tell, "UTF-8") +  "&username=" + URLEncoder.encode(name, "UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8"));
	}

}
