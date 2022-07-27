package com.abc.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.DAO.UserService;
import com.abc.Dto.UserDTO;


@WebServlet("/registerform")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		//getting the printer object to write the response
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>Response Registration</title></head>");
		out.println("<body>");
		//collecting the data from request object using parameters
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("userpwd");
		String useremail = request.getParameter("useremail");
		String usermobile = request.getParameter("usermobile");
		
		
		//creqting the userdto object and setting the values
		UserDTO user = new UserDTO();
		user.setUsername(username);
		user.setPassword(password);
		user.setUseremail(useremail);
		user.setUsermobile(usermobile);
		
		
		UserService service = new UserService();
		boolean status = service.registerData(user);
		if(status) {
			out.println("<h1 style='color:green;text-align:center;'>Registration Successful</h1>");
		}
		else
		{
			out.println("<h1 style='color:red;text-align:center';>Registration Failed</h1>");
			out.println("<h1 style='color:blue;text-align:center;'><a href='registerform.html'>|REGISTER FORM|</a></h1>");
		}
		
		out.println("</body></html>");
		
		out.close();
		
		
		
		
	}

}
