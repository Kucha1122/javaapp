import java.sql.*;

public class DockerConnectMySQL {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/MK?maxReconnects=10&useUnicode=true&characterEncoding=UTF8";

   static final String USER = "mkuspit";
   static final String PASS = "mkuspit";
   
   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      Connection con = DriverManager.getConnection(DB_URL,USER,PASS);

      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("show tables;");
      System.out.println("test");
   }
}
