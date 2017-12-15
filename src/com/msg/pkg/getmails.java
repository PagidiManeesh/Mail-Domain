/**
This program displays all the mails in the right frame of the inbox

Messages are retrived from the saved folder
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
public class getmails extends HttpServlet //importing of data present in the httpservlet
{
	Connection con=null;
	Statement st=null;
	ResultSet rs;
	ResultSetMetaData meta=null;
	PrintWriter out=null;
public void init(ServletConfig sc)throws ServletException/*initiliziing the servlet*/
{
	try/*stating the database connection*/
	{
	super.init(sc);
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/messagedb","root","ROOT");
	st=con.createStatement();/*Creating Statement object for sending SQL statements to the database.*/
	}catch(Exception e)
		{System.out.println(e.toString());}
}
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	try
	{
	res.setContentType("text/html");//setting the response type as html
	out=res.getWriter();
	String foldername=req.getParameter("folder");//rquested folder selected by the user 
	out.println("<html><h2><font color=red>Mails in "+foldername+"</font></h2>");/*printing the specified html i.e displayed at the right frame of inbox*/
	out.println("<table border><BODY ><form action=deletefolder  method=post ><input  type=hidden name=folder value="+foldername+"><input type=submit  value=Delete >");
	out.println("<TABLE BORDER=0><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
	String fol="select * from "+foldername+"";
	rs=st.executeQuery(fol);
	while(rs.next())
	{
		String from2=rs.getString(1);
		String sub2=rs.getString(2);
		String msg2=rs.getString(3);
		String dat2=rs.getString(4);	               	
		out.println("<TR><TD><INPUT TYPE=CHECKBOX  NAME="+from2+"|"+dat2+"></tr>");
		out.println("<TD width=200 align=center><A href=getmessage?from1="+from2+"&sub1="+sub2+"&msg1="+msg2+"&dat1="+dat2+"&foldname="+foldername+" target=rightf>"+from2+"</A>");
		out.println("<TD width=200 align=center>"+sub2+"<TD width=200 align=center>"+dat2+"</TR>");/*here the message is saved in to specified folder*/
	}
		out.println("</form>");
	}catch(Exception e)
	{out.println(e.toString());
	e.printStackTrace();}
}
}	
