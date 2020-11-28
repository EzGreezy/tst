package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * IngredientMap entity.
 */
@Entity(tableName = "ingredients_map",
    foreignKeys = {
        @ForeignKey(
            entity = RecipeItem.class,
            parentColumns = "id", childColumns = "recipe_item_id"
        ),
        @ForeignKey(
            entity = Ingredient.class,
            parentColumns = "id", childColumns = "ingredient_id"
        )
    },
    indices = {
        @Index(value = {"id", "recipe_item_id", "ingredient_id"}, unique = true)
    }
)
public class IngredientMap {
  @PrimaryKey(autoGenerate = true)
  private long id;
  private long ingredient_id;
  private long recipe_item_id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIngredient_id() {
    return ingredient_id;
  }

  public void setIngredient_id(long ingredient_id) {
    this.ingredient_id = ingredient_id;
  }

  public long getRecipe_item_id() {
    return recipe_item_id;
  }

  public void setRecipe_item_id(long recipe_item_id) {
    this.recipe_item_id = recipe_item_id;
  }
}
