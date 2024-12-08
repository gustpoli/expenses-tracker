package expenses_tracker.dao;

import expenses_tracker.model.Expense;
import expenses_tracker.model.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseDAOTest {

  private final CategoryDAO categoryDAO = new CategoryDAO();
  private final ExpenseDAO expenseDAO = new ExpenseDAO();

  private Category createCategory(String name, String icon) {
    Category category = new Category(-1, name, icon);
    categoryDAO.insert(category);
    return category;
  }

  @Test
  void testDeleteAll() {
    Category category = createCategory("Food", "🍔");
    expenseDAO.insert(new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId()));
    expenseDAO.insert(new Expense(-1, BigDecimal.valueOf(50), "Transport", LocalDate.now(), category.getId()));
    assertTrue(expenseDAO.deleteAll());
    List<Expense> expenses = expenseDAO.getAll();
    assertEquals(0, expenses.size());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testInsert() {
    Category category = createCategory("Food", "🍔");
    Expense expense = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    assertTrue(expenseDAO.insert(expense));
    expenseDAO.delete(expense.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testUpdate() {
    Category category = createCategory("Food", "🍔");
    Expense expense = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    expenseDAO.insert(expense);
    Expense updatedExpense = new Expense(expense.getId(), BigDecimal.valueOf(150), "Supermarket", LocalDate.now(), category.getId());
    assertTrue(expenseDAO.update(updatedExpense));
    Expense retrievedExpense = expenseDAO.get(expense.getId());
    assertNotNull(retrievedExpense);
    assertEquals(updatedExpense.getDescription(), retrievedExpense.getDescription());
    assertTrue(updatedExpense.getValue().compareTo(retrievedExpense.getValue()) == 0);
    expenseDAO.delete(expense.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testDelete() {
    Category category = createCategory("Food", "🍔");
    Expense expense = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    expenseDAO.insert(expense);
    assertTrue(expenseDAO.delete(expense.getId()));
    assertNull(expenseDAO.get(expense.getId()));
    categoryDAO.delete(category.getId());
  }

  @Test
  void testeDeleteX(){
    Category category = createCategory("Food", "🍔");
    Expense expense1 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    Expense expense2 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    Expense expense3 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    expenseDAO.insert(expense1);
    expenseDAO.insert(expense2);
    expenseDAO.insert(expense3);
    assertTrue(expenseDAO.delete(Arrays.asList(expense1.getId(), expense2.getId(), expense3.getId())));
    assertNull(expenseDAO.get(expense1.getId()));
    assertNull(expenseDAO.get(expense2.getId()));
    assertNull(expenseDAO.get(expense3.getId()));
    categoryDAO.delete(category.getId());
  }

  @Test
  void testGetAll() {
    Category category = createCategory("Food", "🍔");
    expenseDAO.insert(new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId()));
    expenseDAO.insert(new Expense(-1, BigDecimal.valueOf(50), "Transport", LocalDate.now(), category.getId()));
    List<Expense> expenses = expenseDAO.getAll();
    assertEquals(2, expenses.size());
    for (Expense exp : expenses) {
      expenseDAO.delete(exp.getId());
    }
    categoryDAO.delete(category.getId());
  }

  @Test
  void testGet() {
    Category category = createCategory("Food", "🍔");
    Expense expense = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    expenseDAO.insert(expense);
    Expense retrievedExpense = expenseDAO.get(expense.getId());
    assertNotNull(retrievedExpense);
    assertEquals("Groceries", retrievedExpense.getDescription());
    assertTrue(BigDecimal.valueOf(100).compareTo(retrievedExpense.getValue()) == 0);
    expenseDAO.delete(expense.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testGetByCategory() {
    Category category = createCategory("Food", "🍔");
    Expense expense1 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    Expense expense2 = new Expense(-1, BigDecimal.valueOf(50), "Transport", LocalDate.now(), category.getId());
    expenseDAO.insert(expense1);
    expenseDAO.insert(expense2);
    List<Expense> expenses = expenseDAO.getByCategory(category.getId());
    assertEquals(2, expenses.size());
    expenseDAO.delete(expense1.getId());
    expenseDAO.delete(expense2.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testGetWithinDateRange() {
    Category category = createCategory("Food", "🍔");
    Expense expense1 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    Expense expense2 = new Expense(-1, BigDecimal.valueOf(50), "Transport", LocalDate.now().minusDays(1), category.getId());
    expenseDAO.insert(expense1);
    expenseDAO.insert(expense2);
    List<Expense> expenses = expenseDAO.getWithinDateRange(LocalDate.now().minusDays(2), LocalDate.now());
    assertEquals(2, expenses.size());
    expenseDAO.delete(expense1.getId());
    expenseDAO.delete(expense2.getId());
    categoryDAO.delete(category.getId());
  }

  @Test
  void testList() {
    Category category = createCategory("Food", "🍔");
    Expense expense1 = new Expense(-1, BigDecimal.valueOf(100), "Groceries", LocalDate.now(), category.getId());
    Expense expense2 = new Expense(-1, BigDecimal.valueOf(50), "Transport", LocalDate.now(), category.getId());
    expenseDAO.insert(expense1);
    expenseDAO.insert(expense2);
    List<Expense> expenses = expenseDAO.list(1, 0);
    assertEquals(1, expenses.size());
    expenseDAO.delete(expense1.getId());
    expenseDAO.delete(expense2.getId());
    categoryDAO.delete(category.getId());
  }
}
