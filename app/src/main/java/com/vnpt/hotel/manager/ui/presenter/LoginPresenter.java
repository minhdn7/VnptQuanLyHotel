package com.vnpt.hotel.manager.ui.presenter;

import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.ui.view.LoginView;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public interface LoginPresenter extends Presenter<LoginView>  {
  void login(String loginId, String password);
  void storeLoginResult(String phoneNumber, String password, LoginUser LoginUser);
}
