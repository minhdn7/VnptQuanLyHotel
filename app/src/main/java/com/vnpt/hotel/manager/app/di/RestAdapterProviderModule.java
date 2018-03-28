package com.vnpt.hotel.manager.app.di;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.hotel.manager.R;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiKaLi on 3/2/2018.
 */
@Module(complete = false, library = true) public class RestAdapterProviderModule {
  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }

  @Provides
  public Retrofit.Builder provideRestAdapterBuilder(Context context, OkHttpClient okHttpClient,Gson gson) {
    return new Retrofit.Builder().client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(context.getString(R.string.api_base_url))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
  }
}
