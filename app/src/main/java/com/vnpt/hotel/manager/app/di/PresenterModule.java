package com.vnpt.hotel.manager.app.di;

import com.vnpt.hotel.manager.ui.presenter.StartPresenter;
import com.vnpt.hotel.manager.ui.presenter.StartPresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.customer.CreateCustomerPresenter;
import com.vnpt.hotel.manager.ui.presenter.customer.CreateCustomerPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LiKaLi on 3/5/2018.
 */
@Module(complete = false, library = true) public class PresenterModule {
  @Provides public StartPresenter provideStartPresenter(StartPresenterImpl impl) {
    return impl;
  }

  @Provides public CreateCustomerPresenter provideStartPresenter(CreateCustomerPresenterImpl impl) {
    return impl;
  }

}
