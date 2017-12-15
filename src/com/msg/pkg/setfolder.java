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
public class setfolder extends HttpServlet
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
	String foldername=req.getParameter("foldname");
	out.println(foldername);
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
	String foldcre="create table "+foldername+"(frommail varchar(100),subject varchar(100),message varchar(100),dat varchar(25))";
	st.execute(foldcre);
	String fol="insert into "+una+"folder values('"+foldername+"',4)";
	st.execute(fol);
	out.println("folder inserted successfully,do u want insert onemore<a href='addfolder.html'> folder </a>");
	}catch(Exception e)
	{out.println(e.toString());
	e.printStackTrace();}
}
}