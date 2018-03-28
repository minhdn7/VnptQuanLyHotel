package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface CreateMotelView extends View {
  void onCreateMotelComplete(CreateMotelApiResult motelApiResult);

  void onCreateMotelError(String message);

  void onError();
}
