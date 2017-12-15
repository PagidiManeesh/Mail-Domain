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

public class Register extends HttpServlet {
	Connection con;
	Statement st;
	PrintWriter out;
	PreparedStatement prest;

	public void init(ServletConfig sc) throws ServletException {
		try {
			super.init(sc);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/messagedb", "root", "ROOT");
			System.out.println("connectuon sucess");
			st = con.createStatement();
		} catch (Exception e) {
			System.err.println("Error"+e.getMessage());
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			res.setContentType("text/html");
			out = res.getWriter();
			String username = req.getParameter("text1");
			String password = encry.encryptPlainStrToHex(req.getParameter("text2"));
			System.out.println("register" + password);
			int age = Integer.parseInt(req.getParameter("text4"));
			String gender = req.getParameter("r1");
			String sex = null;
			if (gender.equals("Male"))
				sex = "Male";
			else if (gender.equals("Female"))
				sex = "Female";
			String city = req.getParameter("text6");
			String state = req.getParameter("text7");
			int pincode = Integer.parseInt(req.getParameter("text8"));
			String nation = req.getParameter("text9");
			Cookie cook1 = new Cookie("signin1", username);
			res.addCookie(cook1);
			System.out.println("insertion");
			String Query = "insert into mailusers values('" + username + "','" + password + "'," + age + ",'" + sex
					+ "','" + city + "','" + state + "'," + pincode + ",'" + nation + "')";
			st.execute(Query);
			String UserTabCre = "create table " + username
					+ "(msgfrom varchar(1000),subject varchar(200),msg varchar(1000),msgdate varchar(20))";
			String UserAddCre = "create table " + username
					+ "addbook(name varchar(100),nick varchar(50),addr varchar(200))";
			String UserFolder = "create table " + username
					+ "folder(foldername varchar(25) primary key,totalmails INTEGER(100))";
			System.out.println(UserTabCre);
			prest = con.prepareStatement(UserTabCre);
			prest.execute();
			prest = con.prepareStatement(UserAddCre);
			prest.execute();
			prest = con.prepareStatement(UserFolder);
			prest.execute();
			// res.sendRedirect("http://localhost:8080/inbox1.html");
			res.sendRedirect("inbox.html");
		} catch (Exception e) {
			out.println(e.toString());
			e.printStackTrace();
		}
		// res.sendRedirect("http://localhost:8080/usersignup.html");}
	}
}
