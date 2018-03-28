package com.vnpt.hotel.manager.app;

import android.app.Application;
import com.vnpt.hotel.manager.app.di.AppModule;
import dagger.ObjectGraph;

/**
 * Created by LiKaLi on 1/25/2018.
 */

public class ManagerApplication extends Application {
  private ObjectGraph objectGraph;
  @Override public void onCreate() {
    super.onCreate();
     //dagger
    objectGraph = ObjectGraph.create(new AppModule(this));
    objectGraph.inject(this);
  }
  public void inject(Object object) {
    objectGraph.inject(object);
  }
}

