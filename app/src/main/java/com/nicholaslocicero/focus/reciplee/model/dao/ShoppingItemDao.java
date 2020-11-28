package com.nicholaslocicero.focus.reciplee.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.nicholaslocicero.focus.reciplee.model.entity.ShoppingItem;
import com.nicholaslocicero.focus.reciplee.model.pojo.ShoppingListAssembled;
import java.util.List;

/**
 * The ShoppingItem dao.
 */
@Dao
public interface ShoppingItemDao {

  /**
   * Inserts a shoppingItem
   * @param shoppingItem
   * @return
   */
  @Insert
  long insert(ShoppingItem shoppingItem);

  /**
   * Inserts many ShoppingItem
   * @param shoppingItems
   * @return
   */
  @Insert
  List<Long> insert(ShoppingItem... shoppingItems);
  /**
   * Inserts many ShoppingItem
   * @param shoppingItems
   * @return
   */
  @Insert
  List<Long> insert(List<ShoppingItem> shoppingItems);

  /**
   * Updates many ShoppingItem objects
   */
  @Update
  void update(ShoppingItem... items);

  /**
   * Selects all ShoppingItem objects.
   * @return
   */
  @Query("SELECT * FROM shopping_list")
  List<ShoppingItem> select();

  /**
   * Assembles the complex join to build the data linked from different foreign keys
   * all originating in the shopping_item table.
   * @return
   */
  @Query(Queries.SHOPPING_LIST_ASSEMBLY)
  List<ShoppingListAssembled> assembleShoppingList();

  /**
   * Selects an ingredient_item from the shopping list where it matches certain text.
   * @param text
   * @return
   */
  @Query("Select ingredient_item FROM shopping_list WHERE ingredient_item = :text")
  String selectForIngredientItem(String text);

  /**
   * Selects everything from shopping list that matches certain ingredient_item text.
   * @param text
   * @return
   */
  @Query("Select * FROM shopping_list WHERE shopping_list.ingredient_item = :text")
  ShoppingItem queryIngredientItemForDeletion(String text);

  /**
   * Gets a shopping item by its id.
   * @param id
   * @return
   */
  @Query("Select * FROM shopping_list WHERE shopping_list.recipe_id = :id")
  ShoppingItem selectById(Long id);

  /**
   * Deletes a shopping item.
   * @param shoppingItem
   */
  @Delete
  void delete(ShoppingItem shoppingItem);
}
