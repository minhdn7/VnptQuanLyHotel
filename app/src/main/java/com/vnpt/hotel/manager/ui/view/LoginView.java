package com.vnpt.hotel.manager.ui.view;

import com.vnpt.hotel.manager.domain.model.LoginUser;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public interface LoginView extends View {
  void onLoginSuccess(LoginUser loginUser);

  void onLoginFailed(String message);

  void onLoginError(Throwable e);
}
