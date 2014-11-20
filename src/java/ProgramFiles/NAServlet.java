/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramFiles;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dentm_000
 */
public class NAServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String targetPage;
        String userName;
        String userPassword;
        String userEmail;
  
        response.setContentType("text/html;charset=UTF-8");
        targetPage = request.getParameter("targetpage");
              
        try {
            
            userName = request.getParameter("username");
            userPassword = request.getParameter("password");
            userEmail = request.getParameter("email");
            
            
            TestNewAccount newAccount = new TestNewAccount(); 
            NewAccount account = new NewAccount();
            account.setJspMessage("I am setting the mesage");
            
            System.out.println("**********************this is the jsp msg:" + account.getJspMessage()); 
            
            newAccount.login(account, userName,userEmail);
            HttpSession session = request.getSession(true);
            session.setAttribute("thisUser", account);
            
            
            if (account.isEmail())
            {
            account.setJspMessage("There is already an account for this email");
            System.out.println("**********************this is the jsp msg:" + account.getJspMessage()); 
            response.sendRedirect("NewAccount.jsp");
            } else if (account.isUserName()){
            account.setJspMessage("This username is already taken");
            response.sendRedirect("NewAccount.jsp");
            }
            else{
           
                
                SendEmail email = new SendEmail();
                
                AddUser.login(userName, userPassword, userEmail);
                
                email.setUserName(userName);
                email.setUserEmail(userEmail);
                email.setUserSubject("Password");
                email.setUserMessage("<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<title>Login</title>\n" +
"<style type=\"text/css\">\n" +
".title {\n" +
"	font-size: 16px;\n" +
"	font-weight: bold;\n" +
"}\n" +
".items {\n" +
"	font-size: 16px;\n" +                        
"	font-weight: bold;\n" +
"	color: #00F;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"<div>\n" +
"<a href=\"http://newsfoil.com:8080/login/LoginPage.jsp\"><img src=\"http://newsfoil.com:8080/login/logo.jpg\" alt=\"newfoil\" name=\"Logo\" width=\"480\" height=\"144\" /></a>\n" +
"<br/>\n" +
"Welcome to Newsfoil! We are glad to have you as part of our community.<br/>\n" +
"But you probably want to get started so here is your username and password:<br/>\n" +
"<br/>\n" +
"<span class=\"title\">   Username: &nbsp;&nbsp;</span><span class=\"items\">"+ userName + "</span><br/>\n" +
"<span class=\"title\">   Password: &nbsp;&nbsp;</span><span class=\"itmes\">"+ userPassword +   "</span><br/>\n" +
"<br/>\n" +
"Login here: &nbsp;<a href=\"http://newsfoil.com:8080/login/LoginPage.jsp\">newsfoil.com</a>\n" +
"<br/><br/>\n" +
"Use your password to login.  You can change your password when you login by selecting \"Setting.\"\n" +
"<br/>\n" +
"You have a voice. Be heard.\n" +
"</div>\n" +
"</body>\n" +
"</HTML>");
                email.Send();
             
              response.sendRedirect("NewAccountConfirmation.jsp");
            } 
        }catch (Throwable theException) {
            System.out.println(theException);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
