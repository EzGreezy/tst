package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.nicholaslocicero.focus.reciplee.model.entity.Ingredient;
import com.nicholaslocicero.focus.reciplee.model.entity.Recipe;
import java.util.List;

/**
 * Recipe Dao.
 */
@Dao
public interface RecipeDao {

  /**
   * Inserts a recipe
   * @param recipe
   * @return
   */
  @Insert
  long insert(Recipe recipe);
  /**
   * Inserts recipes
   * @param recipes
   * @return
   */
  @Insert
  List<Long> insert(Recipe... recipes);
  /**
   * Inserts recipes
   * @param recipes
   * @return
   */
  @Insert
  List<Long> insert(List<Recipe> recipes);
  /**
   * Selects the title of all recipes.  Used to build an array to search for in autocompletetextview
   * @return
   */
  @Query("SELECT title FROM recipes")
  List<String> selectRecipeTitles();
  /**
   * select a recipes that match a specific title
   * @param title
   * @return
   */
  @Query("SELECT * FROM recipes WHERE recipes.title = :title")
  List<Recipe> selectRecipeByTitle(String title);
  /**
   * select a recipes where direction matches a specific string
   * @param title
   * @return
   */
  @Query("SELECT directions FROM recipes WHERE title = :title")
  List<String> selectDirectionsFromTitle(String title);

  /**
   * Selects all the recipes in the shopping_list in alphabetical order.
   * @return
   */
  @Query("SELECT * FROM recipes LEFT JOIN shopping_list ON shopping_list.recipe_id = recipes.id WHERE shopping_list.recipe_id = recipes.id ORDER BY recipes.title ASC")
  List<Recipe> selectRecipesInShoppingList();

  /**
   * Selects all recipes that have a title matching text
   * @param text
   * @return
   */
  @Query("Select * FROM recipes WHERE recipes.title = :text")
  Recipe getRecipeByTitle(String text);
}
