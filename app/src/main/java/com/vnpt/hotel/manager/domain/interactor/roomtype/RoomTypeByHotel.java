package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface RoomTypeByHotel {
  Observable<RoomTypeFindByHotelResult> execute(RoomTypeFindByHotel roomTypeFindByHotel);
}
