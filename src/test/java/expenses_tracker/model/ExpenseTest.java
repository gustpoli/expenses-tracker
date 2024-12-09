package expenses_tracker.model;

import org.junit.jupiter.api.Test;

import expenses_tracker.dao.CategoryDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

  @Test
  public void testConstructor() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    assertEquals(1, expense.getId());
    assertEquals(BigDecimal.valueOf(100.50), expense.getValue());
    assertEquals("Lunch", expense.getDescription());
    assertEquals(LocalDate.now(), expense.getDate());
    assertEquals(category, expense.getCategory());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testGetId() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    assertEquals(1, expense.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testGetValue() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    assertEquals(BigDecimal.valueOf(100.50), expense.getValue());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testGetDescription() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    assertEquals("Lunch", expense.getDescription());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testGetDate() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    LocalDate date = LocalDate.of(2023, 10, 30);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", date, category);
    assertEquals(date, expense.getDate());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testGetCategory() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    assertEquals(category, expense.getCategory());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testSetId() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    expense.setId(3);
    assertEquals(3, expense.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testSetValue() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    expense.setValue(BigDecimal.valueOf(150.75));
    assertEquals(BigDecimal.valueOf(150.75), expense.getValue());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testSetDescription() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    expense.setDescription("Dinner");
    assertEquals("Dinner", expense.getDescription());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testSetDate() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    LocalDate newDate = LocalDate.of(2023, 11, 1);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    expense.setDate(newDate);
    assertEquals(newDate, expense.getDate());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testSetCategory() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    expense.setCategory(new Category("categoria2", "🛒"));
    assertEquals(new Category("categoria2", "🛒"), expense.getCategory());
    categoryDAO.delete(category.getId());
  }

  @Test
  public void testToString() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    LocalDate date = LocalDate.now();
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", date, category);
    String expected = "Expense{id=1, value=100.5, description='Lunch', date=" + date + ", category='🛒 categoria'}";
    assertEquals(expected, expense.toString());
    categoryDAO.delete(category.getId());
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testEquals() {
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category("categoria", "🛒");
    categoryDAO.insert(category);
    Category category2 = new Category("categoria2", "🛒");
    categoryDAO.insert(category);
    Expense expense1 = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    Expense expense2 = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), category);
    Expense expense3 = new Expense(2, BigDecimal.valueOf(150.00), "Dinner", LocalDate.now(), category2);
    
    assertTrue(expense1.equals(expense2));
    assertFalse(expense1.equals(expense3));
    assertFalse(expense1.equals(null));
    assertFalse(expense1.equals("string"));
    categoryDAO.delete(category.getId());
    categoryDAO.delete(category2.getId());
  }

}
