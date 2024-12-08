package expenses_tracker.dao;

import java.util.List;

public interface DAO<T> {
  
  boolean insert(T t);
  boolean update(T t);
  boolean delete(int id);
  List<T> getAll();
  T get(int id);

}