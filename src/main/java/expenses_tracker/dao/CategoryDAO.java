package expenses_tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import expenses_tracker.db.DatabaseConnection;
import expenses_tracker.model.Category;

public class CategoryDAO implements DAO<Category> {

  private Connection connection;

  public CategoryDAO(){
    this.connection = DatabaseConnection.getConnection();
  }

  public void setConnection(Connection connection){
    this.connection = connection;
  }

  @Override
  public boolean insert(Category category){
    String query = "INSERT INTO categories (name, icon) VALUES (?, ?)";

    try(PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, category.getName());
      stmt.setString(2, category.getIcon());
      int affectedRows = stmt.executeUpdate();
      if(affectedRows > 0)
        try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
          if(generatedKeys.next()) {
            category.setId(generatedKeys.getInt(1));
            return true;
          };
        }
      return false;
    } catch(SQLException e){
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(Category category){
    String query = "UPDATE categories SET name = ?, icon = ? WHERE id = ?";

    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, category.getName());
      stmt.setString(2, category.getIcon());
      stmt.setInt(3, category.getId());
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean delete(int id){
    String query = "DELETE FROM categories WHERE id = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;    
    }
  }

  @Override
  public Category get(int id){
    String query = "SELECT * FROM categories WHERE id = ?";
    
    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()){
        return new Category(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("icon")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  } 

  @Override
  public List<Category> getAll(){
    List<Category> categories = new ArrayList<>();
    String query = "SELECT * FROM categories";
    
    try(PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        categories.add(new Category(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("icon")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return categories;
  } 
  
}
