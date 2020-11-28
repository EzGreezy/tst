package com.nicholaslocicero.focus.reciplee;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Extends Application to override onCreate and initialize the project with Stetho
 * for view into the sqlite table.
 */
public class RecipleeApplication extends Application{
  /**
   * Overrides onCreate and initializes Stetho for viewing sqlite through chrome://inspect
   */
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
