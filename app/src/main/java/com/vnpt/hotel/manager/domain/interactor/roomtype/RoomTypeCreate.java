package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.domain.model.roomtype.GetListAmenitiesResult;
import okhttp3.MultipartBody;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface RoomTypeCreate {
  Observable<CreateRoomTypeResult> createRoomType(CreateRoomType createRoomType);

  Observable<CommonApiResult> saveAmenities(long roomTypeId,String amenities);

  Observable<CommonApiResult> createRoomTypePrice(CreateRoomTypePrice createRoomTypePrice);

  Observable<CommonApiResult> saveFileRoomType(MultipartBody.Part[] listFileRoomType);
  Observable<GetListAmenitiesResult> getAmenitiesRoomType(String typeRoom);
}
