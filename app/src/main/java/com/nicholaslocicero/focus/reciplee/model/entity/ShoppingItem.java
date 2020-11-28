package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Shopping item entity.
 */
@Entity(
  tableName = "shopping_list",
  foreignKeys = {
    @ForeignKey(
      entity = Ingredient.class,
      parentColumns = "id", childColumns = "ingredient_id"
    ),
    @ForeignKey(
      entity = Recipe.class,
      parentColumns = "id", childColumns = "recipe_id"
    )
  },
    indices = {
        @Index(value = {"id", "ingredient_id", "recipe_id"}, unique = true)
    }
)
public class ShoppingItem {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id", index = true)
  private long id;

  @ColumnInfo(name = "ingredient_id", index = true)
  private Long ingredient_id;
  @ColumnInfo(name = "ingredient_item")
  private String ingredient_item;
  @ColumnInfo(name = "recipe_id", index = true)
  private Long recipe_id;
  @ColumnInfo(name = "remove_string")
  private String remove_string = "";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getIngredient_id() {
    return ingredient_id;
  }

  public void setIngredient_id(Long ingredient_id) {
    this.ingredient_id = ingredient_id;
  }

  public String getIngredient_item() {
    return ingredient_item;
  }

  public void setIngredient_item(String ingredient_item) {
    this.ingredient_item = ingredient_item;
  }

  public Long getRecipe_id() {
    return recipe_id;
  }

  public void setRecipe_id(Long recipe_id) {
    this.recipe_id = recipe_id;
  }

  public String getRemove_string() {
    return remove_string;
  }

  public void setRemove_string(String remove_string) {
    this.remove_string = remove_string;
  }
}
