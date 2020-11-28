package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import com.nicholaslocicero.focus.reciplee.model.entity.IngredientMap;
import java.util.List;

/**
 * Used to build many to many ingredient between recipe_item and ingredients.
 */
@Dao
public interface IngredientMapDao {

  /**
   * inserts one IngredientMap
   * @param ingredientMap
   * @return
   */
  @Insert
  long insert(IngredientMap ingredientMap);
  /**
   * inserts more than one IngredientMap as array
   * @param ingredients
   * @return
   */
  @Insert
  List<Long> insert(IngredientMap... ingredients);
  /**
   * inserts more than one IngredientMap as list
   * @param ingredients
   * @return
   */
  @Insert
  List<Long> insert(List<IngredientMap> ingredients);

}
