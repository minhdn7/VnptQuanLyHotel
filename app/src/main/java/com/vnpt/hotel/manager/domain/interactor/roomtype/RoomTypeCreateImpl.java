package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.domain.model.roomtype.GetListAmenitiesResult;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.CreateRoomTypeApi;
import javax.inject.Inject;
import okhttp3.MultipartBody;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class RoomTypeCreateImpl implements RoomTypeCreate {
  @Inject CreateRoomTypeApi createRoomTypeApi;

  @Override public Observable<CreateRoomTypeResult> createRoomType(CreateRoomType createRoomType) {
    return createRoomTypeApi.createRoomTypeApi(createRoomType);
  }

  @Override public Observable<CommonApiResult> saveAmenities(long roomTypeId, String amenities) {
    return createRoomTypeApi.saveAmenitiesApi(roomTypeId, amenities);
  }

  @Override
  public Observable<CommonApiResult> createRoomTypePrice(CreateRoomTypePrice createRoomTypePrice) {
    return createRoomTypeApi.createRoomTypePriceApi(createRoomTypePrice);
  }

  @Override
  public Observable<CommonApiResult> saveFileRoomType(MultipartBody.Part[] listFileRoomType) {
    return createRoomTypeApi.SaveFileRoomTypeApi(listFileRoomType);
  }

  @Override public Observable<GetListAmenitiesResult> getAmenitiesRoomType(String typeRoom) {
    return createRoomTypeApi.getListAmenites(typeRoom);
  }
}
