package expenses_tracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Expense {

  private int id;
  private BigDecimal value;
  private String description;
  private LocalDate date;
  private Category category;
  
  public Expense(int id, BigDecimal value, String description, LocalDate date, Category category){
    this.id = id;
    this.value = value;
    this.description = description;
    this.date = date;
    this.category = category;
  }

  public Expense(BigDecimal value, String description, LocalDate date, Category category){
    this.id = 0;
    this.value = value;
    this.description = description;
    this.date = date;
    this.category = category;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Expense{id=" + id + ", value=" + value + ", description='" + description + "', date=" + date + ", category='" + category.toString() + "'}";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Expense expense = (Expense) obj;
    return (
      id == expense.id &&
      category.equals(expense.category) &&
      Objects.equals(value, expense.value) &&
      Objects.equals(description, expense.description) &&
      Objects.equals(date, expense.date)
    );
  }


}
