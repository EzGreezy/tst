package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import com.nicholaslocicero.focus.reciplee.model.entity.CategoryMap;
import java.util.List;

/**
 * Used to build category data into sqlite, never used beyond that.
 */
@Dao
public interface CategoryMapDao {

  /**
   * Basic insert method for category map item.
   * @param categoryMap
   * @return
   */
  @Insert
  long insert(CategoryMap categoryMap);
  /**
   * Basic insert method for category map items.
   * @param categoryMaps
   * @return
   */
  @Insert
  List<Long> insert(CategoryMap... categoryMaps);
  /**
   * Basic insert method for category map item list.
   * @param categoryMapsList
   * @return
   */
  @Insert
  List<Long> insert(List<CategoryMap> categoryMapsList);
}
