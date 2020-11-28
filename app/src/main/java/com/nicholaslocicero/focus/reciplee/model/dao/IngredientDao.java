package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.nicholaslocicero.focus.reciplee.model.entity.Ingredient;
import java.util.List;

/**
 * Ingredient Dao for building and querying ingredients.
 */
@Dao
public interface IngredientDao {

  /**
   * Inserts one ingredient.
   * @param ingredient
   * @return
   */
  @Insert()
  long insert(Ingredient ingredient);

  /**
   * Inserts many ingredients in array form (or one).
   * @param ingredients
   * @return
   */
  @Insert
  List<Long> insert(Ingredient... ingredients);
  /**
   * Inserts many ingredients in list form.
   * @param ingredients
   * @return
   */
  @Insert
  List<Long> insert(List<Ingredient> ingredients);

  /**
   * Selects all Ingredient entities in the ingredients table.
   * @return
   */
  @Query("SELECT * FROM ingredients")
  List<Ingredient> select();

  /**
   * Selects the id of an ingredient where a name has a substring of the input.
   * Used for querying for out autocompeletextview when typing to find recipes.
   * @param text the user has put in
   * @return
   */
  @Query("SELECT id FROM ingredients WHERE name LIKE :text")
  Long getIngredientIdByName(String text);
}
