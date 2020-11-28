package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
/**
 * Ingredient entity.
 */
@Entity(tableName = "ingredients")
public class Ingredient {

  @ColumnInfo(name="id")
  @PrimaryKey(autoGenerate = true)
  private long id;
  private String name;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}