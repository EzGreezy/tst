package com.nicholaslocicero.focus.reciplee;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.Map;

/**
 * An implementation of BaseExpandableListAdapter for use with the shopping list.
 * It takes care of showing the shopping list intuitively so that every ingredient needed for
 * different recipes is linked together, and the quantified ingredients, as written in the FANDOM
 * content dump is as it was originally shown and attributed in the xml documents.
 */
public class ShoppingListAdapter extends BaseExpandableListAdapter {

  private Context context;
  private Map<String, List<String>> shoppingListMap;
  private List<String> shoppingIngredientsList;

  /**
   * Initializes the adapter with properties with which to fill the shopping list.
   * @param context The context the view is in.
   * @param shoppingListMap The ingredient name as key and a list of ingredients called for from different recipes sharing that ingredient name.
   * @param shoppingIngredientsList List of the keys, the ingredient names, for building the parent view of the expandable views.
   */
  public ShoppingListAdapter(Context context, Map<String, List<String>> shoppingListMap, List<String> shoppingIngredientsList) {
    this.context = context;
    this.shoppingListMap = shoppingListMap;
    this.shoppingIngredientsList = shoppingIngredientsList;

  }

  /**
   * Keeps track of the size of the expandable list.
   * @return int size of list
   */
  @Override
  public int getGroupCount() {
    return shoppingIngredientsList.size();
  }

  /**
   * Gets the size of the child part of the expandable list view.
   * @param groupPosition The parent view (ingredient name) position in the ingredient name array.
   * @return int size of ingredients called for in different recipes for the certain ingredient
   */
  @Override
  public int getChildrenCount(int groupPosition) {
    return shoppingListMap.get(shoppingIngredientsList.get(groupPosition)).size();
  }

  /**
   * Gets the ingredient for the certain position.
   * @param groupPosition position for the parent (ingredient)
   * @return ingredient name
   */
  @Override
  public Object getGroup(int groupPosition) {
    return shoppingIngredientsList.get(groupPosition);
  }

  /**
   * Gets the specific recipe ingredient item for the ingredient.
   * @param parent location of the ingredient name in the keys/ingredient name array
   * @param child the list position of the specific item for a recipe with that ingredient
   * @return string of the recipe item information for that ingredient (ie how much for the recipe)
   */
  @Override
  public Object getChild(int parent, int child) {
    return shoppingListMap.get(shoppingIngredientsList.get(parent)).get(child);
  }

  /**
   * gets the id for the parent view, ingredient
   * @param groupPosition position in the list
   * @return
   */
  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  /**
   * gets the id for the child of the parent view, ingredient item
   * @param parent the parent, ingredient position
   * @param child the child, ingredient item position
   * @return
   */
  @Override
  public long getChildId(int parent, int child) {
    return child;
  }

  /**
   * Tells the object that we have to recreate all the view on notifyDataChanged
   * because it's false.
   * @return false
   */
  @Override
  public boolean hasStableIds() {
    return false;
  }

  /**
   * Draws the view for the group.
   * @param groupPosition the list position to draw
   * @param isExpanded if it's expanded or not
   * @param convertView the view to convert so we can have something to return
   * @param parent the view for the parent of the group
   * @return
   */
  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
      ViewGroup parent) {
    String groupTitle = (String) getGroup(groupPosition);
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.parent_layout, parent, false);
    }
    TextView parentTextView = (TextView) convertView.findViewById(R.id.parent_text);
    parentTextView.setTypeface(null, Typeface.BOLD);
    parentTextView.setText(groupTitle);
    return convertView;
  }

  /**
   * The child view (the ingredient item for a recipe).
   * @param parent the position of the ingredient name
   * @param child the position of the child in the ingredient list in the map
   * @param lastChild is it the last child in the group?
   * @param convertView the view to convert so we have something to see
   * @param parentView the view above the expanded list
   * @return
   */
  @Override
  public View getChildView(int parent, int child, boolean lastChild,
      View convertView, final ViewGroup parentView) {
    String childTitle = (String) getChild(parent, child);
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.child_layout, parentView, false);
    }
    TextView childTextView = (TextView) convertView.findViewById(R.id.child_txt);
    childTextView.setText(Html.fromHtml(childTitle));
    return convertView;
  }

  /**
   * Turns on clicking for the children, which lets us click them and delete them from the sqlite database table,
   * shopping_item
   * @param groupPosition the position in the ingredients, what ingredient it is
   * @param childPosition the item for the above ingredient name
   * @return
   */
  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }
}
