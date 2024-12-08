package expenses_tracker.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import expenses_tracker.db.DatabaseConnection;
import expenses_tracker.model.Expense;

public class ExpenseDAO implements DAO<Expense> {

  private Connection connection;
  
  public ExpenseDAO(){
    this.connection = DatabaseConnection.getConnection();
  }

  public void setConnection(Connection connection){
    this.connection = connection;
  }

  @Override
  public boolean insert(Expense expense){
    String query = "INSERT INTO expenses (value, description, date, category_id) VALUES (?, ?, ?, ?)";

    try(PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setBigDecimal(1, expense.getValue()); 
      stmt.setString(2, expense.getDescription()); 
      stmt.setDate(3, Date.valueOf(expense.getDate())); 
      stmt.setInt(4, expense.getCategoryId()); 
      int affectedRows = stmt.executeUpdate();
      if(affectedRows > 0)
        try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
          if(generatedKeys.next()) {
            expense.setId(generatedKeys.getInt(1));
            return true; 
          }
        }
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(Expense expense){
    String query = "UPDATE expenses SET value = ?, description = ?, date = ?, category_id = ? WHERE id = ?";
    System.out.println("ID:" + expense.getId());

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setBigDecimal(1, expense.getValue()); 
      stmt.setString(2, expense.getDescription()); 
      stmt.setDate(3, Date.valueOf(expense.getDate())); 
      stmt.setInt(4, expense.getCategoryId()); 
      stmt.setInt(5, expense.getId()); 
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean delete(int id){
    String query = "DELETE FROM expenses WHERE id = ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id); 
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public boolean delete(List<Integer> ids){
    if(ids.size() == 0) return false;

    String query = "DELETE FROM expenses WHERE";
    for(int i = 0; i < ids.size(); i++) 
      if(i == 0) query += " id = ?"; 
      else query += " OR id = ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)){
      for(int i = 0; i < ids.size(); i++) stmt.setInt(i+1, ids.get(i));
      return stmt.executeUpdate() > 0;
    } catch(SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deleteAll(){
    String query = "DELETE FROM expenses";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Expense get(int id){
    String query = "SELECT * FROM expenses WHERE id = ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()){
        return new Expense(
          rs.getInt("id"),
          rs.getBigDecimal("value"),
          rs.getString("description"),
          rs.getDate("date").toLocalDate(),
          rs.getInt("category_id")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;    
    }

    return null;
  }

  @Override
  public List<Expense> getAll(){
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        expenses.add(new Expense(
          rs.getInt("id"),
          rs.getBigDecimal("value"),
          rs.getString("description"),
          rs.getDate("date").toLocalDate(),
          rs.getInt("category_id")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;    
    }

    return expenses;
  }

  public List<Expense> getByCategory(int id){
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses WHERE category_id = ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        expenses.add(new Expense(
          rs.getInt("id"),
          rs.getBigDecimal("value"),
          rs.getString("description"),
          rs.getDate("date").toLocalDate(),
          rs.getInt("category_id")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;    
    }

    return expenses;
  }

  public List<Expense> getWithinDateRange(LocalDate start, LocalDate end){
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses WHERE date BETWEEN ? AND ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setDate(1, Date.valueOf(start));
      stmt.setDate(2, Date.valueOf(end));
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        expenses.add(new Expense(
          rs.getInt("id"),
          rs.getBigDecimal("value"),
          rs.getString("description"),
          rs.getDate("date").toLocalDate(),
          rs.getInt("category_id")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;    
    }

    return expenses;
  }

  public List<Expense> list(int limit, int offset){
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses LIMIT ? OFFSET ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, limit);
      stmt.setInt(2, offset);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        expenses.add(new Expense(
          rs.getInt("id"),
          rs.getBigDecimal("value"),
          rs.getString("description"),
          rs.getDate("date").toLocalDate(),
          rs.getInt("category_id")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;    
    }

    return expenses;
  }

}
