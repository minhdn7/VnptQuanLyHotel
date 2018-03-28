package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.RoomTypeByHotelApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class RoomTypeByHotelImpl implements RoomTypeByHotel {
  @Inject RoomTypeByHotelApi roomTypeByHotelApi;

  @Override
  public Observable<RoomTypeFindByHotelResult> execute(RoomTypeFindByHotel roomTypeFindByHotel) {
    return roomTypeByHotelApi.roomTypeByHotelApi(roomTypeFindByHotel);
  }
}
