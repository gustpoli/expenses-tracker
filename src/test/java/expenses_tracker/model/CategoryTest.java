package expenses_tracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

  @Test
  public void testConstructor() {
    Category category = new Category(3, "Health", "💊");
    assertEquals(3, category.getId());
    assertEquals("Health", category.getName());
    assertEquals("💊", category.getIcon());
  }

  @Test
  public void testGetId(){
    Category category = new Category(1, "Food", "🍱");
    assertEquals(1, category.getId());
  }

  @Test
  public void testGetName(){
    Category category = new Category(1, "Food", "🍱");
    assertEquals("Food", category.getName());
  }

  @Test
  public void testGetIcon(){
    Category category = new Category(1, "Food", "🍱");
    assertEquals("🍱", category.getIcon());
  }

  @Test
  public void testSetId(){
    Category category = new Category(1, "Food", "🍱");
    category.setId(2);
    assertEquals(2, category.getId());
  }

  @Test
  public void testSetName(){
    Category category = new Category(1, "Food", "🍱");
    category.setName("Transportation");
    assertEquals("Transportation", category.getName());
  }

  @Test
  public void testSetIcon(){
    Category category = new Category(1, "Food", "🍱");
    category.setIcon("🚗");
    assertEquals("🚗", category.getIcon());
  }

  @Test
  public void testToString() {
    Category category = new Category(1, "Food", "🍱");
    assertEquals("Category{id=1, name='Food', icon='🍱'}", category.toString());
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testEquals() {
    Category category = new Category(1, "Food", "🍱");
    Category category2 = new Category(1, "Food", "🍱");
    Category category3 = new Category(3, "Drink", "🍹");
    
    assertTrue(category.equals(category2)); // true
    assertFalse(category.equals(category3)); // false
    assertFalse(category.equals(null)); // false
    assertFalse(category.equals("string")); // false
  }

}
