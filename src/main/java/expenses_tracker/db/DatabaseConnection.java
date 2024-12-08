package expenses_tracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static final String URL = "jdbc:postgresql://localhost:5432/expenses_tracker";
  private static final String USER = "postgres";
  private static final String PASSWORD = "postgres";

  public static Connection getConnection(){
    try{
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch(SQLException e){
      throw new RuntimeException("Error when conecting to the database: ", e);
    }
  }

}
