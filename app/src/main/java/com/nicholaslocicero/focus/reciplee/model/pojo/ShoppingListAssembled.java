package com.nicholaslocicero.focus.reciplee.model.pojo;

/**
 * Builds an assembled row from a complex join to get either ingredient and item (amount) to buy.
 * Or it gets title, ingredient, description, and remove.  This tells the java code what to show
 * for items that should or shouldn't be in the shopping list.
 */
public class ShoppingListAssembled {

  private String title;
  private String ingredient;
  private String item;
  private String description;
  private String remove;

  public String getIngredient() {
    return ingredient;
  }

  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRemove() {
    return remove;
  }

  public void setRemove(String remove) {
    this.remove = remove;
  }
}
