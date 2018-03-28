package com.vnpt.hotel.manager.ui.presenter.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.roomtype.CreateRoomTypeView;
import java.io.File;
import java.util.List;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomTypePresenter extends Presenter<CreateRoomTypeView> {
  void createRoomType(CreateRoomType createRoomType,List<File> listImage);

  void saveAmenities(long roomTypeId, String amenities);

  void createRoomTypePriceApi(CreateRoomTypePrice createRoomTypePrice);


  void getListAmenities(String typeRoom);
}
