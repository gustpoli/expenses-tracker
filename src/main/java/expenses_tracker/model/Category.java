package expenses_tracker.model;

import java.util.Objects;

public class Category {

  private int id;
  private String name;
  private String icon;
  
  public Category(int id, String name, String icon){
    if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
    if (icon == null || icon.isEmpty()) throw new IllegalArgumentException("Icon cannot be null or empty");

    this.id = id;
    this.name = name;
    this.icon = icon;
  }

  public Category(String name, String icon){
    if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
    if (icon == null || icon.isEmpty()) throw new IllegalArgumentException("Icon cannot be null or empty");

    this.id = 0;
    this.name = name;
    this.icon = icon;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
    this.name = name;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    if (icon == null || icon.isEmpty()) throw new IllegalArgumentException("Icon cannot be null or empty");
    this.icon = icon;
  }

  @Override
  public String toString() {
    return icon + " " + name;
    //return "Category{id=" + id + ", name='" + name + "', icon='" + icon + "'}";
  }

  @Override
  public boolean equals(Object object){
    if(this == object) return true;
    if(object == null || getClass() != object.getClass()) return false;
    Category category = (Category) object;
    return (
      id == category.id &&
      Objects.equals(name, category.name) &&
      Objects.equals(icon, category.icon)
    );
  }

}
