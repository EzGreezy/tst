package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import com.nicholaslocicero.focus.reciplee.model.entity.Category;
import java.util.List;

/**
 * Unused dao for categories, except to populate the sqlite data on build and install.
 */
@Dao
public interface CategoryDao {
  /**
   * Basic insert method for category map item.
   * @param category
   * @return
   */
  @Insert
  long insert(Category category);
  /**
   * Basic insert method for category map items.
   * @param categories
   * @return
   */
  @Insert
  List<Long> insert(Category... categories);
  /**
   * Basic insert method for category map item list.
   * @param categoriesList
   * @return
   */
  @Insert
  List<Long> insert(List<Category> categoriesList);
}
