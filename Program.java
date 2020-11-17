import java.sql.*;
import java.util.Scanner;
import java.lang.*;
 
public class Program { 
   public static void main(String[] args) throws SQLException, ClassNotFoundException {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";
      String DB_URL = "jdbc:mysql://10.0.10.3:3306/mkuspit?serverTimezone=UTC";
      String DB_USER = "mkuspit";
      String DB_PASS = "mkuspit";
 
      Statement stmt = null;
      Connection conn = null;
      while(conn == null){
      try {
	  conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
          if(conn != null)
          {
              System.out.println("Connected!");
              createDB(conn);
              insertUser(conn,1, "User1", "password1");
              insertUser(conn,2, "User2", "password2");
              insertUser(conn,3, "User3", "password3");
              showMenu();
	      String input = new Scanner(System.in).next();
	      while((input.equals("1") || input.equals("2")|| input.equals("3") || input.equals("4")))
	      {
                switch(input)
                {
                  case "1":
                    System.out.println("Enter id");
                    String ID = new Scanner(System.in).next();
                    int intId=Integer.parseInt(ID);  
                    System.out.println("Enter login");
                    String LOGIN = new Scanner(System.in).next();
                    System.out.println("Enter password");
                    String PASS = new Scanner(System.in).next();
                    insertUser(conn, intId, LOGIN, PASS);
                    break;
                  case "2":
                    showUsers(conn);
                    break;
                  case "3":
                      System.out.println("Enter User ID to delete usuer");
                      ID = new Scanner(System.in).next();
                      intId=Integer.parseInt(ID);
                      deleteUser(conn, intId);
                      break;
                  case "4":
                      System.out.println("Enter User ID to update");
                      ID = new Scanner(System.in).next();
                      intId=Integer.parseInt(ID);
                      System.out.println("Enter login");
                      LOGIN = new Scanner(System.in).next();
                      System.out.println("Enter pass");
                      PASS = new Scanner(System.in).next();
                      updateUser(conn, intId, LOGIN, PASS);
                      break;
                  case "5":
                      System.exit(0);
                  default:
                    System.out.println("Chose option");
                    showMenu();
                    break;       
                }
 
                showMenu();
                System.out.println("Select option: ");
                input = new Scanner(System.in).next();
              }
          }
      }
      catch (SQLException ex)
      {
          System.out.println("Can't connect to db");
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      try {
        Thread.sleep(10000);
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      }
   }
   public static void createDB(Connection conn) throws SQLException
   {
       String sql = "CREATE TABLE User (ID INT NOT NULL, LOGIN VARCHAR(20) NOT NULL, PASS VARCHAR(25) NOT NULL, PRIMARY KEY(ID));";
 
       PreparedStatement statement = conn.prepareStatement(sql);
 
       statement.execute(sql);
       System.out.println("User table is created");
 
   }
 
      public static void insertUser(Connection conn, int id, String user,String pass) throws SQLException
   {
 
       String sql = "INSERT INTO User (ID, LOGIN, PASS) VALUES(?, ?, ?)";
 
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setInt(1, id);
       statement.setString(2, user);
       statement.setString(3, pass);
 
       int rowsInserted = statement.executeUpdate();
       if(rowsInserted > 0)
       {
           System.out.println("A new user was inserted successfully!");
       }
 
   }
   public static void showUsers(Connection conn) throws SQLException
   {
       String sql = "SELECT * FROM User;";
       Statement statement = conn.createStatement();
       ResultSet rs = statement.executeQuery(sql);
 
       while(rs.next())
       {
           int id = rs.getInt("ID");
           String login = rs.getString("LOGIN");
           String pass = rs.getString("PASS");
 
           System.out.println("ID: "+id+", LOGIN: "+login+", PASS: "+pass);
       }
       rs.close();
   }
 
   public static void deleteUser(Connection conn, int userId) throws SQLException
   {
       String sql = "DELETE FROM User WHERE ID = ?";
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setInt(1, userId);
 
       int rowsDeleted = statement.executeUpdate();
       if(rowsDeleted > 0)
       {
           System.out.println("User with"+userId+" is deleted correctly.");
       }
   }
 
   public static void updateUser(Connection conn, int userId, String login, String pass) throws SQLException
   {
       String sql = "UPDATE User SET LOGIN = ?, PASS = ? WHERE ID = ?";
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setString(1, login);
       statement.setString(2, pass);
       statement.setInt(3, userId);
 
       int rowsUpdated = statement.executeUpdate();
       if(rowsUpdated > 0)
       {
           System.out.println("User with"+userId+" is updated correctly.");
       }
       statement.close();
   }
 
   public static void showMenu()
   {
        System.out.println();			        
        System.out.println("              *****************************************                  ");
        System.out.println("              |            Command Menu DB          |                  ");
        System.out.println("              *****************************************                  ");
        System.out.println("              | Options:                              |                  ");
        System.out.println("              |        1. User Data Entry             |                  ");
        System.out.println("              |        2. Show User Table             |                  ");
        System.out.println("              |        3. Remove User with ID         |                  ");
        System.out.println("              |        4. Update user                 |                  ");
        System.out.println("              |        5. Exit                        |                  ");
        System.out.println("              *****************************************                  ");	
        System.out.println();		
   }
 
 
}
