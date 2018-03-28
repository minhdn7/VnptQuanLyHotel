package com.vnpt.hotel.manager.app.di;

import android.content.Context;
import com.vnpt.hotel.manager.domain.interactor.Login;
import com.vnpt.hotel.manager.domain.interactor.LoginImpl;
import com.vnpt.hotel.manager.domain.interactor.TokenDev;
import com.vnpt.hotel.manager.domain.interactor.TokenDevImpl;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CreateCustomer;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CreateCustomerImpl;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CustomerCheck;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CustomerCheckImpl;
import com.vnpt.hotel.manager.domain.interactor.register.RegisterNewUserImpl;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.domain.repository.LoginUserToken;
import com.vnpt.hotel.manager.domain.repository.Preferences;
import com.vnpt.hotel.manager.domain.repository.api.LoginApi;
import com.vnpt.hotel.manager.domain.repository.api.TokenDevApi;
import com.vnpt.hotel.manager.domain.repository.api.TokenFirebaseIdApi;
import com.vnpt.hotel.manager.domain.repository.api.TokenFirebaseIdApiImpl;
import com.vnpt.hotel.manager.domain.repository.api.customer.CreateCustomerApi;
import com.vnpt.hotel.manager.domain.repository.api.customer.CustomerCheckApi;
import com.vnpt.hotel.manager.domain.repository.api.motel.CreateMotelApi;
import com.vnpt.hotel.manager.domain.repository.api.motel.SaveFileHotelApi;
import com.vnpt.hotel.manager.ui.presenter.LoginPresenter;
import com.vnpt.hotel.manager.ui.presenter.LoginPresenterImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by LiKaLi on 3/5/2018.
 */
@Module(complete = false, library = true)

public class RepositoryModule {
  @Provides public Preferences providePreferences(Context context) {
    return new Preferences(context);
  }

  @Provides public AppState provideAppState(Preferences preferences) {
    return new AppState(preferences);
  }

  @Provides public LoginApi provideLoginApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(LoginApi.class);
  }

  @Provides public TokenDevApi provideTokenDevApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(TokenDevApi.class);
  }

  @Provides public TokenFirebaseIdApi provideTokenFirebaseIdApi() {
    return new TokenFirebaseIdApiImpl();
  }
  @Provides public TokenDev provideTokenDev(TokenDevImpl impl) {
    return impl;
  }

  @Provides public CreateCustomerApi provideCustomerApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(CreateCustomerApi.class);
  }

  @Provides public CustomerCheckApi provideCustomerCheckApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(CustomerCheckApi.class);
  }

  @Provides public Login provideLogin(LoginImpl impl) {
    return impl;
  }

  @Provides public LoginPresenter provideLogin(LoginPresenterImpl impl) {
    return impl;
  }

  @Provides public CustomerCheck provideCustomerCheck(CustomerCheckImpl impl) {
    return impl;
  }

  @Provides public CreateCustomer provideLogin(CreateCustomerImpl impl) {
    return impl;
  }
  @Provides
  public LoginUserToken provideLoginUserCookies(Preferences preferences) {
    return new LoginUserToken(preferences);
  }
  @Provides
  public SaveFileHotelApi provideSaveFileHotelApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(SaveFileHotelApi.class);
  }
}
