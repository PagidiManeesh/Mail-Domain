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

class encry
{
	static int BitSize=1024;
	static BigInteger PrimeP = new BigInteger("152216014155936948463019789890251665168053424227600901335553427250453639603984012561884772271582641862208946951111378985441145605556412590706698990404742998581103908185003513618154812560404607606319014566172704605871740439435926392855965963298280770487127152181785781901596795309495264243702592419402896186307");
	static BigInteger PrimeQ = new BigInteger("179184404286446533011485691551736349045632148462161207984061151693267072333373448573454199651608765232377373687043792204102156448881186217956009699682414546580316009307770011016904736647648950602417789406544128711303205317238003339403947481011491827372409320953559975869317866565495936337972108763740543944697");
	static BigInteger ModN = new BigInteger("27274735819388874695720283533351493079800335254281795399358379193653494545425997823710079849924387242475030021844537336009680033519395894787168848957799771622751163010320237387629169407802870081524072583538102945207667659383521578671258106146080022273268948849770230283924329693319616598542191023033392801079939674046033921121960608415389226906880152016896604016208842574682293371318122808497156512925644128176036309939239494496062582426703743786245076871488295895404011141553017524245568854661363577139477053986470833632920925321367838880522742961752098077458646021749269349429793807074183878897145091395163216663979");
	static BigInteger phiN = new BigInteger("27274735819388874695720283533351493079800335254281795399358379193653494545425997823710079849924387242475030021844537336009680033519395894787168848957799771622751163010320237387629169407802870081524072583538102945207667659383521578671258106146080022273268948849770230283924329693319616598542191023033392801079608273627591537640486102933947238892666466444206841906889227995738572659380765347361817541002452721081449989301084323306519280372266144977582368181401138350242591224060243999610509305453310018930740250013754000315745979564693909148262829517442325479599109548613923591658879145199192678315470390212019776532976");
	static BigInteger e = new BigInteger("25515103283490932556091167422891179905228477565247171205317957780214432800037789614346711615921272967518989503193982107265055075857901686212297233138557484155754106655195037680297132211181696065265482690509863762705747253295725800058555432705402294014630778138766215137975826162997492342730045910348824899923451264098192984940292523836641585332112872739751931670166332871759605990394396743338673251260133925851181766325379748927439595674317788848321603848812152350507355732375360683935279294361358854242255782268514300010227671108299689716598593687029544188551643272914277429445393515027769584196163687625390199662943");
	static BigInteger PrivateKey = new BigInteger("21560334540726937611690981709262251224872562482010015677814657387007111365230174206761872655405644813405393574089956784340770162923589677787919028570722666738745126377364116180925993813196950728972788135015592480365078875124800297725506655323268961690702507682352935266442377001927318363051257084356697403776925154189503888463384561884226179438794049518701840732153746891126323747331707895160838114759687406128558752343787500196089005750592929913139325915163637017345672775424386219202158547506243728433867744130811978921013819843697889515338338139046055293333246814447091966190281236659581310783302765177994047300303");
	
/*BigInteger P=;

long	Q=;


long	n=;

long	phiN=;

long	e=;

long d=;*/

	
	 public static String convertStringToHex(String str)//convert input plain text to hex string
    {
        char[] chars = str.toCharArray();
        StringBuilder hex = new StringBuilder();//Constructs a string builder with no characters in it and an initial capacity of 16 characters.
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));//Appends the specified string to this character sequence
        }
        return hex.toString();
    }
	
	 
	 
	
	 private static String encryptMessage(String sHexString, BigInteger N, BigInteger e)
    {
        //System.out.println(":::::encrypt Function Started::::::");
        if(sHexString.length()==0 || sHexString == null)
            return null;
        int iMaxCharLenInOneStr = BitSize/2;
        if (iMaxCharLenInOneStr <= sHexString.length())/*Returns the length of this string. The length is equal to the number of Unicode code units in the string.*/
        {
            String sRetOutStr = "";//return string
            String sOutStr = null;// local string
            int iBeginIndex = 0;
            int iEndIndex = iMaxCharLenInOneStr - 1;
            while (iBeginIndex < sHexString.length())
            {
                if (iEndIndex < sHexString.length()) {
                    sOutStr = (new BigInteger(sHexString.substring(iBeginIndex, iEndIndex), 16)).modPow(e, N).toString(16);/*
                  ***BigInteger(str, radix):  Translates the String representation of a BigInteger in the specified radix into a BigInteger. The String representation consists of optional
                   minus or plus sign followed by a sequence of one or more digits in the specified radix. The character-
                   to-digit mapping is provided by Character.digit. The String may not contain any extraneous characters (whitespace, for example).
                  ***substring(s,e) retuns substring starting from s to e
                  *** calculate m^e mod n  
                   */
                    iBeginIndex = iEndIndex;
                    iEndIndex += (iMaxCharLenInOneStr - 1);
                }
                else
                {
                    sOutStr = (new BigInteger(sHexString.substring(iBeginIndex), 16)).modPow(e, N).toString(16);
                    /*BigInteger(str, radix):  Translates the String representation of a BigInteger in the specified radix into a BigInteger. The String representation consists of optional
                   minus or plus sign followed by a sequence of one or more digits in the specified radix.*/
                    iBeginIndex = sHexString.length();
                }
                if(sOutStr.length() < iMaxCharLenInOneStr)
                {
                    int iLen = iMaxCharLenInOneStr - sOutStr.length();
                    for(int k = 0;k < iLen;k++)
                        sOutStr = "0" + sOutStr;
                }
                sRetOutStr += sOutStr;
            }
            return sRetOutStr;
        }
        else
            return (new BigInteger(sHexString, 16)).modPow(e, N).toString(16);
        //System.out.println(":::::encrypt Function Ended::::::");
    }
	 
	 
	 public static String encryptPlainStrToHex(String sPlainStr)//the method(1 Called method) accepts the plain string and then it invokes encyptmessage with parameter as other function which converts as string 
     {
         return encryptMessage(convertStringToHex(sPlainStr),ModN, PrivateKey);//this function returns encypted message encyptmessage with parameter as other function which converts as string 
         
     }
	 
}

public class Signin extends HttpServlet
{
	Connection con;
	Statement st;
	PrintWriter out;
	ResultSet rs;
	boolean b=false;
	
	 
	public void init(ServletConfig sc)throws ServletException
	{
		try
		{
			super.init(sc);
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/messagedb","root","ROOT");
			st=con.createStatement();
		}catch(Exception e){System.out.println(e.toString());}
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{ 
		try
		{
			
 		res.setContentType("text/html");
		out=res.getWriter();
		String u=req.getParameter("una");
		String s=req.getParameter("pwd");
		String p=encry.encryptPlainStrToHex(s);
		System.out.println("in the service   " +u);
		/**/
		rs=st.executeQuery("select password from mailusers where username='"+u+"'"); 
		if(rs.next())
		{
			String pwd=rs.getString(1);
			System.out.println("in the service   " +p);
			if(pwd.equals(p))
			{
					System.out.println("in the service in password");
				Cookie cook=new Cookie("signin",u);
				res.addCookie(cook);
				//res.sendRedirect("http://localhost:8080/inbox.html");
				out.println("correct username <a href='inbox.html'> inbox");/*//out.println("<a href='logout.jsp'>Log out</a>");
        response.sendRedirect("success.jsp");*/
			}
			else
				out.println("Type correct password");
		}
		else
			out.println("<h1>Invalid user Name,press back button and try again....</h1>"); 
		}catch(Exception e)
		{out.println("Error"+e.toString());
		e.printStackTrace();}
	}
}
