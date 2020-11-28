package com.nicholaslocicero.focus.reciplee.model.dao;

/**
 * Holds query strings so they take up less space in our daos.
 */
public class Queries {

  /**
   * Assembles a complex table to draw either recipe item information or ingredient name and
   * recipe information from the shopping list.
   */
  public static final String SHOPPING_LIST_ASSEMBLY =

      "SELECT "
          + "recipes.title, "
          + "COALESCE ( ingredients.name, ingredients2.name ) AS ingredient, "
          + "recipe_item.description AS description, "
          + "shopping_list.ingredient_item AS item, "
          + "shopping_list.remove_string AS remove "
          + "FROM shopping_list "
          + "LEFT JOIN ("
          +   "recipes "
          +   "INNER JOIN recipe_item ON recipe_item.recipe_id = recipes.id "
          +   "INNER JOIN ingredients_map ON ingredients_map.recipe_item_id = recipe_item.id "
          +   "INNER JOIN ingredients ON ingredients.id = ingredients_map.ingredient_id) "
          + "ON recipes.id = shopping_list.recipe_id "
          + "LEFT JOIN ingredients AS ingredients2 "
          + "ON ingredients2.id = shopping_list.ingredient_id "
          + "WHERE ingredients.id IS NOT NULL "
          +   "OR ingredients2.id IS NOT NULL ";

}
