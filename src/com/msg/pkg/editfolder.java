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
public class editfolder extends HttpServlet
{
	Connection con=null;
	Statement st=null;
	ResultSetMetaData meta=null;
	PrintWriter out=null;
public void init(ServletConfig sc)throws ServletException
{
	try
	{
	super.init(sc);
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractice","root","ROOT");
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
	String folder=req.getParameter("foldedit");
	out.println("<html><head><h2>Changing the Folder name</h2></head><br><br>");
	out.println("<html><body bgcolor=pink><form action=changefolder method=post>OldFolderName:<input type=text name=oldfolder value="+folder+"><br>NewFolderName<input type=text name=newfolder><br><input type=submit name=button value=change></body></form></html>");
	}catch(Exception e)
	{out.println(e.toString());
	e.printStackTrace();}
}
}