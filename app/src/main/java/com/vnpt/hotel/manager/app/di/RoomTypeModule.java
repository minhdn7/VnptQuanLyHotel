package com.vnpt.hotel.manager.app.di;

import com.vnpt.hotel.manager.domain.interactor.roomtype.CreateRoomByList;
import com.vnpt.hotel.manager.domain.interactor.roomtype.CreateRoomByListImpl;
import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeByHotel;
import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeByHotelImpl;
import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeCreate;
import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeCreateImpl;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.CreateRoomByListApi;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.CreateRoomTypeApi;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.RoomTypeByHotelApi;
import com.vnpt.hotel.manager.ui.presenter.roomtype.CreateRoomByListPresenter;
import com.vnpt.hotel.manager.ui.presenter.roomtype.CreateRoomByListPresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.roomtype.CreateRoomTypePresenter;
import com.vnpt.hotel.manager.ui.presenter.roomtype.CreateRoomTypePresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.roomtype.RoomTypeFindByHotelPresenter;
import com.vnpt.hotel.manager.ui.presenter.roomtype.RoomTypeFindByHotelPresenterImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by LiKaLi on 3/12/2018.
 */
@Module(complete = false, library = true) public class RoomTypeModule {
  @Provides public RoomTypeByHotelApi provideRoomTypeByHotelApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(RoomTypeByHotelApi.class);
  }

  @Provides
  public CreateRoomByListApi provideCreateRoomByListApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(CreateRoomByListApi.class);
  }

  @Provides public CreateRoomTypeApi provideCreateRoomTypeApi(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.build().create(CreateRoomTypeApi.class);
  }

  @Provides public RoomTypeByHotel provideRoomTypeByHotel(RoomTypeByHotelImpl impl) {
    return impl;
  }

  @Provides public CreateRoomByListPresenter provideCreateRoomByListPresenter(
      CreateRoomByListPresenterImpl impl) {
    return impl;
  }

  @Provides public CreateRoomByList provideCreateRoomByList(CreateRoomByListImpl impl) {
    return impl;
  }

  @Provides public RoomTypeFindByHotelPresenter provideRoomTypeFindByHotel(
      RoomTypeFindByHotelPresenterImpl impl) {
    return impl;
  }

  @Provides public RoomTypeCreate provideRoomTypeFindByHotel(RoomTypeCreateImpl impl) {
    return impl;
  }

  @Provides
  public CreateRoomTypePresenter provideRoomTypeFindByHotel(CreateRoomTypePresenterImpl impl) {
    return impl;
  }
}
