
 package ProgramFiles;

 import java.text.*; 
 import java.util.*; 
 import java.sql.*; 
 
 public class UserDAO { 
     
     static Connection currentCon = null;
     static ResultSet rs = null; 
     
     public static UserBean login(UserBean bean) { 
//preparing some objects for connection 
         Statement stmt = null; 
         String username = bean.getUser_Name();
         String password = bean.getUser_Password(); 
         String searchQuery = "select * from USERS where (User_Name='" + username + "' OR User_Email='" + username +"') AND User_Password='" + password + "'"; 
// "System.out.println" prints in the console; Normally used to trace the process 
         System.out.println("Your user name is " + username); 
         System.out.println("Your password is " + password); 
         System.out.println("Query: "+searchQuery); 
         
         try { 
//connect to DB 
             currentCon = ConnectionManager.getConnection();   
             stmt=currentCon.createStatement();
             rs = stmt.executeQuery(searchQuery);
             boolean more = rs.next(); 
// if user does not exist set the isValid variable to false 
             if (!more) { System.out.println("Sorry, you are not a registered user! Please sign up first"); 
             bean.setValid(false);
             } 
             
//if user exists set the isValid variable to true 
             else if (more) 
             { 
                 String email = rs.getString("User_Email");
              String userID = rs.getString("User_ID");
              System.out.println("Welcome " + email); 
              
              bean.setUser_Email(email); 
              bean.setUser_ID(userID); 
              bean.setValid(true);
             }
         }
         catch (Exception ex) 
         { 
             System.out.println("Log In failed: An Exception has occurred! " + ex);
         }
         
         
// exception handling 
         finally { 
             if (rs != null) 
             { try { rs.close(); } 
             catch (Exception e) {} 
             
             rs = null; 
             } 
             
             if (stmt != null) { 
                 try { stmt.close(); } 
                 catch (Exception e) {} 
                 stmt = null; } 
             
             if (currentCon != null) 
             { try 
             { currentCon.close(); } 
             catch (Exception e) { } 
             
             currentCon = null; } }
         
         return bean; }
 
 }
