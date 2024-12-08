package expenses_tracker.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

  @Test
  public void testConstructor() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    assertEquals(1, expense.getId());
    assertEquals(BigDecimal.valueOf(100.50), expense.getValue());
    assertEquals("Lunch", expense.getDescription());
    assertEquals(LocalDate.now(), expense.getDate());
    assertEquals(2, expense.getCategoryId());
  }

  @Test
  public void testGetId() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    assertEquals(1, expense.getId());
  }

  @Test
  public void testGetValue() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    assertEquals(BigDecimal.valueOf(100.50), expense.getValue());
  }

  @Test
  public void testGetDescription() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    assertEquals("Lunch", expense.getDescription());
  }

  @Test
  public void testGetDate() {
    LocalDate date = LocalDate.of(2023, 10, 30);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", date, 2);
    assertEquals(date, expense.getDate());
  }

  @Test
  public void testGetCategoryId() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    assertEquals(2, expense.getCategoryId());
  }

  @Test
  public void testSetId() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    expense.setId(3);
    assertEquals(3, expense.getId());
  }

  @Test
  public void testSetValue() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    expense.setValue(BigDecimal.valueOf(150.75));
    assertEquals(BigDecimal.valueOf(150.75), expense.getValue());
  }

  @Test
  public void testSetDescription() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    expense.setDescription("Dinner");
    assertEquals("Dinner", expense.getDescription());
  }

  @Test
  public void testSetDate() {
    LocalDate newDate = LocalDate.of(2023, 11, 1);
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    expense.setDate(newDate);
    assertEquals(newDate, expense.getDate());
  }

  @Test
  public void testSetCategoryId() {
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    expense.setCategoryId(3);
    assertEquals(3, expense.getCategoryId());
  }

  @Test
  public void testToString() {
    LocalDate date = LocalDate.now();
    Expense expense = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", date, 2);
    String expected = "Expense{id=1, value=100.5, description='Lunch', date=" + date + ", categoryId=2}";
    assertEquals(expected, expense.toString());
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testEquals() {
    Expense expense1 = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    Expense expense2 = new Expense(1, BigDecimal.valueOf(100.50), "Lunch", LocalDate.now(), 2);
    Expense expense3 = new Expense(2, BigDecimal.valueOf(150.00), "Dinner", LocalDate.now(), 3);
    
    assertTrue(expense1.equals(expense2));
    assertFalse(expense1.equals(expense3));
    assertFalse(expense1.equals(null));
    assertFalse(expense1.equals("string"));
  }

}
