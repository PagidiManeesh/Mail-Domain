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
import java.util.*;
public class deletefol extends HttpServlet
{
	Connection con;
	ResultSet rs=null;
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
		String una=null;
		Cookie[] c = req.getCookies();
		if(c!=null)
		for(int i=0;i<c.length;i++)
		{
			if(c[i].getName().equals("signin"))
			{
			una=c[i].getValue();
			break;
			}
		}
		String folder=req.getParameter("delete");
		String del="delete  from "+una+"folder  where foldername='"+folder+"'";
		st.executeUpdate(del);		
		con.commit();
		rs=st.executeQuery("select * from "+una+"folder");
	out.println("<p align=right><a href='addfolder.html'>Add</a></p>");
	out.println("<table border=2><tr><th>FolderName<th>TotalMails<th>Options</tr>");
	while(rs.next())
	{	
		String folname=rs.getString(1);
		String total=rs.getString(2);
		
		out.println("<tr><td><form action=getmails method=post><input type=submit  name=folder value="+folname+"></form><td>"+total+"<td><form action=http://localhost:8080/servlet/editfolder method=post><input type=hidden name=foldedit value="+folname+"><input type=submit  name=edit value=Edit></form><form action=deletefol   method=post><input type=hidden name=delete value="+folname+"><input type=submit   name=delete value=Delete></form>");
	}
	}catch(Exception e)
	{out.println(e.toString());
	e.printStackTrace();}
}
}                        