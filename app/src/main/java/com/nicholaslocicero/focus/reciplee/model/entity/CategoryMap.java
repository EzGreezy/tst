package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * CategoryMap entity.
 */
@Entity(tableName = "categories_map",
    foreignKeys = {
    @ForeignKey(
        entity = Category.class,
        parentColumns = "id", childColumns = "category_id"
    ),
    @ForeignKey(
        entity = Recipe.class,
        parentColumns = "id", childColumns = "recipe_id"
    )
},
    indices = {
        @Index(value = {"id", "category_id", "recipe_id"}, unique = true)
    }

    )
public class CategoryMap {
  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(name = "recipe_id")
  private long recipe_id;
  @ColumnInfo(name = "category_id")
  private long category_id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRecipe_id() {
    return recipe_id;
  }

  public void setRecipe_id(long recipId) {
    this.recipe_id = recipe_id;
  }

  public long getCategory_id() {
    return category_id;
  }

  public void setCategory_id(long category_id) {
    this.category_id = category_id;
  }
}
