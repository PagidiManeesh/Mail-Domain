<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript>
	function checkfornull()
	{
		if(document.f1.una.value=="")
		{
			alert("Login User Field Contains no Data","User Name");
			document.f1.una.focus();
		}
		else if (document.f1.pwd.value=="")
		{
			alert("Password field Contains no Data","Password");
			document.f1.pwd.focus();
		}
		else
			document.f1.submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body onload=f1.una.focus()>
<h1 align="center"><font color="#2c3e50">Welcome to My WebSite !</font></h1><br>
<center><h2><FONT COLOR=BLUE>New Users :</FONT><A HREF="Registration.jsp">Register Here</A></h2></center>
<div class="middle" id="result-data">
<table> <tr><td><h2>Please Login to Continue</h2></td></tr></table>
            <form autocomplete="off" action="Signin" method=post name=f1>
                
            <table>
                <tr height="80px">
                    <td><h2>Username:</h2></td>
                    <td><input type="text" name="una" id="una" class="textbox" autocomplete="off" placeholder="Enter Your Username"></td>
                </tr>
                <tr height="20px">
                   <td> <h2>Password: </h2></td>
                    <td><input type="password" name="pwd" id="pwd" class="textbox" placeholder="Enter Your Password"></td>
                </tr>
                <tr>
                    <td><input type="Submit" value="Signin" class="circle" onclick="checkfornull()"></td>
                    <td><div id="loading-icon"></div></td>                            
                </tr>
            </table>
            </form>            
        </div>

</body>
</html>