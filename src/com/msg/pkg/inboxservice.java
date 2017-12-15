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
public class inboxservice extends HttpServlet
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
		String mailsel="select msgfrom,subject,msgdate from "+una;
		rs=st.executeQuery(mailsel);
		out.println("<html><BODY align=center>"
				+ "<form action=selectfolder  method=post name=f1>"
				+ "<h2>Welcome <FONT COLOR=blue>"+una+"</FONT></h2>");
		out.println("<input type=button name=t1 value=Delete onclick=location.href='deletemessage'><h3>Messages in inbox</h3>"
				+ "<TABLE align=center ><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
                              	while(rs.next())
			 {	
			String from=rs.getString(1);
			String sub=rs.getString(2);
			String dat=rs.getString(3);
			out.println("<TR><TD><INPUT TYPE=CHECKBOX NAME="+from+"|"+dat+">");
			out.println("<TD width=200 align=center><"
					+ "A href=getmsg?msgf="+from+"&msgd="+dat+" target=rightf>"+from+"</A>");
			out.println("<TD width=200 align=center>"+sub+""
					+ "<TD width=200 align=center>"+dat+"</TR>");
			}
			out.println("</Table><p><input type=button name=b1 value=Delete onclick=location.href='deletemessage'>");
			out.print("<INPUT TYPE=submit  VALUE='MoveSelected To'><select name='li'>");
			String foldsel="select foldername from "+una+"folder";
			rs=st.executeQuery(foldsel);
			while(rs.next())
				{out.println("<option>"+rs.getString(1));}
			out.println("</select></form></body></html>");
			}catch(Exception e)
			{out.println(e.toString());
			e.printStackTrace();}
	}
}