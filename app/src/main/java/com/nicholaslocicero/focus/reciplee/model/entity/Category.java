package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Category entity.
 */
@Entity(tableName = "categories",
    indices = {
        @Index(value = {"id"}, unique = true)
    }

)
public class Category {

  @ColumnInfo(name="id")
  @PrimaryKey
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
