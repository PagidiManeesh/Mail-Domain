<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script language=javascript>
	function checkpwd()
	{
		if(document.f1.text1.value =="")	
		{	
			alert("Please fill the details..");
			document.form.text1.focus() ;
			return false;
			}
	if(!(document.f1.text2.value==document.f1.text3.value))	
	{	
		alert("Invalid Password Please Retry..");
		document.f1.text2.value="";
		document.f1.text3.value="";
		document.f1.text2.focus();
	}
	else
		document.f1.submit();
	}

</script>
<style type="text/css">body {
  background:#f4f4f4;
}
</style>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body onload=document.f1.text1.focus()>





<FORM action="Register" method=post name=f1>
<H2><center><font color="black"> Register Your Details</font></center></H2>

<center>
<table >
   <font color="white">
             <tr><td><h3>USERNAME</h3></td><td><input maxlength=15 name=text1 placeholder="Enter Your Username"class="textbox" size=15></td></tr>
             <tr><td><h3>PASSWORD</h3></td><td><input maxlength=20 TYPE=PASSWORD placeholder="Enter Your Passwurd " class="textbox" name=text2 size=10></td></tr>
             <tr><td><h3>CONFIRM</h3></td><td><input maxlength=20 TYPE=PASSWORD placeholder="Re-Type Password" class="textbox" name=text3 size=10></td></tr>
             <tr><td><h3>AGE</h3></td><td><input maxlength=3 name=text4 placeholder="Enter Your Age"class="textbox" size=3></td></tr>
             <tr><td><h3>GENDER</h3></td><td><input type=radio name=r1 value="Female">FEMALE<INPUT TYPE=RADIO NAME=r1 value="Male">MALE
             <tr><td><h3>CITY</h3><td><input maxlength=45 class="textbox" placeholder="Enter Your City" name=text6 size=10>
             <tr><td><h3>STATE</h3><td><input maxlength=45 class="textbox" placeholder="Enter Your State" name=text7 size=10>
             <tr><td><h3>PINCODE</h3><td><input maxlength=6 class="textbox" placeholder="Enter Your Pincode" name=text8 size=6>
             <tr><td><h3>NATION</h3><td><input maxlength=45 class="textbox" placeholder="Enter Your Country" name=text9 size=10>
    </font>
</table><p>
<input type=button value = "SUBMIT"  onClick=checkpwd() >
<input type=reset value="RESET"><br>


</center>
</FORM>
</body>
</html>