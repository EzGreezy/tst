package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * RecipeItem entity
 */
@Entity(tableName = "recipe_item",
    foreignKeys = {
        @ForeignKey(
            entity = Recipe.class,
            parentColumns = "id", childColumns = "recipe_id"
        )
    },
    indices = {
        @Index(value = {"id", "recipe_id"}, unique = true)
    }
)
public class RecipeItem {

  @ColumnInfo(name = "id", index = true)
  @PrimaryKey
  private long id;
  @ColumnInfo(name = "recipe_id", index = true)
  private long recipe_id;
  @ColumnInfo(name = "description")
  private String description;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public long getRecipe_id() {
    return recipe_id;
  }
  public void setRecipe_id(long recipe_id) { this.recipe_id = recipe_id; }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
