package com.vnpt.hotel.manager.ui.view.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomListResult;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomByListView extends View {
  void onCreateRoomByListSuccess(CreateRoomListResult createRoomListResult);

  void onRoomTypeByHotelSuccess(RoomTypeFindByHotelResult roomTypeFindByHotelResult);

  void onRoomTypeByHotelFailed(String message);

  void onCreateRoomByListFailed(String message);

  void onError(Throwable e);
}
