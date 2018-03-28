package com.vnpt.hotel.manager.app.di;

import com.vnpt.hotel.manager.domain.interactor.register.RegisterNewUser;
import com.vnpt.hotel.manager.domain.interactor.register.RegisterNewUserImpl;
import com.vnpt.hotel.manager.domain.repository.api.register.RegisterApi;
import com.vnpt.hotel.manager.ui.activity.RegisterActivity;
import com.vnpt.hotel.manager.ui.presenter.register.RegisterPresenter;
import com.vnpt.hotel.manager.ui.presenter.register.RegisterPresenterImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by LiKaLi on 3/2/2018.
 */
@Module(complete = false, library = true) public class RegisterModule {
  @Provides RegisterNewUser provideRegisterNewUser(RegisterNewUserImpl impl) {
    return impl;
  }

  @Provides RegisterPresenter provideRegisterPresenter(RegisterPresenterImpl impl) {
    return impl;
  }

  @Provides RegisterApi provideRegisterApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(RegisterApi.class);
  }
}
