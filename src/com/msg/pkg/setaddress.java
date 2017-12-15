/**
 * 
 */
package com.msg.pkg;

/**
 * @author Pagidi Maneesh
 *
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class setaddress extends HttpServlet
{
	Connection con;
	Statement st;
	PrintWriter out;
public void init(ServletConfig sc)throws ServletException
{
	try
	{
	super.init(sc);
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/messagedb","root","ROOT");
	st=con.createStatement();
	}catch(Exception e)
		{System.out.println(e.toString());}
}
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	try
	{
	res.setContentType("text/html");
	out=res.getWriter();
	String na=req.getParameter("name");
	String ni=req.getParameter("nick");
	String ad=req.getParameter("addr");
	String una=null;	
	Cookie[] userc = req.getCookies(); 
	
	if(userc!=null)
		for(int i=0;i<userc.length;i++)
		{			
			if(userc[i].getName().equals("signin"))
			{
				una=userc[i].getValue();
				break;
			}
		}
	out.println(una);
	st.execute("insert into "+una+"addbook values('"+na+"','"+ni+"','"+ad+"')");
	out.println("record inserted successfully,do u want insert onemore<a href='addnew.html'> address </a>");
	}catch(Exception e)
	{out.println(e.toString());
	e.printStackTrace();}
}
}