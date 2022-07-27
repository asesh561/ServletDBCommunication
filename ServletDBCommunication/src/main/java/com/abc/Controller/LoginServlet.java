package com.abc.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.DAO.UserService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
       
    }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setting the MIME Content type
		response.setContentType("text/html");
		
		//getting the printwriter object to write the response
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>Login</head>");
		out.println("<body>");
		
		//collecting the request parameters
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		
		//creating an object and making a call to user Service checkLogin()
		UserService service = new UserService();
		boolean status=service.checkLogin(uname, pwd);
		
		
		//processing the status sent by the service layer
		if(status)
		{
			out.println("<h1 style='color:green; text-align:center;'>LOGIN SUCCESSFUL</h1>");
		}
		else
		{
			out.println("<h1 style='color:red; text-align:center;'>LOGIN FAILED</h1>");
			out.println("<h1 style='color:blue; text-align:center;'><a href='loginform.html'>|LOGIN PAGE|</h1>");
		}
		
		out.println("</body></html>");
		
	}

}
