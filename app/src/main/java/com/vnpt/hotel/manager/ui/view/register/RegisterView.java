package com.vnpt.hotel.manager.ui.view.register;

import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public interface RegisterView extends View {
  void onRegisterComplete();

  void onRegisterError(RegisterApiResult registerApiResult);

  void onError();
}
