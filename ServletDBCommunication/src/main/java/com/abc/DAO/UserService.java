package com.abc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.abc.Dto.UserDTO;

public class UserService 
{
	
	private static final String SQL_SELECT_QUERY = "SELECT USERNAME,PASSWORD FROM USERINFO WHERE USERNAME=? AND PASSWORD=?";
	//jdbc resources used
	Connection connection=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet resultSet=null;
	
	public UserService()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    connection=DriverManager.getConnection("jdbc:mysql:///abc","root","Root@123");
		    stmt=connection.createStatement();
		    pstmt=connection.prepareStatement(SQL_SELECT_QUERY);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//service of checking the login credentials
	public boolean checkLogin(String username,String password)
	{
		System.out.println("UserService.checkLogin()");
		try
		{
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			resultSet=pstmt.executeQuery();
			if(resultSet.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//service for registering  the data to the application
	public boolean registerData(UserDTO user)
	{
		try
		{
			String SQL_INSERT_QUERY= String.format("insert into userinfo(username,password,useremail,usermobile)values('%s','%s','%s','%s')",user.getUsername(),user.getPassword(),user.getUseremail(),user.getUsermobile());
		    int rowCount = stmt.executeUpdate(SQL_INSERT_QUERY);	
		    if(rowCount>0)
		    {
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
