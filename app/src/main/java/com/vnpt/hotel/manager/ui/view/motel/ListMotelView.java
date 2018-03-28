package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface ListMotelView extends View {
  void onGetListMotelComplete(MotelModel motelMode);

  void onGetListMotelError();

  void onError();
}
