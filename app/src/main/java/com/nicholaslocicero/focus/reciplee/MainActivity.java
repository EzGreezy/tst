package com.nicholaslocicero.focus.reciplee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.TextView;
import com.nicholaslocicero.focus.reciplee.model.db.Reciplee;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.w3c.dom.Text;

/**
 * This is the MainActivity for Reciplee.  It hosts the NavDrawer, the fragments, and everything
 * the user does in the app.
 */
public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{

  private String[] funNavHeaderIcons = {"\uD83E\uDD57", "\uD83C\uDF71", "🍜", "🍿", "🥫", "🍚", "🍛", "🍜", "🍝",
                                "🍠", "🍣", "🍤", "🍥", "🍦", "🥧", "☕", "🍔", "🍟", "🍕", "🌭", "🥪", "🌮", "🌯"};

  private Random rng = new Random();


  /**
   * Creates the main activity view.
   * @param savedInstanceState If the state is non-null, it holds information for us to use from a time before.
   */
  @SuppressLint("StaticFieldLeak")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.imageView);
        navUsername.setText(funNavHeaderIcons[rng.nextInt(funNavHeaderIcons.length)]);
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        drawer.requestLayout();
      }
    };
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    GroceryListFragment shoppingList = new GroceryListFragment();
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction()
        .replace(R.id.main_layout, shoppingList)
        .commit();

    new AsyncTask<Context, Void, Void>() {
      @Override
      protected Void doInBackground(Context... contexts) {
        // Replace Attendance and getStudentDao with the relevant class & method names for your project.
        Reciplee.getInstance(contexts[0]).getIngredientDao().select();
        return null;
      }
    }.execute(this);
    
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("Shopping List");
  }

  /**
   * Checks if the drawer is open, and closes it if it is.  If the drawer is not open, it calls the
   * super class constructor to execute the callback, which is closing the app.
   */
  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  /**
   * Listener for when an item in the nav drawer is selected.  It changes the fragment
   * from shopping list to meal planner ui views.
   * @param item
   * @return
   */
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    if (id == R.id.nav_shopping_list) {
      toolbar.setTitle("Shopping List");

      GroceryListFragment shoppingList = new GroceryListFragment();
      FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction()
          .replace(R.id.main_layout, shoppingList)
          .commit();

    } else if (id == R.id.nav_meal_planner) {
      toolbar.setTitle("Meal Planner");

      MealPlannerFragment mealPlannerFragment = new MealPlannerFragment();
      FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction()
          .replace(R.id.main_layout, mealPlannerFragment)
          .commit();

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }


}
