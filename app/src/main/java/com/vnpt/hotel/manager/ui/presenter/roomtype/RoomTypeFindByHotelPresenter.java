package com.vnpt.hotel.manager.ui.presenter.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.roomtype.RoomTypeFindByHotelView;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface RoomTypeFindByHotelPresenter extends Presenter<RoomTypeFindByHotelView> {
  void roomTypeFindByHotel(RoomTypeFindByHotel roomTypeFindByHotel);
}
