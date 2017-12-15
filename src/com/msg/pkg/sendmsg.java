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
import java.math.BigInteger;
import java.sql.*;


public class sendmsg extends HttpServlet
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
	String to=req.getParameter("tto");
	String sub=req.getParameter("tsub");
	String m=req.getParameter("ta");
	String msg=encry.encryptPlainStrToHex(m);
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
	java.util.Date d = new java.util.Date();
	System.out.println("the date is " + d.getDate());
	st.execute("insert into "+to+" values('"+una+"','"+sub+"','"+msg+"','"+d.getDay()+"/"+d.getMonth()+"/"+d.getYear()+"')");
	out.println("<body><font color=orange><h4>Your Message has been send suceffully,do u want to <FONT COLOR=YELLOW> <a href='compose.html'><I>send</I></FONT></a>  another message</h4></font></body>");
	}catch(Exception e)
	{out.println(e.toString());}
}
}