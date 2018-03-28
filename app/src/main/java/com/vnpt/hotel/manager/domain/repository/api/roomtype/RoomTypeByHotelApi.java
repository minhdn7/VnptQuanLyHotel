package com.vnpt.hotel.manager.domain.repository.api.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface RoomTypeByHotelApi {
  @POST("vnptline/rest/manager/roomType/findByHotel") Observable<RoomTypeFindByHotelResult> roomTypeByHotelApi(
      @Body RoomTypeFindByHotel roomTypeFindByHotel);
}
