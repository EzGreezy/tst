package com.nicholaslocicero.focus.reciplee.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Comparator;

/**
 * Recipe entity
 */
@Entity(tableName = "recipes")
public class Recipe implements Comparator<Recipe> {

  @PrimaryKey
  private long id;

  private String title;
  private String directions;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }


  public String getDirections() {
    return directions;
  }
  public void setDirections(String directions) {
    this.directions = directions;
  }

  @Override
  public int compare(Recipe s1, Recipe s2) {
    if (s1.getTitle() == s2.getTitle()) {
      return 0;
    }
    if (s1.getTitle() == null) {
      return -1;
    }
    if (s2.getTitle() == null) {
      return 1;
    }
    return s1.getTitle().compareTo(s2.getTitle());
  }
}
