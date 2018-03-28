package com.vnpt.hotel.manager.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.hotel.manager.domain.interactor.motel.CreateMotel;
import com.vnpt.hotel.manager.domain.interactor.motel.GetMotelList;
import com.vnpt.hotel.manager.domain.interactor.motel.GetMotelListImpl;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelCreateImpl;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDaoImpl;
import com.vnpt.hotel.manager.domain.interactor.motel.SaveFileHotel;
import com.vnpt.hotel.manager.domain.interactor.motel.SaveFileHotelImpl;
import com.vnpt.hotel.manager.domain.repository.api.motel.CreateMotelApi;
import com.vnpt.hotel.manager.domain.repository.api.motel.GetListMotelApi;
import com.vnpt.hotel.manager.domain.repository.api.motel.MotelApi;
import com.vnpt.hotel.manager.ui.presenter.motel.CreateMotelPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.CreateMotelPresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.motel.MotelListPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.MotelListPresenterImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiKaLi on 3/8/2018.
 */
@Module(complete = false, library = true) public class MotelModule {



  @Provides public GetListMotelApi provideGetListMotelApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(GetListMotelApi.class);
  }

  @Provides public CreateMotelApi provideCreateMotelApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(CreateMotelApi.class);
  }

  @Provides
  public MotelApi MotelApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(MotelApi.class);
  }

  @Provides public MotelListPresenter provideMotelListPresenter(MotelListPresenterImpl impl) {
    return impl;
  }

  @Provides public GetMotelList provideGetMotelList(GetMotelListImpl impl) {
    return impl;
  }

  @Provides public CreateMotelPresenter provideCreateMotelPresenter(CreateMotelPresenterImpl impl) {
    return impl;
  }

  @Provides public CreateMotel provideCreateMotel(MotelCreateImpl impl) {
    return impl;
  }

  @Provides public SaveFileHotel provideSaveFileHotel(SaveFileHotelImpl impl) {
    return impl;
  }

  @Provides public ListMotelOverviewPresenter listMotelOverviewPresenter(ListMotelOverviewPresenterImpl impl) {
    return impl;
  }

  @Provides public MotelDao provideMotelDao(MotelDaoImpl impl) {
    return impl;
  }

}
