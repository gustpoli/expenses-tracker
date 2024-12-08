package expenses_tracker.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
 
  @Test
  public void testGetConnection() {
    try (Connection connection = DatabaseConnection.getConnection()) {
      assertNotNull(connection, "Connection should not be null");
      assertFalse(connection.isClosed(), "Connection should be open");
    } catch (SQLException e) {
      fail("Should not have thrown any SQLException: " + e.getMessage());
    }
  }

}
