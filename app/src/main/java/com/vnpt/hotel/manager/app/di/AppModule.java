package com.vnpt.hotel.manager.app.di;

import android.content.Context;

import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.app.ManagerApplication;
import com.vnpt.hotel.manager.app.http.interceptor.AddCookieInterceptor;
import com.vnpt.hotel.manager.domain.repository.LoginUserToken;
import com.vnpt.hotel.manager.ui.activity.CreateAndEditHotelActivity;
import com.vnpt.hotel.manager.ui.activity.CreateCustomerHotelActivity;
import com.vnpt.hotel.manager.ui.activity.ListHotelRoomActivity;
import com.vnpt.hotel.manager.ui.activity.LoginActivity;
import com.vnpt.hotel.manager.ui.activity.MainActivity;
import com.vnpt.hotel.manager.ui.activity.RegisterActivity;
import com.vnpt.hotel.manager.ui.activity.StartActivity;
import com.vnpt.hotel.manager.ui.fragment.HotelFragment;
import com.vnpt.hotel.manager.ui.fragment.ListBookRoomFragment;
import com.vnpt.hotel.manager.ui.fragment.ListRoomFragment;
import com.vnpt.hotel.manager.ui.fragment.ListStaffFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by LiKaLi on 3/1/2018.
 */
@Module(includes = {
    //        LoginModule.class,
    RegisterModule.class, RestAdapterProviderModule.class, PresenterModule.class,
    RepositoryModule.class, MotelModule.class,RoomTypeModule.class
}, injects = {
    //App
    ManagerApplication.class,
    // - view

    //Activity
    BaseActivity.class, StartActivity.class, LoginActivity.class, RegisterActivity.class,
    ListHotelRoomActivity.class, CreateAndEditHotelActivity.class,
    CreateCustomerHotelActivity.class, MainActivity.class,
    //Fragment
    BaseFragment.class,
    HotelFragment.class,
        ListRoomFragment.class,
    ListBookRoomFragment.class,
    ListStaffFragment.class
}, library = true) public class AppModule {

  private Context context;

  public AppModule(Context context) {
    this.context = context;
  }

  @Provides @Singleton Cache provideHttpCache() {
    int cacheSize = 10 * 1024 * 1024;
    Cache cache = new Cache(context.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return this.context;
  }

  @Provides public AddCookieInterceptor provideAddCookieInterceptor(LoginUserToken loginUserToken) {
    return new AddCookieInterceptor(loginUserToken);
  }

  @Provides @Singleton OkHttpClient provideOkhttpClient(AddCookieInterceptor addCookieInterceptor,
      Cache cache) {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.addInterceptor(addCookieInterceptor);
    client.connectTimeout(TimeUnit.MINUTES.toMillis(5L), TimeUnit.MILLISECONDS);
    client.readTimeout(TimeUnit.MINUTES.toMillis(10L), TimeUnit.MILLISECONDS);
    client.cache(cache);
    return client.build();
  }
}
