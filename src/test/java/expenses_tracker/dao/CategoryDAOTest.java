package expenses_tracker.dao;

import static org.junit.jupiter.api.Assertions.*;

import expenses_tracker.model.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

class CategoryDAOTest {

  @Test
  void testInsert() {
    CategoryDAO categoryDAO = new CategoryDAO();

    Category category = new Category(-1, "Food", "🍔");
    assertTrue(categoryDAO.insert(category));

    categoryDAO.delete(category.getId());
  }

  @Test
  void testUpdate() {
    CategoryDAO categoryDAO = new CategoryDAO();

    Category category = new Category(-1, "Food", "🍔");
    categoryDAO.insert(category);

    Category updatedCategory = new Category(category.getId(), "Groceries", "🛒");
    assertTrue(categoryDAO.update(updatedCategory));

    Category retrievedCategory = categoryDAO.get(category.getId());
    assertNotNull(retrievedCategory);
    assertEquals("Groceries", retrievedCategory.getName());
    assertEquals("🛒", retrievedCategory.getIcon());

    categoryDAO.delete(category.getId());
  }

  @Test
  void testDelete() {
    CategoryDAO categoryDAO = new CategoryDAO();

    Category category = new Category(-1, "Food", "🍔");
    categoryDAO.insert(category);

    assertTrue(categoryDAO.delete(category.getId()));
    assertNull(categoryDAO.get(category.getId()));
  }

  @Test
  void testGetAll() {
    CategoryDAO categoryDAO = new CategoryDAO();

    Category category1 = new Category(-1, "Food", "🍔");
    Category category2 = new Category(-1, "Transport", "🚗");

    categoryDAO.insert(category1);
    categoryDAO.insert(category2);

    List<Category> categories = categoryDAO.getAll();
    assertEquals(2, categories.size());

    categoryDAO.delete(category1.getId());
    categoryDAO.delete(category2.getId());
  }

  @Test
  void testGet() {
    CategoryDAO categoryDAO = new CategoryDAO();

    Category category = new Category(-1, "Food", "🍔");
    categoryDAO.insert(category);

    Category retrievedCategory = categoryDAO.get(category.getId());
    assertNotNull(retrievedCategory);
    assertEquals("Food", retrievedCategory.getName());
    assertEquals("🍔", retrievedCategory.getIcon());

    categoryDAO.delete(category.getId());
  }
}
