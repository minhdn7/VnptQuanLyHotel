package com.vnpt.hotel.manager.ui.view.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.ui.view.View;
import java.util.List;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomTypeView extends View {
  void onCreateRoomTypeApiSuccess(CreateRoomTypeResult createRoomTypeResult);
  void onCreateRoomTypeApiFailed(String message);

  void onSaveAmenitiesApiSuccess();
  void onSaveAmenitiesApiFailed(String message);

  void onGetAmenitiesApiSuccess(List<String> amenities);

  void onGetAmenitiesApiFailed(String message);

  void onCreateRoomTypePriceApiSuccess();
  void onCreateRoomTypePriceApiFailed(String message);

  void onSaveFileRoomTypeApiFailed(String message);

  void onError(Throwable e);
}
