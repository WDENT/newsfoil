
<%@ page language="java" contentType="text/html; charset=windows-1256" 
 pageEncoding="windows-1256" 
 import="ProgramFiles.NewAccount" 
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>New Account</title>

        <link href="css/login.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript">

        var keylist="abcdefghijklmnopqrstuvwxyz123456789";
        var temp='';

        function generatepass(){
        temp='';
        for (i=0;i<7;i++)
        temp+=keylist.charAt(Math.floor(Math.random()*keylist.length));
        return temp;
        }

            function populateform() {

                var screenMsg = " ";
                document.account.password.value = generatepass();


                if (validateFields() === "true")
                    document.forms["account"].submit();
            }

        </script>

        <script language="JavaScript1.2">

            function validateFields()
            {
                var testresults = "";
                var str = document.account.email.value;
                var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
                var screenMsg = "";
                if (!document.account.username.value)
                    screenMsg = "Please enter a username ";
                else if (!document.account.email.value)
                {
                    if (screenMsg == "")
                        screenMsg = "Please enter a email";
                    else
                        screenMsg = screenMsg + "<br/> Please enter a email. ";
                }
                else if (document.account.email.value != document.account.email2.value)
                {
                    screenMsg = "Emails do not match";
                }
                else {
                    if (filter.test(str))
                        testresults = "true";
                    else
                        screenMsg = "Email is not valid";
                }

                document.getElementById("errormsg").innerHTML = screenMsg;
                return testresults;
            }
        </script>

    </head>

    <body>

        <div class="container">



            <div class="header"><!-- end .header -->

                <div class="fltlft">
                    <img src="images/Voice.jpg" alt="Logo" width="192" height="144" border="1" />
                </div>

                <div class="fltcent">
                    <img src="images/logo.jpg" alt="Logo" align="middle" />
                </div>



                <div class="fltrt">
                    <form name="login" action="NFServlet" method = "post">             

                        <input ID="tb1" type="username" name="username" placeholder="username"/>
                        <input  ID="tb1" type="password" name="password" placeholder="password"/>
                        <input ID="tb1" type="submit" value="Sign In">   
                            <input type="hidden" name="targetpage" value="Login"/>
                    </form>

                    <a class="pwitem" href="NewAccount.jsp">&nbsp;New Account &nbsp;</a>
                    <a class="pwitem" href="ForgottenPassword.jsp">&nbsp; Forgot Password &nbsp;</a>
                </div>


                <div class="content">

                    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                    <script src="typed.js" type="text/javascript"></script>

                    <form id= "account" name="account" action="NAServlet" method = "post"> 
                    <input type="hidden" name="password">
                    <input type="hidden" name="targetpage" value="NewAccount">
                    <span class="contentText">  Please enter a username :</span>
                    <input type="text" name="username"/><br/><br/>
                    <span class="contentText">  Please enter your email address</span>
                    <input type="text" name="email"/> <br/><br/>
                    <span class="contentText">  Please re-enter your email address:</span>
                    <input type="text" name="email2"/> <br/><br/>
                    <span class="emsg" id="errormsg"> 
                    
                    <% NewAccount currentUser = (NewAccount) session.getAttribute("thisUser");%>
                    <%out.println(currentUser.getJspMessage());%>  

                    
                    </span>
                                    

                                    
                                    
                                <br/><br/>
                    <input type="button" value="Sign Up" onClick="populateform()">


                     </form> 
                    

                                    <div class="element"></div>
                                    <!-- end .content --></div>

                                    <!-- end .container --></div>
                                    </body>
                                    </html>


